package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_portal.LeaveRecord;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LeaveManagementDAOImplTest {

    private LeaveManagementDAOImpl leaveDAO;

    @BeforeAll
    public static void initAll() {
        System.out.println("Starting LeaveDAOImplTest...");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("Finished LeaveDAOImplTest.");
    }

    @BeforeEach
    public void setUp() {
        leaveDAO = new LeaveManagementDAOImpl();
    }

    @AfterEach
    public void tearDown() {
        leaveDAO = null;
    }

    @Test
    public void testUpdateLeaveRecord() {
        System.out.println("Test: updateLeaveRecord");

        
        LeaveRecord leave = new LeaveRecord(
            50046,
            "10009",
            "Rosie",
            "Atienza",
            "2025-07-01",
            "2025-07-05",
            "Vacation",
            "Approved"
        );

        // Act
        boolean result = leaveDAO.updateLeaveRecord(leave);

        // Assert
        assertTrue(result, "Expected updateLeaveRecord to return true.");
    }

    @Test
    public void testDeleteLeaveRecord() {
        System.out.println("Test: deleteLeaveRecord");

        // Arrange - Ensure leaveId 9999 exists in DB
        LeaveRecord leave = new LeaveRecord(
            50046,
            "10009",
            "Rosie",
            "Atienza",
            "2025-07-01",
            "2025-07-05",
            "Vacation",
            "Approved"
        );

        // Act
        boolean result = leaveDAO.deleteLeaveRecord(leave);

        // Assert
        assertTrue(result, "Expected deleteLeaveRecord to return true.");
    }

    @Test
    public void testUpdateLeaveRecord_InvalidId() {
        System.out.println("Test: updateLeaveRecord with invalid ID");

        LeaveRecord leave = new LeaveRecord(
            5046,
            "10009",
            "Rosie",
            "Atienza",
            "2025-07-01",
            "2025-07-05",
            "Vacation",
            "Approved"
        );

        boolean result = leaveDAO.updateLeaveRecord(leave);

        assertFalse(result, "Expected updateLeaveRecord to fail for invalid leaveId.");
    }

    @Test
    public void testDeleteLeaveRecord_InvalidId() {
        System.out.println("Test: deleteLeaveRecord with invalid ID");

        LeaveRecord leave = new LeaveRecord(
            5046,
            "10009",
            "Rosie",
            "Atienza",
            "2025-07-01",
            "2025-07-05",
            "Vacation",
            "Approved"
        );

        boolean result = leaveDAO.deleteLeaveRecord(leave);

        assertFalse(result, "Expected deleteLeaveRecord to fail for invalid leaveId.");
    }
}
