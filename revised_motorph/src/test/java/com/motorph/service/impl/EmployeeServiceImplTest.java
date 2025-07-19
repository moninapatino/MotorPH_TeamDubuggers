package com.motorph.service.impl;

import com.motorph.dao.EmployeeDAO;
import com.motorph.exception.DuplicateEmployeeException;
import com.motorph.exception.EmployeeNotFoundException;
import com.motorph.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Unit tests for EmployeeServiceImpl demonstrating proper testing practices.
 * Uses Mockito for dependency injection and behavior verification.
 */
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    
    @Mock
    private EmployeeDAO employeeDAO;
    
    private EmployeeServiceImpl employeeService;
    private Employee testEmployee;
    
    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl(employeeDAO);
        
        testEmployee = new Employee(
            "10001",
            "John",
            "Doe",
            "john.doe@motorph.com",
            LocalDate.of(1990, 1, 1),
            "johndoe",
            "password123"
        );
        testEmployee.setPosition("HR Manager");
        testEmployee.setBasicSalary(50000.0);
    }
    
    @Test
    void createEmployee_Success() throws DuplicateEmployeeException {
        // Arrange
        when(employeeDAO.existsById(anyString())).thenReturn(false);
        when(employeeDAO.existsByUsername(anyString())).thenReturn(false);
        when(employeeDAO.existsByEmail(anyString())).thenReturn(false);
        when(employeeDAO.save(any(Employee.class))).thenReturn(testEmployee);
        
        // Act
        Employee result = employeeService.createEmployee(testEmployee);
        
        // Assert
        assertNotNull(result);
        assertEquals(testEmployee.getEmployeeId(), result.getEmployeeId());
        assertTrue(result.isActive());
        
        verify(employeeDAO).existsById(testEmployee.getEmployeeId());
        verify(employeeDAO).existsByUsername(testEmployee.getUsername());
        verify(employeeDAO).existsByEmail(testEmployee.getEmail());
        verify(employeeDAO).save(testEmployee);
    }
    
    @Test
    void createEmployee_DuplicateId_ThrowsException() {
        // Arrange
        when(employeeDAO.existsById(testEmployee.getEmployeeId())).thenReturn(true);
        
        // Act & Assert
        DuplicateEmployeeException exception = assertThrows(
            DuplicateEmployeeException.class,
            () -> employeeService.createEmployee(testEmployee)
        );
        
        assertTrue(exception.getMessage().contains("Employee ID already exists"));
        verify(employeeDAO).existsById(testEmployee.getEmployeeId());
        verify(employeeDAO, never()).save(any(Employee.class));
    }
    
    @Test
    void createEmployee_DuplicateUsername_ThrowsException() {
        // Arrange
        when(employeeDAO.existsById(anyString())).thenReturn(false);
        when(employeeDAO.existsByUsername(testEmployee.getUsername())).thenReturn(true);
        
        // Act & Assert
        DuplicateEmployeeException exception = assertThrows(
            DuplicateEmployeeException.class,
            () -> employeeService.createEmployee(testEmployee)
        );
        
        assertTrue(exception.getMessage().contains("Username already exists"));
        verify(employeeDAO).existsByUsername(testEmployee.getUsername());
        verify(employeeDAO, never()).save(any(Employee.class));
    }
    
    @Test
    void createEmployee_DuplicateEmail_ThrowsException() {
        // Arrange
        when(employeeDAO.existsById(anyString())).thenReturn(false);
        when(employeeDAO.existsByUsername(anyString())).thenReturn(false);
        when(employeeDAO.existsByEmail(testEmployee.getEmail())).thenReturn(true);
        
        // Act & Assert
        DuplicateEmployeeException exception = assertThrows(
            DuplicateEmployeeException.class,
            () -> employeeService.createEmployee(testEmployee)
        );
        
        assertTrue(exception.getMessage().contains("Email already exists"));
        verify(employeeDAO).existsByEmail(testEmployee.getEmail());
        verify(employeeDAO, never()).save(any(Employee.class));
    }
    
    @Test
    void getEmployeeById_Success() throws EmployeeNotFoundException {
        // Arrange
        when(employeeDAO.findById(testEmployee.getEmployeeId())).thenReturn(Optional.of(testEmployee));
        
        // Act
        Employee result = employeeService.getEmployeeById(testEmployee.getEmployeeId());
        
        // Assert
        assertNotNull(result);
        assertEquals(testEmployee.getEmployeeId(), result.getEmployeeId());
        verify(employeeDAO).findById(testEmployee.getEmployeeId());
    }
    
    @Test
    void getEmployeeById_NotFound_ThrowsException() {
        // Arrange
        String employeeId = "NONEXISTENT";
        when(employeeDAO.findById(employeeId)).thenReturn(Optional.empty());
        
        // Act & Assert
        EmployeeNotFoundException exception = assertThrows(
            EmployeeNotFoundException.class,
            () -> employeeService.getEmployeeById(employeeId)
        );
        
        assertTrue(exception.getMessage().contains("Employee not found with ID"));
        verify(employeeDAO).findById(employeeId);
    }
    
    @Test
    void getEmployeeByUsername_Success() throws EmployeeNotFoundException {
        // Arrange
        when(employeeDAO.findByUsername(testEmployee.getUsername())).thenReturn(Optional.of(testEmployee));
        
        // Act
        Employee result = employeeService.getEmployeeByUsername(testEmployee.getUsername());
        
        // Assert
        assertNotNull(result);
        assertEquals(testEmployee.getUsername(), result.getUsername());
        verify(employeeDAO).findByUsername(testEmployee.getUsername());
    }
    
    @Test
    void getAllActiveEmployees_Success() {
        // Arrange
        List<Employee> employees = Arrays.asList(testEmployee);
        when(employeeDAO.findAllActive()).thenReturn(employees);
        
        // Act
        List<Employee> result = employeeService.getAllActiveEmployees();
        
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testEmployee.getEmployeeId(), result.get(0).getEmployeeId());
        verify(employeeDAO).findAllActive();
    }
    
    @Test
    void getEmployeesByDepartment_Success() {
        // Arrange
        String department = "IT";
        List<Employee> employees = Arrays.asList(testEmployee);
        when(employeeDAO.findByDepartment(department)).thenReturn(employees);
        
        // Act
        List<Employee> result = employeeService.getEmployeesByDepartment(department);
        
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(department, result.get(0).getDepartment());
        verify(employeeDAO).findByDepartment(department);
    }
    
    @Test
    void getEmployeesByDepartment_NullDepartment_ThrowsException() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> employeeService.getEmployeesByDepartment(null)
        );
        
        assertTrue(exception.getMessage().contains("Department cannot be null or empty"));
        verify(employeeDAO, never()).findByDepartment(anyString());
    }
    
    @Test
    void authenticateEmployee_Success() throws EmployeeNotFoundException {
        // Arrange
        String username = "johndoe";
        String password = "password123";
        when(employeeDAO.validateCredentials(username, password)).thenReturn(true);
        when(employeeDAO.findByUsername(username)).thenReturn(Optional.of(testEmployee));
        
        // Act
        Employee result = employeeService.authenticateEmployee(username, password);
        
        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(employeeDAO).validateCredentials(username, password);
        verify(employeeDAO).findByUsername(username);
    }
    
    @Test
    void authenticateEmployee_InvalidCredentials_ThrowsException() {
        // Arrange
        String username = "johndoe";
        String password = "wrongpassword";
        when(employeeDAO.validateCredentials(username, password)).thenReturn(false);
        
        // Act & Assert
        EmployeeNotFoundException exception = assertThrows(
            EmployeeNotFoundException.class,
            () -> employeeService.authenticateEmployee(username, password)
        );
        
        assertTrue(exception.getMessage().contains("Invalid username or password"));
        verify(employeeDAO).validateCredentials(username, password);
        verify(employeeDAO, never()).findByUsername(anyString());
    }
    
    @Test
    void validateEmployee_ValidEmployee_Success() {
        // Act & Assert - should not throw exception
        assertDoesNotThrow(() -> employeeService.validateEmployee(testEmployee));
    }
    
    @Test
    void validateEmployee_NullEmployee_ThrowsException() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> employeeService.validateEmployee(null)
        );
        
        assertEquals("Employee cannot be null", exception.getMessage());
    }
    
    @Test
    void validateEmployee_EmptyEmployeeId_ThrowsException() {
        // Arrange
        testEmployee.setEmployeeId("");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> employeeService.validateEmployee(testEmployee)
        );
        
        assertTrue(exception.getMessage().contains("Employee ID is required"));
    }
    
    @Test
    void validateEmployee_InvalidEmail_ThrowsException() {
        // Arrange
        testEmployee.setEmail("invalid-email");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> employeeService.validateEmployee(testEmployee)
        );
        
        assertTrue(exception.getMessage().contains("Invalid email format"));
    }
    
    @Test
    void validateEmployee_ShortPassword_ThrowsException() {
        // Arrange
        testEmployee.setPassword("123");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> employeeService.validateEmployee(testEmployee)
        );
        
        assertTrue(exception.getMessage().contains("Password must be at least 6 characters long"));
    }
    
    @Test
    void employeeExists_True() {
        // Arrange
        when(employeeDAO.existsById(testEmployee.getEmployeeId())).thenReturn(true);
        
        // Act
        boolean result = employeeService.employeeExists(testEmployee.getEmployeeId());
        
        // Assert
        assertTrue(result);
        verify(employeeDAO).existsById(testEmployee.getEmployeeId());
    }
    
    @Test
    void employeeExists_False() {
        // Arrange
        when(employeeDAO.existsById(testEmployee.getEmployeeId())).thenReturn(false);
        
        // Act
        boolean result = employeeService.employeeExists(testEmployee.getEmployeeId());
        
        // Assert
        assertFalse(result);
        verify(employeeDAO).existsById(testEmployee.getEmployeeId());
    }
}
