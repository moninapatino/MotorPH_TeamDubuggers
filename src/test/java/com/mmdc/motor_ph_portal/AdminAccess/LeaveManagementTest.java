package com.mmdc.motor_ph_portal.AdminAccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class LeaveManagementTest {

    private LeaveManagement adminLeave;

    @BeforeEach
    public void setUp() {
        adminLeave = new LeaveManagement();
        adminLeave.setVisible(true);
    }

    @Test
    public void testApproveLeaveRequestAsAdmin() {
        JTable leaveTable = adminLeave.getLeaveTable();
        assertNotNull(leaveTable, "Leave table should not be null");

        int targetRow = -1;
        for (int i = 0; i < leaveTable.getRowCount(); i++) {
            String leaveId = leaveTable.getValueAt(i, 0).toString();
            if ("10046".equals(leaveId)) {
                targetRow = i;
                break;
            }
        }

        assertTrue(targetRow != -1, "Leave ID 10046 must exist in the table");

        // Select the row
        leaveTable.setRowSelectionInterval(targetRow, targetRow);

        // Simulate radio button selection
        adminLeave.getApproveRadioButton().setSelected(true);

        // Simulate button click
        adminLeave.getUpdateButton().doClick();

        // Small delay to wait for update to reflect
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Recheck the status in the table
        String updatedStatus = leaveTable.getValueAt(targetRow, 7).toString();
        assertEquals("Approved", updatedStatus, "Leave status should be updated to 'Approved'");
    }

    @Test
    public void testRejectLeaveRequestAsAdmin() {
        JTable leaveTable = adminLeave.getLeaveTable();
        assertNotNull(leaveTable);

        // Find row with Leave ID 10043
        int targetRow = -1;
        for (int i = 0; i < leaveTable.getRowCount(); i++) {
            String leaveId = leaveTable.getValueAt(i, 0).toString();
            if ("10043".equals(leaveId)) {
                targetRow = i;
                break;
            }
        }

        assertTrue(targetRow != -1, "Leave ID 10043 must exist in the table");

        leaveTable.setRowSelectionInterval(targetRow, targetRow);
        adminLeave.getRejectRadioButton().setSelected(true);
        adminLeave.getUpdateButton().doClick();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String updatedStatus = leaveTable.getValueAt(targetRow, 7).toString();
        assertEquals("Rejected", updatedStatus, "Leave status should be updated to 'Rejected'");
    }

    @Test
    public void testDeleteLeaveRecordAsAdmin() {
        JTable leaveTable = adminLeave.getLeaveTable();
        assertNotNull(leaveTable);

        int targetRow = -1;
        for (int i = 0; i < leaveTable.getRowCount(); i++) {
            String leaveId = leaveTable.getValueAt(i, 0).toString();
            if ("10043".equals(leaveId)) {
                targetRow = i;
                break;
            }
        }

        assertTrue(targetRow != -1, "Leave ID 10043 must exist in the table to delete");

        leaveTable.setRowSelectionInterval(targetRow, targetRow);
        adminLeave.getDeleteButton().doClick();

        // Wait for UI refresh
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Check that the row is no longer in the table
        boolean recordStillExists = false;
        for (int i = 0; i < leaveTable.getRowCount(); i++) {
            if ("10043".equals(leaveTable.getValueAt(i, 0).toString())) {
                recordStillExists = true;
                break;
            }
        }

        assertFalse(recordStillExists, "Leave ID 10043 should be deleted from the table");
    }
}
