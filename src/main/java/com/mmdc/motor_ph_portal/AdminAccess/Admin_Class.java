
package com.mmdc.motor_ph_portal.AdminAccess;

import com.mmdc.motor_ph_portal.User;
import com.mmdc.motor_ph_util.DatabaseConnect;
import javax.swing.JOptionPane;


public class Admin_Class extends User {
  

    public Admin_Class(String employeeID, String firstName, String lastName, String birthday, String address, 
              String phoneNumber, String sssNum, String philHealthNum, String tinNum, 
              String pagibigNum, String status, String position, String supervisor, 
              double basicSalary, double sssC, double riceA, double phoneA, 
              double clothingA, double grossSemiMonthlyRate, double hourlyRate, 
              String username, String password, String role) {
        super( employeeID,  firstName,  lastName,  birthday,  address, 
               phoneNumber,  sssNum,  philHealthNum,  tinNum, 
               pagibigNum,  status,  position,  supervisor, 
               basicSalary,  sssC,  riceA,  phoneA, 
               clothingA,  grossSemiMonthlyRate,  hourlyRate, 
               username,  password);
        
    }
    public Admin_Class getEmployeeDetails(String employeeID) {
        DatabaseConnect dbConnect = new DatabaseConnect() {
            // You can implement any abstract methods here if needed
        };
        return dbConnect.getAAEmployeeById(employeeID);
    } 
    
     public void addEmployee() {
        DatabaseConnect dbConnect = new DatabaseConnect() {
            // You can implement any abstract methods here if needed
        };

        // Call the addEmployee method from DatabaseConnect
        dbConnect.addEmployee(this); // Pass the current instance of Admin_Class
    }
    
    @Override
    public void login(String username, String password) {
    JOptionPane.showMessageDialog(null, "Employee login successful for: " + firstName, 
                                  "Login Successful", JOptionPane.INFORMATION_MESSAGE);

    // Redirect to the Admin Access Employee Portal
    EmployeePortal empPortal = new EmployeePortal(); 
    empPortal.setVisible(true);
    }

    @Override
    public String getRole() {
        return "Admin_Class";
    } 

    public String getEmployeeId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
