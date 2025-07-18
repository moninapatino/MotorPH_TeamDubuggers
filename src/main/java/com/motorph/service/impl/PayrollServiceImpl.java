package com.motorph.service.impl;

import com.motorph.model.PayrollEntry;
import com.motorph.service.PayrollService;
import com.motorph.dao.PayrollDAO;
import net.sf.jasperreports.engine.*;
import java.util.HashMap;
import java.util.Map;

public abstract class PayrollServiceImpl implements PayrollService {
    private final PayrollDAO payrollDAO;

    public PayrollServiceImpl(PayrollDAO payrollDAO) {
        this.payrollDAO = payrollDAO;
    }

    @Override
    public PayrollEntry getPayrollEntry(String employeeId, int monthNumber) {
        return payrollDAO.findPayrollEntry(employeeId, monthNumber);
    }

    @Override
    public String generatePayslip(String employeeId, int monthNumber) throws Exception {
        PayrollEntry payroll = getPayrollEntry(employeeId, monthNumber);
        if (payroll == null) throw new Exception("No payroll record found");
        // Compile and fill JasperReport
        String reportPath = "C:\\Users\\Administrator\\Desktop\\VSPractice\\MotorPH_TeamDubuggers\\src\\main\\java\\com\\mmdc\\motor_ph_util\\reportPayslipTemplate.jrxml";
        JasperReport jr = JasperCompileManager.compileReport(reportPath);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("employee_id", employeeId);
        parameters.put("month_number", monthNumber);
        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, payrollDAO.getConnection());
        if (jp.getPages().isEmpty()) throw new Exception("No Payslip Data");
        String outputPath = "payslip_" + employeeId + "_" + monthNumber + ".pdf";
        JasperExportManager.exportReportToPdfFile(jp, outputPath);
        return outputPath;
    }
}
