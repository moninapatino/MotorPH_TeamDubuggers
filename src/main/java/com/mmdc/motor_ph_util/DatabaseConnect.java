
package com.mmdc.motor_ph_util;

import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import com.mmdc.motor_ph_portal.EmployeeAccess.Employee_Class;
import com.mmdc.motor_ph_portal.LeaveRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public abstract class DatabaseConnect {
    protected String url;
    protected String user;
    protected String password;
    protected String employee_id; 
    
    public DatabaseConnect() {
        this.url = "jdbc:postgresql://localhost:5432/postgres";
        this.user = "postgres";
        this.password = "@dm1n";
        
    }

    public String getUrl() {
        return url;
    }

    public String getUser () {
        return user;
    }

    public String getPassword() {
        return password;
    }
    
    public Connection connect() {
    Connection conn = null;
    try {
        Class.forName("org.postgresql.Driver");
        System.out.println("Trying to connect");
        
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connection Established Successfully");
        
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "PostgreSQL JDBC Driver not found.");
    } catch (SQLException ex) {
        Logger lgr = Logger.getLogger(DatabaseConnect.class.getName());
        lgr.log(Level.SEVERE, ex.getMessage(), ex);
        JOptionPane.showMessageDialog(null, "Unable to make connection with DB: " + ex.getMessage());
    }
    return conn; // Return the connection object

    }
    
    public Employee_Class getEmployeeDetails(String employeeId) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Employee_Class employee = null;

        try {
            conn = connect();
            String sql = "SELECT * FROM public.employee_data WHERE employee_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, employeeId);
            rs = pst.executeQuery();

            if (rs.next()) {
                employee = new Employee_Class(
                        rs.getString("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("birthday"),
                        rs.getString("address"),
                        rs.getString("phone_number"),               
                        rs.getString("sss_num"),
                        rs.getString("philhealth_num"), 
                        rs.getString("tin_num"),
                        rs.getString("pagibig_num"),
                        rs.getString("status"),
                        rs.getString("position"),   
                        rs.getString("supervisor"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Consider logging this instead
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace(); // Handle closing exceptions
            }
        }
        return employee;
    }
  
       public void addEmployee(Admin_Class employeeId) {
        Admin_Class employee = employeeId;
        String sql = "INSERT INTO public.employee_data (employee_id, first_name, last_name, birthday, address, "
                   + "phone_number, sss_num, philhealth_num, tin_num, pagibig_num, status, position) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            // Set parameters from the Employee object
            pst.setString(1, employee.getEmployeeID());
            pst.setString(2, employee.getFirstName());
            pst.setString(3, employee.getLastName());
            pst.setString(4, employee.getBirthday());
            pst.setString(5, employee.getAddress());
            pst.setString(6, employee.getPhoneNumber());
            pst.setString(7, employee.getSssNum());
            pst.setString(8, employee.getPhilHealthNum());
            pst.setString(9, employee.getTinNum());
            pst.setString(10, employee.getPagibigNum());
            pst.setString(11, employee.getStatus());
            pst.setString(12, employee.getPosition());
           
            // Execute the insert operation
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Employee Profile Added!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding employee: " + e.getMessage());
        }
     
    } 
      public void updateEmployee(Employee_Class employeeId) {
        Employee_Class employee = null;
        String sql = "UPDATE public.employee_data SET last_name = ?, phone_number = ?, position = ?, status = ? WHERE employee_id = ?";

        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            // Set parameters from the Employee object
            pst.setString(1, employee.getLastName());
            pst.setString(2, employee.getPhoneNumber());
            pst.setString(3, employee.getPosition());
            pst.setString(4, employee.getStatus());
            pst.setString(5, employee.getEmployeeID());

            // Execute the update operation
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Data successfully updated!");
            } else {
                JOptionPane.showMessageDialog(null, "No data was updated. Please check the employee ID.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating employee: " + e.getMessage());
        }
       // return employee;
    }
   public void deleteEmployee(String employeeId) {
       
        String sql = "DELETE FROM public.employee_data WHERE employee_id = ?";

        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, employeeId);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Employee Profile Deleted!");
            } else {
                JOptionPane.showMessageDialog(null, "No employee found with the given ID.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error deleting employee: " + e.getMessage());
        }
      //   return employee;
    }   
    
    public String getHoursWorked(String employeeId, String payPeriod) {
        String hoursWorked = "";
        String sql = "SELECT * FROM public.attendance_record WHERE employee_id = ?";
        
        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setString(1, employeeId);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                if ("January 1-15, 2024".equals(payPeriod)) {
                    hoursWorked = rs.getString("first_cutOff");
                } else if ("January 16-31, 2024".equals(payPeriod)) {
                    hoursWorked = rs.getString("second_cutOff");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return hoursWorked;
    }
    public ArrayList<LeaveRecord> userList() {
        ArrayList<LeaveRecord> leaveRecords = new ArrayList<>();
        String sql = "SELECT * FROM public.leave_record";

        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                LeaveRecord record = new LeaveRecord(
                    rs.getString("leave_num"),
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
    
    public ArrayList<LeaveRecord> refreshList() {
        ArrayList<LeaveRecord> leaveRecords = new ArrayList<>();
        String sql = "SELECT * FROM public.leave_record";

        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                LeaveRecord record = new LeaveRecord(
                    rs.getString("leave_num"),
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
    
    public boolean addLeaveRequest(LeaveRecord leaveRequest) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = connect();
            pst = conn.prepareStatement("INSERT INTO public.leave_record(leave_num, employee_id, first_name, last_name, start_date, end_date, leave_type, status) VALUES(?,?,?,?,?,?,?,?)");
            
            pst.setString(1, leaveRequest.getLeaveNum());
            pst.setString(2, leaveRequest.getEmployeeId());
            pst.setString(3, leaveRequest.getFirstName());
            pst.setString(4, leaveRequest.getLastName());
            pst.setString(5, leaveRequest.getStartDate());
            pst.setString(6, leaveRequest.getEndDate());
            pst.setString(7, leaveRequest.getLeaveType());
            pst.setString(8, leaveRequest.getStatus());

            pst.executeUpdate();
            return true; 
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        } 
            
        
    }

}
        
        
        
    

