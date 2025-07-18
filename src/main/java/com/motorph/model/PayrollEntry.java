package com.motorph.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PayrollEntry {
    private String employeeId;
    private LocalDate payPeriodStart;
    private LocalDate payPeriodEnd;
    private BigDecimal basicPay;
    private BigDecimal grossPay;
    private BigDecimal netPay;
    private BigDecimal deductions;
    private BigDecimal allowances;
    private BigDecimal overtime;
    private int daysWorked;
    private int daysAbsent;

    public PayrollEntry(String employeeId, LocalDate payPeriodStart, LocalDate payPeriodEnd,
                       BigDecimal basicPay, BigDecimal grossPay, BigDecimal netPay,
                       BigDecimal deductions, BigDecimal allowances, BigDecimal overtime,
                       int daysWorked, int daysAbsent) {
        this.employeeId = employeeId;
        this.payPeriodStart = payPeriodStart;
        this.payPeriodEnd = payPeriodEnd;
        this.basicPay = basicPay;
        this.grossPay = grossPay;
        this.netPay = netPay;
        this.deductions = deductions;
        this.allowances = allowances;
        this.overtime = overtime;
        this.daysWorked = daysWorked;
        this.daysAbsent = daysAbsent;
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public LocalDate getPayPeriodStart() { return payPeriodStart; }
    public LocalDate getPayPeriodEnd() { return payPeriodEnd; }
    public BigDecimal getBasicPay() { return basicPay; }
    public BigDecimal getGrossPay() { return grossPay; }
    public BigDecimal getNetPay() { return netPay; }
    public BigDecimal getDeductions() { return deductions; }
    public BigDecimal getAllowances() { return allowances; }
    public BigDecimal getOvertime() { return overtime; }
    public int getDaysWorked() { return daysWorked; }
    public int getDaysAbsent() { return daysAbsent; }

    public char[] getHourlyRate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHourlyRate'");
    }

    public char[] getRiceSubsidy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRiceSubsidy'");
    }

    public char[] getPhoneAllowance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPhoneAllowance'");
    }

    public char[] getSssContribution() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSssContribution'");
    }
}
