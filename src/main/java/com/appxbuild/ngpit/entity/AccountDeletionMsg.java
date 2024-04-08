package com.appxbuild.ngpit.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "account_deletion_msg")
public class AccountDeletionMsg {

    // fields
    @Id
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "login_id")
    private LoginDetails loginDetails;

    @Column(name = "message")
    private String message;

    @Column(name = "created")
    private LocalDateTime created;


    // constructor
    public AccountDeletionMsg() {}

    public AccountDeletionMsg(String message, LocalDateTime created) {
        this.message = message;
        this.created = created;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }


    // toString()

    @Override
    public String toString() {
        return "AccountDeletionMsg{" +
                "id=" + id +
                ", loginDetails=" + loginDetails +
                ", message='" + message + '\'' +
                ", created=" + created +
                '}';
    }
}
