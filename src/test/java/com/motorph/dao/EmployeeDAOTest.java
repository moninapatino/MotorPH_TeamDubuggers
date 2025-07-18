package com.motorph.dao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.motorph.dao.EmployeeDAO;
import com.motorph.dao.EmployeeDAOImpl;
import com.motorph.model.Employee;

class EmployeeDAOTest {
    private final EmployeeDAO dao = new EmployeeDAOImpl();

    @Test
    void testFindById_found() {
        Employee employee = dao.findById("10001");
        assertNotNull(employee);
        assertEquals("10001", employee.getEmployeeId());
    }

    @Test
    void testFindById_notFound() {
        Employee employee = dao.findById("INVALID");
        assertNull(employee);
    }

    @Test
    void testFindByUsername_found() {
        Employee employee = dao.findByUsername("Admin");
        assertNotNull(employee);
        assertTrue(employee.isAdmin());
    }

    @Test
    void testFindByUsername_notFound() {
        Employee employee = dao.findByUsername("invalid");
        assertNull(employee);
    }

    @Test
    void testFindAll_notEmpty() {
        List<Employee> employees = dao.findAll();
        assertFalse(employees.isEmpty());
    }

    @Test
    void testAuthenticate_validCredentials() {
        Employee employee = dao.authenticate("Admin", "admin");
        assertNotNull(employee);
        assertTrue(employee.isAdmin());
    }

    @Test
    void testAuthenticate_invalidCredentials() {
        Employee employee = dao.authenticate("invalid", "invalid");
        assertNull(employee);
    }
}
