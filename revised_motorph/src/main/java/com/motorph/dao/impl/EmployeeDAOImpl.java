package com.motorph.dao.impl;

import com.motorph.dao.EmployeeDAO;
import com.motorph.model.Employee;
import com.motorph.util.DatabaseConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of EmployeeDAO using JDBC for database operations.
 * Follows proper error handling and resource management.
 */
public class EmployeeDAOImpl implements EmployeeDAO {
    
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);
    private final DatabaseConnectionManager connectionManager;
    
    public EmployeeDAOImpl(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    
    @Override
    public Employee save(Employee employee) {
        String sql = """
            INSERT INTO employees (employee_id, first_name, last_name, email, birthday, 
                                 phone_number, sss_number, philhealth_number, tin_number, 
                                 pagibig_number, username, password, department, position, 
                                 basic_salary, is_active) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            setEmployeeParameters(stmt, employee);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Employee saved successfully: {}", employee.getEmployeeId());
                return employee;
            } else {
                throw new RuntimeException("Failed to save employee");
            }
            
        } catch (SQLException e) {
            logger.error("Error saving employee: {}", employee.getEmployeeId(), e);
            throw new RuntimeException("Database error while saving employee", e);
        }
    }
    
    @Override
    public Optional<Employee> findById(String employeeId) {
        String sql = "SELECT * FROM employees WHERE employee_id = ?";
        
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, employeeId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToEmployee(rs));
                }
            }
            
        } catch (SQLException e) {
            logger.error("Error finding employee by ID: {}", employeeId, e);
            throw new RuntimeException("Database error while finding employee", e);
        }
        
        return Optional.empty();
    }
    
    @Override
    public Optional<Employee> findByUsername(String username) {
        String sql = "SELECT * FROM employees WHERE username = ? AND is_active = true";
        
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToEmployee(rs));
                }
            }
            
        } catch (SQLException e) {
            logger.error("Error finding employee by username: {}", username, e);
            throw new RuntimeException("Database error while finding employee", e);
        }
        
        return Optional.empty();
    }
    
    @Override
    public Optional<Employee> findByEmail(String email) {
        String sql = "SELECT * FROM employees WHERE email = ? AND is_active = true";
        
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToEmployee(rs));
                }
            }
            
        } catch (SQLException e) {
            logger.error("Error finding employee by email: {}", email, e);
            throw new RuntimeException("Database error while finding employee", e);
        }
        
        return Optional.empty();
    }
    
    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees ORDER BY last_name, first_name";
        List<Employee> employees = new ArrayList<>();
        
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                employees.add(mapResultSetToEmployee(rs));
            }
            
        } catch (SQLException e) {
            logger.error("Error finding all employees", e);
            throw new RuntimeException("Database error while finding employees", e);
        }
        
        return employees;
    }
    
    @Override
    public List<Employee> findAllActive() {
        String sql = "SELECT * FROM employees WHERE is_active = true ORDER BY last_name, first_name";
        List<Employee> employees = new ArrayList<>();
        
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                employees.add(mapResultSetToEmployee(rs));
            }
            
        } catch (SQLException e) {
            logger.error("Error finding active employees", e);
            throw new RuntimeException("Database error while finding employees", e);
        }
        
        return employees;
    }
    
    @Override
    public List<Employee> findByDepartment(String department) {
        String sql = "SELECT * FROM employees WHERE department = ? AND is_active = true ORDER BY last_name, first_name";
        List<Employee> employees = new ArrayList<>();
        
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, department);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    employees.add(mapResultSetToEmployee(rs));
                }
            }
            
        } catch (SQLException e) {
            logger.error("Error finding employees by department: {}", department, e);
            throw new RuntimeException("Database error while finding employees", e);
        }
        
        return employees;
    }
    
    @Override
    public Employee update(Employee employee) {
        String sql = """
            UPDATE employees SET first_name = ?, last_name = ?, email = ?, birthday = ?, 
                               phone_number = ?, sss_number = ?, philhealth_number = ?, 
                               tin_number = ?, pagibig_number = ?, username = ?, password = ?, 
                               department = ?, position = ?, basic_salary = ?, is_active = ? 
            WHERE employee_id = ?
            """;
        
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            setEmployeeParameters(stmt, employee);
            stmt.setString(16, employee.getEmployeeId()); // WHERE clause parameter
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Employee updated successfully: {}", employee.getEmployeeId());
                return employee;
            } else {
                throw new RuntimeException("Employee not found for update: " + employee.getEmployeeId());
            }
            
        } catch (SQLException e) {
            logger.error("Error updating employee: {}", employee.getEmployeeId(), e);
            throw new RuntimeException("Database error while updating employee", e);
        }
    }
    
    @Override
    public boolean deleteById(String employeeId) {
        String sql = "UPDATE employees SET is_active = false WHERE employee_id = ?";
        
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, employeeId);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Employee deactivated successfully: {}", employeeId);
                return true;
            }
            
        } catch (SQLException e) {
            logger.error("Error deactivating employee: {}", employeeId, e);
            throw new RuntimeException("Database error while deactivating employee", e);
        }
        
        return false;
    }
    
    @Override
    public boolean existsById(String employeeId) {
        String sql = "SELECT 1 FROM employees WHERE employee_id = ?";
        
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, employeeId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
            
        } catch (SQLException e) {
            logger.error("Error checking employee existence: {}", employeeId, e);
            throw new RuntimeException("Database error while checking employee existence", e);
        }
    }
    
    @Override
    public boolean existsByUsername(String username) {
        String sql = "SELECT 1 FROM employees WHERE username = ?";
        
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
            
        } catch (SQLException e) {
            logger.error("Error checking username existence: {}", username, e);
            throw new RuntimeException("Database error while checking username existence", e);
        }
    }
    
    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT 1 FROM employees WHERE email = ?";
        
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
            
        } catch (SQLException e) {
            logger.error("Error checking email existence: {}", email, e);
            throw new RuntimeException("Database error while checking email existence", e);
        }
    }
    
    @Override
    public boolean validateCredentials(String username, String password) {
        String sql = "SELECT 1 FROM employees WHERE username = ? AND password = ? AND is_active = true";
        
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password); // In production, use hashed passwords
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
            
        } catch (SQLException e) {
            logger.error("Error validating credentials for username: {}", username, e);
            throw new RuntimeException("Database error while validating credentials", e);
        }
    }
    
    /**
     * Helper method to set employee parameters in PreparedStatement
     */
    private void setEmployeeParameters(PreparedStatement stmt, Employee employee) throws SQLException {
        stmt.setString(1, employee.getEmployeeId());
        stmt.setString(2, employee.getFirstName());
        stmt.setString(3, employee.getLastName());
        stmt.setString(4, employee.getEmail());
        stmt.setDate(5, Date.valueOf(employee.getBirthday()));
        stmt.setString(6, employee.getPhoneNumber());
        stmt.setString(7, employee.getSssNumber());
        stmt.setString(8, employee.getPhilHealthNumber());
        stmt.setString(9, employee.getTinNumber());
        stmt.setString(10, employee.getPagibigNumber());
        stmt.setString(11, employee.getUsername());
        stmt.setString(12, employee.getPassword());
        stmt.setString(13, employee.getDepartment());
        stmt.setString(14, employee.getPosition());
        stmt.setDouble(15, employee.getBasicSalary());
        stmt.setBoolean(16, employee.isActive());
    }
    
    /**
     * Helper method to map ResultSet to Employee object
     */
    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getString("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setEmail(rs.getString("email"));
        
        Date birthday = rs.getDate("birthday");
        if (birthday != null) {
            employee.setBirthday(birthday.toLocalDate());
        }
        
        employee.setPhoneNumber(rs.getString("phone_number"));
        employee.setSssNumber(rs.getString("sss_number"));
        employee.setPhilHealthNumber(rs.getString("philhealth_number"));
        employee.setTinNumber(rs.getString("tin_number"));
        employee.setPagibigNumber(rs.getString("pagibig_number"));
        employee.setUsername(rs.getString("username"));
        employee.setPassword(rs.getString("password"));
        employee.setDepartment(rs.getString("department"));
        employee.setPosition(rs.getString("position"));
        employee.setBasicSalary(rs.getDouble("basic_salary"));
        employee.setActive(rs.getBoolean("is_active"));
        
        return employee;
    }
}
