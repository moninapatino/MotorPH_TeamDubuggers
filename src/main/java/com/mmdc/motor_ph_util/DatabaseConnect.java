package com.mmdc.motor_ph_util;

import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import com.mmdc.motor_ph_portal.AdminAccess.PayrollCalculation;
import com.mmdc.motor_ph_portal.AdminAccess.TimeLogEntry;
import com.mmdc.motor_ph_portal.LeaveRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public abstract class DatabaseConnect {

    protected String url;
    protected String user;
    protected String password;
    protected String employee_id;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public DatabaseConnect() {
        this.url = "jdbc:mysql://localhost:3306/motorph?useSSL=false";
        this.user = "root";
        this.password = "@dm1nistr4tor";
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
    // CONNECTION TO SQL
    public Connection connect() {
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
        return conn; // Return the connection object

    }
    // EMPLOYEE DETAILS KEY RELEASED
    public Admin_Class getEmployeeDetails(String employeeId) {
        Admin_Class employee = null;

        try {
            conn = connect();
            String sql = "SELECT e.employee_id, e.first_name, e.last_name, e.email, e.birthday, " +
                     "e.phone, e.sss_num, e.philhealth_num, e.tin, e.pagibig_num, " +
                     "a.street, a.barangay, a.city, a.province, a.postalcode " + // Add address fields as needed
                     "FROM employee e " +
                     "JOIN address a ON e.address_id = a.address_id " +
                     "WHERE e.employee_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, employeeId);
            rs = pst.executeQuery();

            if (rs.next()) {
                employee = new Admin_Class(
                        rs.getString("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("birthday"),
                        rs.getString("street"),
                        rs.getString("barangay"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("postalcode"),
                        rs.getString("phone"),
                        rs.getString("sss_num"),
                        rs.getString("philhealth_num"),
                        rs.getString("tin"),
                        rs.getString("pagibig_num"),
                        null,null,null);

            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Consider logging this instead
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace(); // Handle closing exceptions
            }
        }
        return employee;
    }
    // ADD BUTTON FOR EMPLOYEE PROFILE
    public void addEmployee(Admin_Class employeeId) {
        Admin_Class employee = employeeId;
        String sql = "INSERT INTO employee_data (employee_id, first_name, last_name, birthday, address, "
                + "phone_number, sss_num, philhealth_num, tin_num, pagibig_num, status, position) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pst = conn.prepareStatement(sql)) {

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
            

            // Execute the insert operation
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Employee Profile Added!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding employee: " + e.getMessage());
        }

    }
    // UPDATE BTN FOR EMPLOYEE PROFILE
    public void updateEmployee(Admin_Class employeeId) {
        Admin_Class employee = employeeId;
        String sql = "UPDATE employee SET last_name = ?, phone_number = ?, position = ?, status = ? WHERE employee_id = ?";

        try (Connection conn = connect(); PreparedStatement pst = conn.prepareStatement(sql)) {

            // Set parameters from the Employee object
            pst.setString(1, employee.getLastName());
            pst.setString(2, employee.getPhoneNumber());
           
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

    //DELETE BTN FOR EMPLOYEE PROFILE
    public void deleteEmployee(String employeeId) {

        String sql = "DELETE FROM employee WHERE employee_id = ?";

        try (Connection conn = connect(); PreparedStatement pst = conn.prepareStatement(sql)) {

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

    //HOURSWORKED FOR EMPLOYEE ATTENDANCE
    public String getHoursWorked(String employeeId, String payPeriod) {
        String hoursWorked = "";
        String sql = "SELECT * FROM attendance_records WHERE employee_id = ?";

        try (Connection conn = connect(); PreparedStatement pst = conn.prepareStatement(sql)) {

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
        String sql = "SELECT * FROM leave_records";

        try (Connection conn = connect(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

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

    // 
    public ArrayList<LeaveRecord> refreshList() {
        ArrayList<LeaveRecord> leaveRecords = new ArrayList<>();
        String sql = "SELECT * FROM leave_records";

        try (Connection conn = connect(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

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
            pst = conn.prepareStatement("INSERT INTO leave_records(leave_num, employee_id, first_name, last_name, start_date, end_date, leave_type, status) VALUES(?,?,?,?,?,?,?,?)");

            pst.setString(1, leaveRequest.getLeaveId());
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
    // PAYROLL DETAILS KEY RELEASE
    public PayrollCalculation getPayrollDetails(String employeeId) {
        PayrollCalculation employee = null;

        try {
            conn = connect();
            String sql = "SELECT * FROM employee WHERE employee_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, employeeId);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                employee = new PayrollCalculation(
                        rs.getString("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDouble("basic_salary"),
                        rs.getDouble("sss_c"),
                        rs.getDouble("rice_s"),
                        rs.getDouble("phone_a"),
                        rs.getDouble("clothing_a"),                 
                        rs.getDouble("hourly_rate"),
                        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
                        
                                
                    
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the exception
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace(); // Handle closing exceptions
            }
        }
        return employee; // Return null if no employee is found
    }
    public boolean deleteLeaveRecord(LeaveRecord leaveRecord) {
        String leaveNum = leaveRecord.getLeaveId();
        try {
            conn = connect();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM leave_records WHERE leave_num = ?"); 
             
            pst.setString(1, leaveNum);
            int rowsAffected = pst.executeUpdate(); // Use executeUpdate for DELETE operations
            return rowsAffected > 0; // Return true if the record was deleted
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
            return false; // Indicate failure
        }
    }
  
    public boolean updateLeaveRecord(LeaveRecord leaveRecord) {
        String status = leaveRecord.getStatus();
        String leaveNum = leaveRecord.getLeaveId();

        try { conn = connect();
             PreparedStatement pst = conn.prepareStatement("UPDATE leave_records SET status = ? WHERE leave_num = ?");
             
            pst.setString(1, status);
            pst.setString(2, leaveNum);
            return pst.executeUpdate() > 0; // Return true if the update was successful
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return false; // Indicate failure
        }
    }
    public boolean logTimeIn(String employeeId, String firstName, String lastName, String date, String time) {

    try {
        // Establish the database connection
        conn = connect();
        
        // Prepare the SQL statement
        pst = conn.prepareStatement("INSERT INTO attendance_records (employee_id, first_name, last_name, date, time_in, time_out) VALUES(?, ?, ?, ?, ?, ?)");
        
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
        conn = connect();
        
        // Prepare the SQL statement
        pst = conn.prepareStatement("INSERT INTO attendance_records (employee_id, first_name, last_name, date, time_in, time_out) VALUES(?, ?, ?, ?, ?, ?)");
        
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
     public void show_table(DefaultTableModel attendanceTable) {
       
        try {
            // Establish the database connection
            conn = connect();

            // SQL query to fetch attendance records
            String sql = "SELECT ar.attendance_id, ar.employee_id, ar.date, e.first_name, e.last_name, ar.time_in, ar.time_out, ar.status " +
                        "FROM attendance_records ar " +
                        "JOIN employee e ON ar.employee_id = e.employee_id";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            // Clear existing rows in the table model
            attendanceTable.setRowCount(0);

            // Process the result set and populate the table
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("attendance_id"));
                row.add(rs.getString("employee_id"));
                row.add(rs.getString("date"));
                row.add(rs.getString("first_name"));
                row.add(rs.getString("last_name"));
                row.add(rs.getString("time_in"));
                row.add(rs.getString("time_out"));
                row.add(rs.getString("status"));
                
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
   
        
        
        
        
}
