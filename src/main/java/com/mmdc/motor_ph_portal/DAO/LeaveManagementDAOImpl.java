package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_portal.LeaveRecord;
import com.mmdc.motor_ph_util.DatabaseConnect; // Import your DatabaseConnect class
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

public class LeaveManagementDAOImpl implements LeaveManagementDAO {
    
    // Method to establish a connection to the database
    private Connection connect() {
        return DatabaseConnect.getConnection(); // Use your existing method to get the connection
    }

    @Override
    public ArrayList<LeaveRecord> userList(String employeeID) {
        ArrayList<LeaveRecord> leaveRecords = new ArrayList<>();
        String sql = "SELECT lr.leave_id, lr.employee_id, e.first_name, e.last_name, " +
                     "lr.start_date, lr.end_date, lr.leave_type, lr.status " +
                     "FROM leave_records lr JOIN employee e ON lr.employee_id = e.employee_id " +
                     "WHERE lr.employee_id = ?";

        try (Connection conn = connect(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, employeeID); // ✅ Set employee ID

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    LeaveRecord record = new LeaveRecord(
                            rs.getInt("leave_id"),
                            rs.getString("employee_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("start_date"),
                            rs.getString("end_date"),
                            rs.getString("leave_type"),
                            rs.getString("status")
                    );
                    leaveRecords.add(record);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return leaveRecords;
    }
    
    @Override
    public ArrayList<LeaveRecord> leaveMngUserList() {
        ArrayList<LeaveRecord> leaveRecords = new ArrayList<>();
        String sql = "SELECT lr.leave_id, lr.employee_id, e.first_name, e.last_name, " +
                     "lr.start_date, lr.end_date, lr.leave_type, lr.status " +
                     "FROM leave_records lr JOIN employee e ON lr.employee_id = e.employee_id";

        try (Connection conn = connect(); 
             PreparedStatement pst = conn.prepareStatement(sql); 
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                LeaveRecord record = new LeaveRecord(
                        rs.getInt("leave_id"),
                        rs.getString("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getString("leave_type"),
                        rs.getString("status")
                );
                leaveRecords.add(record);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return leaveRecords;
    }

    @Override
    public int getNextLeaveId() {
        String sql = "SELECT MAX(leave_id) FROM leave_records"; // Adjust the table name as necessary
        try (Connection conn = connect(); 
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1) + 1; // Return the next Leave ID
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }
        return 1; // Return 1 if no records exist
    }

    @Override
    public boolean addLeaveRequest(LeaveRecord leaveRequest) {
        String sql = "INSERT INTO leave_records (leave_id, employee_id, start_date, end_date, leave_type, status) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = connect(); 
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            // Set parameters
            pst.setInt(1, leaveRequest.getLeaveId()); // Assuming leaveId is an int
            pst.setString(2, leaveRequest.getEmployeeId());
            pst.setString(3, leaveRequest.getStartDate());
            pst.setString(4, leaveRequest.getEndDate());
            pst.setString(5, leaveRequest.getLeaveType());
            pst.setString(6, leaveRequest.getStatus());
            // Execute the update
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
            return false;
        } catch (Exception e) {
            e.printStackTrace(); // Log any other exceptions
            return false;
        }
    }

    @Override
    public boolean deleteLeaveRecord(LeaveRecord leaveRecord) {
        String sql = "DELETE FROM leave_records WHERE leave_id = ?";
        
        try (Connection conn = connect(); 
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, leaveRecord.getLeaveId()); // Assuming leaveId is an int
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0; // Return true if a record was deleted
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
            return false;
        }
    }

    @Override
    public boolean updateLeaveRecord(LeaveRecord leaveRecord) {
        String status = leaveRecord.getStatus();
        int leaveNum = leaveRecord.getLeaveId();

        String sql = "UPDATE leave_records SET status = ? WHERE leave_id = ?";

        try (Connection conn = connect(); 
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            // Set parameters
            pst.setString(1, status);
            pst.setInt(2, leaveNum);

            // Execute the update and return true if successful
            return pst.executeUpdate() > 0; 
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
            return false; // Indicate failure
        }
    }

    @Override
    public ArrayList<Vector<String>> getLeaveRecordsByEmployeeId(String employeeId) {
        String sql = """
            SELECT 
                lr.leave_id, 
                lr.employee_id, 
                e.first_name, 
                e.last_name, 
                lr.start_date, 
                lr.end_date, 
                lr.leave_type, 
                lr.status 
            FROM leave_records lr
            JOIN employee e ON lr.employee_id = e.employee_id
            WHERE lr.employee_id = ?
        """;

        ArrayList<Vector<String>> leaveList = new ArrayList<>();

        try (
            Connection conn = connect();
            PreparedStatement pst = conn.prepareStatement(sql)
        ) {
            pst.setString(1, employeeId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Vector<String> row = new Vector<>();
                    row.add(rs.getString("leave_id"));
                    row.add(rs.getString("employee_id"));
                    row.add(rs.getString("first_name"));
                    row.add(rs.getString("last_name"));
                    row.add(rs.getString("start_date"));
                    row.add(rs.getString("end_date"));
                    row.add(rs.getString("leave_type"));
                    row.add(rs.getString("status"));
                    leaveList.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Optional: replace with Logger
        }

        return leaveList;
    }

}
