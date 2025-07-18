package com.motorph.service;

import com.motorph.model.PasswordRecovery;

public interface PasswordRecoveryService {
    PasswordRecovery initiatePasswordRecovery(String email);
    boolean verifyCode(String email, String code);
    boolean updatePassword(String employeeId, String newPassword);
    boolean sendVerificationCode(String email, String code);
}
