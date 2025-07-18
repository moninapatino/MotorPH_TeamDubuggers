package com.motorph.view.employee;

import com.motorph.model.Employee;
import com.motorph.service.EmployeeService;
import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private final EmployeeService employeeService;
    private final String employeeId;
    
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField departmentField;
    private JTextField positionField;
    private JButton updateButton;

    public ProfilePanel(EmployeeService employeeService, String employeeId) {
        this.employeeService = employeeService;
        this.employeeId = employeeId;
        initializeUI();
        setupListeners();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Personal Information Section
        addSection(formPanel, gbc, "Personal Information");
        addFormField(formPanel, gbc, "First Name:", firstNameField = new JTextField(20));
        addFormField(formPanel, gbc, "Last Name:", lastNameField = new JTextField(20));
        addFormField(formPanel, gbc, "Email:", emailField = new JTextField(20));

        // Employment Information Section
        addSection(formPanel, gbc, "Employment Information");
        addFormField(formPanel, gbc, "Department:", departmentField = new JTextField(20));
        addFormField(formPanel, gbc, "Position:", positionField = new JTextField(20));

        // Make fields read-only
        firstNameField.setEditable(false);
        lastNameField.setEditable(false);
        emailField.setEditable(false);
        departmentField.setEditable(false);
        positionField.setEditable(false);

        // Add form to scroll pane
        JScrollPane scrollPane = new JScrollPane(formPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        updateButton = new JButton("Update Profile");
        buttonPanel.add(updateButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addSection(JPanel panel, GridBagConstraints gbc, String title) {
        gbc.gridwidth = 2;
        gbc.gridy++;
        panel.add(new JLabel(title), gbc);
        gbc.gridwidth = 1;
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String label, JComponent field) {
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void setupListeners() {
        updateButton.addActionListener(e -> showUpdateProfileDialog());
    }

    public void refreshData() {
        try {
            Employee employee = employeeService.getEmployeeById(employeeId);
            
            firstNameField.setText(employee.getFirstName());
            lastNameField.setText(employee.getLastName());
            emailField.setText(employee.getEmail());
            departmentField.setText(employee.getDepartment());
            positionField.setText(employee.getPosition());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error loading profile: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showUpdateProfileDialog() {
        // Show dialog to update profile
        // This could be limited to certain fields that employees are allowed to update
    }
}
