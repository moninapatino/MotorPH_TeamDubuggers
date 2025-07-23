package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeProfileDAOImplTest {

    private EmployeeProfileDAOImpl employeeDAO;

    @BeforeEach
    public void setUp() {
        employeeDAO = new EmployeeProfileDAOImpl();
    }

    @Test
    public void testGetEmployeeDetails_ValidId() {
        String employeeId = "10001"; // Ensure this employee exists in the test DB
        Admin_Class employee = employeeDAO.getEmployeeDetails(employeeId);
        assertNotNull(employee);
        assertEquals(employeeId, employee.getEmployeeID());
    }

    @Test
    public void testGetEmployeeDetails_InvalidId() {
        String employeeId = "99999"; // Assume this doesn't exist
        Admin_Class employee = employeeDAO.getEmployeeDetails(employeeId);
        assertNull(employee);
    }

    @Test
    public void testGetNextEmployeeId() {
        int nextId = employeeDAO.getNextEmployeeId();
        assertTrue(nextId >= 10001);
    }

    @Test
    public void testAddAndDeleteEmployee() throws Exception {
        // Add address first
        int addressId = employeeDAO.addAddressAndReturnId("Test Street", "Test Barangay", "Test City", "Test Province", "0000");
        assertTrue(addressId > 0);

        Admin_Class employee = new Admin_Class(
                null,
                "Test",
                "User",
                "test.user@example.com",
                "1990-01-01",
                null,
                "Test Street",
                "Test Barangay",
                "Test City",
                "Test Province",
                "0000",
                "09123456789",
                "123456789",
                "987654321",
                "111222333",
                "444555666",
                null,
                null
        ) {
        };

        int employeeId = employeeDAO.addEmployeeAndReturnId(employee, addressId);
        assertTrue(employeeId > 0);

        // Clean up
        employeeDAO.deleteEmployee(String.valueOf(employeeId));
        Admin_Class deleted = employeeDAO.getEmployeeDetails(String.valueOf(employeeId));
        assertNull(deleted);
    }

    @Test
    public void testVerifyCredentials_Success() {
        String username = "EHernandez";
        String password = "P@ssword";
        assertTrue(employeeDAO.verifyCredentials(username, password));
    }

    @Test
    public void testVerifyCredentials_Failure() {
        assertFalse(employeeDAO.verifyCredentials("Admin123", "fakepassword"));
    }
}
