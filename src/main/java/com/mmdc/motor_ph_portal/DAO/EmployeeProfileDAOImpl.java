
package com.mmdc.motor_ph_portal.DAO;

import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import com.mmdc.motor_ph_util.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class EmployeeProfileDAOImpl implements EmployeeProfileDAO {

    @Override
    public Admin_Class getEmployeeDetails(String employeeId) {
        Admin_Class employee = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnect.getConnection();
            String sql = "SELECT e.employee_id, e.first_name, e.last_name, e.email, e.birthday, " +
                         "e.phone, e.sss_num, e.philhealth_num, e.tin, e.pagibig_num, " +
                         "a.street, a.barangay, a.city, a.province, a.postalcode " +
                         "FROM employee e " +
                         "JOIN address a ON e.address_id = a.address_id " +
                         "WHERE e.employee_id = ?";

            pst = conn.prepareStatement(sql);
            pst.setString(1, employeeId);
            rs = pst.executeQuery();

            if (rs.next()) {
                employee = new Admin_Class(
                        rs.getString("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("birthday"),
                        null, // address_id is not directly in constructor
                        rs.getString("street"),
                        rs.getString("barangay"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("postalcode"),
                        rs.getString("phone"),
                        rs.getString("sss_num"),
                        rs.getString("philhealth_num"),
                        rs.getString("tin"),
                        rs.getString("pagibig_num"),
                        null, null) {}; // username, password not in this query
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnect.closeResources(rs, pst, conn);
        }
        return employee;
    }

    @Override
    public int getNextEmployeeId() {
        String sql = "SELECT MAX(employee_id) FROM employee";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnect.getConnection();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeResources(rs, pst, conn);
        }
        return 10001;
    }

    @Override
    public int addEmployeeAndReturnId(Admin_Class employee, int addressId) throws SQLException {
        String sql = "INSERT INTO employee (first_name, last_name, email, birthday, " +
                     "address_id, phone, sss_num, philhealth_num, tin, pagibig_num) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnect.getConnection();
            pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, employee.getFirstName());
            pst.setString(2, employee.getLastName());
            pst.setString(3, employee.getEmail());
            pst.setString(4, employee.getBirthday());
            pst.setInt(5, addressId);
            pst.setString(6, employee.getPhoneNumber());
            pst.setString(7, employee.getSssNum());
            pst.setString(8, employee.getPhilHealthNum());
            pst.setString(9, employee.getTinNum());
            pst.setString(10, employee.getPagibigNum());

            pst.executeUpdate();

            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            DatabaseConnect.closeResources(rs, pst, conn);
        }
        throw new SQLException("Failed to insert employee and retrieve ID.");
    }

    @Override
    public int addAddressAndReturnId(String street, String barangay, String city, String province, String postalcode) throws SQLException {
        String sql = "INSERT INTO address (street, barangay, city, province, postalcode) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnect.getConnection();
            pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, street);
            pst.setString(2, barangay);
            pst.setString(3, city);
            pst.setString(4, province);
            pst.setString(5, postalcode);

            pst.executeUpdate();

            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            DatabaseConnect.closeResources(rs, pst, conn);
        }
        throw new SQLException("Failed to insert address and retrieve ID.");
    }

    @Override
    public void updateEmployee(Admin_Class employee) {
        String employeeSql = "UPDATE employee SET last_name = ?, phone = ? WHERE employee_id = ?";
        String addressSql = "UPDATE address SET street = ?, barangay = ?, city = ?, province = ?, "
                + "postalcode = ? WHERE address_id = (SELECT address_id FROM employee WHERE employee_id = ?)";
        Connection conn = null;
        PreparedStatement employeePst = null;
        PreparedStatement addressPst = null;

        try {
            conn = DatabaseConnect.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // Update employee table
            employeePst = conn.prepareStatement(employeeSql);
            employeePst.setString(1, employee.getLastName());
            employeePst.setString(2, employee.getPhoneNumber());
            employeePst.setString(3, employee.getEmployeeID());
            employeePst.executeUpdate();

            // Update address table
            addressPst = conn.prepareStatement(addressSql);
            addressPst.setString(1, employee.getStreet());
            addressPst.setString(2, employee.getBarangay());
            addressPst.setString(3, employee.getCity());
            addressPst.setString(4, employee.getProvince());
            addressPst.setString(5, employee.getPostalcode());
            addressPst.setString(6, employee.getEmployeeID());
            addressPst.executeUpdate();

            conn.commit(); // Commit transaction
            JOptionPane.showMessageDialog(null, "Employee data successfully updated!");

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Error updating employee: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); // Reset auto-commit
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DatabaseConnect.closeResources(employeePst, addressPst, conn);
        }
    }

    @Override
    public void deleteEmployee(String employeeId) {
        Connection conn = null;
        PreparedStatement getAddressPst = null;
        PreparedStatement employeePst = null;
        PreparedStatement addressPst = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnect.getConnection();
            conn.setAutoCommit(false);

            int addressId = 0;
            String getAddressIdSql = "SELECT address_id FROM employee WHERE employee_id = ?";
            getAddressPst = conn.prepareStatement(getAddressIdSql);
            getAddressPst.setString(1, employeeId);
            rs = getAddressPst.executeQuery();
            if (rs.next()) {
                addressId = rs.getInt("address_id");
            } else {
                conn.rollback();
                JOptionPane.showMessageDialog(null, "No employee found with ID: " + employeeId);
                return;
            }

            String deleteEmployeeSql = "DELETE FROM employee WHERE employee_id = ?";
            employeePst = conn.prepareStatement(deleteEmployeeSql);
            employeePst.setString(1, employeeId);
            int employeeRowsAffected = employeePst.executeUpdate();

            int addressRowsAffected = 0;
            if (addressId > 0) {
                String deleteAddressSql = "DELETE FROM address WHERE address_id = ?";
                addressPst = conn.prepareStatement(deleteAddressSql);
                addressPst.setInt(1, addressId);
                addressRowsAffected = addressPst.executeUpdate();
            }

            if (employeeRowsAffected > 0) {
                conn.commit();
                String message = "Employee Profile Deleted!";
                if (addressRowsAffected > 0) {
                    message += " (Including associated address)";
                }
                JOptionPane.showMessageDialog(null, message);
            } else {
                conn.rollback();
                JOptionPane.showMessageDialog(null, "Failed to delete employee.");
            }

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Database error while deleting employee: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DatabaseConnect.closeResources(rs, getAddressPst, employeePst, addressPst, conn);
        }
    }

    @Override
    public String getEmployeeIdByEmail(String email) {
        String employeeID = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnect.getConnection();
            String sql = "SELECT employee_id FROM employee WHERE email = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                employeeID = rs.getString("employee_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeResources(rs, pst, conn);
        }
        return employeeID;
    }

    @Override
    public Admin_Class getEmployeeByUsername(String username) {
        Admin_Class employee = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String sql = "SELECT " +
                     "e.employee_id, e.first_name, e.last_name, e.email, e.birthday, " +
                     "e.address_id, a.street, a.barangay, a.city, a.province, a.postalcode, " +
                     "e.phone, e.sss_num, e.philhealth_num, e.tin, e.pagibig_num, " +
                     "p.username, p.password " +
                     "FROM employee e " +
                     "JOIN address a ON e.address_id = a.address_id " +
                     "JOIN position p ON e.employee_id = p.employee_id " +
                     "WHERE p.username = ?";

        try {
            conn = DatabaseConnect.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();

            if (rs.next()) {
                employee = new Admin_Class(
                    rs.getString("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("birthday"),
                    rs.getString("address_id"),
                    rs.getString("street"),
                    rs.getString("barangay"),
                    rs.getString("city"),
                    rs.getString("province"),
                    rs.getString("postalcode"),
                    rs.getString("phone"),
                    rs.getString("sss_num"),
                    rs.getString("philhealth_num"),
                    rs.getString("tin"),
                    rs.getString("pagibig_num"),
                    rs.getString("username"),
                    rs.getString("password")
                ) {};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeResources(rs, pst, conn);
        }
        return employee;
    }

    @Override
    public String getUsernameByEmployeeID(String employeeID) {
        String username = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT username FROM position WHERE employee_id = ?";
        try {
            conn = DatabaseConnect.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, employeeID);
            rs = pst.executeQuery();
            if (rs.next()) {
                username = rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeResources(rs, pst, conn);
        }
        return username;
    }

    @Override
    public boolean updatePassword(String employeeID, String newPassword) {
        boolean isUpdated = false;
        Connection conn = null;
        PreparedStatement pst = null;
        String updateQuery = "UPDATE position SET password = ? WHERE employee_id = ?";
        try {
            conn = DatabaseConnect.getConnection();
            pst = conn.prepareStatement(updateQuery);
            pst.setString(1, newPassword);
            pst.setString(2, employeeID);
            int rowsAffected = pst.executeUpdate();
            isUpdated = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeResources(pst, conn);
        }
        return isUpdated;
    }

    @Override
    public boolean verifyCredentials(String username, String password) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM position WHERE username = ? AND password = ?";
        try {
            conn = DatabaseConnect.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnect.closeResources(rs, pst, conn);
        }
    }
}