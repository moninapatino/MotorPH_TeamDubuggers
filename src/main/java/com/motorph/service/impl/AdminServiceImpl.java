package com.motorph.service.impl;

import com.motorph.service.AdminService;
import com.motorph.service.AttendanceService;
import com.motorph.dao.EmployeeDAO;
import com.motorph.dao.AttendanceDAO;
import com.motorph.model.Employee;
import com.motorph.model.Attendance;
import com.motorph.exception.ServiceException;
import java.time.LocalDateTime;
import java.util.*;

public abstract class AdminServiceImpl implements AdminService {
    private final EmployeeDAO employeeDAO;
    private final AttendanceDAO attendanceDAO;
    private final AttendanceService attendanceService;

    public AdminServiceImpl(EmployeeDAO employeeDAO, AttendanceDAO attendanceDAO, 
            AttendanceService attendanceService) {
        this.employeeDAO = employeeDAO;
        this.attendanceDAO = attendanceDAO;
        this.attendanceService = attendanceService;
    }

    @Override
    public void addEmployee(Employee employee) throws ServiceException {
        try {
            validateEmployeeData(employee);
            employeeDAO.save(employee);
        } catch (Exception e) {
            throw new ServiceException("Failed to add employee", e);
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws ServiceException {
        try {
            validateEmployeeData(employee);
            if (employeeDAO.findById(employee.getEmployeeId()).isEmpty()) {
                throw new ServiceException("Employee not found: " + employee.getEmployeeId());
            }
            employeeDAO.update(employee);
        } catch (Exception e) {
            throw new ServiceException("Failed to update employee", e);
        }
    }

    @Override
    public void deactivateEmployee(String employeeId) throws ServiceException {
        try {
            Optional<Employee> employee = employeeDAO.findById(employeeId);
            if (employee.isEmpty()) {
                throw new ServiceException("Employee not found: " + employeeId);
            }
            
            // Update employee status
            Employee emp = employee.get();
            // Set status to inactive
            employeeDAO.update(emp);
        } catch (Exception e) {
            throw new ServiceException("Failed to deactivate employee", e);
        }
    }

    @Override
    public void reactivateEmployee(String employeeId) throws ServiceException {
        try {
            Optional<Employee> employee = employeeDAO.findById(employeeId);
            if (employee.isEmpty()) {
                throw new ServiceException("Employee not found: " + employeeId);
            }
            
            // Update employee status
            Employee emp = employee.get();
            // Set status to active
            employeeDAO.update(emp);
        } catch (Exception e) {
            throw new ServiceException("Failed to reactivate employee", e);
        }
    }

    @Override
    public void updateAttendance(Attendance attendance) throws ServiceException {
        attendanceService.updateAttendance(attendance);
    }

    @Override
    public void deleteAttendance(Long attendanceId) throws ServiceException {
        attendanceService.deleteAttendance(attendanceId);
    }

    @Override
    public List<Attendance> getAttendanceReport(LocalDateTime startDate, LocalDateTime endDate) 
            throws ServiceException {
        return attendanceService.getAttendanceByDateRange(startDate, endDate);
    }

    @Override
    public Map<String, Integer> getAttendanceSummary(LocalDateTime startDate, LocalDateTime endDate) 
            throws ServiceException {
        try {
            List<Attendance> attendances = attendanceDAO.findByDateRange(startDate, endDate);
            Map<String, Integer> summary = new HashMap<>();
            
            int present = 0, late = 0, absent = 0;
            
            for (Attendance attendance : attendances) {
                switch (attendance.getStatus().toUpperCase()) {
                    case "PRESENT": present++; break;
                    case "LATE": late++; break;
                    case "ABSENT": absent++; break;
                }
            }
            
            summary.put("present", present);
            summary.put("late", late);
            summary.put("absent", absent);
            
            return summary;
        } catch (Exception e) {
            throw new ServiceException("Failed to generate attendance summary", e);
        }
    }

    @Override
    public List<Employee> searchEmployees(String query) throws ServiceException {
        // Implementation would depend on your search requirements
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Employee> getActiveEmployees() throws ServiceException {
        // Implementation depends on how you track active status
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Employee> getInactiveEmployees() throws ServiceException {
        // Implementation depends on how you track active status
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Map<String, Object> generatePayrollReport(LocalDateTime startDate, LocalDateTime endDate) 
            throws ServiceException {
        // Implementation for payroll report generation
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Map<String, Object> generateLeaveReport(LocalDateTime startDate, LocalDateTime endDate) 
            throws ServiceException {
        // Implementation for leave report generation
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private void validateEmployeeData(Employee employee) throws ServiceException {
        if (employee.getEmployeeId() == null || employee.getEmployeeId().trim().isEmpty()) {
            throw new ServiceException("Employee ID is required");
        }
        if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) {
            throw new ServiceException("First name is required");
        }
        if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) {
            throw new ServiceException("Last name is required");
        }
        // Add more validations as needed
    }
}
