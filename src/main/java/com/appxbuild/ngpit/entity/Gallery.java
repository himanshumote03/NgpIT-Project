package com.appxbuild.ngpit.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "gallery")
public class Gallery {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "image")
    private String image;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;


    // constructor
    public Gallery() {}

    public Gallery(String image, LocalDateTime created, LocalDateTime modified) {
        this.image = image;
        this.created = created;
        this.modified = modified;
    }


    // getter/setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }


    // toString()
    @Override
    public String toString() {
        return "Gallery{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
