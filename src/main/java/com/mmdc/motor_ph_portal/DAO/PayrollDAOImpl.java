package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_portal.AdminAccess.PayrollCalculation;
import com.mmdc.motor_ph_util.DatabaseConnect; // Import your DatabaseConnect class
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayrollDAOImpl implements PayrollDAO { // Changed to concrete class

    // Method to establish a connection to the database
    private Connection connect() {
        return DatabaseConnect.getConnection(); // Use your existing method to get the connection
    }

    @Override
    public PayrollCalculation getPayrollDetails(String employeeId, int monthNumber) {
        PayrollCalculation payroll = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = connect();

            // SQL query to fetch payroll details
            String sql = "SELECT * FROM payrollsystem_db.vw_payslip_report WHERE employee_id = ? AND MONTH(period_start_date) = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, employeeId);
            pst.setInt(2, monthNumber); 

            rs = pst.executeQuery();

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
                    null, null, null, null // Assuming these are placeholders for future use
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close resources
            DatabaseConnect.closeResources(rs, pst, conn); // Use your existing method to close resources
        }

        return payroll;
    }
}
