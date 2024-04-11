package com.appxbuild.ngpit.rest;

import com.appxbuild.ngpit.entity.LoginDetails;
import com.appxbuild.ngpit.security.AESEncryption;
import com.appxbuild.ngpit.service.LoginDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LoginDetailsRestController {

    private LoginDetailsService loginDetailsService;

    @Autowired
    public LoginDetailsRestController(LoginDetailsService loginDetailsService) {
        this.loginDetailsService = loginDetailsService;
    }


    @GetMapping("/loginDetails")
    public List<LoginDetails> findAll(){
        return loginDetailsService.findAll();
    }


    @GetMapping("/loginDetails/{id}")
    public LoginDetails getLoginDetail(@PathVariable int id){
        LoginDetails theLoginDetails = loginDetailsService.findById(id);
        if(theLoginDetails==null){
            throw new RuntimeException("Login Detail id is not found " + id);
        }
        return theLoginDetails;
    }


    @PostMapping("/signup")
    public LoginDetails addLoginDetails(@RequestBody LoginDetails loginDetails) {

        // Encryption
        String encryptedPwd = AESEncryption.encrypt(loginDetails.getPassword());
//        System.out.println("Encrypted Password : " + encryptedPwd);
        loginDetails.setId(0);
        LocalDateTime dt = LocalDateTime.now();
        loginDetails.setCreated(dt);
        loginDetails.setModified(null);
        loginDetails.setPassword(encryptedPwd);
        LoginDetails newLoginDetails = loginDetailsService.save(loginDetails);

        return newLoginDetails;
    }



    @PutMapping("/loginDetails")
    public LoginDetails updateLoginDetails(@RequestBody LoginDetails theLoginDetails){
        // Retrieve the existing LoginDetails object from the database
        LoginDetails existingLoginDetails = loginDetailsService.findById(theLoginDetails.getId());

        // Check if the existing LoginDetails object exists
        if (existingLoginDetails == null) {
            throw new RuntimeException("Login Detail with id " + theLoginDetails.getId() + " not found");
        }

        // Set the created field from the existing LoginDetails object
        theLoginDetails.setCreated(existingLoginDetails.getCreated());

        // Encrypt the password
        String encryptedPwd = AESEncryption.encrypt(theLoginDetails.getPassword());

        // Set the modified field and password
        LocalDateTime dt = LocalDateTime.now();
        theLoginDetails.setModified(dt);
        theLoginDetails.setPassword(encryptedPwd);

        // Save the updated LoginDetails object
        LoginDetails updatedLoginDetails = loginDetailsService.save(theLoginDetails);

        return updatedLoginDetails;
    }


    @DeleteMapping("/loginDetails/{id}")
    public String deleteLoginDetail(@PathVariable int id){
        LoginDetails theLoginDetails = loginDetailsService.findById(id);
        if(theLoginDetails==null){
            throw new RuntimeException("Login Detail id is not found " + id);
        }
        return "Deleted Login Detail Id " + id;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDetails loginDetails) {
        LoginDetails user = loginDetailsService.findByEmail(loginDetails.getEmail());
        System.out.println("\n Existing details: " + user.getEmail() + " : " + user.getPassword());
        System.out.println("\n email: " + loginDetails.getEmail() + "\n pass:  " + loginDetails.getPassword());
        if (user != null) {
            String decryptedPwd = AESEncryption.decrypt(user.getPassword());
            System.out.println("Decrypted Pass: " + decryptedPwd);
            if (decryptedPwd.equals(loginDetails.getPassword())) {
                return ResponseEntity.ok("Login successful.");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
    }

}
