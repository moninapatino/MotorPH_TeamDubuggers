package com.motorph.model;

public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String birthday;
    private Address address;
    private String phoneNumber;
    private String sssNum;
    private String philHealthNum;
    private String tinNum;
    private String pagibigNum;
    private String username;
    private String role;

    public Employee(String employeeId, String firstName, String lastName, String email,
                   String birthday, Address address, String phoneNumber,
                   String sssNum, String philHealthNum, String tinNum,
                   String pagibigNum, String username, String role) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sssNum = sssNum;
        this.philHealthNum = philHealthNum;
        this.tinNum = tinNum;
        this.pagibigNum = pagibigNum;
        this.username = username;
        this.role = role;
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getBirthday() { return birthday; }
    public Address getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getSssNum() { return sssNum; }
    public String getPhilHealthNum() { return philHealthNum; }
    public String getTinNum() { return tinNum; }
    public String getPagibigNum() { return pagibigNum; }
    public String getUsername() { return username; }
    public String getRole() { return role; }

    public boolean isAdmin() {
        return "Admin".equals(role);
    }
}
