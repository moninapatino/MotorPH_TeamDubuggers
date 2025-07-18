package com.motorph.model;

import java.time.LocalDateTime;

public class Attendance {
    private Long id;
    private String employeeId;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private String status; // PRESENT, LATE, ABSENT, etc.
    private String remarks;
    private LocalDateTime date;

    public Attendance(String employeeId, LocalDateTime date) {
        this.employeeId = employeeId;
        this.date = date;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public LocalDateTime getTimeIn() { return timeIn; }
    public void setTimeIn(LocalDateTime timeIn) { this.timeIn = timeIn; }

    public LocalDateTime getTimeOut() { return timeOut; }
    public void setTimeOut(LocalDateTime timeOut) { this.timeOut = timeOut; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    // Business logic methods
    public boolean isLate() {
        if (timeIn == null) return false;
        return timeIn.getHour() > 9 || (timeIn.getHour() == 9 && timeIn.getMinute() > 0);
    }

    public double getHoursWorked() {
        if (timeIn == null || timeOut == null) return 0.0;
        return java.time.Duration.between(timeIn, timeOut).toHours();
    }
}
