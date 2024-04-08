package com.appxbuild.ngpit.rest;

import com.appxbuild.ngpit.entity.LoginDetails;
import com.appxbuild.ngpit.service.LoginDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("/loginDetails")
    public LoginDetails addLoginDetails(@RequestBody LoginDetails loginDetails) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPwd = bcrypt.encode(loginDetails.getPassword());
        loginDetails.setId(0);
        LocalDateTime dt = LocalDateTime.now();
        loginDetails.setCreated(dt);
        loginDetails.setModified(null);
        loginDetails.setPassword(encryptedPwd);
        LoginDetails newLoginDetails = loginDetailsService.save(loginDetails);

        return newLoginDetails;
    }


//    @PutMapping("/loginDetails")
//    public LoginDetails updateLoginDetails(@RequestBody LoginDetails theLoginDetails){
//        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//        String encryptedPwd = bcrypt.encode(theLoginDetails.getPassword());
//        LocalDateTime dt = LocalDateTime.now();
//        theLoginDetails.setModified(dt);
//        theLoginDetails.setPassword(encryptedPwd);
//        LoginDetails newLoginDetails = loginDetailsService.save(theLoginDetails);
//        return newLoginDetails;
//    }

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
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPwd = bcrypt.encode(theLoginDetails.getPassword());

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

}
