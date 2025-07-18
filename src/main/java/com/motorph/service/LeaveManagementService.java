package com.motorph.service;

import java.util.List;

import com.motorph.model.LeaveRecord;

public interface LeaveManagementService {
    LeaveRecord getLeaveById(int leaveId);
    List<LeaveRecord> getLeavesByEmployeeId(String employeeId);
    boolean requestLeave(LeaveRecord leave);
    boolean approveLeave(int leaveId);
    boolean rejectLeave(int leaveId);
}
