package com.motorph.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 * PayrollRecord entity representing employee payroll information.
 * Uses BigDecimal for precise financial calculations.
 */
public class PayrollRecord {
    
    private String payrollId;
    
    @NotBlank(message = "Employee ID is required")
    private String employeeId;
    
    @NotNull(message = "Pay period start date is required")
    private LocalDate payPeriodStart;
    
    @NotNull(message = "Pay period end date is required")
    private LocalDate payPeriodEnd;
    
    @PositiveOrZero(message = "Days worked must be non-negative")
    private double daysWorked;
    
    @PositiveOrZero(message = "Daily rate must be non-negative")
    private BigDecimal dailyRate;
    
    // Allowances
    private BigDecimal riceAllowance;
    private BigDecimal phoneAllowance;
    private BigDecimal clothingAllowance;
    
    // Deductions
    private BigDecimal sssContribution;
    private BigDecimal philHealthContribution;
    private BigDecimal pagibigContribution;
    private BigDecimal withholdingTax;
    
    // Calculated fields
    private BigDecimal grossPay;
    private BigDecimal totalAllowances;
    private BigDecimal totalDeductions;
    private BigDecimal netPay;
    
    private LocalDate payDate;
    private String status; // DRAFT, APPROVED, PAID
    
    // Default constructor
    public PayrollRecord() {
        this.riceAllowance = BigDecimal.ZERO;
        this.phoneAllowance = BigDecimal.ZERO;
        this.clothingAllowance = BigDecimal.ZERO;
        this.sssContribution = BigDecimal.ZERO;
        this.philHealthContribution = BigDecimal.ZERO;
        this.pagibigContribution = BigDecimal.ZERO;
        this.withholdingTax = BigDecimal.ZERO;
        this.status = "DRAFT";
    }
    
    // Constructor with required fields
    public PayrollRecord(String employeeId, LocalDate payPeriodStart, 
                        LocalDate payPeriodEnd, double daysWorked, BigDecimal dailyRate) {
        this();
        this.employeeId = employeeId;
        this.payPeriodStart = payPeriodStart;
        this.payPeriodEnd = payPeriodEnd;
        this.daysWorked = daysWorked;
        this.dailyRate = dailyRate;
        calculatePayroll();
    }
    
    // Getters and Setters
    public String getPayrollId() {
        return payrollId;
    }
    
    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public LocalDate getPayPeriodStart() {
        return payPeriodStart;
    }
    
    public void setPayPeriodStart(LocalDate payPeriodStart) {
        this.payPeriodStart = payPeriodStart;
    }
    
    public LocalDate getPayPeriodEnd() {
        return payPeriodEnd;
    }
    
    public void setPayPeriodEnd(LocalDate payPeriodEnd) {
        this.payPeriodEnd = payPeriodEnd;
    }
    
    public double getDaysWorked() {
        return daysWorked;
    }
    
    public void setDaysWorked(double daysWorked) {
        this.daysWorked = daysWorked;
        calculatePayroll();
    }
    
    public BigDecimal getDailyRate() {
        return dailyRate;
    }
    
    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
        calculatePayroll();
    }
    
    public BigDecimal getRiceAllowance() {
        return riceAllowance;
    }
    
    public void setRiceAllowance(BigDecimal riceAllowance) {
        this.riceAllowance = riceAllowance;
        calculatePayroll();
    }
    
    public BigDecimal getPhoneAllowance() {
        return phoneAllowance;
    }
    
    public void setPhoneAllowance(BigDecimal phoneAllowance) {
        this.phoneAllowance = phoneAllowance;
        calculatePayroll();
    }
    
    public BigDecimal getClothingAllowance() {
        return clothingAllowance;
    }
    
    public void setClothingAllowance(BigDecimal clothingAllowance) {
        this.clothingAllowance = clothingAllowance;
        calculatePayroll();
    }
    
    public BigDecimal getSssContribution() {
        return sssContribution;
    }
    
    public void setSssContribution(BigDecimal sssContribution) {
        this.sssContribution = sssContribution;
        calculatePayroll();
    }
    
    public BigDecimal getPhilHealthContribution() {
        return philHealthContribution;
    }
    
    public void setPhilHealthContribution(BigDecimal philHealthContribution) {
        this.philHealthContribution = philHealthContribution;
        calculatePayroll();
    }
    
    public BigDecimal getPagibigContribution() {
        return pagibigContribution;
    }
    
    public void setPagibigContribution(BigDecimal pagibigContribution) {
        this.pagibigContribution = pagibigContribution;
        calculatePayroll();
    }
    
    public BigDecimal getWithholdingTax() {
        return withholdingTax;
    }
    
    public void setWithholdingTax(BigDecimal withholdingTax) {
        this.withholdingTax = withholdingTax;
        calculatePayroll();
    }
    
    public BigDecimal getGrossPay() {
        return grossPay;
    }
    
    public BigDecimal getTotalAllowances() {
        return totalAllowances;
    }
    
    public BigDecimal getTotalDeductions() {
        return totalDeductions;
    }
    
    public BigDecimal getNetPay() {
        return netPay;
    }
    
    public LocalDate getPayDate() {
        return payDate;
    }
    
    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * Calculate payroll amounts based on current values
     */
    public void calculatePayroll() {
        if (dailyRate == null) {
            dailyRate = BigDecimal.ZERO;
        }
        
        // Calculate gross pay
        this.grossPay = dailyRate.multiply(BigDecimal.valueOf(daysWorked))
                                 .setScale(2, RoundingMode.HALF_UP);
        
        // Calculate total allowances
        this.totalAllowances = riceAllowance.add(phoneAllowance).add(clothingAllowance)
                                           .setScale(2, RoundingMode.HALF_UP);
        
        // Calculate total deductions
        this.totalDeductions = sssContribution.add(philHealthContribution)
                                             .add(pagibigContribution)
                                             .add(withholdingTax)
                                             .setScale(2, RoundingMode.HALF_UP);
        
        // Calculate net pay
        this.netPay = grossPay.add(totalAllowances).subtract(totalDeductions)
                             .setScale(2, RoundingMode.HALF_UP);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PayrollRecord that = (PayrollRecord) o;
        return Objects.equals(payrollId, that.payrollId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(payrollId);
    }
    
    @Override
    public String toString() {
        return "PayrollRecord{" +
                "payrollId='" + payrollId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", payPeriodStart=" + payPeriodStart +
                ", payPeriodEnd=" + payPeriodEnd +
                ", daysWorked=" + daysWorked +
                ", grossPay=" + grossPay +
                ", netPay=" + netPay +
                ", status='" + status + '\'' +
                '}';
    }
}
