package com.motorph.service.impl;

import com.motorph.dao.impl.AttendanceDAOImpl;
import com.motorph.util.DatabaseConnectionManager;
import javax.swing.table.DefaultTableModel;

public class AttendanceServiceImpl {
    private final AttendanceDAOImpl attendanceDAO;

    public AttendanceServiceImpl(DatabaseConnectionManager dbManager) {
        this.attendanceDAO = new AttendanceDAOImpl(dbManager);
    }

    // Fill a table model with all attendance records
    public void fillAttendanceTable(DefaultTableModel model) {
        attendanceDAO.fillAttendanceTable(model);
    }

    // Add a new attendance record
    public boolean addAttendance(String employeeId, String firstName, String lastName, String date, String timeIn, String timeOut) {
        return attendanceDAO.addAttendance(employeeId, firstName, lastName, date, timeIn, timeOut);
    }

    // Update time out for an attendance record
    public boolean updateTimeOut(int attendanceId, String timeOut) {
        return attendanceDAO.updateTimeOut(attendanceId, timeOut);
    }

    // Delete an attendance record
    public boolean deleteAttendance(int attendanceId) {
        return attendanceDAO.deleteAttendance(attendanceId);
    }
}
