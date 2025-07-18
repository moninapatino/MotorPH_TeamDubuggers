package com.motorph.model;

public abstract class User {
    private String employeeID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String birthday;
    private String addressID;
    private String street;
    private String barangay;
    private String city;
    private String province;
    private String postalcode;
    private String email;
    private String phoneNumber;
    private String sssNum;
    private String philHealthNum;
    private String tinNum;
    private String pagibigNum;

    public User(String employeeID, String firstName, String lastName, String birthday, 
                String addressID, String street, String barangay, String city, String province,
                String postalcode, String email, String phoneNumber, String sssNum, 
                String philHealthNum, String tinNum, String pagibigNum, 
                String username, String password) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.addressID = addressID;
        this.street = street;
        this.barangay = barangay;
        this.city = city;
        this.province = province;
        this.postalcode = postalcode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.sssNum = sssNum;
        this.philHealthNum = philHealthNum;
        this.tinNum = tinNum;
        this.pagibigNum = pagibigNum;
    }

    public String getEmployeeID() { return employeeID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getBirthday() { return birthday; }
    public String getAddressID() { return addressID; }
    public String getStreet() { return street; }
    public String getBarangay() { return barangay; }
    public String getCity() { return city; }
    public String getProvince() { return province; }
    public String getPostalcode() { return postalcode; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getSssNum() { return sssNum; }
    public String getPhilHealthNum() { return philHealthNum; }
    public String getTinNum() { return tinNum; }
    public String getPagibigNum() { return pagibigNum; }

public abstract String getRole(); // This can be overridden in subclasses

public abstract void setPassword(String string);
    
}