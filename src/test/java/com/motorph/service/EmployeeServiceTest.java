package com.motorph.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.motorph.model.Employee;
import com.motorph.service.EmployeeService;
import com.motorph.service.EmployeeServiceImpl;

class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    void testAuthenticate_validAdmin() {
        Employee employee = employeeService.authenticateEmployee("Admin", "admin");
        assertNotNull(employee);
        assertTrue(employee.isAdmin());
    }

    @Test
    void testAuthenticate_validEmployee() {
        Employee employee = employeeService.authenticateEmployee("10001", "password");
        assertNotNull(employee);
        assertFalse(employee.isAdmin());
    }

    @Test
    void testAuthenticate_invalid() {
        Employee employee = employeeService.authenticateEmployee("invalid", "invalid");
        assertNull(employee);
    }

    @Test
    void testGetEmployeeById_found() {
        Employee employee = employeeService.getEmployeeById("10001");
        assertNotNull(employee);
        assertEquals("10001", employee.getEmployeeId());
    }

    @Test
    void testGetEmployeeById_notFound() {
        Employee employee = employeeService.getEmployeeById("INVALID");
        assertNull(employee);
    }
}
