
package com.mmdc.motor_ph_portal.AdminAccess;

import com.mmdc.motor_ph_portal.AdminAccess.PayrollDetails;

public class PayrollCalculation extends PayrollDetails {
 
    public PayrollCalculation(String employeeID, String firstName, String lastName, double hourlyRate, double riceA,
                    double phoneA, double clothingA, double sssC, double basicSalary) {
        super(employeeID, firstName, lastName, hourlyRate, riceA,
                    phoneA, clothingA, sssC, basicSalary);
    }
   
    @Override
    public void PayrollCalc(String employeeID, String firstName, String lastName, 
            double hourlyRate, double riceA, double phoneA, double clothingA, 
            double sssC, double basicSalary) {
        /*try {
            // Retrieve input values from the UI fields
            hourlyRate = getHourlyRate(); // Use getter from PayrollDetails
            hoursWorked = Double.parseDouble(hoursWorked_field.getText());
            riceA = Double.parseDouble(riceA_field.getText());
            phoneA = Double.parseDouble(phoneA_field.getText());
            clothingA = Double.parseDouble(clothing_field.getText());
            basicSalary = getBasicSalary(); // Use getter from PayrollDetails
            sssC = Double.parseDouble(sss_field.getText());

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
        }*/
    }
    
}
