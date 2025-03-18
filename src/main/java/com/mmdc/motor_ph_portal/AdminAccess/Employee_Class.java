
package com.mmdc.motor_ph_portal.AdminAccess;

import com.mmdc.motor_ph_portal.EmployeeAccess.*;
import com.mmdc.motor_ph_portal.User;
import javax.swing.JOptionPane;


public class Employee_Class extends User {
      
    
    public Employee_Class(String employeeID, String firstName, String lastName, String birthday, String address, 
                    String phoneNumber, String sssNum, String philHealthNum, String tinNum, 
                    String pagibigNum, String status, String position, String supervisor,
                    String username, String password) {
        super(employeeID, firstName, lastName, birthday, address, 
              phoneNumber, sssNum, philHealthNum, tinNum, 
              pagibigNum, status, position, supervisor, 
              0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 
              username, password); 
    }

    @Override
    public void login(String username, String password) {
        // Implement Employee-specific login logic if needed
        JOptionPane.showMessageDialog(null, "Employee login successful for: " + firstName, 
                                  "Login Successful", JOptionPane.INFORMATION_MESSAGE);
        // Redirect to the Admin Access Employee Portal
        EmployeeAccessPortal empAccessPortal = new EmployeeAccessPortal(); 
        empAccessPortal.setVisible(true);
    
    }

    @Override
    public String getRole() {
        return "Employee";
    }
    
   
}
