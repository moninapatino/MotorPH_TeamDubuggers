
package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import java.sql.SQLException;

public interface EmployeeProfileDAO {
    Admin_Class getEmployeeDetails(String employeeId);
    int getNextEmployeeId();
    int addEmployeeAndReturnId(Admin_Class employee, int addressId) throws SQLException;
    int addAddressAndReturnId(String street, String barangay, String city, String province, String postalcode) throws SQLException;
    void updateEmployee(Admin_Class employee);
    void deleteEmployee(String employeeId);
    String getEmployeeIdByEmail(String email);
    Admin_Class getEmployeeByUsername(String username);
    String getUsernameByEmployeeID(String employeeID);
    boolean updatePassword(String employeeID, String newPassword);
    boolean verifyCredentials(String username, String password);
}
