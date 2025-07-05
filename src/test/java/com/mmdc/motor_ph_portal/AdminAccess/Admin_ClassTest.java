/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mmdc.motor_ph_portal.AdminAccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author user
 */
public class Admin_ClassTest {
    
    public Admin_ClassTest() {
    }
     private Admin_Class admin;

    @BeforeEach
    void setUp() {
        admin = new Admin_Class(
            "10001", "MotorPH", "Admin", "admin@email.com", "1990-01-01",
            "ADDR01", "Main St", "Barangay Uno", "City", "Province",
            "1234", "09123456789", "SSS001",
            "PH001", "TIN001", "PAG001",
            "Admin", "admin"
        );
    }

    // Test Scenario for #1 - Junit test by Maricon C.
    @Test 
    void testSuccessfulLogin() {
        admin.login("Admin", "admin");
        assertTrue(admin.isLoggedIn()); // ✅ Passed if login is successful
    }

    // Test Scenario for #2 - Junit test by Maricon C.
    @Test
    void testInvalidLoginFails() {
        admin.login("wrong", "user");
        assertFalse(admin.isLoggedIn());
    }

}
