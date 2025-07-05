package com.mmdc.motor_ph_portal.AdminAccess;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.mmdc.motor_ph_portal.AdminAccess.LeaveManagement;
import javax.swing.JTextField;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Leave Management functionality in the Admin Portal.
 */
public class LeaveManagementTest {

    private LeaveManagement adminLeave;

    @BeforeAll
    public static void setUpClass() {
        // Setup resources shared across all tests if needed
    }

    @AfterAll
    public static void tearDownClass() {
        // Cleanup resources shared across all tests if needed
    }

    @BeforeEach
    public void setUp() {
        adminLeave = new LeaveManagement(); // initialize before each test
    }

    @AfterEach
    public void tearDown() {
        adminLeave = null;
    }

         @Test
    public void testApproveLeaveRequestAsAdmin() {
        JTable leaveTable = adminLeave.getLeaveTable();
        assertNotNull(leaveTable, "Leave table should not be null");

        int rowCount = leaveTable.getRowCount();
        assertTrue(rowCount > 0, "Leave table must have at least one row");

        // Get the last row
        int lastRow = rowCount - 1;
        Object leaveIdObj = leaveTable.getValueAt(lastRow, 0); // Column 0 = Leave ID
        assertNotNull(leaveIdObj, "Leave ID should not be null");

        String leaveId = leaveIdObj.toString();
        assertEquals("50042", leaveId, "Expected Leave ID 50042 in the last row");

        // 🔘 Select and click the last row to trigger the auto-fill logic
        leaveTable.setRowSelectionInterval(lastRow, lastRow);
        leaveTable.requestFocus();
        leaveTable.editCellAt(lastRow, 0); // optional, if cell editing is part of the logic
        leaveTable.dispatchEvent(new java.awt.event.MouseEvent(
            leaveTable,
            java.awt.event.MouseEvent.MOUSE_CLICKED,
            System.currentTimeMillis(),
            0,
            10, 10, // x, y inside cell area
            1,
            false
        ));

        // Wait briefly for GUI to update fields
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // ✅ Verify the Leave Number was populated
        JTextField leaveNumField = adminLeave.getLeaveNumberField();
        assertNotNull(leaveNumField, "Leave Number field should not be null");
        assertEquals("50042", leaveNumField.getText().trim(), "Leave Number field should be auto-filled");

        // 🔘 Click "Approve" radio button
        JRadioButton approveRadio = adminLeave.getApproveRadioButton();
        assertNotNull(approveRadio);
        approveRadio.setSelected(true);

        // 🔘 Click "Update" button
        JButton updateBtn = adminLeave.getUpdateButton();
        assertNotNull(updateBtn);
        updateBtn.doClick();

        // Wait for GUI to process update
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // ✅ Check that the leave was approved
        String updatedStatus = leaveTable.getValueAt(lastRow, 7).toString(); // Column 7 = Status
        assertEquals("Approved", updatedStatus, "Leave status should be updated to Approved");
    }


        @Test
    public void testDeleteLeaveRequestAsAdmin() {
        LeaveManagement adminLeave = new LeaveManagement();

        JTable leaveTable = adminLeave.getLeaveTable();
        assertNotNull(leaveTable, "Leave table should not be null");
        assertTrue(leaveTable.getRowCount() > 0, "Leave table should have at least one row");

        // Find the row with Leave ID: 50041
        int rowToDelete = -1;
        for (int i = 0; i < leaveTable.getRowCount(); i++) {
            Object leaveId = leaveTable.getValueAt(i, 0); // Column 0 = Leave ID
            if (leaveId != null && leaveId.toString().equals("50041")) {
                rowToDelete = i;
                break;
            }
        }

        assertTrue(rowToDelete != -1, "Leave ID 50041 should exist in the table");

        // Select and simulate mouse click on the row
        leaveTable.setRowSelectionInterval(rowToDelete, rowToDelete);
        leaveTable.requestFocus();
        leaveTable.dispatchEvent(new java.awt.event.MouseEvent(
            leaveTable,
            java.awt.event.MouseEvent.MOUSE_CLICKED,
            System.currentTimeMillis(),
            0,
            10, 10, // x and y inside cell
            1,
            false
        ));

        // Click the Delete button
        JButton deleteBtn = adminLeave.getDeleteButton();
        assertNotNull(deleteBtn, "Delete button should not be null");
        deleteBtn.doClick();

        // Wait for deletion processing
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Ensure Leave ID 50041 no longer exists
        boolean stillExists = false;
        for (int i = 0; i < leaveTable.getRowCount(); i++) {
            Object leaveId = leaveTable.getValueAt(i, 0);
            if (leaveId != null && leaveId.toString().equals("50041")) {
                stillExists = true;
                break;
            }
        }

        assertFalse(stillExists, "Leave ID 50041 should be deleted from the table");
    }

}
