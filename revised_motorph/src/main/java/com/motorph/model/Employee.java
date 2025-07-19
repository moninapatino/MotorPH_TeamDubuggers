package com.motorph.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Employee entity representing an employee in the MotorPH system.
 * Follows proper encapsulation and validation principles.
 */
public class Employee {
    
    @NotBlank(message = "Employee ID is required")
    private String employeeId;
    
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    private String lastName;
    
    @Email(message = "Valid email is required")
    private String email;
    
    @NotNull(message = "Birthday is required")
    private LocalDate birthday;
    
    private Address address;
    
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    
    private String sssNumber;
    private String philHealthNumber;
    private String tinNumber;
    private String pagibigNumber;
    
    @NotBlank(message = "Username is required")
    private String username;
    
    @NotBlank(message = "Password is required")
    private String password;
    
    private String department;
    private String position;
    private double basicSalary;
    private boolean isActive;
    
    // Default constructor
    public Employee() {
        this.isActive = true;
    }
    
    // Constructor with required fields
    public Employee(String employeeId, String firstName, String lastName, 
                   String email, LocalDate birthday, String username, String password) {
        this();
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
    }
    
    // Getters and Setters
    public String getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDate getBirthday() {
        return birthday;
    }
    
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getSssNumber() {
        return sssNumber;
    }
    
    public void setSssNumber(String sssNumber) {
        this.sssNumber = sssNumber;
    }
    
    public String getPhilHealthNumber() {
        return philHealthNumber;
    }
    
    public void setPhilHealthNumber(String philHealthNumber) {
        this.philHealthNumber = philHealthNumber;
    }
    
    public String getTinNumber() {
        return tinNumber;
    }
    
    public void setTinNumber(String tinNumber) {
        this.tinNumber = tinNumber;
    }
    
    public String getPagibigNumber() {
        return pagibigNumber;
    }
    
    public void setPagibigNumber(String pagibigNumber) {
        this.pagibigNumber = pagibigNumber;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public double getBasicSalary() {
        return basicSalary;
    }
    
    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
