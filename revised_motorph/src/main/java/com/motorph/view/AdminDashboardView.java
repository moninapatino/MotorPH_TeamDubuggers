package com.motorph.view;

import com.motorph.model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Admin dashboard view with proper MVC separation.
 * Placeholder implementation demonstrating the enhanced architecture.
 */
public class AdminDashboardView extends JFrame {
    
    private JLabel welcomeLabel;
    private JLabel totalEmployeesLabel;
    private JLabel pendingLeaveRequestsLabel;
    private JLabel activePayrollPeriodsLabel;
    
    private JButton employeeManagementButton;
    private JButton leaveManagementButton;
    private JButton payrollManagementButton;
    private JButton reportsButton;
    private JButton logoutButton;
    
    private Runnable logoutAction;
    private Runnable employeeManagementAction;
    private Runnable leaveManagementAction;
    private Runnable payrollManagementAction;
    private Runnable reportsAction;
    
    public AdminDashboardView() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        configureWindow();
    }
    
    private void initializeComponents() {
        welcomeLabel = new JLabel("Welcome, Admin");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        totalEmployeesLabel = new JLabel("Total Employees: 0");
        pendingLeaveRequestsLabel = new JLabel("Pending Leave Requests: 0");
        activePayrollPeriodsLabel = new JLabel("Active Payroll Periods: 0");
        
        employeeManagementButton = new JButton("Employee Management");
        leaveManagementButton = new JButton("Leave Management");
        payrollManagementButton = new JButton("Payroll Management");
        reportsButton = new JButton("Reports");
        logoutButton = new JButton("Logout");
        
        // Style buttons
        styleButton(employeeManagementButton);
        styleButton(leaveManagementButton);
        styleButton(payrollManagementButton);
        styleButton(reportsButton);
        styleButton(logoutButton);
    }
    
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(200, 40));
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(welcomeLabel);
        headerPanel.add(Box.createHorizontalGlue());
        headerPanel.add(logoutButton);
        
        // Statistics panel
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Dashboard Statistics"));
        statsPanel.add(totalEmployeesLabel);
        statsPanel.add(pendingLeaveRequestsLabel);
        statsPanel.add(activePayrollPeriodsLabel);
        
        // Menu panel
        JPanel menuPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        menuPanel.setBorder(BorderFactory.createTitledBorder("Admin Functions"));
        menuPanel.add(employeeManagementButton);
        menuPanel.add(leaveManagementButton);
        menuPanel.add(payrollManagementButton);
        menuPanel.add(reportsButton);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(statsPanel, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.CENTER);
        
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void setupEventHandlers() {
        employeeManagementButton.addActionListener(e -> {
            if (employeeManagementAction != null) {
                employeeManagementAction.run();
            }
        });
        
        leaveManagementButton.addActionListener(e -> {
            if (leaveManagementAction != null) {
                leaveManagementAction.run();
            }
        });
        
        payrollManagementButton.addActionListener(e -> {
            if (payrollManagementAction != null) {
                payrollManagementAction.run();
            }
        });
        
        reportsButton.addActionListener(e -> {
            if (reportsAction != null) {
                reportsAction.run();
            }
        });
        
        logoutButton.addActionListener(e -> {
            if (logoutAction != null) {
                logoutAction.run();
            }
        });
    }
    
    private void configureWindow() {
        setTitle("MotorPH - Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }
    
    // Public methods for controller interaction
    public void setWelcomeMessage(String message) {
        welcomeLabel.setText(message);
    }
    
    public void setTotalEmployees(int count) {
        totalEmployeesLabel.setText("Total Employees: " + count);
    }
    
    public void setPendingLeaveRequests(int count) {
        pendingLeaveRequestsLabel.setText("Pending Leave Requests: " + count);
    }
    
    public void setActivePayrollPeriods(int count) {
        activePayrollPeriodsLabel.setText("Active Payroll Periods: " + count);
    }
    
    public void setLogoutAction(Runnable action) {
        this.logoutAction = action;
    }
    
    public void setEmployeeManagementAction(Runnable action) {
        this.employeeManagementAction = action;
    }
    
    public void setLeaveManagementAction(Runnable action) {
        this.leaveManagementAction = action;
    }
    
    public void setPayrollManagementAction(Runnable action) {
        this.payrollManagementAction = action;
    }
    
    public void setReportsAction(Runnable action) {
        this.reportsAction = action;
    }
    
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void close() {
        dispose();
    }
}
