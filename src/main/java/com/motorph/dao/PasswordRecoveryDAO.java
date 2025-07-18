package com.motorph.dao;

public interface PasswordRecoveryDAO {
    String findEmployeeIdByEmail(String email);
    boolean updatePassword(String employeeId, String newPassword);
    boolean verifyEmail(String email);
}
