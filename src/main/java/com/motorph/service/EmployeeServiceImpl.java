package com.motorph.service;

import java.util.List;

import com.motorph.dao.EmployeeDAO;
import com.motorph.dao.EmployeeDAOImpl;
import com.motorph.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public Employee getEmployeeById(String employeeId) {
        return employeeDAO.findById(employeeId);
    }

    @Override
    public Employee getEmployeeByUsername(String username) {
        return employeeDAO.findByUsername(username);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeDAO.update(employee);
    }

    @Override
    public boolean deleteEmployee(String employeeId) {
        return employeeDAO.delete(employeeId);
    }

    @Override
    public Employee authenticateEmployee(String username, String password) {
        return employeeDAO.authenticate(username, password);
    }
}
