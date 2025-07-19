package com.motorph.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Attendance entity representing employee attendance records.
 */
public class Attendance {
    
    private String attendanceId;
    
    @NotBlank(message = "Employee ID is required")
    private String employeeId;
    
    @NotNull(message = "Date is required")
    private LocalDate date;
    
    private LocalTime timeIn;
    private LocalTime timeOut;
    private LocalTime breakStart;
    private LocalTime breakEnd;
    
    private double hoursWorked;
    private double overtimeHours;
    private boolean isPresent;
    private boolean isLate;
    private String remarks;
    
    // Default constructor
    public Attendance() {
        this.isPresent = false;
        this.isLate = false;
    }
    
    // Constructor with required fields
    public Attendance(String employeeId, LocalDate date) {
        this();
        this.employeeId = employeeId;
        this.date = date;
    }
    
    // Getters and Setters
    public String getAttendanceId() {
        return attendanceId;
    }
    
    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public LocalTime getTimeIn() {
        return timeIn;
    }
    
    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
        this.isPresent = true;
        // Check if late (assuming 8:00 AM is standard start time)
        this.isLate = timeIn != null && timeIn.isAfter(LocalTime.of(8, 0));
    }
    
    public LocalTime getTimeOut() {
        return timeOut;
    }
    
    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
        calculateHoursWorked();
    }
    
    public LocalTime getBreakStart() {
        return breakStart;
    }
    
    public void setBreakStart(LocalTime breakStart) {
        this.breakStart = breakStart;
        calculateHoursWorked();
    }
    
    public LocalTime getBreakEnd() {
        return breakEnd;
    }
    
    public void setBreakEnd(LocalTime breakEnd) {
        this.breakEnd = breakEnd;
        calculateHoursWorked();
    }
    
    public double getHoursWorked() {
        return hoursWorked;
    }
    
    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
    
    public double getOvertimeHours() {
        return overtimeHours;
    }
    
    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }
    
    public boolean isPresent() {
        return isPresent;
    }
    
    public void setPresent(boolean present) {
        isPresent = present;
    }
    
    public boolean isLate() {
        return isLate;
    }
    
    public void setLate(boolean late) {
        isLate = late;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    /**
     * Calculate hours worked based on time in/out and break times
     */
    private void calculateHoursWorked() {
        if (timeIn != null && timeOut != null) {
            double totalMinutes = java.time.Duration.between(timeIn, timeOut).toMinutes();
            
            // Subtract break time if available
            if (breakStart != null && breakEnd != null) {
                double breakMinutes = java.time.Duration.between(breakStart, breakEnd).toMinutes();
                totalMinutes -= breakMinutes;
            }
            
            this.hoursWorked = totalMinutes / 60.0;
            
            // Calculate overtime (assuming 8 hours is standard)
            this.overtimeHours = Math.max(0, this.hoursWorked - 8.0);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return Objects.equals(attendanceId, that.attendanceId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(attendanceId);
    }
    
    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId='" + attendanceId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", date=" + date +
                ", timeIn=" + timeIn +
                ", timeOut=" + timeOut +
                ", hoursWorked=" + hoursWorked +
                ", isPresent=" + isPresent +
                ", isLate=" + isLate +
                '}';
    }
}
