package com.motorph.service.impl;

import com.motorph.service.EmployeeService;
import com.motorph.dao.EmployeeDAO;
import com.motorph.model.Employee;
import com.motorph.exception.ServiceException;
import java.util.List;
import java.util.Optional;

public abstract class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee getEmployeeById(String employeeId) throws ServiceException {
        try {
            return employeeDAO.findById(employeeId)
                .orElseThrow(() -> new ServiceException("Employee not found: " + employeeId));
        } catch (Exception e) {
            throw new ServiceException("Error retrieving employee", e);
        }
    }

    @Override
    public List<Employee> getAllEmployees() throws ServiceException {
        try {
            return employeeDAO.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error retrieving employees", e);
        }
    }

    @Override
    public void createEmployee(Employee employee) throws ServiceException {
        try {
            // Validate employee data
            validateEmployee(employee);
            employeeDAO.save(employee);
        } catch (Exception e) {
            throw new ServiceException("Error creating employee", e);
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws ServiceException {
        try {
            // Validate employee exists
            if (employeeDAO.findById(employee.getEmployeeId()).isEmpty()) {
                throw new ServiceException("Employee not found: " + employee.getEmployeeId());
            }
            
            // Validate employee data
            validateEmployee(employee);
            employeeDAO.update(employee);
        } catch (Exception e) {
            throw new ServiceException("Error updating employee", e);
        }
    }

    @Override
    public void deleteEmployee(String employeeId) throws ServiceException {
        try {
            // Check if employee exists
            if (employeeDAO.findById(employeeId).isEmpty()) {
                throw new ServiceException("Employee not found: " + employeeId);
            }
            employeeDAO.delete(employeeId);
        } catch (Exception e) {
            throw new ServiceException("Error deleting employee", e);
        }
    }


    private void validateEmployee(Employee employee) throws ServiceException {
        if (employee.getEmployeeId() == null || employee.getEmployeeId().trim().isEmpty()) {
            throw new ServiceException("Employee ID is required");
        }
        if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) {
            throw new ServiceException("First name is required");
        }
        if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) {
            throw new ServiceException("Last name is required");
        }
        // Add more validations as needed
    }
}
