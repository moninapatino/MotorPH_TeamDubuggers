package com.motorph.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * LeaveRequest entity representing employee leave requests.
 * Enhanced version of the original LeaveRecord with proper validation and design.
 */
public class LeaveRequest {
    
    private String leaveId;
    
    @NotBlank(message = "Employee ID is required")
    private String employeeId;
    
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    
    @NotNull(message = "End date is required")
    private LocalDate endDate;
    
    @NotBlank(message = "Leave type is required")
    private String leaveType;
    
    private String reason;
    private String status; // PENDING, APPROVED, REJECTED
    private LocalDate dateRequested;
    private String approvedBy;
    private LocalDate dateApproved;
    private String remarks;
    
    // Leave types enum for better type safety
    public enum LeaveType {
        SICK_LEAVE("Sick Leave"),
        VACATION_LEAVE("Vacation Leave"),
        EMERGENCY_LEAVE("Emergency Leave"),
        MATERNITY_LEAVE("Maternity Leave"),
        PATERNITY_LEAVE("Paternity Leave"),
        BEREAVEMENT_LEAVE("Bereavement Leave");
        
        private final String displayName;
        
        LeaveType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    // Status enum for better type safety
    public enum Status {
        PENDING("Pending"),
        APPROVED("Approved"),
        REJECTED("Rejected"),
        CANCELLED("Cancelled");
        
        private final String displayName;
        
        Status(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    // Default constructor
    public LeaveRequest() {
        this.status = Status.PENDING.name();
        this.dateRequested = LocalDate.now();
    }
    
    // Constructor with required fields
    public LeaveRequest(String employeeId, LocalDate startDate, LocalDate endDate, 
                       String leaveType, String reason) {
        this();
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.reason = reason;
    }
    
    // Getters and Setters
    public String getLeaveId() {
        return leaveId;
    }
    
    public void setLeaveId(String leaveId) {
        this.leaveId = leaveId;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public String getLeaveType() {
        return leaveType;
    }
    
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDate getDateRequested() {
        return dateRequested;
    }
    
    public void setDateRequested(LocalDate dateRequested) {
        this.dateRequested = dateRequested;
    }
    
    public String getApprovedBy() {
        return approvedBy;
    }
    
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
    
    public LocalDate getDateApproved() {
        return dateApproved;
    }
    
    public void setDateApproved(LocalDate dateApproved) {
        this.dateApproved = dateApproved;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    /**
     * Calculate the number of leave days requested
     * @return number of days between start and end date (inclusive)
     */
    public long getLeaveDays() {
        if (startDate != null && endDate != null) {
            return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
        }
        return 0;
    }
    
    /**
     * Check if leave request is approved
     * @return true if status is APPROVED
     */
    public boolean isApproved() {
        return Status.APPROVED.name().equals(status);
    }
    
    /**
     * Check if leave request is pending
     * @return true if status is PENDING
     */
    public boolean isPending() {
        return Status.PENDING.name().equals(status);
    }
    
    /**
     * Approve the leave request
     * @param approvedBy the person who approved the request
     */
    public void approve(String approvedBy) {
        this.status = Status.APPROVED.name();
        this.approvedBy = approvedBy;
        this.dateApproved = LocalDate.now();
    }
    
    /**
     * Reject the leave request
     * @param rejectedBy the person who rejected the request
     * @param remarks reason for rejection
     */
    public void reject(String rejectedBy, String remarks) {
        this.status = Status.REJECTED.name();
        this.approvedBy = rejectedBy;
        this.dateApproved = LocalDate.now();
        this.remarks = remarks;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaveRequest that = (LeaveRequest) o;
        return Objects.equals(leaveId, that.leaveId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(leaveId);
    }
    
    @Override
    public String toString() {
        return "LeaveRequest{" +
                "leaveId='" + leaveId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", leaveType='" + leaveType + '\'' +
                ", status='" + status + '\'' +
                ", leaveDays=" + getLeaveDays() +
                '}';
    }
}
