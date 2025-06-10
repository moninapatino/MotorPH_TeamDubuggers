
package com.mmdc.motor_ph_portal.AdminAccess;

public class PayrollCalculation extends PayrollDetails {
 
    public PayrollCalculation(String employeeID, String firstName, String lastName, double basicSalary, double sssC, 
                    double riceA, double phoneA, double clothingA, double hourlyRate, double hoursWorked,
                    double totalAllowances, double totalDeductions, double philhealthC, 
                    double pagibigC, double grossPay, double netPay) {
        super(employeeID, firstName, lastName, hourlyRate, hoursWorked, 
                          riceA, phoneA, clothingA, sssC, basicSalary, 
                          totalAllowances, totalDeductions, philhealthC, pagibigC, 
                          0.0, null, null, null, null, 
                          null, null , null);
    }
    
    @Override
    public double calculateTotalAllowances() {
        return riceA + phoneA + clothingA;
    }
    @Override
    public double calculateBasicSalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public double calculatePhilHealth() {
        return calculateBasicSalary() * 0.05 / 2;
    }

    @Override
    public double calculatePagibig() {
        return calculateBasicSalary() * 0.02;
    }

    @Override
    public double calculateTotalDeductions() {
        return calculatePhilHealth() + calculatePagibig() + sssC;
    }

    @Override
    public double calculateGrossPay() {
        return calculateBasicSalary() + calculateTotalAllowances() - calculateTotalDeductions();
    }
    
    public double calculateTax() {
        return calculateBasicSalary() * 0.12;
    }
    
    public double calculateNetPay() {
        return calculateGrossPay() - calculateTax();
    }
}
