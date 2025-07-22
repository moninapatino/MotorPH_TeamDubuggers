package com.motorph.service;

import com.motorph.exception.ServiceException;

/**
 * Service interface for password recovery operations.
 * Enhanced version of the password recovery functionality from Portal_Access.
 */
public interface PasswordRecoveryService {
    
    /**
     * Initiate password recovery process
     * @param email the email address for recovery
     * @return recovery token
     * @throws ServiceException if email not found or recovery fails
     */
    String initiatePasswordRecovery(String email) throws ServiceException;
    
    /**
     * Validate recovery token
     * @param token the recovery token
     * @return true if token is valid and not expired
     */
    boolean validateRecoveryToken(String token);
    
    /**
     * Reset password using recovery token
     * @param token the recovery token
     * @param newPassword the new password
     * @throws ServiceException if token is invalid or password reset fails
     */
    void resetPassword(String token, String newPassword) throws ServiceException;
    
    /**
     * Change password for authenticated user
     * @param employeeId the employee ID
     * @param currentPassword the current password
     * @param newPassword the new password
     * @throws ServiceException if current password is incorrect or change fails
     */
    void changePassword(String employeeId, String currentPassword, String newPassword) throws ServiceException;
    
    /**
     * Validate password strength
     * @param password the password to validate
     * @return true if password meets strength requirements
     */
    boolean validatePasswordStrength(String password);
    
    /**
     * Generate secure recovery token
     * @return secure random token
     */
    String generateRecoveryToken();
}
