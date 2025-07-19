package com.motorph.service;

import com.motorph.model.LeaveRequest;
import com.motorph.exception.ServiceException;
import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for LeaveRequest business operations.
 * Handles leave request management with proper business logic.
 */
public interface LeaveRequestService {
    
    /**
     * Submit a new leave request
     * @param leaveRequest the leave request to submit
     * @return the submitted leave request
     * @throws ServiceException if business rules are violated
     */
    LeaveRequest submitLeaveRequest(LeaveRequest leaveRequest) throws ServiceException;
    
    /**
     * Get leave request by ID
     * @param leaveId the leave ID
     * @return the leave request
     * @throws ServiceException if leave request not found
     */
    LeaveRequest getLeaveRequestById(String leaveId) throws ServiceException;
    
    /**
     * Get leave requests for an employee
     * @param employeeId the employee ID
     * @return list of leave requests
     */
    List<LeaveRequest> getLeaveRequestsByEmployee(String employeeId);
    
    /**
     * Get pending leave requests for approval
     * @return list of pending leave requests
     */
    List<LeaveRequest> getPendingLeaveRequests();
    
    /**
     * Approve a leave request
     * @param leaveId the leave ID
     * @param approvedBy the person approving the request
     * @return the approved leave request
     * @throws ServiceException if approval fails
     */
    LeaveRequest approveLeaveRequest(String leaveId, String approvedBy) throws ServiceException;
    
    /**
     * Reject a leave request
     * @param leaveId the leave ID
     * @param rejectedBy the person rejecting the request
     * @param remarks reason for rejection
     * @return the rejected leave request
     * @throws ServiceException if rejection fails
     */
    LeaveRequest rejectLeaveRequest(String leaveId, String rejectedBy, String remarks) throws ServiceException;
    
    /**
     * Cancel a leave request (by employee)
     * @param leaveId the leave ID
     * @param employeeId the employee ID (for authorization)
     * @throws ServiceException if cancellation fails
     */
    void cancelLeaveRequest(String leaveId, String employeeId) throws ServiceException;
    
    /**
     * Get leave balance for an employee
     * @param employeeId the employee ID
     * @param leaveType the type of leave
     * @return remaining leave balance
     */
    int getLeaveBalance(String employeeId, String leaveType);
    
    /**
     * Validate leave request against business rules
     * @param leaveRequest the leave request to validate
     * @throws ServiceException if validation fails
     */
    void validateLeaveRequest(LeaveRequest leaveRequest) throws ServiceException;
}
