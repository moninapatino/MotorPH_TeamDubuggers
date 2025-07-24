package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_portal.AdminAccess.PayrollCalculation;
import java.time.LocalDate;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for PayrollDAOImpl#getPayrollDetails
 */
public class PayrollDAOImplTest {

    private PayrollDAO payrollDAO;

    @BeforeEach
    public void setUp() {
        payrollDAO = new PayrollDAOImpl();
    }

   @Test
    public void testGetPayrollDetails_validEmployeeAndMonth_shouldReturnPayrollCalculation() {
        // Arrange
        String employeeId = "10003"; // Make sure this ID exists in the test database
        int monthNumber = 7; // July — period_start_date is in July

        // Act
        PayrollCalculation result = payrollDAO.getPayrollDetails(employeeId, monthNumber);

        // Assert
        assertNotNull(result, "PayrollCalculation should not be null for valid employee and month.");
        assertEquals(employeeId, result.getEmployeeID(), "Employee ID should match.");

        // Parse the pay date (assumes format YYYY-MM-DD)
        LocalDate payDate = LocalDate.parse(result.getPayDate());

        // Adjusted: payDate is expected to be in monthNumber + 1
        int expectedPayDateMonth = (monthNumber % 12) + 1; // handle December wrap-around
        assertEquals(expectedPayDateMonth, payDate.getMonthValue(), 
                     "Pay date's month should be the month after the queried period start month.");
    }



    @Test
    public void testGetPayrollDetails_invalidEmployee_shouldReturnNull() {
        // Arrange
        String invalidEmployeeId = "99999"; // Assume does not exist
        int monthNumber = 6;

        // Act
        PayrollCalculation result = payrollDAO.getPayrollDetails(invalidEmployeeId, monthNumber);

        // Assert
        assertNull(result, "Should return null if employee does not exist.");
    }

    @Test
    public void testGetPayrollDetails_noDataForMonth_shouldReturnNull() {
        // Arrange
        String employeeId = "10003"; // Assume valid
        int monthNumber = 1; // Assume no data 

        // Act
        PayrollCalculation result = payrollDAO.getPayrollDetails(employeeId, monthNumber);

        // Assert
        assertNull(result, "Should return null if no payroll data exists for that month.");
    }
}
