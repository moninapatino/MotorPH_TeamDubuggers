package com.motorph.service;

import com.motorph.model.Employee;
import com.motorph.model.Admin;
import com.motorph.exception.EmployeeNotFoundException;

/**
 * Service interface for authentication operations.
 * Handles login, logout, and session management.
 */
public interface AuthenticationService {
    
    /**
     * Authenticate user credentials
     * @param username the username
     * @param password the password
     * @return the authenticated employee
     * @throws EmployeeNotFoundException if credentials are invalid
     */
    Employee authenticate(String username, String password) throws EmployeeNotFoundException;
    
    /**
     * Check if user is an admin
     * @param employee the employee to check
     * @return true if employee has admin privileges
     */
    boolean isAdmin(Employee employee);
    
    /**
     * Get admin object if employee has admin privileges
     * @param employee the employee
     * @return Admin object if applicable, null otherwise
     */
    Admin getAdminPrivileges(Employee employee);
    
    /**
     * Validate session token (for future implementation)
     * @param token the session token
     * @return true if token is valid
     */
    boolean validateSession(String token);
    
    /**
     * Create session for authenticated user (for future implementation)
     * @param employee the authenticated employee
     * @return session token
     */
    String createSession(Employee employee);
    
    /**
     * Invalidate user session
     * @param token the session token to invalidate
     */
    void invalidateSession(String token);
}
