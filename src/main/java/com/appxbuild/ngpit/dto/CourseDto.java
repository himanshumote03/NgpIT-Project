package com.appxbuild.ngpit.dto;

import com.appxbuild.ngpit.entity.Faculty;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.web.multipart.MultipartFile;

public class CourseDto {

    // fields
    private String title;

    private String description;

    private MultipartFile imageFile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;

    // constructor
    public CourseDto() {}

    public CourseDto(String title, String description, MultipartFile imageFile, Faculty faculty) {
        this.title = title;
        this.description = description;
        this.imageFile = imageFile;
        this.faculty = faculty;
    }

    // getter/setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }


    // toString()
    @Override
    public String toString() {
        return "CourseDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageFile=" + imageFile +
                ", faculty=" + faculty +
                '}';
    }
}
