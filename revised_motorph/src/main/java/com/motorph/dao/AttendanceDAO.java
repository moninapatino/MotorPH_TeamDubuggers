package com.motorph.dao;

import com.motorph.model.Attendance;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Data Access Object interface for Attendance operations.
 */
public interface AttendanceDAO {
    
    /**
     * Save attendance record
     * @param attendance the attendance record to save
     * @return the saved attendance record
     */
    Attendance save(Attendance attendance);
    
    /**
     * Find attendance by ID
     * @param attendanceId the attendance ID
     * @return Optional containing the attendance if found
     */
    Optional<Attendance> findById(String attendanceId);
    
    /**
     * Find attendance records by employee ID
     * @param employeeId the employee ID
     * @return list of attendance records for the employee
     */
    List<Attendance> findByEmployeeId(String employeeId);
    
    /**
     * Find attendance record for specific employee and date
     * @param employeeId the employee ID
     * @param date the date
     * @return Optional containing the attendance if found
     */
    Optional<Attendance> findByEmployeeIdAndDate(String employeeId, LocalDate date);
    
    /**
     * Find attendance records within date range
     * @param employeeId the employee ID
     * @param startDate the start date
     * @param endDate the end date
     * @return list of attendance records within the range
     */
    List<Attendance> findByEmployeeIdAndDateRange(String employeeId, LocalDate startDate, LocalDate endDate);
    
    /**
     * Find all attendance records for a specific date
     * @param date the date
     * @return list of all attendance records for that date
     */
    List<Attendance> findByDate(LocalDate date);
    
    /**
     * Update attendance record
     * @param attendance the attendance to update
     * @return the updated attendance
     */
    Attendance update(Attendance attendance);
    
    /**
     * Delete attendance record
     * @param attendanceId the attendance ID
     * @return true if deleted successfully
     */
    boolean deleteById(String attendanceId);
    
    /**
     * Check if attendance exists for employee on specific date
     * @param employeeId the employee ID
     * @param date the date
     * @return true if attendance exists
     */
    boolean existsByEmployeeIdAndDate(String employeeId, LocalDate date);
}
