package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_util.DatabaseConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public boolean authenticateUser (String username, String password) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            DatabaseConnect dbConnect = new DatabaseConnect() {};
            conn = dbConnect.getConnection();

            String sql = "SELECT e.employee_id, e.first_name, e.last_name, ur.role_name " +
                         "FROM employee e " +
                         "JOIN position p ON e.employee_id = p.employee_id " +
                         "JOIN user_role ur ON p.position_id = ur.position_id " +
                         "WHERE p.username = ? AND p.password = ?";

            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                return true; // User authenticated
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false; // Authentication failed
    }
}
