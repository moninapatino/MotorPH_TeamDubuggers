package com.motorph.service;

import com.motorph.model.PayrollEntry;
import java.time.LocalDate;
import java.util.List;

public interface PayrollService {
    List<PayrollEntry> getEmployeePayrollHistory(String employeeId);
    PayrollEntry getCurrentPayrollPeriod(String employeeId);
    byte[] generatePayslip(String employeeId, LocalDate payPeriodStart, LocalDate payPeriodEnd);
}
