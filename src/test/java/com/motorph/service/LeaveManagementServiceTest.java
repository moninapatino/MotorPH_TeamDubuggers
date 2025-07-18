package com.motorph.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.motorph.model.LeaveRecord;

class LeaveManagementServiceTest {
    private final LeaveManagementService service = new LeaveManagementServiceImpl();

    @Test
    void testGetLeaveById_notFound() {
        LeaveRecord leave = service.getLeaveById(-1);
        assertNull(leave);
    }

    @Test
    void testGetLeavesByEmployeeId_empty() {
        List<LeaveRecord> leaves = service.getLeavesByEmployeeId("nonexistent");
        assertTrue(leaves.isEmpty());
    }
}
