package com.appxbuild.ngpit.rest;

import com.appxbuild.ngpit.dao.FacultyDao;
import com.appxbuild.ngpit.dto.FacultyDto;
import com.appxbuild.ngpit.entity.Faculty;
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
public class FacultyRestController {

    @Autowired
    private FacultyDao facultyDao;

    @GetMapping("/faculty")
    public List<Faculty> findAll() {
        return facultyDao.findAll();
    }

    @GetMapping("/faculty/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable int id) {
        Optional<Faculty> faculty = facultyDao.findById(id);
        return faculty.map(faculty1 -> new ResponseEntity<>(faculty1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/faculty/image/{id}")
    public ResponseEntity<byte[]> getFacultyImage(@PathVariable int id) throws IOException {
        Optional<Faculty> theFaculty = facultyDao.findById(id);
        if (theFaculty.isPresent()) {
            Faculty faculty = theFaculty.get();
            Path imagePath = Path.of("public/images/", faculty.getImage()); // Adjust the path as needed
            if (Files.exists(imagePath)) {
                byte[] imageBytes = Files.readAllBytes(imagePath);
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
            }
        }
        // Return 404 if the product or image does not exist
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/faculty")
    public ResponseEntity<String> addFaculty(
            @Valid @ModelAttribute FacultyDto facultyDto,
            @RequestParam("imageFile")MultipartFile imageFile,
            BindingResult result
            ) {
        if (result.hasErrors()) {
            return new ResponseEntity<>("The request body contains validation errors", HttpStatus.BAD_REQUEST);
        }

        // save image file
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

        // create and save the faculty
        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        faculty.setDescription(facultyDto.getDescription());
        faculty.setImage(storageFileName);
        faculty.setCreated(dt);
        faculty.setModified(null);

        facultyDao.save(faculty);

        return new ResponseEntity<>("Faculty added successfullty", HttpStatus.OK);
    }


    @PutMapping("/faculty/{id}")
    public ResponseEntity<String> updateFaculty(
            @PathVariable int id,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @Valid @ModelAttribute FacultyDto facultyDto,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            return new ResponseEntity<>("The request body contains validation errors", HttpStatus.BAD_REQUEST);
        }

        Optional<Faculty> theFaculty = facultyDao.findById(id);
        if (theFaculty.isPresent()) {
            Faculty existingFaculty = theFaculty.get();
            LocalDateTime dt = LocalDateTime.now();
            String storageFileName = dt.getMinute() + "_" + imageFile.getOriginalFilename();

            // Update product information
            existingFaculty.setName(facultyDto.getName());
            existingFaculty.setDescription(facultyDto.getDescription());
            existingFaculty.setModified(dt);

            // Handle image file if provided
            if (imageFile != null && !imageFile.isEmpty()) {
                try {
                    String uploadDir = "public/images/";
                    Path uploadPath = Paths.get(uploadDir);

                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }

                    // Delete old image file
                    if (existingFaculty.getImage() != null) {
                        Path oldImagePath = Paths.get(uploadDir + existingFaculty.getImage());
                        Files.deleteIfExists(oldImagePath);
                    }

                    // Save new image file
                    String storageFile = dt.getMinute() + "_" + imageFile.getOriginalFilename();
                    Path filePath = Paths.get(uploadDir + storageFileName);
                    Files.copy(imageFile.getInputStream(), filePath);
                    existingFaculty.setImage(storageFileName);
                } catch (IOException ex) {
                    return new ResponseEntity<>("Failed to upload image file: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            facultyDao.save(existingFaculty);
            return new ResponseEntity<>("Faculty updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Faculty not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/faculty/{id}")
    public ResponseEntity<String> deleteFaculty(@PathVariable int id) {
        Optional<Faculty> faculty = facultyDao.findById(id);
        if (faculty.isPresent()) {
            Faculty newFaculty = faculty.get();
            facultyDao.delete(newFaculty);
            return new ResponseEntity<>("Faculty deleted successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Faculty not found", HttpStatus.NOT_FOUND);
        }
    }

}
