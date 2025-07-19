package com.motorph.service.impl;

import com.motorph.dao.EmployeeDAO;
import com.motorph.exception.DuplicateEmployeeException;
import com.motorph.exception.EmployeeNotFoundException;
import com.motorph.model.Employee;
import com.motorph.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of EmployeeService with proper business logic separation.
 * Handles validation, business rules, and coordinates with DAO layer.
 */
public class EmployeeServiceImpl implements EmployeeService {
    
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeDAO employeeDAO;
    
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    
    @Override
    public Employee createEmployee(Employee employee) throws DuplicateEmployeeException {
        logger.info("Creating new employee: {}", employee.getEmployeeId());
        
        // Validate employee data
        validateEmployee(employee);
        
        // Check for duplicates
        if (employeeDAO.existsById(employee.getEmployeeId())) {
            throw new DuplicateEmployeeException("Employee ID already exists: " + employee.getEmployeeId());
        }
        
        if (employeeDAO.existsByUsername(employee.getUsername())) {
            throw new DuplicateEmployeeException("Username already exists: " + employee.getUsername());
        }
        
        if (employeeDAO.existsByEmail(employee.getEmail())) {
            throw new DuplicateEmployeeException("Email already exists: " + employee.getEmail());
        }
        
        // Set default values
        employee.setActive(true);
        
        // Save employee
        Employee savedEmployee = employeeDAO.save(employee);
        logger.info("Employee created successfully: {}", savedEmployee.getEmployeeId());
        
        return savedEmployee;
    }
    
    @Override
    public Employee getEmployeeById(String employeeId) throws EmployeeNotFoundException {
        logger.debug("Finding employee by ID: {}", employeeId);
        
        Optional<Employee> employee = employeeDAO.findById(employeeId);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + employeeId);
        }
        
        return employee.get();
    }
    
    @Override
    public Employee getEmployeeByUsername(String username) throws EmployeeNotFoundException {
        logger.debug("Finding employee by username: {}", username);
        
        Optional<Employee> employee = employeeDAO.findByUsername(username);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found with username: " + username);
        }
        
        return employee.get();
    }
    
    @Override
    public List<Employee> getAllActiveEmployees() {
        logger.debug("Retrieving all active employees");
        return employeeDAO.findAllActive();
    }
    
    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        logger.debug("Finding employees by department: {}", department);
        
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be null or empty");
        }
        
        return employeeDAO.findByDepartment(department);
    }
    
    @Override
    public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException {
        logger.info("Updating employee: {}", employee.getEmployeeId());
        
        // Validate employee data
        validateEmployee(employee);
        
        // Check if employee exists
        if (!employeeDAO.existsById(employee.getEmployeeId())) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + employee.getEmployeeId());
        }
        
        // Update employee
        Employee updatedEmployee = employeeDAO.update(employee);
        logger.info("Employee updated successfully: {}", updatedEmployee.getEmployeeId());
        
        return updatedEmployee;
    }
    
    @Override
    public void deactivateEmployee(String employeeId) throws EmployeeNotFoundException {
        logger.info("Deactivating employee: {}", employeeId);
        
        if (!employeeDAO.existsById(employeeId)) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + employeeId);
        }
        
        boolean deleted = employeeDAO.deleteById(employeeId);
        if (!deleted) {
            throw new RuntimeException("Failed to deactivate employee: " + employeeId);
        }
        
        logger.info("Employee deactivated successfully: {}", employeeId);
    }
    
    @Override
    public Employee authenticateEmployee(String username, String password) throws EmployeeNotFoundException {
        logger.debug("Authenticating employee: {}", username);
        
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        
        // Validate credentials
        if (!employeeDAO.validateCredentials(username, password)) {
            throw new EmployeeNotFoundException("Invalid username or password");
        }
        
        // Return the authenticated employee
        return getEmployeeByUsername(username);
    }
    
    @Override
    public boolean employeeExists(String employeeId) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            return false;
        }
        
        return employeeDAO.existsById(employeeId);
    }
    
    @Override
    public void validateEmployee(Employee employee) throws IllegalArgumentException {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        
        // Validate required fields
        if (employee.getEmployeeId() == null || employee.getEmployeeId().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID is required");
        }
        
        if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        
        if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
        
        if (employee.getEmail() == null || employee.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        
        if (employee.getUsername() == null || employee.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        
        if (employee.getPassword() == null || employee.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        
        // Validate email format
        if (!isValidEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        
        // Validate username format (alphanumeric, minimum 3 characters)
        if (!isValidUsername(employee.getUsername())) {
            throw new IllegalArgumentException("Username must be alphanumeric and at least 3 characters long");
        }
        
        // Validate password strength (minimum 6 characters)
        if (employee.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
        
        logger.debug("Employee validation passed for: {}", employee.getEmployeeId());
    }
    
    /**
     * Validate email format using simple regex
     */
    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
    
    /**
     * Validate username format
     */
    private boolean isValidUsername(String username) {
        return username != null && username.matches("^[A-Za-z0-9]{3,}$");
    }
}
