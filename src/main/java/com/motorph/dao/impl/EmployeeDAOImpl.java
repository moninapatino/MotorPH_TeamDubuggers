package com.motorph.dao.impl;

import com.motorph.model.Employee;
import com.motorph.model.Address;
import com.motorph.util.DatabaseConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl {
    private final DatabaseConnectionManager dbManager;

    public EmployeeDAOImpl(DatabaseConnectionManager dbManager) {
        this.dbManager = dbManager;
    }

    // Find employee by ID
    public Employee findById(String employeeId) {
        String sql = "SELECT e.*, a.* FROM employee e JOIN address a ON e.address_id = a.address_id WHERE e.employee_id = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, employeeId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEmployee(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Add a new employee (assumes address already exists and address_id is set)
    public boolean addEmployee(Employee employee) {
        String sql = "INSERT INTO employee (employee_id, first_name, last_name, email, birthday, address_id, phone_number, sss_number, philhealth_number, tin_number, pagibig_number, username, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, employee.getEmployeeId());
            pst.setString(2, employee.getFirstName());
            pst.setString(3, employee.getLastName());
            pst.setString(4, employee.getEmail());
            pst.setString(5, employee.getBirthday());
            pst.setString(6, employee.getAddress() != null ? employee.getAddress().getAddressId() : null);
            pst.setString(7, employee.getPhoneNumber());
            pst.setString(8, employee.getSssNum());
            pst.setString(9, employee.getPhilHealthNum());
            pst.setString(10, employee.getTinNum());
            pst.setString(11, employee.getPagibigNum());
            pst.setString(12, employee.getUsername());
            pst.setString(13, employee.getRole());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an employee (and address)
    public boolean updateEmployee(Employee employee) {
        String sqlEmp = "UPDATE employee SET first_name=?, last_name=?, email=?, birthday=?, phone_number=?, sss_number=?, philhealth_number=?, tin_number=?, pagibig_number=?, username=?, role=? WHERE employee_id=?";
        String sqlAddr = "UPDATE address SET street=?, barangay=?, city=?, province=?, postal_code=? WHERE address_id=?";
        try (Connection conn = dbManager.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // Update address
                if (employee.getAddress() != null) {
                    try (PreparedStatement pstAddr = conn.prepareStatement(sqlAddr)) {
                        pstAddr.setString(1, employee.getAddress().getStreet());
                        pstAddr.setString(2, employee.getAddress().getBarangay());
                        pstAddr.setString(3, employee.getAddress().getCity());
                        pstAddr.setString(4, employee.getAddress().getProvince());
                        pstAddr.setString(5, employee.getAddress().getPostalCode());
                        pstAddr.setString(6, employee.getAddress().getAddressId());
                        pstAddr.executeUpdate();
                    }
                }
                // Update employee
                try (PreparedStatement pstEmp = conn.prepareStatement(sqlEmp)) {
                    pstEmp.setString(1, employee.getFirstName());
                    pstEmp.setString(2, employee.getLastName());
                    pstEmp.setString(3, employee.getEmail());
                    pstEmp.setString(4, employee.getBirthday());
                    pstEmp.setString(5, employee.getPhoneNumber());
                    pstEmp.setString(6, employee.getSssNum());
                    pstEmp.setString(7, employee.getPhilHealthNum());
                    pstEmp.setString(8, employee.getTinNum());
                    pstEmp.setString(9, employee.getPagibigNum());
                    pstEmp.setString(10, employee.getUsername());
                    pstEmp.setString(11, employee.getRole());
                    pstEmp.setString(12, employee.getEmployeeId());
                    int result = pstEmp.executeUpdate();
                    conn.commit();
                    return result > 0;
                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an employee
    public boolean deleteEmployee(String employeeId) {
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, employeeId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // List all employees
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT e.*, a.* FROM employee e JOIN address a ON e.address_id = a.address_id";
        try (Connection conn = dbManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                employees.add(mapResultSetToEmployee(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // Map ResultSet to Employee object
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
            rs.getString("role")
        );
    }
}
