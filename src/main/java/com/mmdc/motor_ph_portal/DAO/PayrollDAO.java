
package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_portal.AdminAccess.PayrollCalculation;

public interface PayrollDAO {
    PayrollCalculation getPayrollDetails(String employeeId, int monthNumber);
}