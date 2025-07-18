package com.motorph.dao.impl;

import com.motorph.util.DatabaseConnectionManager;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class AttendanceDAOImpl {
    private final DatabaseConnectionManager dbManager;

    public AttendanceDAOImpl(DatabaseConnectionManager dbManager) {
        this.dbManager = dbManager;
    }

    // Fill a DefaultTableModel with all attendance records
    public void fillAttendanceTable(DefaultTableModel model) {
        String sql = "SELECT attendance_id, employee_id, first_name, last_name, date, time_in, time_out FROM attendance";
        try (Connection conn = dbManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("attendance_id"),
                    rs.getString("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("date"),
                    rs.getString("time_in"),
                    rs.getString("time_out")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a new attendance record
    public boolean addAttendance(String employeeId, String firstName, String lastName, String date, String timeIn, String timeOut) {
        String sql = "INSERT INTO attendance (employee_id, first_name, last_name, date, time_in, time_out) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, employeeId);
            pst.setString(2, firstName);
            pst.setString(3, lastName);
            pst.setString(4, date);
            pst.setString(5, timeIn);
            pst.setString(6, timeOut);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update time_out for an attendance record (by attendance_id)
    public boolean updateTimeOut(int attendanceId, String timeOut) {
        String sql = "UPDATE attendance SET time_out = ? WHERE attendance_id = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, timeOut);
            pst.setInt(2, attendanceId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an attendance record
    public boolean deleteAttendance(int attendanceId) {
        String sql = "DELETE FROM attendance WHERE attendance_id = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, attendanceId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
