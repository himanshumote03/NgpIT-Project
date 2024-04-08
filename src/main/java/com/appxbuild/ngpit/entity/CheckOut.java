package com.appxbuild.ngpit.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "check_out")
public class CheckOut {

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

    @Column(name = "todays_task")
    private String todayTask;

    @Column(name = "working_hours_report")
    private String WorkingHoursReport;

    @Column(name = "total_hours")
    private int totalHours;

    @Column(name = "created")
    private LocalDateTime created;

    // constructor
    public CheckOut() {}

    public CheckOut(String projectName, String todayTask, String workingHoursReport, int totalHours, LocalDateTime created) {
        this.projectName = projectName;
        this.todayTask = todayTask;
        WorkingHoursReport = workingHoursReport;
        this.totalHours = totalHours;
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

    public String getTodayTask() {
        return todayTask;
    }

    public void setTodayTask(String todayTask) {
        this.todayTask = todayTask;
    }

    public String getWorkingHoursReport() {
        return WorkingHoursReport;
    }

    public void setWorkingHoursReport(String workingHoursReport) {
        WorkingHoursReport = workingHoursReport;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
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
        return "CheckOut{" +
                "id=" + id +
                ", loginDetails=" + loginDetails +
                ", projectName='" + projectName + '\'' +
                ", todayTask='" + todayTask + '\'' +
                ", WorkingHoursReport='" + WorkingHoursReport + '\'' +
                ", totalHours=" + totalHours +
                ", created=" + created +
                '}';
    }
}
