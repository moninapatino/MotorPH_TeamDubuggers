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
    public void testGeneratePayslip() {
        // Fill required fields
        JTextField employeeIdField = payroll.getEmployeeId();
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
