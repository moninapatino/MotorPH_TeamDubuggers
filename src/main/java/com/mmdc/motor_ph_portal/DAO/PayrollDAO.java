
package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_portal.AdminAccess.PayrollCalculation;
import java.util.ArrayList;
import java.util.Vector;

public interface PayrollDAO {
    PayrollCalculation getPayrollDetails(String employeeId, int monthNumber);
    ArrayList<Vector<String>> getPayslipsByEmployeeId(String employeeId);

}