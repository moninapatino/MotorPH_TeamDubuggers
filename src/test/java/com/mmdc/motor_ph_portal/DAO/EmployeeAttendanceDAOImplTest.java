package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_util.DatabaseConnect;
import org.junit.jupiter.api.*;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeAttendanceDAOImplTest {

    private EmployeeAttendanceDAOImpl attendanceDAO;

    @BeforeEach
    public void setUp() {
        attendanceDAO = new EmployeeAttendanceDAOImpl();
    }

    @Test
    public void testLogTimeIn_Success() throws SQLException {
        String employeeId = "10001";
        String date = "2025-07-24";
        String time = "08:00:00";

        // Clean up previous records to ensure a fresh test
        clearAttendanceRecord(employeeId, date);

        boolean result = attendanceDAO.logTimeIn(employeeId, date, time);
        assertTrue(result, "Expected time-in to succeed.");
    }

    @Test
    public void testLogTimeIn_AlreadyTimedIn() throws SQLException {
        String employeeId = "10001";
        String date = "2025-07-24";
        String time = "08:00:00";

        clearAttendanceRecord(employeeId, date);
        attendanceDAO.logTimeIn(employeeId, date, time);
        boolean result = attendanceDAO.logTimeIn(employeeId, date, time);
        assertFalse(result, "Expected time-in to fail if already timed in.");
    }

    @Test
    public void testLogTimeOut_Success() throws SQLException {
        String employeeId = "10001";
        String date = "2025-07-24";
        String timeIn = "08:00:00";
        String timeOut = "17:00:00";

        clearAttendanceRecord(employeeId, date);
        attendanceDAO.logTimeIn(employeeId, date, timeIn);
        boolean result = attendanceDAO.logTimeOut(employeeId, date, timeOut);
        assertTrue(result, "Expected time-out to succeed.");
    }

    @Test
    public void testLogTimeOut_NoTimeIn() throws SQLException {
        String employeeId = "10002";
        String date = "2025-07-24";
        String time = "17:00:00";

        clearAttendanceRecord(employeeId, date);

        boolean result = attendanceDAO.logTimeOut(employeeId, date, time);
        assertFalse(result, "Expected time-out to fail when no time-in exists.");
    }

    @Test
    public void testShowAttendanceTable_NoException() {
        DefaultTableModel tableModel = new DefaultTableModel();
        assertDoesNotThrow(() -> attendanceDAO.showAttendanceTable(tableModel));
    }

    private void clearAttendanceRecord(String employeeId, String date) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DatabaseConnect.getConnection();
            String sql = "DELETE FROM attendance_record WHERE employee_id = ? AND date = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, employeeId);
            pst.setString(2, date);
            pst.executeUpdate();
        } finally {
            DatabaseConnect.closeResources(null, pst, conn);
        }
    }
}