package com.motorph.dao;

import com.motorph.model.AttendanceRecord;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceDAO {
    List<AttendanceRecord> findByEmployeeId(String employeeId, LocalDate startDate, LocalDate endDate);
    AttendanceRecord findCurrentDayRecord(String employeeId);
    boolean saveTimeIn(String employeeId);
    boolean saveTimeOut(String employeeId);
    boolean save(AttendanceRecord record);
}
