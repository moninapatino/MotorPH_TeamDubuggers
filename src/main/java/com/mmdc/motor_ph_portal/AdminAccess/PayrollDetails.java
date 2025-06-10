
package com.mmdc.motor_ph_portal.AdminAccess;


public abstract class PayrollDetails {
    protected String employeeID;
    protected String firstName;
    protected String lastName;
    protected double hourlyRate;
    protected double hoursWorked;
    protected double riceA;
    protected double phoneA;
    protected double clothingA;
    protected double sssC;
    protected double basicSalary;
    protected double totalAllowances;
    protected double totalDeductions;
    protected double philhealthC;
    protected double pagibigC;
    protected double grossPay;
    protected double netPay;
    protected double tax;
    protected String deductionId;
    protected String date;
    protected String payperiodId;
    protected String startDate;
    protected String endDate;
    protected String cutOff;
    protected String payDate;
    
    // Constructor
    public PayrollDetails(String employeeID, String firstName, String lastName, double hourlyRate, double hoursWorked, 
                          double riceA, double phoneA, double clothingA, double sssC, double basicSalary, 
                          double totalAllowances, double totalDeductions, double philhealthC, double pagibigC, 
                          double tax, String deductionId, String date, String payperiodId, String startDate, 
                          String endDate, String cutOff, String payDate) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.riceA = riceA;
        this.phoneA = phoneA;
        this.clothingA = clothingA;
        this.sssC = sssC;
        this.basicSalary = basicSalary;
        this.totalAllowances = totalAllowances;
        this.totalDeductions = totalDeductions;
        this.philhealthC = philhealthC;
        this.pagibigC = pagibigC;
        this.tax = tax;
        this.deductionId = deductionId;
        this.date = date;
        this.payperiodId = payperiodId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cutOff = cutOff;
        this.payDate = payDate;
        
    }
    
   /* public void payrollCalc() { 
    }*/
     

    public double calculateTotalAllowances() {
        return riceA + phoneA + clothingA;
    }
    public double calculateBasicSalary() {
        return hourlyRate * hoursWorked;
    }

    public double calculatePhilHealth() {
        return calculateBasicSalary() * 0.05 / 2;
    }

    public double calculatePagibig() {
        return calculateBasicSalary() * 0.02;
    }

    public double calculateTotalDeductions() {
        return calculatePhilHealth() + calculatePagibig() + sssC;
    }

    public double calculateGrossPay() {
        return calculateBasicSalary() + calculateTotalAllowances() - calculateTotalDeductions();
    }
    
    
    // Getters
    public String getEmployeeID() { return employeeID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public double getHourlyRate() { return hourlyRate; }
    public double getHoursWorked() { return hoursWorked; }
    public double getRiceA() { return riceA; }
    public double getPhoneA() { return phoneA; }
    public double getClothingA() { return clothingA; }
    public double getSssC() { return sssC; }
    public double getBasicSalary() { return basicSalary; }
    public double getTotalAllowances() { return totalAllowances;  }
    public double getTotalDeductions() { return totalDeductions;  }
    public double getPhilhealthC() { return philhealthC;   }
    public double getPagibigC() { return pagibigC;  }
    public double getGrossPay() { return grossPay;  }
    public double getNetPay() { return netPay; }
    public double getTax() { return tax; }
    public String getDeductionId() { return deductionId; }
    public String getDate() { return date; }
    public String getPayperiodId() { return payperiodId; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getCutOff() { return cutOff; }
    public String getPayDate() { return payDate; }

    //Setters
    public void setEmployeeID(String employeeID) { this.employeeID = employeeID; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }
    public void setHoursWorked(double hoursWorked) { this.hoursWorked = hoursWorked; }
    public void setRiceA(double riceA) { this.riceA = riceA; }
    public void setPhoneA(double phoneA) { this.phoneA = phoneA; }
    public void setClothingA(double clothingA) { this.clothingA = clothingA; } 
    public void setSssC(double sssC) { this.sssC = sssC; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }
    public void setTotalAllowances(double totalAllowances) { this.totalAllowances = totalAllowances; }
    public void setTotalDeductions(double totalDeductions) { this.totalDeductions = totalDeductions; }
    public void setPhilhealthC(double philhealthC) { this.philhealthC = philhealthC; }
    public void setPagibigC(double pagibigC) { this.pagibigC = pagibigC; }
    public void setGrossPay(double grossPay) { this.grossPay = grossPay; }
    public void setNetPay(double netPay) { this.netPay = netPay; } 
    public void setTax(double tax) { this.tax = tax; }
    public void setDeductionId(String deductionId) { this.deductionId = deductionId; }
    public void setDate(String date) { this.date = date; }
    public void setPayperiodId(String payperiodId) { this.payperiodId = payperiodId; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public void setCutOff(String cutOff) { this.cutOff = cutOff; }
    public void setPayDate(String payDate) { this.payDate = payDate; }
    
    @Override
    public String toString() {
        return "PayrollDetails{" +
                "employeeID='" + employeeID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", grossPay=" + grossPay +
                ", netPay=" + netPay +
                '}';
    }

                
}
