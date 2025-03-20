
package com.mmdc.motor_ph_portal.AdminAccess;

import com.mmdc.motor_ph_portal.User;
import javax.swing.JOptionPane;


public class Admin_Class extends User {
 
    public Admin_Class(String employeeID, String firstName, String lastName,
            String birthday, String address, String phoneNumber,
            String sssNum, String philHealthNum, String tinNum,
            String pagibigNum, String status, String position, String supervisor,
            String username, String password, String role) {
        super(employeeID, firstName, lastName, birthday, address, 
              phoneNumber, sssNum, philHealthNum, tinNum, 
              pagibigNum, status, position, supervisor, 
              0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 
              username, password);
        
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
        return "Admin";
    } 

    @Override
    public void payrollCalc(String employeeId) {
    }

  
}
