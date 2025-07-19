package com.motorph.model;

import java.time.LocalDate;

/**
 * Admin entity extending Employee with administrative privileges.
 * Follows proper inheritance and encapsulation principles.
 */
public class Admin extends Employee {
    
    private String adminLevel;
    private boolean canManageEmployees;
    private boolean canManagePayroll;
    private boolean canViewReports;
    
    // Default constructor
    public Admin() {
        super();
        this.adminLevel = "STANDARD";
        this.canManageEmployees = true;
        this.canManagePayroll = true;
        this.canViewReports = true;
    }
    
    // Constructor with required fields
    public Admin(String employeeId, String firstName, String lastName, 
                String email, LocalDate birthday, String username, String password) {
        super(employeeId, firstName, lastName, email, birthday, username, password);
        this.adminLevel = "STANDARD";
        this.canManageEmployees = true;
        this.canManagePayroll = true;
        this.canViewReports = true;
    }
    
    // Getters and Setters
    public String getAdminLevel() {
        return adminLevel;
    }
    
    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }
    
    public boolean canManageEmployees() {
        return canManageEmployees;
    }
    
    public void setCanManageEmployees(boolean canManageEmployees) {
        this.canManageEmployees = canManageEmployees;
    }
    
    public boolean canManagePayroll() {
        return canManagePayroll;
    }
    
    public void setCanManagePayroll(boolean canManagePayroll) {
        this.canManagePayroll = canManagePayroll;
    }
    
    public boolean canViewReports() {
        return canViewReports;
    }
    
    public void setCanViewReports(boolean canViewReports) {
        this.canViewReports = canViewReports;
    }
    
    /**
     * Check if admin has permission for a specific action
     */
    public boolean hasPermission(String action) {
        switch (action.toUpperCase()) {
            case "MANAGE_EMPLOYEES":
                return canManageEmployees;
            case "MANAGE_PAYROLL":
                return canManagePayroll;
            case "VIEW_REPORTS":
                return canViewReports;
            default:
                return false;
        }
    }
    
    @Override
    public String toString() {
        return "Admin{" +
                "employeeId='" + getEmployeeId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", adminLevel='" + adminLevel + '\'' +
                ", canManageEmployees=" + canManageEmployees +
                ", canManagePayroll=" + canManagePayroll +
                ", canViewReports=" + canViewReports +
                '}';
    }
}
