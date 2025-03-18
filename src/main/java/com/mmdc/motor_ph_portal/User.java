
package com.mmdc.motor_ph_portal;

public abstract class User {
    protected String employeeID;
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
    protected String birthday;
    protected String address;
    protected String phoneNumber;
    protected String sssNum;
    protected String philHealthNum;
    protected String tinNum;
    protected String pagibigNum;
    protected String status;
    protected String position;
    protected String supervisor;
    protected double basicSalary;
    protected double sssC;
    protected double riceA;
    protected double phoneA;
    protected double clothingA;
    protected double grossSemiMonthlyRate;
    protected double hourlyRate;

    public User(String employeeID, String firstName, String lastName, String birthday, String address, 
              String phoneNumber, String sssNum, String philHealthNum, String tinNum, 
              String pagibigNum, String status, String position, String supervisor, 
              double basicSalary, double sssC, double riceA, double phoneA, 
              double clothingA, double grossSemiMonthlyRate, double hourlyRate, 
              String username, String password) {
        
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sssNum = sssNum;
        this.philHealthNum = philHealthNum;
        this.tinNum = tinNum;
        this.pagibigNum = pagibigNum;
        this.status = status;
        this.position = position;
        this.supervisor = supervisor;
        this.basicSalary = basicSalary;
        this.sssC = sssC;
        this.riceA = riceA;
        this.phoneA = phoneA;
        this.clothingA = clothingA;
        this.grossSemiMonthlyRate = grossSemiMonthlyRate;
        this.hourlyRate = hourlyRate;
    }
    public abstract void login(String username, String password);
  
    public String getEmployeeID() {
        return employeeID;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
        
    }
    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSssNum() {
        return sssNum;
    }

    public String getPhilHealthNum() {
        return philHealthNum;
    }

    public String getTinNum() {
        return tinNum;
    }

    public String getPagibigNum() {
        return pagibigNum;
    }

    public String getStatus() {
        return status;
    }

    public String getPosition() {
        return position;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getSssC() {
        return sssC;
    }

    public double getRiceA() {
        return riceA;
    }

    public double getPhoneA() {
        return phoneA;
    }

    public double getClothingA() {
        return clothingA;
    }

    public double getGrossSemiMonthlyRate() {
        return grossSemiMonthlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public abstract String getRole(); // This can be overridden in subclasses
    

    
}

   


