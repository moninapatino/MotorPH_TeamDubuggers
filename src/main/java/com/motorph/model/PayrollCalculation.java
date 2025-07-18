package com.motorph.model;

public class PayrollCalculation {
    private final String employeeId;
    private final String employeeName;
    private final double dailyRate;
    private final double daysWorked;
    private final double riceSubsidy;
    private final double phoneAllowance;
    private final double clothingAllowance;
    private final double sssContribution;
    private final double philhealthContribution;
    private final double pagibigContribution;
    private final double totalAllowances;
    private final double totalDeductions;
    private final double grossPay;
    private final double withholdingTax;
    private final double netPay;
    private final String payDate;
    private final String payPeriodId;
    private final String startDate;
    private final String endDate;
    private final String cutOff;

    public PayrollCalculation(String employeeId, String employeeName, double dailyRate, double daysWorked,
                             double riceSubsidy, double phoneAllowance, double clothingAllowance, double sssContribution,
                             double philhealthContribution, double pagibigContribution, double totalAllowances,
                             double totalDeductions, double grossPay, double withholdingTax, double netPay,
                             String payDate, String payPeriodId, String startDate, String endDate, String cutOff) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.dailyRate = dailyRate;
        this.daysWorked = daysWorked;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.sssContribution = sssContribution;
        this.philhealthContribution = philhealthContribution;
        this.pagibigContribution = pagibigContribution;
        this.totalAllowances = totalAllowances;
        this.totalDeductions = totalDeductions;
        this.grossPay = grossPay;
        this.withholdingTax = withholdingTax;
        this.netPay = netPay;
        this.payDate = payDate;
        this.payPeriodId = payPeriodId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cutOff = cutOff;
    }

    public String getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public double getDailyRate() { return dailyRate; }
    public double getDaysWorked() { return daysWorked; }
    public double getRiceSubsidy() { return riceSubsidy; }
    public double getPhoneAllowance() { return phoneAllowance; }
    public double getClothingAllowance() { return clothingAllowance; }
    public double getSssContribution() { return sssContribution; }
    public double getPhilhealthContribution() { return philhealthContribution; }
    public double getPagibigContribution() { return pagibigContribution; }
    public double getTotalAllowances() { return totalAllowances; }
    public double getTotalDeductions() { return totalDeductions; }
    public double getGrossPay() { return grossPay; }
    public double getWithholdingTax() { return withholdingTax; }
    public double getNetPay() { return netPay; }
    public String getPayDate() { return payDate; }
    public String getPayPeriodId() { return payPeriodId; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getCutOff() { return cutOff; }
}
