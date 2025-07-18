package com.motorph.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import com.motorph.model.UserAccount;

class AuthenticationServiceTest {
    private final AuthenticationService service = new AuthenticationServiceImpl();

    @Test
    void testAuthenticate_valid() {
        UserAccount user = service.authenticate("testuser", "testpass");
        // Replace with actual test data or use a test DB
        assertNull(user); // Should be null if not found
    }

    @Test
    void testAuthenticate_invalid() {
        UserAccount user = service.authenticate("invalid", "invalid");
        assertNull(user);
    }
}
