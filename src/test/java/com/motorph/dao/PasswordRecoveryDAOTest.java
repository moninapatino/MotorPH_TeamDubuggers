package com.motorph.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class PasswordRecoveryDAOTest {
    private final PasswordRecoveryDAO dao = new PasswordRecoveryDAOImpl();

    @Test
    void testFindEmployeeIdByEmail_notFound() {
        String employeeId = dao.findEmployeeIdByEmail("nonexistent@email.com");
        assertNull(employeeId);
    }

    @Test
    void testVerifyEmail_invalidEmail() {
        boolean result = dao.verifyEmail("test123@email.com");
        assertFalse(result);
    }

    @Test
    void testUpdatePassword_success() {
        boolean result = dao.updatePassword("10001", "pass1234");
        assertTrue(result);
    }
}
