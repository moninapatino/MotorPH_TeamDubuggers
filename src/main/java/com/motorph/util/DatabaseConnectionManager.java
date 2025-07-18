package com.motorph.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class DatabaseConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/payrollsystem_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "enaxor";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Database connection failed: " + ex.getMessage());
            return null;
        }
    }
}
