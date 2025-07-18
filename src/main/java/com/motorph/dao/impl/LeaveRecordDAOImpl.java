package com.motorph.dao.impl;

import com.mmdc.motor_ph_portal.LeaveRecord;
import com.motorph.util.DatabaseConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveRecordDAOImpl {
    private final DatabaseConnectionManager dbManager;

    public LeaveRecordDAOImpl(DatabaseConnectionManager dbManager) {
        this.dbManager = dbManager;
    }

    // Find leave by ID
    public LeaveRecord getLeaveById(int leaveId) {
        String sql = "SELECT * FROM leave_record WHERE leave_id = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, leaveId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToLeaveRecord(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Find leaves by employee ID
    public List<LeaveRecord> getLeavesByEmployeeId(String employeeId) {
        List<LeaveRecord> leaves = new ArrayList<>();
        String sql = "SELECT * FROM leave_record WHERE employee_id = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, employeeId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    leaves.add(mapResultSetToLeaveRecord(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaves;
    }

    // Add a new leave record
    public boolean addLeave(LeaveRecord leave) {
        String sql = "INSERT INTO leave_record (employee_id, first_name, last_name, start_date, end_date, leave_type, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, leave.getEmployeeId());
            pst.setString(2, leave.getFirstName());
            pst.setString(3, leave.getLastName());
            pst.setString(4, leave.getStartDate());
            pst.setString(5, leave.getEndDate());
            pst.setString(6, leave.getLeaveType());
            pst.setString(7, leave.getStatus());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update leave status
    public boolean updateLeaveStatus(int leaveId, String status) {
        String sql = "UPDATE leave_record SET status = ? WHERE leave_id = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, status);
            pst.setInt(2, leaveId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a leave record
    public boolean deleteLeave(int leaveId) {
        String sql = "DELETE FROM leave_record WHERE leave_id = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, leaveId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Map ResultSet to LeaveRecord object
    private LeaveRecord mapResultSetToLeaveRecord(ResultSet rs) throws SQLException {
        return new LeaveRecord(
            rs.getInt("leave_id"),
            rs.getString("employee_id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("start_date"),
            rs.getString("end_date"),
            rs.getString("leave_type"),
            rs.getString("status")
        );
    }
}
