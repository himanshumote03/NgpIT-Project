package com.appxbuild.ngpit.dto;

import com.appxbuild.ngpit.entity.Courses;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.OneToMany;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FacultyDto {

    // fields
    private String name;

    private String description;

    private MultipartFile imageFile;

    @OneToMany(mappedBy = "faculty")
    @JsonIgnoreProperties("faculty")
    private List<Courses> courses;

    // constructor
    public FacultyDto() {}

    public FacultyDto(String name, String description, MultipartFile imageFile, List<Courses> courses) {
        this.name = name;
        this.description = description;
        this.imageFile = imageFile;
        this.courses = courses;
    }


    // getter/setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }


    // toString()
    @Override
    public String toString() {
        return "FacultyDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageFile=" + imageFile +
                ", courses=" + courses +
                '}';
    }
}
