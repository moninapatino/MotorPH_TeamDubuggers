package com.motorph.service;

import com.motorph.model.Employee;
import com.motorph.model.Attendance;
import com.motorph.exception.ServiceException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface AdminService {
    // Employee Management
    void addEmployee(Employee employee) throws ServiceException;
    void updateEmployee(Employee employee) throws ServiceException;
    void deactivateEmployee(String employeeId) throws ServiceException;
    void reactivateEmployee(String employeeId) throws ServiceException;
    
    // Attendance Management
    void updateAttendance(Attendance attendance) throws ServiceException;
    void deleteAttendance(Long attendanceId) throws ServiceException;
    List<Attendance> getAttendanceReport(LocalDateTime startDate, LocalDateTime endDate) throws ServiceException;
    Map<String, Integer> getAttendanceSummary(LocalDateTime startDate, LocalDateTime endDate) throws ServiceException;
    
    // Employee Search and Filtering
    List<Employee> searchEmployees(String query) throws ServiceException;
    List<Employee> getEmployeesByDepartment(String department) throws ServiceException;
    List<Employee> getActiveEmployees() throws ServiceException;
    List<Employee> getInactiveEmployees() throws ServiceException;
    
    // Reports
    Map<String, Object> generatePayrollReport(LocalDateTime startDate, LocalDateTime endDate) throws ServiceException;
    Map<String, Object> generateAttendanceReport(LocalDateTime startDate, LocalDateTime endDate) throws ServiceException;
    Map<String, Object> generateLeaveReport(LocalDateTime startDate, LocalDateTime endDate) throws ServiceException;
}
