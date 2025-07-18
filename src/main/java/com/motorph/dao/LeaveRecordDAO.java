package com.motorph.dao;

import java.util.List;

import com.motorph.model.LeaveRecord;

public interface LeaveRecordDAO {
    LeaveRecord getLeaveById(int leaveId);
    List<LeaveRecord> getLeavesByEmployeeId(String employeeId);
    boolean addLeave(LeaveRecord leave);
    boolean updateLeaveStatus(int leaveId, String status);
}
