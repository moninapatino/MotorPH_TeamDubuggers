package com.motorph.service.impl;

import com.motorph.exception.EmployeeNotFoundException;
import com.motorph.model.Admin;
import com.motorph.model.Employee;
import com.motorph.service.AuthenticationService;
import com.motorph.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of AuthenticationService with proper security practices.
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private final EmployeeService employeeService;
    private final Map<String, Employee> activeSessions;
    
    public AuthenticationServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.activeSessions = new ConcurrentHashMap<>();
    }
    
    @Override
    public Employee authenticate(String username, String password) throws EmployeeNotFoundException {
        logger.info("Attempting authentication for username: {}", username);
        
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        
        try {
            Employee employee = employeeService.authenticateEmployee(username, password);
            logger.info("Authentication successful for user: {}", username);
            return employee;
        } catch (EmployeeNotFoundException e) {
            logger.warn("Authentication failed for username: {}", username);
            throw e;
        }
    }
    
    @Override
    public boolean isAdmin(Employee employee) {
        if (employee == null) {
            return false;
        }
        
        // Check if employee has admin role or admin privileges
        // This could be based on department, position, or specific admin flag
        return "Admin".equalsIgnoreCase(employee.getDepartment()) ||
               "Administrator".equalsIgnoreCase(employee.getPosition()) ||
               employee instanceof Admin;
    }
    
    @Override
    public Admin getAdminPrivileges(Employee employee) {
        if (!isAdmin(employee)) {
            return null;
        }
        
        if (employee instanceof Admin) {
            return (Admin) employee;
        }
        
        // Convert regular employee to admin if they have admin privileges
        Admin admin = new Admin(
            employee.getEmployeeId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getEmail(),
            employee.getBirthday(),
            employee.getUsername(),
            employee.getPassword()
        );
        
        // Copy other properties
        admin.setAddress(employee.getAddress());
        admin.setPhoneNumber(employee.getPhoneNumber());
        admin.setSssNumber(employee.getSssNumber());
        admin.setPhilHealthNumber(employee.getPhilHealthNumber());
        admin.setTinNumber(employee.getTinNumber());
        admin.setPagibigNumber(employee.getPagibigNumber());
        admin.setDepartment(employee.getDepartment());
        admin.setPosition(employee.getPosition());
        admin.setBasicSalary(employee.getBasicSalary());
        admin.setActive(employee.isActive());
        
        return admin;
    }
    
    @Override
    public boolean validateSession(String token) {
        if (token == null || token.trim().isEmpty()) {
            return false;
        }
        
        return activeSessions.containsKey(token);
    }
    
    @Override
    public String createSession(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        
        String sessionToken = UUID.randomUUID().toString();
        activeSessions.put(sessionToken, employee);
        
        logger.info("Session created for user: {}", employee.getUsername());
        return sessionToken;
    }
    
    @Override
    public void invalidateSession(String token) {
        if (token != null) {
            Employee employee = activeSessions.remove(token);
            if (employee != null) {
                logger.info("Session invalidated for user: {}", employee.getUsername());
            }
        }
    }
    
    /**
     * Get employee from session token
     * @param token the session token
     * @return the employee if session is valid
     */
    public Employee getEmployeeFromSession(String token) {
        return activeSessions.get(token);
    }
    
    /**
     * Get all active sessions count (for monitoring)
     * @return number of active sessions
     */
    public int getActiveSessionCount() {
        return activeSessions.size();
    }
}
