package com.appxbuild.ngpit.dto;

import org.springframework.web.multipart.MultipartFile;

public class GalleryDto {

    // fields
    private MultipartFile imageFile;

    // constructor
    public GalleryDto() {}

    public GalleryDto(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    // getter/setter
    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    // toString()
    @Override
    public String toString() {
        return "GalleryDto{" +
                "imageFile=" + imageFile +
                '}';
    }
}
