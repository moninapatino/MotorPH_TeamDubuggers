package com.motorph.dao;

import com.motorph.model.PayrollEntry;
import java.time.LocalDate;
import java.util.List;

public interface PayrollDAO {
    List<PayrollEntry> findByEmployeeId(String employeeId);
    PayrollEntry findByEmployeeIdAndPeriod(String employeeId, LocalDate startDate, LocalDate endDate);
    boolean save(PayrollEntry entry);
}
