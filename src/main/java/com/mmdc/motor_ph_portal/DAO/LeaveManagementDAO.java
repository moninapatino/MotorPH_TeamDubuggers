package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_portal.LeaveRecord;
import java.util.ArrayList;
import java.util.Vector;


public interface LeaveManagementDAO {
    ArrayList<LeaveRecord> userList(String employeeID);
    ArrayList<LeaveRecord> leaveMngUserList();
    int getNextLeaveId();
    boolean addLeaveRequest(LeaveRecord leaveRequest);
    boolean deleteLeaveRecord(LeaveRecord leaveRecord);
    boolean updateLeaveRecord(LeaveRecord leaveRecord);
    ArrayList<Vector<String>> getLeaveRecordsByEmployeeId(String employeeId);
}