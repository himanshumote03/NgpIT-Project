package com.appxbuild.ngpit.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "login_details")
public class LoginDetails {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    // mapped foreign-keys
    @OneToMany(mappedBy = "loginDetails")
    @JsonIgnoreProperties("login_details")
    private List<CheckIn> checkIn;

    @OneToMany(mappedBy = "loginDetails")
    @JsonIgnoreProperties("login_details")
    private List<CheckOut> checkOut;

    @OneToMany(mappedBy = "loginDetails")
    @JsonIgnoreProperties("login_details")
    private List<MyCourses> myCourses;


    // constructor
    public LoginDetails() {}

    public LoginDetails(String email, String password, LocalDateTime created, LocalDateTime modified) {
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "LoginDetails{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
