package com.motorph.dao.impl;

import com.motorph.model.UserAccount;
import com.motorph.util.DatabaseConnectionManager;
import java.sql.*;

public class UserAccountDAOImpl {
    private final DatabaseConnectionManager dbManager;

    public UserAccountDAOImpl(DatabaseConnectionManager dbManager) {
        this.dbManager = dbManager;
    }

    // Find user account by username and password
    public UserAccount findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT e.employee_id, e.first_name, e.last_name, ur.role_name " +
            "FROM employee e " +
            "JOIN position p ON e.employee_id = p.employee_id " +
            "JOIN user_role ur ON p.position_id = ur.position_id " +
            "WHERE p.username = ? AND p.password = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new UserAccount(
                        rs.getString("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        username,
                        password,
                        rs.getString("role_name")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
