
package com.mmdc.motor_ph_portal.AdminAccess;

import com.mmdc.motor_ph_portal.User;


public abstract class PayrollDetails {
    private String employeeID;
    private String firstName;
    private String lastName;
    private double hourlyRate;
    private double riceA;
    private double phoneA;
    private double clothingA;
    private double sssC;
    private double basicSalary;

    // Constructor
    public PayrollDetails(String employeeID, String firstName, String lastName, double hourlyRate, double riceA,
                    double phoneA, double clothingA, double sssC, double basicSalary) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hourlyRate = hourlyRate;
        this.riceA = riceA;
        this.phoneA = phoneA;
        this.clothingA = clothingA;
        this.sssC = sssC;
        this.basicSalary = basicSalary;
        
    }
    
    public abstract void PayrollCalc(String employeeID, String firstName, String lastName, double hourlyRate, double riceA,
                    double phoneA, double clothingA, double sssC, double basicSalary);

    // Getters
    public String getEmployeeID() { return employeeID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public double getHourlyRate() { return hourlyRate; }
    public double getRiceA() { return riceA; }
    public double getPhoneA() { return phoneA; }
    public double getClothingAllowance() { return clothingA; }
    public double getSssC() { return sssC; }
    public double getBasicSalary() { return basicSalary; }
    
    //setters
    public void setEmployeeID(String employeeID) { this.employeeID = employeeID; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }
    public void setRiceA(double riceA) { this.riceA = riceA; }
    public void setPhoneA(double phoneA) { this.phoneA = phoneA; }
    public void setClothingAllowance(double clothingA) { this.clothingA = clothingA; }
    public void setSssC(double sssC) { this.sssC = sssC; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }

}
