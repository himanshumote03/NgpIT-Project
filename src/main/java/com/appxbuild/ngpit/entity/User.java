package com.appxbuild.ngpit.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {

    // fields
    @Id
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "login_id")
    private LoginDetails loginDetails;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "image")
    private String image;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private int age;

    @Column(name = "current_address_1")
    private String currentAddress1;

    @Column(name = "current_address_2")
    private String currentAddress2;

    @Column(name = "current_city")
    private String currentCity;

    @Column(name = "current_state")
    private String currentState;

    @Column(name = "current_pincode")
    private String currentPincode;

    @Column(name = "current_country")
    private String currentCountry;

    @Column(name = "permanent_address_1")
    private String permanentAddress1;

    @Column(name = "permanent_address_2")
    private String permanentAddress2;

    @Column(name = "permanent_city")
    private String permanentCity;

    @Column(name = "permanent_state")
    private String permanentState;

    @Column(name = "permanent_pincode")
    private String permanentPincode;

    @Column(name = "permanent_country")
    private String permanentCountry;

    @Column(name = "last_education")
    private String lastEducation;

    @Column(name = "grades")
    private String grades;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    // constructor
    public User() {}

    public User(String firstName, String lastName, String image, String phoneNo, String gender, int age, String currentAddress1, String currentAddress2, String currentCity, String currentState, String currentPincode, String currentCountry, String permanentAddress1, String permanentAddress2, String permanentCity, String permanentState, String permanentPincode, String permanentCountry, String lastEducation, String grades, LocalDateTime created, LocalDateTime modified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.age = age;
        this.currentAddress1 = currentAddress1;
        this.currentAddress2 = currentAddress2;
        this.currentCity = currentCity;
        this.currentState = currentState;
        this.currentPincode = currentPincode;
        this.currentCountry = currentCountry;
        this.permanentAddress1 = permanentAddress1;
        this.permanentAddress2 = permanentAddress2;
        this.permanentCity = permanentCity;
        this.permanentState = permanentState;
        this.permanentPincode = permanentPincode;
        this.permanentCountry = permanentCountry;
        this.lastEducation = lastEducation;
        this.grades = grades;
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

    public LoginDetails getLoginDetails() {
        return loginDetails;
    }

    public void setLoginDetails(LoginDetails loginDetails) {
        this.loginDetails = loginDetails;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCurrentAddress1() {
        return currentAddress1;
    }

    public void setCurrentAddress1(String currentAddress1) {
        this.currentAddress1 = currentAddress1;
    }

    public String getCurrentAddress2() {
        return currentAddress2;
    }

    public void setCurrentAddress2(String currentAddress2) {
        this.currentAddress2 = currentAddress2;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getCurrentPincode() {
        return currentPincode;
    }

    public void setCurrentPincode(String currentPincode) {
        this.currentPincode = currentPincode;
    }

    public String getCurrentCountry() {
        return currentCountry;
    }

    public void setCurrentCountry(String currentCountry) {
        this.currentCountry = currentCountry;
    }

    public String getPermanentAddress1() {
        return permanentAddress1;
    }

    public void setPermanentAddress1(String permanentAddress1) {
        this.permanentAddress1 = permanentAddress1;
    }

    public String getPermanentAddress2() {
        return permanentAddress2;
    }

    public void setPermanentAddress2(String permanentAddress2) {
        this.permanentAddress2 = permanentAddress2;
    }

    public String getPermanentCity() {
        return permanentCity;
    }

    public void setPermanentCity(String permanentCity) {
        this.permanentCity = permanentCity;
    }

    public String getPermanentState() {
        return permanentState;
    }

    public void setPermanentState(String permanentState) {
        this.permanentState = permanentState;
    }

    public String getPermanentPincode() {
        return permanentPincode;
    }

    public void setPermanentPincode(String permanentPincode) {
        this.permanentPincode = permanentPincode;
    }

    public String getPermanentCountry() {
        return permanentCountry;
    }

    public void setPermanentCountry(String permanentCountry) {
        this.permanentCountry = permanentCountry;
    }

    public String getLastEducation() {
        return lastEducation;
    }

    public void setLastEducation(String lastEducation) {
        this.lastEducation = lastEducation;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
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
        return "User{" +
                "id=" + id +
                ", loginDetails=" + loginDetails +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", image='" + image + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", currentAddress1='" + currentAddress1 + '\'' +
                ", currentAddress2='" + currentAddress2 + '\'' +
                ", currentCity='" + currentCity + '\'' +
                ", currentState='" + currentState + '\'' +
                ", currentPincode='" + currentPincode + '\'' +
                ", currentCountry='" + currentCountry + '\'' +
                ", permanentAddress1='" + permanentAddress1 + '\'' +
                ", permanentAddress2='" + permanentAddress2 + '\'' +
                ", permanentCity='" + permanentCity + '\'' +
                ", permanentState='" + permanentState + '\'' +
                ", permanentPincode='" + permanentPincode + '\'' +
                ", permanentCountry='" + permanentCountry + '\'' +
                ", lastEducation='" + lastEducation + '\'' +
                ", grades='" + grades + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
