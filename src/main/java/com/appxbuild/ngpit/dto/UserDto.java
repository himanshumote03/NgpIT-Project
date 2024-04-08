package com.appxbuild.ngpit.dto;

import com.appxbuild.ngpit.entity.LoginDetails;
import org.springframework.web.multipart.MultipartFile;

public class UserDto {

    // fields
    private int id;

    private LoginDetails loginDetails;

    private String firstName;

    private String lastName;

    private String phoneNo;

    private String gender;

    private int age;

    private String currentAddress1;

    private String currentAddress2;

    private String currentCity;

    private String currentState;

    private String currentPincode;

    private String currentCountry;

    private String permanentAddress1;

    private String permanentAddress2;

    private String permanentCity;

    private String permanentState;

    private String permanentPincode;

    private String permanentCountry;

    private String lastEducation;

    private String grades;

    private MultipartFile imageFile;

    // constructor
    public UserDto() {}

    public UserDto(String firstName, String lastName, String phoneNo, String gender, int age, String currentAddress1, String currentAddress2, String currentCity, String currentState, String currentPincode, String currentCountry, String permanentAddress1, String permanentAddress2, String permanentCity, String permanentState, String permanentPincode, String permanentCountry, String lastEducation, String grades, MultipartFile imageFile) {
        this.firstName = firstName;
        this.lastName = lastName;
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
        this.imageFile = imageFile;
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

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }


    // toString
    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", loginDetails=" + loginDetails +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
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
                ", imageFile=" + imageFile +
                '}';
    }
}
