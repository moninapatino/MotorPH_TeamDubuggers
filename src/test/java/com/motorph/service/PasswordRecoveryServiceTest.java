package com.motorph.test.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.motorph.model.PasswordRecovery;
import com.motorph.service.PasswordRecoveryService;
import com.motorph.service.PasswordRecoveryServiceImpl;

class PasswordRecoveryServiceTest {
    private final PasswordRecoveryService service = new PasswordRecoveryServiceImpl();

    @Test
    void testInitiatePasswordRecovery_invalidEmail() {
        PasswordRecovery recovery = service.initiatePasswordRecovery("invalid@email.com");
        assertNull(recovery);
    }

    @Test
    void testUpdatePassword_success() {
        boolean result = service.updatePassword("EMP001", "newPassword123");
        assertTrue(result);
    }

    @Test
    void testVerifyCode_success() {
        boolean result = service.verifyCode("test@email.com", "123456");
        assertTrue(result);
    }
}
