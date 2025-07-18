package com.motorph.dao.impl;

import com.motorph.model.UserAccount;
import com.motorph.util.DatabaseConnectionManager;
import java.sql.*;

public class UserDAOImpl {
    private final DatabaseConnectionManager dbManager;

    public UserDAOImpl(DatabaseConnectionManager dbManager) {
        this.dbManager = dbManager;
    }

    // Find user by username
    public UserAccount findByUsername(String username) {
        String sql = "SELECT e.employee_id, e.first_name, e.last_name, p.username, p.password, ur.role_name " +
            "FROM employee e " +
            "JOIN position p ON e.employee_id = p.employee_id " +
            "JOIN user_role ur ON p.position_id = ur.position_id " +
            "WHERE p.username = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new UserAccount(
                        rs.getString("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role_name")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Find user by email
    public UserAccount findByEmail(String email) {
        String sql = "SELECT e.employee_id, e.first_name, e.last_name, p.username, p.password, ur.role_name " +
            "FROM employee e " +
            "JOIN position p ON e.employee_id = p.employee_id " +
            "JOIN user_role ur ON p.position_id = ur.position_id " +
            "WHERE e.email = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new UserAccount(
                        rs.getString("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role_name")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Find user by employee ID
    public UserAccount findById(String employeeId) {
        String sql = "SELECT e.employee_id, e.first_name, e.last_name, p.username, p.password, ur.role_name " +
            "FROM employee e " +
            "JOIN position p ON e.employee_id = p.employee_id " +
            "JOIN user_role ur ON p.position_id = ur.position_id " +
            "WHERE e.employee_id = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, employeeId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new UserAccount(
                        rs.getString("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role_name")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update user password
    public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE position SET password = ? WHERE username = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, newPassword);
            pst.setString(2, username);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
