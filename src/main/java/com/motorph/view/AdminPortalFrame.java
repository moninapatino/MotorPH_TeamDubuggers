package com.motorph.view;

import com.motorph.model.Employee;
import com.motorph.service.EmployeeService;
import com.motorph.service.EmployeeServiceImpl;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminPortalFrame extends JFrame {
    private final EmployeeService employeeService;
    private JTable employeeTable;
    private JButton viewButton;
    private JButton editButton;
    private JButton deleteButton;

    public AdminPortalFrame(Employee admin) {
        this.employeeService = new EmployeeServiceImpl();
        initComponents(admin);
        loadEmployeeData();
        setupFrame();
    }

    private void setupFrame() {
        setTitle("Admin Portal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents(Employee admin) {
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(new JLabel("Welcome, " + admin.getFirstName() + " " + admin.getLastName()));
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Create table
        String[] columns = {"ID", "Name", "Email", "Role"};
        employeeTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        viewButton = new JButton("View Details");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        viewButton.addActionListener(e -> handleViewEmployee());
        editButton.addActionListener(e -> handleEditEmployee());
        deleteButton.addActionListener(e -> handleDeleteEmployee());

        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void loadEmployeeData() {
        List<Employee> employees = employeeService.getAllEmployees();
        // Convert employees to table model and set to table
        // Implementation details...
    }

    private void handleViewEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeId = (String) employeeTable.getValueAt(selectedRow, 0);
            Employee employee = employeeService.getEmployeeById(employeeId);
            if (employee != null) {
                // Show employee details dialog
                // Implementation details...
            }
        }
    }

    private void handleEditEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeId = (String) employeeTable.getValueAt(selectedRow, 0);
            Employee employee = employeeService.getEmployeeById(employeeId);
            if (employee != null) {
                // Show edit employee dialog
                // Implementation details...
            }
        }
    }

    private void handleDeleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeId = (String) employeeTable.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this employee?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (employeeService.deleteEmployee(employeeId)) {
                    loadEmployeeData(); // Refresh table
                    JOptionPane.showMessageDialog(this, "Employee deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete employee");
                }
            }
        }
    }
}
