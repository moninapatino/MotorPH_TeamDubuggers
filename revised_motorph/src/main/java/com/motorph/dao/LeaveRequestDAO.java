package com.motorph.dao;

import com.motorph.model.LeaveRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Data Access Object interface for LeaveRequest operations.
 */
public interface LeaveRequestDAO {
    
    /**
     * Save leave request
     * @param leaveRequest the leave request to save
     * @return the saved leave request
     */
    LeaveRequest save(LeaveRequest leaveRequest);
    
    /**
     * Find leave request by ID
     * @param leaveId the leave ID
     * @return Optional containing the leave request if found
     */
    Optional<LeaveRequest> findById(String leaveId);
    
    /**
     * Find leave requests by employee ID
     * @param employeeId the employee ID
     * @return list of leave requests for the employee
     */
    List<LeaveRequest> findByEmployeeId(String employeeId);
    
    /**
     * Find leave requests by status
     * @param status the status (PENDING, APPROVED, REJECTED)
     * @return list of leave requests with the specified status
     */
    List<LeaveRequest> findByStatus(String status);
    
    /**
     * Find leave requests within date range
     * @param startDate the start date
     * @param endDate the end date
     * @return list of leave requests within the range
     */
    List<LeaveRequest> findByDateRange(LocalDate startDate, LocalDate endDate);
    
    /**
     * Find pending leave requests for approval
     * @return list of pending leave requests
     */
    List<LeaveRequest> findPendingRequests();
    
    /**
     * Update leave request
     * @param leaveRequest the leave request to update
     * @return the updated leave request
     */
    LeaveRequest update(LeaveRequest leaveRequest);
    
    /**
     * Delete leave request
     * @param leaveId the leave ID
     * @return true if deleted successfully
     */
    boolean deleteById(String leaveId);
    
    /**
     * Check if employee has overlapping leave requests
     * @param employeeId the employee ID
     * @param startDate the start date
     * @param endDate the end date
     * @return true if there are overlapping requests
     */
    boolean hasOverlappingRequests(String employeeId, LocalDate startDate, LocalDate endDate);
}
