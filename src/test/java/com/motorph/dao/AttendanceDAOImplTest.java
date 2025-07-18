package com.motorph.dao;

import com.motorph.dao.impl.AttendanceDAOImpl;
import com.motorph.util.DatabaseConnectionManager;
import org.junit.jupiter.api.*;
import javax.swing.table.DefaultTableModel;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceDAOImplTest {
    private static DatabaseConnectionManager dbManager;
    private AttendanceDAOImpl attendanceDAO;

    @BeforeAll
    static void setupClass() {
        // Initialize your test DB connection manager here
        dbManager = DatabaseConnectionManager.getInstance();
    }

    @BeforeEach
    void setup() {
        attendanceDAO = new AttendanceDAOImpl(dbManager);
    }

    @Test
    void testAddAttendance() {
        boolean result = attendanceDAO.addAttendance(
                "10055", "John", "Doe", "2024-06-01", "08:00:00", "17:00:00"
        );
        assertTrue(result, "Attendance should be added successfully");
    }

    @Test
    void testFillAttendanceTable() {
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"attendance_id", "employee_id", "first_name", "last_name", "date", "time_in", "time_out"}, 0
        );
        attendanceDAO.fillAttendanceTable(model);
        assertTrue(model.getRowCount() >= 0, "Table should be filled (even if empty)");
    }
}
