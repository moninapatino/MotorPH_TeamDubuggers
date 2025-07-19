package com.motorph.exception;

/**
 * Exception thrown when attempting to create an employee with duplicate information.
 */
public class DuplicateEmployeeException extends Exception {
    
    public DuplicateEmployeeException(String message) {
        super(message);
    }
    
    public DuplicateEmployeeException(String message, Throwable cause) {
        super(message, cause);
    }
}
