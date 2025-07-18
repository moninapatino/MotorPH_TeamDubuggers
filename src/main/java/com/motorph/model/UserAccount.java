package com.motorph.model;

public class UserAccount {
    private String employeeID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String role;

    public UserAccount(String employeeID, String firstName, String lastName, String username, String password, String role) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getEmployeeID() { return employeeID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}
