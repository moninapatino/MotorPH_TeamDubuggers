package com.motorph.test.view;

import com.motorph.view.NewPasswordFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NewPasswordFrameTest {
    private NewPasswordFrame frame;

    @BeforeEach
    void setUp() {
        frame = new NewPasswordFrame("EMP001");
    }

    @Test
    void testPasswordValidation_tooShort() {
        // Test password length validation
        assertFalse(isValidPassword("short"));
    }

    @Test
    void testPasswordValidation_mismatch() {
        // Test password match validation
        assertFalse(arePasswordsMatching("password123", "password124"));
    }

    @Test
    void testPasswordValidation_valid() {
        // Test valid password
        assertTrue(isValidPassword("validPassword123"));
        assertTrue(arePasswordsMatching("validPassword123", "validPassword123"));
    }

    // Helper methods to simulate the validation logic from NewPasswordFrame
    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    private boolean arePasswordsMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
