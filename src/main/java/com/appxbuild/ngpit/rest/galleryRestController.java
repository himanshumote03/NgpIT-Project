package com.appxbuild.ngpit.rest;

import com.appxbuild.ngpit.dao.GalleryDao;
import com.appxbuild.ngpit.dto.GalleryDto;
import com.appxbuild.ngpit.entity.Gallery;
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
public class galleryRestController {

    @Autowired
    private GalleryDao galleryDao;


    @GetMapping("/gallery")
    public List<Gallery> findAll(){
        return galleryDao.findAll();
    }


    @GetMapping("/gallery/{id}")
    public ResponseEntity<Gallery> getGalleryById(@PathVariable int id) {
        Optional<Gallery> theGallery = galleryDao.findById(id);
        return theGallery.map(gallery -> new ResponseEntity<>(gallery, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/gallery/image/{id}")
    public ResponseEntity<byte[]> GetGalleryImage(@PathVariable int id) throws IOException {
        Optional<Gallery> theGallery = galleryDao.findById(id);
        if (theGallery.isPresent()) {
            Gallery gallery = theGallery.get();
            Path imagePath = Path.of("public/images/", gallery.getImage()); // Adjust the path as needed
            if (Files.exists(imagePath)) {
                byte[] imageBytes = Files.readAllBytes(imagePath);
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
            }
        }
        // Return 404 if the product or image does not exist
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/gallery")
    public ResponseEntity<String> addGallery(
            @Valid @ModelAttribute GalleryDto galleryDto,
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
        String storageFileName= dt.getMinute() + "_" + imageFile.getOriginalFilename();

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

        // Create and save the Gallery
        Gallery gallery = new Gallery();
        gallery.setImage(storageFileName);
        gallery.setCreated(dt);
        gallery.setModified(null);

        galleryDao.save(gallery);

        return new ResponseEntity<>("Gallery added successfully", HttpStatus.CREATED);
    }


    @PutMapping("/gallery/{id}")
    public ResponseEntity<String> updateGallery (
            @PathVariable int id,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @Valid @ModelAttribute GalleryDto galleryDto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return new ResponseEntity<>("The request body contains validation errors", HttpStatus.BAD_REQUEST);
        }

        Optional<Gallery> theGallery = galleryDao.findById(id);
        if (theGallery.isPresent()) {
            Gallery existingGallery = theGallery.get();
            LocalDateTime dt = LocalDateTime.now();
            String storageFileName = dt.getMinute() + "_" + imageFile.getOriginalFilename();
            // Update product information
            existingGallery.setModified(dt);

            // Handle image file if provided
            if (imageFile != null && !imageFile.isEmpty()) {
                try {
                    String uploadDir = "public/images/";
                    Path uploadPath = Paths.get(uploadDir);

                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }

                    // Delete old image file
                    if (existingGallery.getImage() != null) {
                        Path oldImagePath = Paths.get(uploadDir + existingGallery.getImage());
                        Files.deleteIfExists(oldImagePath);
                    }

                    // Save new image file
                    String storageFile = dt.getMinute() + "_" + imageFile.getOriginalFilename();
                    Path filePath = Paths.get(uploadDir + storageFile);
                    Files.copy(imageFile.getInputStream(), filePath);
                    existingGallery.setImage(storageFile);
                } catch (IOException ex) {
                    return new ResponseEntity<>("Failed to upload image file: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            galleryDao.save(existingGallery);
            return new ResponseEntity<>("Gallery updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Gallery not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/gallery/{id}")
    public ResponseEntity<String> deleteGallery(@PathVariable int id) {
        Optional<Gallery> theGallery = galleryDao.findById(id);
        if (theGallery.isPresent()) {
            Gallery gallery = theGallery.get();
            galleryDao.delete(gallery);
            return new ResponseEntity<>("Gallery deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Gallery not found", HttpStatus.NOT_FOUND);
        }
    }

}
