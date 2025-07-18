package com.motorph.service;

import java.util.List;

import com.motorph.dao.LeaveRecordDAO;
import com.motorph.dao.LeaveRecordDAOImpl;
import com.motorph.model.LeaveRecord;

public class LeaveManagementServiceImpl implements LeaveManagementService {
    private final LeaveRecordDAO leaveDAO = new LeaveRecordDAOImpl();

    public LeaveManagementServiceImpl(LeaveRecordDAO leaveDAO2) {
        //TODO Auto-generated constructor stub
    }

    @Override
    public LeaveRecord getLeaveById(int leaveId) {
        return leaveDAO.getLeaveById(leaveId);
    }

    @Override
    public List<LeaveRecord> getLeavesByEmployeeId(String employeeId) {
        return leaveDAO.getLeavesByEmployeeId(employeeId);
    }

    @Override
    public boolean requestLeave(LeaveRecord leave) {
        return leaveDAO.addLeave(leave);
    }

    @Override
    public boolean approveLeave(int leaveId) {
        return leaveDAO.updateLeaveStatus(leaveId, "Approved");
    }

    @Override
    public boolean rejectLeave(int leaveId) {
        return leaveDAO.updateLeaveStatus(leaveId, "Rejected");
    }
}
