package com.motorph.service;

import com.motorph.model.Employee;
import com.motorph.exception.EmployeeNotFoundException;
import com.motorph.exception.DuplicateEmployeeException;
import java.util.List;

/**
 * Service interface for Employee business operations.
 * Provides abstraction for employee-related business logic.
 */
public interface EmployeeService {
    
    /**
     * Create a new employee
     * @param employee the employee to create
     * @return the created employee
     * @throws DuplicateEmployeeException if employee ID, username, or email already exists
     */
    Employee createEmployee(Employee employee) throws DuplicateEmployeeException;
    
    /**
     * Get employee by ID
     * @param employeeId the employee ID
     * @return the employee
     * @throws EmployeeNotFoundException if employee not found
     */
    Employee getEmployeeById(String employeeId) throws EmployeeNotFoundException;
    
    /**
     * Get employee by username
     * @param username the username
     * @return the employee
     * @throws EmployeeNotFoundException if employee not found
     */
    Employee getEmployeeByUsername(String username) throws EmployeeNotFoundException;
    
    /**
     * Get all active employees
     * @return list of active employees
     */
    List<Employee> getAllActiveEmployees();
    
    /**
     * Get employees by department
     * @param department the department name
     * @return list of employees in the department
     */
    List<Employee> getEmployeesByDepartment(String department);
    
    /**
     * Update employee information
     * @param employee the employee to update
     * @return the updated employee
     * @throws EmployeeNotFoundException if employee not found
     */
    Employee updateEmployee(Employee employee) throws EmployeeNotFoundException;
    
    /**
     * Deactivate an employee (soft delete)
     * @param employeeId the employee ID
     * @throws EmployeeNotFoundException if employee not found
     */
    void deactivateEmployee(String employeeId) throws EmployeeNotFoundException;
    
    /**
     * Authenticate employee credentials
     * @param username the username
     * @param password the password
     * @return the authenticated employee
     * @throws EmployeeNotFoundException if credentials are invalid
     */
    Employee authenticateEmployee(String username, String password) throws EmployeeNotFoundException;
    
    /**
     * Check if employee exists
     * @param employeeId the employee ID
     * @return true if employee exists
     */
    boolean employeeExists(String employeeId);
    
    /**
     * Validate employee data before save/update
     * @param employee the employee to validate
     * @throws IllegalArgumentException if validation fails
     */
    void validateEmployee(Employee employee) throws IllegalArgumentException;
}
