package com.motorph.exception;

/**
 * Exception thrown when an employee is not found in the system.
 */
public class EmployeeNotFoundException extends Exception {
    
    public EmployeeNotFoundException(String message) {
        super(message);
    }
    
    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
