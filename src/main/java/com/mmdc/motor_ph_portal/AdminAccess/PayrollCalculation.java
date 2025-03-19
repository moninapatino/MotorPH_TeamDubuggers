
package com.mmdc.motor_ph_portal.AdminAccess;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;


public abstract class PayrollCalculation extends PayrollDetails {
    private double hoursWorked;
    private double totalAllowances;
    private double totalDeductions;
    private double grossPay;
    private double phealthC;
    private double pagibigC;
    
    public PayrollCalculation(String employeeID, String firstName, String lastName, double hourlyRate, double riceA,
                    double phoneA, double clothingA, double sssC, double basicSalary) {
        super(employeeID, firstName, lastName, hourlyRate, riceA,
                    phoneA, clothingA, sssC, basicSalary);
    }
    
  /*  public void getPayrollCalculation (){
        try {
            // Retrieve input values from the UI fields
            double hourlyRate = getHourlyRate(); // Use getter from PayrollDetails
            hoursWorked = Double.parseDouble(hoursWorked_field.getText());
            double riceA = Double.parseDouble(riceA_field.getText());
            double phoneA = Double.parseDouble(phoneA_field.getText());
            double clothingA = Double.parseDouble(clothing_field.getText());
            double basicSalary = getBasicSalary(); // Use getter from PayrollDetails
            double sssC = Double.parseDouble(sss_field.getText());

            // Calculate total allowances
            totalAllowances = riceA + phoneA + clothingA;
            // Calculate deductions
            phealthC = basicSalary * 0.05 / 2; // Assuming this is the calculation for PhilHealth
            pagibigC = basicSalary * 0.02;
            totalDeductions = phealthC + pagibigC + sssC;
 
            // Calculate gross pay
            grossPay = (hourlyRate * hoursWorked) + totalAllowances - totalDeductions;


        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers for all fields.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
        }
    }*/
    
}
