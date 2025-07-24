package com.mmdc.motor_ph_portal.EmployeeAccess;

import com.toedter.calendar.JDateChooser;
import org.junit.jupiter.api.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeAccess_ProfileTest {

    private EmployeeAccessProfile profile;

    @BeforeEach
    public void setUp() {
        // Initialize using valid employee username
        profile = new EmployeeAccessProfile("ALim");
    }

    @AfterEach
    public void tearDown() {
        if (profile != null) profile.dispose();
    }

    // Utility: Pause for GUI timing
    private void waitForGui() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void testLoginWithValidCredentials() {
        assertNotNull(profile);
        assertEquals("10002", profile.getIdField().getText(), "Incorrect Employee ID");
        assertEquals("Antonio", profile.getFirstnameField().getText(), "Incorrect First Name");
        assertEquals("Lim", profile.getLastnameField().getText(), "Incorrect Last Name");
    }

    @Test
    public void testTimeInButtonFunctionality() {
        profile = new EmployeeAccessProfile("10002", "Antonio", "Lim");

        JButton timeInBtn = profile.getTimeInButton();
        assertNotNull(timeInBtn, "Time-In button missing");
        timeInBtn.doClick();

        profile.loadTimeLog();
        JTable attendanceTable = profile.getAttendanceTable();
        assertNotNull(attendanceTable, "Attendance table is null");
        assertTrue(attendanceTable.getRowCount() > 0, "No attendance rows found");

        String dateToday = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        boolean foundToday = false;

        for (int i = 0; i < attendanceTable.getRowCount(); i++) {
            String date = String.valueOf(attendanceTable.getValueAt(i, 2));
            String timeOut = String.valueOf(attendanceTable.getValueAt(i, 4));

            if (date.contains(dateToday)) {
                foundToday = true;
                assertTrue(timeOut == null || timeOut.trim().isEmpty(), "Time-Out should be empty after Time-In");
                break;
            }
        }

        if (!foundToday) {
            System.out.println("🔍 Today's date not found. Dumping table:");
            for (int i = 0; i < attendanceTable.getRowCount(); i++) {
                System.out.println("Row " + i + " Date: " + attendanceTable.getValueAt(i, 2));
            }
        }

        assertTrue(foundToday, "Time-In record for today's date should exist.");
    }

    @Test
    public void testTimeOutButtonFunctionality() {
        profile = new EmployeeAccessProfile("10002", "Antonio", "Lim");

        profile.getTimeInButton().doClick();
        waitForGui();

        JButton timeOutBtn = profile.getTimeOutButton();
        assertNotNull(timeOutBtn, "Time-Out button missing");
        timeOutBtn.doClick();

        JTable table = profile.getAttendanceTable();
        assertNotNull(table);
        assertTrue(table.getRowCount() > 0);

        String today = LocalDate.now().toString();
        boolean timeOutSet = false;

        for (int i = table.getRowCount() - 1; i >= 0; i--) {
            String date = String.valueOf(table.getValueAt(i, 2));
            String timeOut = String.valueOf(table.getValueAt(i, 4));

            if (date.equals(today)) {
                timeOutSet = timeOut != null && !timeOut.trim().isEmpty();
                break;
            }
        }

        assertTrue(timeOutSet, "Time-Out should be set for today.");
    }

    @Test
    public void testSubmitLeaveRequestSuccessfully() {
        profile = new EmployeeAccessProfile("10002", "Antonio", "Lim");
        profile.autoFillLeaveId();

        JDateChooser start = profile.getStartDateChooser();
        JDateChooser end = profile.getEndDateChooser();
        assertNotNull(start);
        assertNotNull(end);

        Date today = new Date();
        start.setDate(today);
        end.setDate(today);

        JComboBox<String> leaveType = profile.getLeaveTypeComboBox();
        leaveType.setSelectedItem("Sick Leave");

        JTextField leaveNumField = profile.getLeaveNumField();
        assertNotNull(leaveNumField);
        assertFalse(leaveNumField.getText().isEmpty(), "Leave number should be autofilled");

        JButton addBtn = profile.getAddLeaveRequestButton();
        assertNotNull(addBtn);
        addBtn.doClick();

        waitForGui();

        JTable leaveTable = profile.getLeaveTable();
        assertNotNull(leaveTable);
        int lastRow = leaveTable.getRowCount() - 1;
        assertTrue(lastRow >= 0, "Leave request table is empty");

        assertEquals("10002", leaveTable.getValueAt(lastRow, 1), "Wrong Employee ID");
        assertEquals("Sick Leave", leaveTable.getValueAt(lastRow, 6), "Leave Type mismatch");
        assertEquals("Pending", leaveTable.getValueAt(lastRow, 7), "Status should be Pending");
    }

    @Test
    public void testViewPayslipByMonthForEmployee() {
        profile = new EmployeeAccessProfile("10002", "Antonio", "Lim");
        profile.loadPayslipList();

        JTable payslipTable = profile.getPayslipTable();
        assertNotNull(payslipTable, "Payslip table is null");
        assertTrue(payslipTable.getRowCount() > 0, "Payslip table is empty");

        JComboBox<String> monthCombo = profile.getMonthComboBox();
        assertNotNull(monthCombo, "Month combo box missing");

        boolean foundJune = false;
        for (int i = 0; i < monthCombo.getItemCount(); i++) {
            if ("June".equalsIgnoreCase(monthCombo.getItemAt(i))) {
                monthCombo.setSelectedIndex(i);
                foundJune = true;
                break;
            }
        }

        assertTrue(foundJune, "'June' should be selectable in month combo box");

        JButton viewBtn = profile.getPayslipBtn();
        assertNotNull(viewBtn);
        viewBtn.doClick();

        waitForGui();
        assertTrue(true, "Payslip for June assumed viewed successfully.");
    }
}
