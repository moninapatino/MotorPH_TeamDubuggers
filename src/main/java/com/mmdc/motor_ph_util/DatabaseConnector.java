
package com.mmdc.motor_ph_util;
import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import com.mmdc.motor_ph_portal.AdminAccess.TimeLogEntry;
import com.mmdc.motor_ph_portal.LeaveRecord;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

public class DatabaseConnector extends DatabaseConnect {
    Connection conn;
    PreparedStatement pst = null;
    ResultSet rs = null;
    DatabaseConnect dbConnect = new DatabaseConnect() {};
        
    @Override
    
    public Connection connect() {
        
        conn = dbConnect.connect();
        return conn;
    }
    
    public void show_table(DefaultTableModel attendanceTable) {
       
        try {
            // Establish the database connection
            conn = dbConnect.connect();

            // SQL query to fetch attendance records
            String sql = "SELECT * FROM public.attendance_record";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            // Clear existing rows in the table model
            attendanceTable.setRowCount(0);

            // Process the result set and populate the table
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("date"));
                row.add(rs.getString("employee_id"));
                row.add(rs.getString("first_name"));
                row.add(rs.getString("last_name"));
                row.add(rs.getString("clock_in"));
                row.add(rs.getString("clock_out"));
                row.add(rs.getString("late"));
                row.add(rs.getString("overtime"));
                row.add(rs.getString("hours_worked"));
                attendanceTable.addRow(row);
            }
        } catch (SQLException ex) {
            // Handle SQL exceptions
            JOptionPane.showMessageDialog(null, "Error fetching attendance records: " + ex.getMessage());
        } finally {
            // Close resources in the finally block
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error closing resources: " + ex.getMessage());
            }
            
        }
    }
     public ArrayList<TimeLogEntry> getTimeLog(String employeeId) {
        ArrayList<TimeLogEntry> timeLog = new ArrayList<>();
        String sql = "SELECT first_name, last_name, date, time_in, time_out FROM public.employeetime_log WHERE employee_id = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, employeeId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String startDate = rs.getString("start_date");
                String endDate = rs.getString("end_date");
                String timeIn = rs.getString("time_in");
                String timeOut = rs.getString("time_out");

                // Create a new TimeLogEntry object and add it to the list
                TimeLogEntry entry = new TimeLogEntry(firstName, lastName, startDate, endDate, timeIn, timeOut);
                timeLog.add(entry);
            }
        } catch (SQLException ex) {
            System.err.println("Error retrieving time log: " + ex.getMessage());
        }

        return timeLog;
    }

    // Method to close the database connection
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.err.println("Error closing connection: " + ex.getMessage());
        }
    }
    
    public boolean logTimeIn(String employeeId, String firstName, String lastName, String date, String time) {

    try {
        // Establish the database connection
        conn = dbConnect.connect();
        
        // Prepare the SQL statement
        pst = conn.prepareStatement("INSERT INTO public.employeetime_log(employee_id, first_name, last_name, date, time_in, time_out) VALUES(?, ?, ?, ?, ?, ?)");
        
        // Set the parameters
        pst.setString(1, employeeId);
        pst.setString(2, firstName);
        pst.setString(3, lastName);
        pst.setString(4, date);
        pst.setString(5, time);
        pst.setString(6, null); // Set time out to null for time in

        // Execute the update
        pst.executeUpdate();
        return true; // Indicate success
    } catch (SQLException e) {
        e.printStackTrace(); // Log the exception
        return false; // Indicate failure
    } finally {
        // Close resources
        try {
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Log any exceptions during resource closing
        }
    }
}
     public boolean logTimeOut(String employeeId, String firstName, String lastName, String date, String time) {
 
    try {
        // Establish the database connection
        conn = dbConnect.connect();
        
        // Prepare the SQL statement
        pst = conn.prepareStatement("INSERT INTO public.employeetime_log(employee_id, first_name, last_name, date, time_in, time_out) VALUES(?, ?, ?, ?, ?, ?)");
        
        // Set the parameters
        pst.setString(1, employeeId);
        pst.setString(2, firstName);
        pst.setString(3, lastName);
        pst.setString(4, date);
        pst.setString(5, null);
        pst.setString(6, time); // Set time out to null for time in

        // Execute the update
        pst.executeUpdate();
        return true; // Indicate success
    } catch (SQLException e) {
        e.printStackTrace(); // Log the exception
        return false; // Indicate failure
    } finally {
        // Close resources
        try {
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Log any exceptions during resource closing
        }
    }
}
      public boolean updateLeaveRecord(LeaveRecord leaveRecord) {
        String status = leaveRecord.getStatus();
        String leaveNum = leaveRecord.getLeaveNum();

        try (Connection conn = dbConnect.connect();
             PreparedStatement pst = conn.prepareStatement("UPDATE public.leave_record SET status = ? WHERE leave_num = ?")) {
             
            pst.setString(1, status);
            pst.setString(2, leaveNum);
            return pst.executeUpdate() > 0; // Return true if the update was successful
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return false; // Indicate failure
        }
    }
     public boolean deleteLeaveRecord(LeaveRecord leaveRecord) {
        String leaveNum = leaveRecord.getLeaveNum();
        try (Connection conn = dbConnect.connect();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM public.leave_record WHERE leave_num = ?")) {
             
            pst.setString(1, leaveNum);
            int rowsAffected = pst.executeUpdate(); // Use executeUpdate for DELETE operations
            return rowsAffected > 0; // Return true if the record was deleted
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
            return false; // Indicate failure
        }
    }
    
}