package com.motorph.dao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.motorph.model.LeaveRecord;

class LeaveRecordDAOTest {
    private final LeaveRecordDAO dao = new LeaveRecordDAOImpl();

    @Test
    void testGetLeaveById_notFound() {
        LeaveRecord leave = dao.getLeaveById(-1);
        assertNull(leave);
    }

    @Test
    void testGetLeavesByEmployeeId_empty() {
        List<LeaveRecord> leaves = dao.getLeavesByEmployeeId("nonexistent");
        assertTrue(leaves.isEmpty());
    }
}
