package com.motorph.dao;

import com.motorph.model.Employee;
import java.util.List;

public interface EmployeeDAO {
    Employee findById(String employeeId);
    Employee findByUsername(String username);
    List<Employee> findAll();
    void save(Employee employee);
    void update(Employee employee);
    void delete(String employeeId);
}
