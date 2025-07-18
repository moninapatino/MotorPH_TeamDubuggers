package com.motorph.dao.impl;

import com.motorph.util.DatabaseConnectionManager;
import java.sql.*;

public class PasswordRecoveryDAOImpl {
    private final DatabaseConnectionManager dbManager;

    public PasswordRecoveryDAOImpl(DatabaseConnectionManager dbManager) {
        this.dbManager = dbManager;
    }

    // Find employee ID by email
    public String findEmployeeIdByEmail(String email) {
        String sql = "SELECT employee_id FROM employee WHERE email = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("employee_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update password for an employee
    public boolean updatePassword(String employeeId, String newPassword) {
        String sql = "UPDATE position SET password = ? WHERE employee_id = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, newPassword);
            pst.setString(2, employeeId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Verify if email exists
    public boolean verifyEmail(String email) {
        String sql = "SELECT COUNT(*) FROM employee WHERE email = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
