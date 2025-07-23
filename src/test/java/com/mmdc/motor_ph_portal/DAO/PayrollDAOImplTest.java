/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_portal.AdminAccess.PayrollCalculation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author user
 */
public class PayrollDAOImplTest {
    
    public PayrollDAOImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getPayrollDetails method, of class PayrollDAOImpl.
     */
    @Test
    public void testGetPayrollDetails() {
        System.out.println("getPayrollDetails");
        String employeeId = "";
        int monthNumber = 0;
        PayrollDAOImpl instance = new PayrollDAOImpl();
        PayrollCalculation expResult = null;
        PayrollCalculation result = instance.getPayrollDetails(employeeId, monthNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
