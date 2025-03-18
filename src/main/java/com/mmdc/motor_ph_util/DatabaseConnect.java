
package com.mmdc.motor_ph_util;

import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import com.mmdc.motor_ph_portal.EmployeeAccess.Employee_Class;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public Employee_Class getEAEmployeeById(String employeeId) {
        Employee_Class employee = null;
        String sql = "SELECT * FROM public.employee_data WHERE employee_id = ?";
        
        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setString(1, employeeId);
            try (ResultSet rs = pst.executeQuery()) {
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
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return employee;
    }
     public Admin_Class getAAEmployeeById(String employeeId) {
        Admin_Class employee = null;
        String sql = "SELECT * FROM public.employee_data WHERE employee_id = ?";

        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setString(1, employeeId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    employee = new Admin_Class(
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
                        rs.getDouble("basic_salary"),
                        rs.getDouble("sss_c"),
                        rs.getDouble("rice_s"),
                        rs.getDouble("phone_a"),
                        rs.getDouble("clothing_a"),
                        rs.getDouble("grosssemi_monthly_rate"),
                        rs.getDouble("hourly_rate"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                    );
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return employee;
    }
    public void addEmployee(Admin_Class employeeId) {
        Admin_Class employee = employeeId;
        String sql = "INSERT INTO public.employee_data (employee_id, first_name, last_name, birthday, address, "
                   + "phone_number, sss_num, philhealth_num, tin_num, pagibig_num, status, position, "
                   + "supervisor, basic_salary, sss_c, rice_s, phone_a, clothing_a, "
                   + "grosssemi_monthly_rate, hourly_rate, username, password) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            // Set parameters from the Employee object
            pst.setString(1, employee.getEmployeeId());
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
            pst.setString(13, employee.getSupervisor());
            pst.setDouble(14, employee.getBasicSalary());
            pst.setDouble(15, employee.getSssC());
            pst.setDouble(16, employee.getRiceA());
            pst.setDouble(17, employee.getPhoneA());
            pst.setDouble(18, employee.getClothingA());
            pst.setDouble(19, employee.getGrossSemiMonthlyRate());
            pst.setDouble(20, employee.getHourlyRate());
            pst.setString(21, employee.getUsername());
            pst.setString(22, employee.getPassword());

            // Execute the insert operation
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Employee Profile Added!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding employee: " + e.getMessage());
        }
     
    } 
      public void updateEmployee(Admin_Class employeeId) {
          Admin_Class employee = null;
        String sql = "UPDATE public.employee_data SET last_name = ?, phone_number = ?, position = ?, status = ? WHERE employee_id = ?";

        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            // Set parameters from the Employee object
            pst.setString(1, employee.getLastName());
            pst.setString(2, employee.getPhoneNumber());
            pst.setString(3, employee.getPosition());
            pst.setString(4, employee.getStatus());
            pst.setString(5, employee.getEmployeeId());

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
}
        
        
        
    

