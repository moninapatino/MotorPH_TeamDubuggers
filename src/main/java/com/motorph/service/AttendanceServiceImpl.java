package com.motorph.service;

import com.motorph.dao.AttendanceDAO;
import com.motorph.model.AttendanceRecord;
import java.time.LocalDate;
import java.util.List;

public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceDAO attendanceDAO;

    public AttendanceServiceImpl(AttendanceDAO attendanceDAO) {
        this.attendanceDAO = attendanceDAO;
    }

    @Override
    public List<AttendanceRecord> getEmployeeAttendance(String employeeId, LocalDate startDate, LocalDate endDate) {
        return attendanceDAO.findByEmployeeId(employeeId, startDate, endDate);
    }

    @Override
    public AttendanceRecord getCurrentDayAttendance(String employeeId) {
        return attendanceDAO.findCurrentDayRecord(employeeId);
    }

    @Override
    public boolean recordTimeIn(String employeeId) {
        // Check if already timed in
        AttendanceRecord current = getCurrentDayAttendance(employeeId);
        if (current != null && current.getTimeIn() != null) {
            return false;
        }
        return attendanceDAO.saveTimeIn(employeeId);
    }

    @Override
    public boolean recordTimeOut(String employeeId) {
        // Check if already timed out
        AttendanceRecord current = getCurrentDayAttendance(employeeId);
        if (current == null || current.getTimeOut() != null) {
            return false;
        }
        return attendanceDAO.saveTimeOut(employeeId);
    }
}
