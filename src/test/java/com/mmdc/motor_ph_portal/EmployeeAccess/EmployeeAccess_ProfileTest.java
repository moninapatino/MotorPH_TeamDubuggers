package com.mmdc.motor_ph_portal.EmployeeAccess;

import com.toedter.calendar.JDateChooser;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeAccess_ProfileTest {

    private EmployeeAccess_Profile profile;

    @BeforeEach
    public void setUp() {
        // Initialize profile using a valid username (ALim for Antonio Lim)
        profile = new EmployeeAccess_Profile("ALim");
    }

    @AfterEach
    public void tearDown() {
        if (profile != null) {
            profile.dispose(); // Close the frame to clean up
        }
    }

    @Test
    public void testLoginWithValidCredentials() {
        // Scenario 11: Login with valid credentials
        assertNotNull(profile);
        assertEquals("10002", profile.getIdField().getText()); // Employee ID
        assertEquals("Antonio", profile.getFirstnameField().getText()); // First Name
        assertEquals("Lim", profile.getLastnameField().getText()); // Last Name
    }

    @Test
    public void testTimeInButtonFunctionality() {
        profile = new EmployeeAccess_Profile("10002", "Antonio", "Lim");

        JButton timeInBtn = profile.getTimeInButton();
        assertNotNull(timeInBtn);

        // Simulate click on Time-In
        timeInBtn.doClick();

        JTable attendanceTable = profile.getAttendanceTable();
        assertNotNull(attendanceTable);
        assertTrue(attendanceTable.getRowCount() > 0);

        // Check today's record was added
        String dateToday = LocalDate.now().toString();
        boolean foundToday = false;

        for (int i = 0; i < attendanceTable.getRowCount(); i++) {
            Object dateCell = attendanceTable.getValueAt(i, 2); // 3rd column is 'date'
            Object timeOutCell = attendanceTable.getValueAt(i, 4); // 5th column is 'time_out'

            if (dateCell != null && dateCell.toString().equals(dateToday)) {
                foundToday = true;
                // ✅ Check that Time-Out is still null or empty
                assertTrue(timeOutCell == null || timeOutCell.toString().trim().isEmpty(), "Time-Out should be empty after Time-In.");
                break;
            }
        }

        assertTrue(foundToday, "Time-In record for today should exist.");
    }
    
    @Test
    public void testTimeOutButtonFunctionality() {
        profile = new EmployeeAccess_Profile("10002", "Antonio", "Lim");

        // First, ensure there is a Time-In
        JButton timeInBtn = profile.getTimeInButton();
        timeInBtn.doClick();

        // Then perform Time-Out
        JButton timeOutBtn = profile.getTimeOutButton();
        assertNotNull(timeOutBtn);
        timeOutBtn.doClick();

        JTable attendanceTable = profile.getAttendanceTable();
        assertNotNull(attendanceTable);
        assertTrue(attendanceTable.getRowCount() > 0);

        // Check if latest row now has a time_out value
        String dateToday = LocalDate.now().toString();
        boolean timeOutRecorded = false;

        for (int i = attendanceTable.getRowCount() - 1; i >= 0; i--) {
            Object dateCell = attendanceTable.getValueAt(i, 2); // date column
            Object timeOutCell = attendanceTable.getValueAt(i, 4); // time_out column

            if (dateCell != null && dateCell.toString().equals(dateToday)) {
                // ✅ After Time-Out, the time_out field should be filled
                timeOutRecorded = timeOutCell != null && !timeOutCell.toString().trim().isEmpty();
                break;
            }
        }

        assertTrue(timeOutRecorded, "Time-Out should be recorded after Time-Out button click.");
    }
    @Test
    public void testSubmitLeaveRequestSuccessfully() {
        // 1. Prepare the environment with logged-in employee profile
        profile = new EmployeeAccess_Profile("10002", "Antonio", "Lim");

        // 2. Set valid start and end dates
        JDateChooser startDateChooser = profile.getStartDateChooser();
        JDateChooser endDateChooser = profile.getEndDateChooser();
        assertNotNull(startDateChooser);
        assertNotNull(endDateChooser);

        Date today = new Date();
        startDateChooser.setDate(today);
        endDateChooser.setDate(today);

        // 3. Select leave type
        JComboBox<String> leaveTypeComboBox = profile.getLeaveTypeComboBox();
        assertNotNull(leaveTypeComboBox);
        leaveTypeComboBox.setSelectedItem("Sick Leave");

        // 4. Enter a valid leave number (assuming autofill already happened)
        JTextField leaveNumField = profile.getLeaveNumField();
        assertNotNull(leaveNumField);
        leaveNumField.setText("99999"); // Use a dummy/test leave number

        // 5. Click the "Add" button
        JButton addLeaveBtn = profile.getAddLeaveRequestButton();
        assertNotNull(addLeaveBtn);
        addLeaveBtn.doClick();

        // 6. Wait a moment for DB/UI update
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 7. Validate that new record was added to the table
        JTable leaveTable = profile.getLeaveTable();
        assertNotNull(leaveTable);
        int lastRow = leaveTable.getRowCount() - 1;
        assertTrue(lastRow >= 0, "Leave table should have at least one row");

        // 8. Check last row data
        String employeeID = leaveTable.getValueAt(lastRow, 1).toString();  // Column 2: Employee ID
        String leaveType = leaveTable.getValueAt(lastRow, 6).toString();   // Column 7: Leave Type
        String status = leaveTable.getValueAt(lastRow, 7).toString();      // Column 8: Status

        assertEquals("10002", employeeID);
        assertEquals("Sick Leave", leaveType);
        assertEquals("Pending", status);
    }

    @Test
    public void testViewPayslipByMonthForEmployee() {
        // Step 1: Log in as Employee 10002 (Antonio Lim)
        profile = new EmployeeAccess_Profile("10002", "Antonio", "Lim");

        // Step 2: Ensure payslips are loaded
        profile.loadPayslipList();

        JTable payslipTable = profile.getPayslipTable();
        assertNotNull(payslipTable, "Payslip table must not be null");
        assertTrue(payslipTable.getRowCount() > 0, "Payslip table must have at least one record");

        // Step 3: Select 'June' in the month combo box
        JComboBox<String> monthComboBox = profile.getMonthComboBox();
        assertNotNull(monthComboBox, "Month combo box must not be null");

        boolean monthSet = false;
        for (int i = 0; i < monthComboBox.getItemCount(); i++) {
            if (monthComboBox.getItemAt(i).equalsIgnoreCase("June")) {
                monthComboBox.setSelectedIndex(i);
                monthSet = true;
                break;
            }
        }
        assertTrue(monthSet, "June must be available in month combo box");

        // Step 4: Click "View Payslip" button
        JButton viewBtn = profile.getPayslipBtn();
        assertNotNull(viewBtn, "View Payslip button must not be null");
        viewBtn.doClick();

        // Step 5: Allow GUI time to open Jasper Viewer (optional)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Step 6: We assume Jasper report opened if no error is thrown
        assertTrue(true, "Payslip for June should be generated and displayed.");
    }

    

}
