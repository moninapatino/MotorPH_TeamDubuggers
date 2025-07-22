package com.motorph.dao;

import com.motorph.model.Employee;
import java.util.List;
import java.util.Optional;

/**
 * Data Access Object interface for Employee operations.
 * Provides abstraction for employee data persistence.
 */
public interface EmployeeDAO {
    
    /**
     * Save a new employee or update an existing one
     * @param employee the employee to save
     * @return the saved employee with generated ID if applicable
     */
    Employee save(Employee employee);
    
    /**
     * Find an employee by their ID
     * @param employeeId the employee ID
     * @return Optional containing the employee if found
     */
    Optional<Employee> findById(String employeeId);
    
    /**
     * Find an employee by their username
     * @param username the username
     * @return Optional containing the employee if found
     */
    Optional<Employee> findByUsername(String username);
    
    /**
     * Find an employee by their email
     * @param email the email address
     * @return Optional containing the employee if found
     */
    Optional<Employee> findByEmail(String email);
    
    /**
     * Get all employees
     * @return list of all employees
     */
    List<Employee> findAll();
    
    /**
     * Get all active employees
     * @return list of active employees
     */
    List<Employee> findAllActive();
    
    /**
     * Find employees by department
     * @param department the department name
     * @return list of employees in the department
     */
    List<Employee> findByDepartment(String department);
    
    /**
     * Update an existing employee
     * @param employee the employee to update
     * @return the updated employee
     */
    Employee update(Employee employee);
    
    /**
     * Delete an employee by ID (soft delete - mark as inactive)
     * @param employeeId the employee ID
     * @return true if deleted successfully
     */
    boolean deleteById(String employeeId);
    
    /**
     * Check if an employee exists by ID
     * @param employeeId the employee ID
     * @return true if employee exists
     */
    boolean existsById(String employeeId);
    
    /**
     * Check if a username is already taken
     * @param username the username to check
     * @return true if username exists
     */
    boolean existsByUsername(String username);
    
    /**
     * Check if an email is already taken
     * @param email the email to check
     * @return true if email exists
     */
    boolean existsByEmail(String email);
    
    /**
     * Validate employee credentials
     * @param username the username
     * @param password the password
     * @return true if credentials are valid
     */
    boolean validateCredentials(String username, String password);
}
