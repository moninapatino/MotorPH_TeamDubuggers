package com.motorph.dao;

import com.motorph.model.PayrollRecord;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Data Access Object interface for Payroll operations.
 */
public interface PayrollDAO {
    
    /**
     * Save payroll record
     * @param payrollRecord the payroll record to save
     * @return the saved payroll record
     */
    PayrollRecord save(PayrollRecord payrollRecord);
    
    /**
     * Find payroll record by ID
     * @param payrollId the payroll ID
     * @return Optional containing the payroll record if found
     */
    Optional<PayrollRecord> findById(String payrollId);
    
    /**
     * Find payroll records by employee ID
     * @param employeeId the employee ID
     * @return list of payroll records for the employee
     */
    List<PayrollRecord> findByEmployeeId(String employeeId);
    
    /**
     * Find payroll record for specific employee and pay period
     * @param employeeId the employee ID
     * @param payPeriodStart the pay period start date
     * @param payPeriodEnd the pay period end date
     * @return Optional containing the payroll record if found
     */
    Optional<PayrollRecord> findByEmployeeIdAndPayPeriod(String employeeId, 
                                                        LocalDate payPeriodStart, 
                                                        LocalDate payPeriodEnd);
    
    /**
     * Find payroll records by status
     * @param status the status (DRAFT, APPROVED, PAID)
     * @return list of payroll records with the specified status
     */
    List<PayrollRecord> findByStatus(String status);
    
    /**
     * Find payroll records within date range
     * @param startDate the start date
     * @param endDate the end date
     * @return list of payroll records within the range
     */
    List<PayrollRecord> findByDateRange(LocalDate startDate, LocalDate endDate);
    
    /**
     * Update payroll record
     * @param payrollRecord the payroll record to update
     * @return the updated payroll record
     */
    PayrollRecord update(PayrollRecord payrollRecord);
    
    /**
     * Delete payroll record
     * @param payrollId the payroll ID
     * @return true if deleted successfully
     */
    boolean deleteById(String payrollId);
    
    /**
     * Check if payroll exists for employee in pay period
     * @param employeeId the employee ID
     * @param payPeriodStart the pay period start date
     * @param payPeriodEnd the pay period end date
     * @return true if payroll exists
     */
    boolean existsByEmployeeIdAndPayPeriod(String employeeId, 
                                          LocalDate payPeriodStart, 
                                          LocalDate payPeriodEnd);
}
