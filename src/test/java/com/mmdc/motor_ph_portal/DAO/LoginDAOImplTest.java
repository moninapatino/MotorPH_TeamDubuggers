package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_util.DatabaseConnect;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginDAOImplTest {
    
    private LoginDAOImpl loginDAO;

    public LoginDAOImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println("Starting LoginDAOImplTest...");
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("Finished LoginDAOImplTest.");
    }
    
    @BeforeEach
    public void setUp() {
        loginDAO = new LoginDAOImpl();
    }
    
    @AfterEach
    public void tearDown() {
        loginDAO = null;
    }

    @Test
    public void testAuthenticateUser_ValidCredentials() throws Exception {
        System.out.println("Test: authenticateUser  with valid credentials");

        // Arrange - use known valid credentials from your database
        String username = "ALim";  
        String password = "P@ssword"; 

        // Act
        boolean result = loginDAO.authenticateUser (username, password);

        // Assert
        assertTrue(result, "Expected authentication to succeed but it failed.");
        System.out.println("User  authenticated successfully.");
    }

    @Test
    public void testAuthenticateUser_InvalidCredentials() throws Exception {
        System.out.println("Test: authenticateUser  with invalid credentials");

        // Arrange
        String username = "Admin1";
        String password = "password123";

        // Act
        boolean result = loginDAO.authenticateUser (username, password);

        // Assert
        assertFalse(result, "Expected authentication to fail but it succeeded.");
    }
}
