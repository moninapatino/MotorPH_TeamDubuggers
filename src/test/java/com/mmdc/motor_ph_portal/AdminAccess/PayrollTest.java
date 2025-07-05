package com.mmdc.motor_ph_portal.AdminAccess;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

public class PayrollTest {

    private Payroll payroll;

    @BeforeEach
    public void setUp() {
        payroll = new Payroll();
    }

    @AfterEach
    public void tearDown() {
        payroll.dispose(); // Clean up GUI window if open
    }
    
    @Test
    public void testCalculateNetpay() {
        // Simulate input
        JTextField employeeIdField = payroll.getEmployeeIdField();
        JComboBox<String> monthComboBox = payroll.getMonthComboBox();
        JTextField hoursWorkedField = payroll.getHoursWorkedField();
        JButton calculateBtn = payroll.getCalculateNetpayButton();
        JTextField netpayField = payroll.getNetpayField();

        assertNotNull(employeeIdField);
        assertNotNull(monthComboBox);
        assertNotNull(hoursWorkedField);
        assertNotNull(calculateBtn);
        assertNotNull(netpayField);

        // Fill form
        employeeIdField.setText("10006");
        monthComboBox.setSelectedItem("September");

        // Click Calculate
        calculateBtn.doClick();

        // Wait to allow action to complete
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Validate result
        String netpayText = netpayField.getText().trim();
        assertFalse(netpayText.isEmpty(), "Netpay should be calculated and displayed");
    }

    @Test
    public void testGeneratePayslip() {
        // Fill required fields
        JTextField employeeIdField = payroll.getEmployeeIdField();
        JComboBox<String> monthComboBox = payroll.getMonthComboBox();
        JButton generatePayslipBtn = payroll.getGeneratePayslipButton();

        assertNotNull(employeeIdField);
        assertNotNull(monthComboBox);
        assertNotNull(generatePayslipBtn);

        employeeIdField.setText("10006");
        monthComboBox.setSelectedItem("September");

        // Click the generate button
        generatePayslipBtn.doClick();

        // Manual or visual validation (optional)
        System.out.println("Payslip generation triggered for Employee ID 10006, September");
    }


   
}
