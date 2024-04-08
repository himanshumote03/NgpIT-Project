package com.appxbuild.ngpit.rest;

import com.appxbuild.ngpit.dao.UserDao;
import com.appxbuild.ngpit.dto.UserDto;
import com.appxbuild.ngpit.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserDao userDao;


    @GetMapping("/user")
    public List<User> getUsers(){
        return userDao.findAll();
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userDao.findById(id);
        return user.map(course -> new ResponseEntity<>(course, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/user/login/{loginId}")
    public ResponseEntity<User> getUserByLoginId(@PathVariable int loginId) {
        Optional<User> user = userDao.findAll()
                .stream()
                .filter(u -> u.getLoginDetails() != null && u.getLoginDetails().getId() == loginId)
                .findFirst();

        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @GetMapping("/user/image/{id}")
    public ResponseEntity<byte[]> getUserImage(@PathVariable int id) throws IOException {
        Optional<User> theUser = userDao.findById(id);
        if (theUser.isPresent()) {
            User user = theUser.get();
            Path imagePath = Path.of("public/images/", user.getImage()); // Adjust the path as needed
            if (Files.exists(imagePath)) {
                byte[] imageBytes = Files.readAllBytes(imagePath);
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
            }
        }

        // Return 404 if the product or image does not exist
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/user")
    public ResponseEntity<String> addUser(
            @Valid @ModelAttribute UserDto userDto,
            @RequestParam("imageFile") MultipartFile imageFile,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return new ResponseEntity<>("The request body contains validation errors", HttpStatus.BAD_REQUEST);
        }

        // Save image file
        if (imageFile.isEmpty()) {
            return new ResponseEntity<>("Image file is required", HttpStatus.BAD_REQUEST);
        }

        LocalDateTime dt = LocalDateTime.now();
        String storageFileName = dt.getMinute() + "_" + imageFile.getOriginalFilename();
        
        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (var inputStream = imageFile.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName));
            }
        } catch (IOException ex) {
            return new ResponseEntity<>("Failed to save image file: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // create and save user
        
        User user = new User();
        user.setLoginDetails(userDto.getLoginDetails());
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNo(userDto.getPhoneNo());
        user.setGender(userDto.getGender());
        user.setAge(userDto.getAge());
        user.setCurrentAddress1(userDto.getCurrentAddress1());
        user.setCurrentAddress2(userDto.getCurrentAddress2());
        user.setCurrentCity(userDto.getCurrentCity());
        user.setCurrentState(userDto.getCurrentState());
        user.setCurrentPincode(userDto.getCurrentPincode());
        user.setCurrentCountry(userDto.getCurrentCountry());
        user.setPermanentAddress1(userDto.getPermanentAddress1());
        user.setPermanentAddress2(userDto.getPermanentAddress2());
        user.setPermanentCity(userDto.getPermanentCity());
        user.setPermanentState(userDto.getPermanentState());
        user.setPermanentPincode(userDto.getPermanentPincode());
        user.setPermanentCountry(userDto.getPermanentCountry());
        user.setLastEducation(userDto.getLastEducation());
        user.setGrades(userDto.getGrades());
        user.setImage(storageFileName);
        user.setCreated(dt);
        user.setModified(null);
        userDao.save(user);

        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
    }


    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable int id,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @Valid @ModelAttribute UserDto userDto,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            return new ResponseEntity<>("The request body contains validation errors", HttpStatus.BAD_REQUEST);
        }

        Optional<User> theUser = userDao.findById(id);
        if (theUser.isPresent()) {
            User existingUser = theUser.get();
            LocalDateTime dt = LocalDateTime.now();
            String storageFileName = dt.getMinute() + "_" + imageFile.getOriginalFilename();

            // Update product information
            existingUser.setFirstName(userDto.getFirstName());
            existingUser.setLastName(userDto.getLastName());
            existingUser.setPhoneNo(userDto.getPhoneNo());
            existingUser.setGender(userDto.getGender());
            existingUser.setAge(userDto.getAge());
            existingUser.setCurrentAddress1(userDto.getCurrentAddress1());
            existingUser.setCurrentAddress2(userDto.getCurrentAddress2());
            existingUser.setCurrentCity(userDto.getCurrentCity());
            existingUser.setCurrentPincode(userDto.getCurrentPincode());
            existingUser.setCurrentCountry(userDto.getCurrentCountry());
            existingUser.setCurrentState(userDto.getCurrentState());
            existingUser.setPermanentAddress1(userDto.getPermanentAddress1());
            existingUser.setPermanentAddress2(userDto.getPermanentAddress2());
            existingUser.setPermanentCity(userDto.getPermanentCity());
            existingUser.setPermanentState(userDto.getPermanentState());
            existingUser.setPermanentPincode(userDto.getPermanentPincode());
            existingUser.setPermanentCountry(userDto.getPermanentCountry());
            existingUser.setLastEducation(userDto.getLastEducation());
            existingUser.setGrades(userDto.getGrades());
            existingUser.setLoginDetails(userDto.getLoginDetails());
            existingUser.setImage(storageFileName);
            existingUser.setModified(dt);
            userDao.save(existingUser);

            // Handle imageFile if provided
            if (imageFile != null && !imageFile.isEmpty()) {
                try {
                    String uploadDir = "public/images/";
                    Path uploadPath = Paths.get(uploadDir);

                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }

                    // Delete old image file
                    if (existingUser.getImage() != null) {
                        Path oldImagePath = Paths.get(uploadDir + existingUser.getImage());
                        Files.deleteIfExists(oldImagePath);
                    }

                    // Save new image file
                    String storageFile = dt.getMinute() + "_" + imageFile.getOriginalFilename();
                    Path filePath = Paths.get(uploadDir + storageFile);
                    Files.copy(imageFile.getInputStream(), filePath);
                    existingUser.setImage(storageFile);
                } catch (IOException ex) {
                    return new ResponseEntity<>("Failed to upload image file: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            userDao.save(existingUser);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        Optional<User> theUser = userDao.findById(id);
        if (theUser.isPresent()) {
            User user = theUser.get();
            userDao.delete(user);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

}
