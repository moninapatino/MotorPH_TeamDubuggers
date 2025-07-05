package com.mmdc.motor_ph_portal.AdminAccess;

import com.mmdc.motor_ph_portal.User;
import javax.swing.JOptionPane;

public class Admin_Class extends User {

    private boolean loggedIn = false;  //  Make it mutable (no final) For Junit Testing

    public Admin_Class(String employeeID, String firstName, String lastName, String email, String birthday, 
                String addressID, String street, String barangay, String city, String province,
                String postalcode,  String phoneNumber, String sssNum, 
                String philHealthNum, String tinNum, String pagibigNum, 
                String username, String password) {
        super(employeeID, firstName, lastName, birthday, 
              addressID, street, barangay, city, province,
              postalcode, email, phoneNumber, sssNum, 
              philHealthNum, tinNum, pagibigNum, 
              username, password);
    }

    @Override
    public void login(String username, String password) {
        if ("Admin".equals(username) && "admin".equals(password)) {
            loggedIn = true;
            JOptionPane.showMessageDialog(null, "Employee login successful for: " + firstName + " " + lastName, 
                                          "Login Successful", JOptionPane.INFORMATION_MESSAGE);

            EmployeePortal empPortal = new EmployeePortal(); 
            empPortal.setVisible(true);
        } else {
            loggedIn = false;
            JOptionPane.showMessageDialog(null, "Invalid credentials", 
                                          "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    @Override
    public void payrollCalc(String employeeId) {
        // Not implemented yet
    }
}

