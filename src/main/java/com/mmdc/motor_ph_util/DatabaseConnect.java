package com.mmdc.motor_ph_util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DatabaseConnect { 

    private static final String url = "jdbc:mysql://localhost:3306/payrollsystem_db?useSSL=false";
    private static final String user = "root";
    private static final String password = "enaxor";

   
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Trying to connect");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Established Successfully");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "MySQL Driver not found." + ex.getMessage());
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DatabaseConnect.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            JOptionPane.showMessageDialog(null, "Unable to make connection with DB: " + ex.getMessage());
        }
        return conn;
    }

    public static void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception ex) {
                    Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, "Error closing resource", ex);
                }
            }
        }
    }
}
