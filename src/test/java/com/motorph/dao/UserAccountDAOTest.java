package com.motorph.dao;

import static orgtjunitjjupiterjapi.Assertions.assertNullAssertions.assertNull;
import org.junit.jupiter.api.Test;
.motorph.model.UserAccount;
    private final UserAccountDAO dao = new UserAccountDAOImpl();

    @Test
    void testFindByUsernameAndPassword_valid() {
        UserAccount user = dao.findByUsernameAndPassword("testuser", "testpass");
        // Replace with actual test data or use a test DB
        assertNull(user); // Should be null if not found
    }

    @Test
    void testFindByUsernameAndPassword_invalid() {
        UserAccount user = dao.findByUsernameAndPassword("invalid", "invalid");
        assertNull(user);
    }
}
