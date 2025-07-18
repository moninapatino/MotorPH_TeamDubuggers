package com.motorph.model;

import java.math.BigDecimal;

public class PayrollDetails {
    private final String employeeID;
    private final String employeeName;
    private final BigDecimal daysRate;
    private final BigDecimal daysWorked;
    private final BigDecimal riceA;
    private final BigDecimal phoneA;
    private final BigDecimal clothingA;
    private final BigDecimal sssC;
    private final BigDecimal philhealthC;
    private final BigDecimal pagibigC;
    private final BigDecimal totalAllowances;
    private final BigDecimal totalDeductions;
    private final BigDecimal grossPay;
    private final BigDecimal tax;
    private final BigDecimal netPay;
    private final String payDate;
    private final String payperiodId;
    private final String startDate;
    private final String endDate;
    private final String cutOff;

    public PayrollDetails(String employeeID, String employeeName, BigDecimal daysRate, BigDecimal daysWorked,
                         BigDecimal riceA, BigDecimal phoneA, BigDecimal clothingA, BigDecimal sssC,
                         BigDecimal philhealthC, BigDecimal pagibigC, BigDecimal totalAllowances,
                         BigDecimal totalDeductions, BigDecimal grossPay, BigDecimal tax, BigDecimal netPay,
                         String payDate, String payperiodId, String startDate, String endDate, String cutOff) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.daysRate = daysRate;
        this.daysWorked = daysWorked;
        this.riceA = riceA;
        this.phoneA = phoneA;
        this.clothingA = clothingA;
        this.sssC = sssC;
        this.philhealthC = philhealthC;
        this.pagibigC = pagibigC;
        this.totalAllowances = totalAllowances;
        this.totalDeductions = totalDeductions;
        this.grossPay = grossPay;
        this.tax = tax;
        this.netPay = netPay;
        this.payDate = payDate;
        this.payperiodId = payperiodId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cutOff = cutOff;
    }

    public String getEmployeeID() { return employeeID; }
    public String getEmployeeName() { return employeeName; }
    public BigDecimal getDaysRate() { return daysRate; }
    public BigDecimal getDaysWorked() { return daysWorked; }
    public BigDecimal getRiceA() { return riceA; }
    public BigDecimal getPhoneA() { return phoneA; }
    public BigDecimal getClothingA() { return clothingA; }
    public BigDecimal getSssC() { return sssC; }
    public BigDecimal getPhilhealthC() { return philhealthC; }
    public BigDecimal getPagibigC() { return pagibigC; }
    public BigDecimal getTotalAllowances() { return totalAllowances; }
    public BigDecimal getTotalDeductions() { return totalDeductions; }
    public BigDecimal getGrossPay() { return grossPay; }
    public BigDecimal getTax() { return tax; }
    public BigDecimal getNetPay() { return netPay; }
    public String getPayDate() { return payDate; }
    public String getPayperiodId() { return payperiodId; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getCutOff() { return cutOff; }
}
