package com.motorph.service;

import com.motorph.PayrollDAOImpl;
import com.motorph.dao.PayrollDAO;
import com.motorph.model.PayrollEntry;
import net.sf.jasperreports.engine.*;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayrollServiceImpl implements PayrollService {
    private final PayrollDAO payrollDAO;

    public PayrollServiceImpl(PayrollDAOImpl payrollDAO2) {
        this.payrollDAO = payrollDAO2;
    }

    @Override
    public List<PayrollEntry> getEmployeePayrollHistory(String employeeId) {
        return payrollDAO.findByEmployeeId(employeeId);
    }

    @Override
    public PayrollEntry getCurrentPayrollPeriod(String employeeId) {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.withDayOfMonth(1);
        LocalDate endDate = today.withDayOfMonth(today.lengthOfMonth());
        return payrollDAO.findByEmployeeIdAndPeriod(employeeId, startDate, endDate);
    }

    @Override
    public byte[] generatePayslip(String employeeId, LocalDate payPeriodStart, LocalDate payPeriodEnd) {
        try {
            PayrollEntry payroll = payrollDAO.findByEmployeeIdAndPeriod(employeeId, payPeriodStart, payPeriodEnd);
            if (payroll == null) {
                return null;
            }

            // Load the JasperReport template
            JasperReport report = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/reportPayslipTemplate.jrxml"));

            // Set parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("employeeId", employeeId);
            parameters.put("startDate", payPeriodStart);
            parameters.put("endDate", payPeriodEnd);
            parameters.put("basicPay", payroll.getBasicPay());
            parameters.put("grossPay", payroll.getGrossPay());
            parameters.put("netPay", payroll.getNetPay());
            parameters.put("deductions", payroll.getDeductions());
            parameters.put("allowances", payroll.getAllowances());
            parameters.put("overtime", payroll.getOvertime());

            // Fill the report and export to PDF
            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(print, output);
            return output.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating payslip", e);
        }
    }
}
