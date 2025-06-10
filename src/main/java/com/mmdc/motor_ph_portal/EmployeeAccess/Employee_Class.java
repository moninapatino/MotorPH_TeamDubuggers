
package com.mmdc.motor_ph_portal.EmployeeAccess;


import com.mmdc.motor_ph_portal.AdminAccess.EmployeePortal;
import com.mmdc.motor_ph_portal.User;
import javax.swing.JOptionPane;


public class Employee_Class extends User {
  

    public Employee_Class(String employeeID, String firstName, String lastName, String birthday, 
                String address, String street, String barangay, String city, String province,
                String postalcode, String email, String phoneNumber, String sssNum, 
                String philHealthNum, String tinNum, String pagibigNum, 
                String username, String password) {
        super(employeeID, firstName, lastName, birthday, 
                 address, street, barangay, city, province,
                 postalcode, email, phoneNumber, sssNum, 
                 philHealthNum, tinNum, pagibigNum, 
                 username, password);
        
    }
   
    @Override
    public void login(String username, String password) {
    JOptionPane.showMessageDialog(null, "Employee login successful for: " + firstName + lastName, 
                                  "Login Successful", JOptionPane.INFORMATION_MESSAGE);

    // Redirect to the Admin Access Employee Portal
    EmployeeAccess_Profile empPortal = new EmployeeAccess_Profile(); 
    empPortal.setVisible(true);
    }

    @Override
    public String getRole() {
        return "Employee";
    } 

    @Override
    public void payrollCalc(String employeeId) {
    }

  
}