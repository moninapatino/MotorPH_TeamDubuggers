package com.motorph.view.admin;

import com.motorph.model.Employee;
import com.motorph.service.AdminService;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EmployeeManagementPanel extends JPanel {
    private final AdminService adminService;
    
    private JTable employeeTable;
    private JTextField searchField;
    private JButton addButton;
    private JButton editButton;
    private JButton deactivateButton;
    private JComboBox<String> departmentFilter;

    public EmployeeManagementPanel(AdminService adminService) {
        this.adminService = adminService;
        initializeUI();
        setupListeners();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Search and filter panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        departmentFilter = new JComboBox<>(new String[]{"All Departments", "HR", "IT", "Finance", "Operations"});
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(new JLabel("Department:"));
        topPanel.add(departmentFilter);
        add(topPanel, BorderLayout.NORTH);

        // Employee table
        employeeTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addButton = new JButton("Add Employee");
        editButton = new JButton("Edit Employee");
        deactivateButton = new JButton("Deactivate Employee");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deactivateButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { searchEmployees(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { searchEmployees(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { searchEmployees(); }
        });

        departmentFilter.addActionListener(e -> filterByDepartment());

        addButton.addActionListener(e -> showAddEmployeeDialog());
        editButton.addActionListener(e -> showEditEmployeeDialog());
        deactivateButton.addActionListener(e -> deactivateSelectedEmployee());
    }

    public void refreshData() {
        try {
            String department = departmentFilter.getSelectedItem().toString();
            List<Employee> employees;
            
            if ("All Departments".equals(department)) {
                employees = adminService.getActiveEmployees();
            } else {
                employees = adminService.getEmployeesByDepartment(department);
            }
            
            updateEmployeeTable(employees);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error loading employees: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchEmployees() {
        try {
            String query = searchField.getText();
            List<Employee> employees = adminService.searchEmployees(query);
            updateEmployeeTable(employees);
        } catch (Exception e) {
            // Handle error
        }
    }

    private void filterByDepartment() {
        refreshData();
    }

    private void updateEmployeeTable(List<Employee> employees) {
        // Update table model with employee data
    }

    private void showAddEmployeeDialog() {
        // Show dialog to add new employee
    }

    private void showEditEmployeeDialog() {
        // Show dialog to edit selected employee
    }

    private void deactivateSelectedEmployee() {
        // Deactivate selected employee
    }
}
