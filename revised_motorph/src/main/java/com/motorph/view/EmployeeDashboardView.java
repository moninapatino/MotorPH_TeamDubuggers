package com.motorph.view;

import com.motorph.model.Employee;

import javax.swing.*;
import java.awt.*;

/**
 * Employee dashboard view with proper MVC separation.
 * Placeholder implementation demonstrating the enhanced architecture.
 */
public class EmployeeDashboardView extends JFrame {
    
    private JLabel welcomeLabel;
    private JLabel employeeInfoLabel;
    private JLabel recentLeaveRequestsLabel;
    private JLabel pendingLeaveRequestsLabel;
    
    private JButton profileButton;
    private JButton leaveRequestButton;
    private JButton attendanceButton;
    private JButton payrollButton;
    private JButton logoutButton;
    
    private Runnable logoutAction;
    private Runnable profileAction;
    private Runnable leaveRequestAction;
    private Runnable attendanceAction;
    private Runnable payrollAction;
    
    public EmployeeDashboardView() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        configureWindow();
    }
    
    private void initializeComponents() {
        welcomeLabel = new JLabel("Welcome, Employee");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        employeeInfoLabel = new JLabel("Employee Information");
        recentLeaveRequestsLabel = new JLabel("Recent Leave Requests: 0");
        pendingLeaveRequestsLabel = new JLabel("Pending Leave Requests: 0");
        
        profileButton = new JButton("My Profile");
        leaveRequestButton = new JButton("Leave Request");
        attendanceButton = new JButton("Attendance");
        payrollButton = new JButton("Payroll");
        logoutButton = new JButton("Logout");
        
        // Style buttons
        styleButton(profileButton);
        styleButton(leaveRequestButton);
        styleButton(attendanceButton);
        styleButton(payrollButton);
        styleButton(logoutButton);
    }
    
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(200, 40));
        button.setBackground(new Color(40, 167, 69));
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
        
        // Info panel
        JPanel infoPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Employee Information"));
        infoPanel.add(employeeInfoLabel);
        infoPanel.add(recentLeaveRequestsLabel);
        infoPanel.add(pendingLeaveRequestsLabel);
        
        // Menu panel
        JPanel menuPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        menuPanel.setBorder(BorderFactory.createTitledBorder("Employee Functions"));
        menuPanel.add(profileButton);
        menuPanel.add(leaveRequestButton);
        menuPanel.add(attendanceButton);
        menuPanel.add(payrollButton);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.CENTER);
        
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void setupEventHandlers() {
        profileButton.addActionListener(e -> {
            if (profileAction != null) {
                profileAction.run();
            }
        });
        
        leaveRequestButton.addActionListener(e -> {
            if (leaveRequestAction != null) {
                leaveRequestAction.run();
            }
        });
        
        attendanceButton.addActionListener(e -> {
            if (attendanceAction != null) {
                attendanceAction.run();
            }
        });
        
        payrollButton.addActionListener(e -> {
            if (payrollAction != null) {
                payrollAction.run();
            }
        });
        
        logoutButton.addActionListener(e -> {
            if (logoutAction != null) {
                logoutAction.run();
            }
        });
    }
    
    private void configureWindow() {
        setTitle("MotorPH - Employee Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
    }
    
    // Public methods for controller interaction
    public void setWelcomeMessage(String message) {
        welcomeLabel.setText(message);
    }
    
    public void setEmployeeInfo(Employee employee) {
        if (employee != null) {
            employeeInfoLabel.setText(String.format("ID: %s | Dept: %s | Position: %s", 
                employee.getEmployeeId(), 
                employee.getDepartment() != null ? employee.getDepartment() : "N/A",
                employee.getPosition() != null ? employee.getPosition() : "N/A"));
        }
    }
    
    public void setRecentLeaveRequests(int count) {
        recentLeaveRequestsLabel.setText("Recent Leave Requests: " + count);
    }
    
    public void setPendingLeaveRequests(int count) {
        pendingLeaveRequestsLabel.setText("Pending Leave Requests: " + count);
    }
    
    public void setLogoutAction(Runnable action) {
        this.logoutAction = action;
    }
    
    public void setProfileAction(Runnable action) {
        this.profileAction = action;
    }
    
    public void setLeaveRequestAction(Runnable action) {
        this.leaveRequestAction = action;
    }
    
    public void setAttendanceAction(Runnable action) {
        this.attendanceAction = action;
    }
    
    public void setPayrollAction(Runnable action) {
        this.payrollAction = action;
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
