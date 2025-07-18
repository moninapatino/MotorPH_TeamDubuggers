package com.motorph.service;

import com.motorph.model.AttendanceRecord;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    List<AttendanceRecord> getEmployeeAttendance(String employeeId, LocalDate startDate, LocalDate endDate);
    AttendanceRecord getCurrentDayAttendance(String employeeId);
    boolean recordTimeIn(String employeeId);
    boolean recordTimeOut(String employeeId);
}
