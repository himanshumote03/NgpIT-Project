package com.appxbuild.ngpit.rest;

import com.appxbuild.ngpit.dao.CoursesDao;
import com.appxbuild.ngpit.dto.CourseDto;
import com.appxbuild.ngpit.entity.Courses;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CoursesRestController {

    @Autowired
    private CoursesDao coursesDao;


    @GetMapping("/courses")
    public List<Courses> findAll() {
        return coursesDao.findAll();
    }


    @GetMapping("/courses/{id}")
    public ResponseEntity<Courses> getCourseById(@PathVariable int id) {
        Optional<Courses> courses = coursesDao.findById(id);
        return courses.map(course -> new ResponseEntity<>(course, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/courses/image/{id}")
    public ResponseEntity<byte[]> getCourseImage(@PathVariable int id) throws Exception {
        Optional<Courses> theCourses = coursesDao.findById(id);
        if (theCourses.isPresent()) {
            Courses courses = theCourses.get();
            Path imagePath = Path.of("public/images/", courses.getImage());  // path of image folder

            if (Files.exists(imagePath)) {
                byte[] imageBytes = Files.readAllBytes(imagePath);
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
            }
        }

        // Return 404 if the course imafe does not exist
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/courses")
    public ResponseEntity<String> addCourse (
            @Valid @ModelAttribute CourseDto courseDto,
            @RequestParam("imageFile") MultipartFile imageFile,
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

        // create and save the course
        Courses courses = new Courses();
        courses.setTitle(courseDto.getTitle());
        courses.setDescription(courseDto.getDescription());
        courses.setFaculty(courseDto.getFaculty());
        courses.setImage(storageFileName);
        courses.setCreated(dt);
        courses.setModified(null);

        coursesDao.save(courses);

        return new ResponseEntity<>("Course added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<String> updateCourse(
            @PathVariable int id,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @Valid @ModelAttribute CourseDto courseDto,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            return new ResponseEntity<>("The request body contains validation errors", HttpStatus.BAD_REQUEST);
        }

        Optional<Courses> courses = coursesDao.findById(id);
        if (courses.isPresent()) {
            Courses existingCourse = courses.get();
            LocalDateTime dt = LocalDateTime.now();
            String storageFileName = dt.getMinute() + "_" + imageFile.getOriginalFilename();

            // update course information
            existingCourse.setTitle(courseDto.getTitle());
            existingCourse.setDescription(courseDto.getDescription());
            existingCourse.setFaculty(courseDto.getFaculty());
            existingCourse.setModified(dt);

            // Handle imageFile if provided
            if (imageFile != null && !imageFile.isEmpty()) {
                try {
                    String uploadDir = "public/images/";
                    Path uploadPath = Paths.get(uploadDir);

                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }

                    // Delete old imageFile
                    if (existingCourse.getImage() != null) {
                        Path oldImage = Paths.get(uploadDir + existingCourse.getImage());
                        Files.deleteIfExists(oldImage);
                    }

                    // save new imageFile
                    String storageFile = dt.getMinute() + "_" + imageFile.getOriginalFilename();
                    Path filePath = Paths.get(uploadDir + storageFile);
                    Files.copy(imageFile.getInputStream(), filePath);
                    existingCourse.setImage(storageFile);

                } catch (IOException ex) {
                    return new ResponseEntity<>("Failed to upload image file: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            coursesDao.save(existingCourse);
            return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        Optional<Courses> theCourses = coursesDao.findById(id);
        if (theCourses.isPresent()) {
            Courses courses = theCourses.get();
            coursesDao.delete(courses);
            return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
    }

}
