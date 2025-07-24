package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_portal.AdminAccess.PayrollCalculation;
import com.mmdc.motor_ph_util.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class PayrollDAOImpl implements PayrollDAO {

    @Override
    public PayrollCalculation getPayrollDetails(String employeeId, int monthNumber) {
        PayrollCalculation payroll = null;

        String sql = """
            SELECT * FROM payrollsystem_db.vw_payslip_report 
            WHERE employee_id = ? 
            AND MONTH(period_start_date) = ?
        """;

        try (
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)
        ) {
            pst.setString(1, employeeId);
            pst.setInt(2, monthNumber);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    payroll = new PayrollCalculation(
                        rs.getString("employee_id"),
                        rs.getString("employee_name"),
                        rs.getDouble("hourly_rate"),
                        rs.getDouble("net_hours_paid"),
                        rs.getDouble("rice_allowance"),
                        rs.getDouble("phone_allowance"),
                        rs.getDouble("clothing_allowance"),
                        rs.getDouble("sss"),
                        rs.getDouble("philhealth"),
                        rs.getDouble("pagibig"),
                        rs.getDouble("total_allowance"),
                        rs.getDouble("total_deductions"),
                        rs.getDouble("gross_income"),
                        rs.getDouble("withholding_tax"),
                        rs.getDouble("take_home_pay"),
                        rs.getString("pay_date"),
                        null, null, null, null // Optional placeholders
                    );
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace(); // Use logging for production
        }

        return payroll;
    }

    @Override
    public ArrayList<Vector<String>> getPayslipsByEmployeeId(String employeeId) {
        ArrayList<Vector<String>> payslipList = new ArrayList<>();

        if (employeeId == null || employeeId.trim().isEmpty()) {
            return payslipList;
        }

        String sql = """
            SELECT 
                p.payslip_number, 
                p.employee_id, 
                pp.start_date, 
                pp.end_date, 
                pp.pay_date, 
                p.status
            FROM payslip p
            JOIN pay_period pp ON p.payperiod_id = pp.payperiod_id
            WHERE p.employee_id = ?
            ORDER BY pp.pay_date DESC
        """;

        try (
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)
        ) {
            pst.setString(1, employeeId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Vector<String> row = new Vector<>();
                    row.add(rs.getString("pay_date"));
                    row.add(rs.getString("payslip_number"));
                    row.add(rs.getString("employee_id"));
                    row.add(rs.getString("start_date"));
                    row.add(rs.getString("end_date"));
                    row.add(rs.getString("status"));
                    payslipList.add(row);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging if needed
        }

        return payslipList;
    }
}
