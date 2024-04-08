package com.appxbuild.ngpit.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "check_in")
public class CheckIn {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "login_id", referencedColumnName = "id")
    private LoginDetails loginDetails;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "total_task")
    private int totalTask;

    @Column(name = "created")
    private LocalDateTime created;

    // constructor
    public CheckIn() {}

    public CheckIn(String projectName, String taskDescription, int totalTask, LocalDateTime created) {
        this.projectName = projectName;
        this.taskDescription = taskDescription;
        this.totalTask = totalTask;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getTotalTask() {
        return totalTask;
    }

    public void setTotalTask(int totalTask) {
        this.totalTask = totalTask;
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
        return "CheckIn{" +
                "id=" + id +
                ", loginDetails=" + loginDetails +
                ", projectName='" + projectName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", totalTask=" + totalTask +
                ", created=" + created +
                '}';
    }
}
