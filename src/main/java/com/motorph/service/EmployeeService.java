package com.motorph.service;

import java.util.List;

import com.motorph.model.Employee;

public interface EmployeeService {
    Employee getEmployeeById(String employeeId);
    Employee getEmployeeByUsername(String username);
    List<Employee> getAllEmployees();
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(String employeeId);
    Employee authenticateEmployee(String username, String password);
}
