package com.motorph.dao;

import com.motorph.model.Employee;
import com.motorph.model.Address;
import com.motorph.util.DatabaseConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private final DatabaseConnectionManager dbManager;

    public EmployeeDAOImpl(DatabaseConnectionManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public Employee findById(String employeeId) {
        try (Connection conn = dbManager.getConnection()) {
            String sql = "SELECT e.*, a.* FROM employee e " +
                        "JOIN address a ON e.address_id = a.address_id " +
                        "WHERE e.employee_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, employeeId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return mapResultSetToEmployee(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding employee by ID", e);
        }
        return null;
    }

    @Override
    public Employee findByUsername(String username) {
        try (Connection conn = dbManager.getConnection()) {
            String sql = "SELECT e.*, a.* FROM employee e " +
                        "JOIN address a ON e.address_id = a.address_id " +
                        "JOIN position p ON e.employee_id = p.employee_id " +
                        "WHERE p.username = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return mapResultSetToEmployee(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding employee by username", e);
        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = dbManager.getConnection()) {
            String sql = "SELECT e.*, a.* FROM employee e " +
                        "JOIN address a ON e.address_id = a.address_id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                employees.add(mapResultSetToEmployee(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all employees", e);
        }
        return employees;
    }

    @Override
    public boolean update(Employee employee) {
        try (Connection conn = dbManager.getConnection()) {
            // Start transaction
            conn.setAutoCommit(false);
            try {
                // Update address first
                updateAddress(conn, employee.getAddress());
                
                // Then update employee
                String sql = "UPDATE employee SET first_name=?, last_name=?, email=?, " +
                           "birthday=?, phone_number=?, sss_number=?, philhealth_number=?, " +
                           "tin_number=?, pagibig_number=? WHERE employee_id=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, employee.getFirstName());
                pst.setString(2, employee.getLastName());
                pst.setString(3, employee.getEmail());
                pst.setString(4, employee.getBirthday());
                pst.setString(5, employee.getPhoneNumber());
                pst.setString(6, employee.getSssNum());
                pst.setString(7, employee.getPhilHealthNum());
                pst.setString(8, employee.getTinNum());
                pst.setString(9, employee.getPagibigNum());
                pst.setString(10, employee.getEmployeeId());

                int result = pst.executeUpdate();
                conn.commit();
                return result > 0;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating employee", e);
        }
    }

    @Override
    public boolean delete(String employeeId) {
        try (Connection conn = dbManager.getConnection()) {
            String sql = "DELETE FROM employee WHERE employee_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, employeeId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting employee", e);
        }
    }

    @Override
    public Employee authenticate(String username, String password) {
        try (Connection conn = dbManager.getConnection()) {
            String sql = "SELECT e.*, a.*, ur.role_name FROM employee e " +
                        "JOIN address a ON e.address_id = a.address_id " +
                        "JOIN position p ON e.employee_id = p.employee_id " +
                        "JOIN user_role ur ON p.position_id = ur.position_id " +
                        "WHERE p.username = ? AND p.password = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return mapResultSetToEmployee(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error authenticating employee", e);
        }
        return null;
    }

    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        Address address = new Address(
            rs.getString("address_id"),
            rs.getString("street"),
            rs.getString("barangay"),
            rs.getString("city"),
            rs.getString("province"),
            rs.getString("postal_code")
        );

        return new Employee(
            rs.getString("employee_id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("email"),
            rs.getString("birthday"),
            address,
            rs.getString("phone_number"),
            rs.getString("sss_number"),
            rs.getString("philhealth_number"),
            rs.getString("tin_number"),
            rs.getString("pagibig_number"),
            rs.getString("username"),
            rs.getString("role_name")
        );
    }

    private void updateAddress(Connection conn, Address address) throws SQLException {
        String sql = "UPDATE address SET street=?, barangay=?, city=?, " +
                    "province=?, postal_code=? WHERE address_id=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, address.getStreet());
        pst.setString(2, address.getBarangay());
        pst.setString(3, address.getCity());
        pst.setString(4, address.getProvince());
        pst.setString(5, address.getPostalCode());
        pst.setString(6, address.getAddressId());
        pst.executeUpdate();
    }
}
