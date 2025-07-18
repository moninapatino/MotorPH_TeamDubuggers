package com.motorph.view;

import com.motorph.model.Employee;
import com.motorph.service.EmployeeService;
import com.motorph.service.EmployeeServiceImpl;
import javax.swing.*;
import java.awt.*;

public class EmployeePortalFrame extends JFrame {
    private final Employee employee;
    private final EmployeeService employeeService;

    public EmployeePortalFrame(Employee employee) {
        this.employee = employee;
        this.employeeService = new EmployeeServiceImpl();
        initComponents();
        setupFrame();
    }

    private void setupFrame() {
        setTitle("Employee Portal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(new JLabel("Welcome, " + employee.getFirstName() + " " + employee.getLastName()));
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Create tabs matching screenshot: Profile, Time Log, Leave, Payslip
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Profile", createProfilePanel());
        tabbedPane.addTab("Time Log", createTimeLogPanel());
        tabbedPane.addTab("Leave", createLeavePanel());
        tabbedPane.addTab("Payslip", createPayslipPanel());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createProfilePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Personal Information
        addProfileField(panel, gbc, "Employee ID:", employee.getEmployeeId());
        addProfileField(panel, gbc, "Name:", employee.getFirstName() + " " + employee.getLastName());
        addProfileField(panel, gbc, "Email:", employee.getEmail());
        addProfileField(panel, gbc, "Phone:", employee.getPhoneNumber());
        addProfileField(panel, gbc, "Birthday:", employee.getBirthday());

        // Government IDs
        addProfileField(panel, gbc, "SSS Number:", employee.getSssNum());
        addProfileField(panel, gbc, "PhilHealth:", employee.getPhilHealthNum());
        addProfileField(panel, gbc, "TIN:", employee.getTinNum());
        addProfileField(panel, gbc, "Pag-IBIG:", employee.getPagibigNum());

        return new JScrollPane(panel);
    }

    private void addProfileField(JPanel panel, GridBagConstraints gbc, String label, String value) {
        gbc.gridx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(value), gbc);
        gbc.gridy++;
    }

    private JPanel createTimeLogPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        // Add time log components here
        panel.add(new JLabel("Time Log page (to be implemented)"), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createLeavePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        // Add leave management components here
        panel.add(new JLabel("Leave page (to be implemented)"), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPayslipPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        // Add payslip components here
        panel.add(new JLabel("Payslip page (to be implemented)"), BorderLayout.CENTER);
        return panel;
    }
}
