package com.motorph.model;

public abstract class Admin extends User {
    public Admin(String employeeID, String firstName, String lastName, String email, String birthday, 
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
    public String getRole() {
        return "Admin";
    }
}
