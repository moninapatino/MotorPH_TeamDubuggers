package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_util.DatabaseConnect; // Import your DatabaseConnect class
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class EmployeeAttendanceDAOImpl implements EmployeeAttendanceDAO {
    
    // Method to establish a connection to the database
    private Connection connect() {
        return DatabaseConnect.getConnection(); // Use your existing method to get the connection
    }
    
    @Override
    public boolean logTimeIn(String employeeId, String date, String time) {
        Connection conn = null;
        PreparedStatement checkPst = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Connect to the database
            conn = connect();

            // 1. Check if employee already timed in today
            String checkSql = "SELECT COUNT(*) FROM attendance_record WHERE employee_id = ? AND date = ? AND time_in IS NOT NULL";
            checkPst = conn.prepareStatement(checkSql);
            checkPst.setString(1, employeeId);
            checkPst.setString(2, date);
            rs = checkPst.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Employee has already timed in today.");
                return false;
            }

            // 2. Insert new time-in record
            String insertSql = "INSERT INTO attendance_record (employee_id, date, time_in) VALUES (?, ?, ?)";
            pst = conn.prepareStatement(insertSql);
            pst.setString(1, employeeId);
            pst.setString(2, date);  // Format: yyyy-MM-dd
            pst.setString(3, time);  // Format: HH:mm:ss

            int rowsAffected = pst.executeUpdate();
            System.out.println("Time In - Rows affected: " + rowsAffected);

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("SQL Error in logTimeIn: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            // Close resources
            DatabaseConnect.closeResources(rs, checkPst, pst, conn);
        }
    }
    
    @Override
    public boolean logTimeOut(String employeeId, String date, String time) {
        Connection conn = null;
        PreparedStatement checkPst = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Establish the database connection
            conn = connect();
            String checkSql = "SELECT COUNT(*) FROM attendance_record WHERE employee_id = ? AND date = ? AND time_in IS NOT NULL AND time_out IS NULL";
            checkPst = conn.prepareStatement(checkSql);
            checkPst.setString(1, employeeId);
            checkPst.setString(2, date);
            rs = checkPst.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("No valid time-in record found for today, or already timed out");
                return false;
            }

            // 2. Update the latest matching record (optional ORDER BY for safety)
            String updateSql = "UPDATE attendance_record SET time_out = ? " +
                               "WHERE employee_id = ? AND date = ? AND time_out IS NULL " +
                               "ORDER BY time_in DESC LIMIT 1";

            pst = conn.prepareStatement(updateSql);
            pst.setString(1, time);
            pst.setString(2, employeeId);
            pst.setString(3, date);

            int rowsAffected = pst.executeUpdate();
            System.out.println("Time Out - Rows affected: " + rowsAffected);

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("SQL Error in logTimeOut: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            // Close resources
            DatabaseConnect.closeResources(rs, checkPst, pst, conn);
        }
    }

    @Override
    public void showAttendanceTable(DefaultTableModel attendanceTable) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Establish the database connection
            conn = connect();

            // SQL query to fetch attendance records
            String sql = "SELECT ar.attendance_id, ar.employee_id, ar.date, e.first_name, e.last_name, ar.time_in, ar.time_out, ar.status " +
                         "FROM attendance_record ar " +
                         "JOIN employee e ON ar.employee_id = e.employee_id";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            // Clear existing rows in the table model
            attendanceTable.setRowCount(0);

            // Process the result set and populate the table
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("attendance_id"));
                row.add(rs.getString("employee_id"));
                row.add(rs.getString("first_name"));
                row.add(rs.getString("last_name"));
                row.add(rs.getString("date"));
                row.add(rs.getString("time_in"));
                row.add(rs.getString("time_out"));
                row.add(rs.getString("status"));

                attendanceTable.addRow(row);
            }
        } catch (SQLException ex) {
            // Handle SQL exceptions
            JOptionPane.showMessageDialog(null, "Error fetching attendance records: " + ex.getMessage());
        } finally {
            // Close resources
            DatabaseConnect.closeResources(rs, pst, conn);
        }
    }
    
    @Override
    public ArrayList<Vector<String>> getAttendanceRecordsByEmployeeId(String employeeId) {
        ArrayList<Vector<String>> timeLog = new ArrayList<>();

        String sql = """
            SELECT 
                e.first_name, 
                e.last_name, 
                ar.date, 
                ar.time_in, 
                ar.time_out
            FROM attendance_record ar
            JOIN employee e ON ar.employee_id = e.employee_id
            WHERE ar.employee_id = ?
        """;

        try (
            Connection conn = connect();
            PreparedStatement pst = conn.prepareStatement(sql)
        ) {
            pst.setString(1, employeeId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Vector<String> row = new Vector<>();
                    row.add(rs.getString("first_name"));
                    row.add(rs.getString("last_name"));
                    row.add(rs.getString("date"));
                    row.add(rs.getString("time_in"));
                    row.add(rs.getString("time_out"));
                    timeLog.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // You can replace this with a logger
        }

        return timeLog;
    }

}
