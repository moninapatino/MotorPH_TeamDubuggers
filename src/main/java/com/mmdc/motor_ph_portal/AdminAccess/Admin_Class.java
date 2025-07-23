package com.mmdc.motor_ph_portal.AdminAccess;

import com.mmdc.motor_ph_portal.User;
import javax.swing.JOptionPane;

public class Admin_Class extends User {

    private boolean loggedIn = false;  // Mutable for testing purposes

    public Admin_Class(String employeeID, String firstName, String lastName, String email, String birthday, 
                       String addressID, String street, String barangay, String city, String province,
                       String postalcode, String phoneNumber, String sssNum, 
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
        // Validate credentials against the instance's username and password
        if (validateCredentials(username, password)) {
            loggedIn = true; // Set loggedIn to true upon successful login
            JOptionPane.showMessageDialog(null, "Employee login successful for: " + firstName + " " + lastName, 
                                          "Login Successful", JOptionPane.INFORMATION_MESSAGE);

            // Open the admin portal
            AdminPortal adminPortal = new AdminPortal(); // Replace with your actual admin portal class
            adminPortal.setVisible(true);
        } else {
            loggedIn = false; // Set loggedIn to false if login fails
            JOptionPane.showMessageDialog(null, "Invalid credentials", 
                                          "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateCredentials(String username, String password) {
        // Check if the provided username and password match the instance's credentials
        return this.username.equals(username) && this.password.equals(password);
    }

    public boolean isLoggedIn() {
        return loggedIn; // Return the login status
    }

    @Override
    public String getRole() {
        return "Admin";
    }
}
