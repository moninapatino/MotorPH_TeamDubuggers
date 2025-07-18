package com.motorph.model;

import java.time.LocalDateTime;

public class AttendanceRecord {
    private String employeeId;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private double hoursWorked;
    private double overtimeHours;

    public AttendanceRecord(String employeeId, LocalDateTime timeIn, LocalDateTime timeOut,
                          double hoursWorked, double overtimeHours) {
        this.employeeId = employeeId;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.hoursWorked = hoursWorked;
        this.overtimeHours = overtimeHours;
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public LocalDateTime getTimeIn() { return timeIn; }
    public LocalDateTime getTimeOut() { return timeOut; }
    public double getHoursWorked() { return hoursWorked; }
    public double getOvertimeHours() { return overtimeHours; }
}
