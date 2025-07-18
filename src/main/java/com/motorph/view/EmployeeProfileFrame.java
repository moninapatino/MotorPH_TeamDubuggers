package com.motorph.view;

import com.motorph.model.Employee;
import com.motorph.model.PayrollEntry;
import com.motorph.model.AttendanceRecord;
import com.motorph.service.PayrollService;
import com.motorph.service.AttendanceService;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmployeeProfileFrame extends JFrame {
    private final Employee employee;
    private final PayrollService payrollService;
    private final AttendanceService attendanceService;
    private JTabbedPane tabbedPane;

    public EmployeeProfileFrame(Employee employee, PayrollService payrollService, AttendanceService attendanceService) {
        this.employee = employee;
        this.payrollService = payrollService;
        this.attendanceService = attendanceService;
        initComponents();
        setupFrame();
    }

    private void setupFrame() {
        setTitle("Employee Profile - " + employee.getFirstName() + " " + employee.getLastName());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        // Main tabbed pane
        tabbedPane = new JTabbedPane();
        
        // Add tabs
        tabbedPane.addTab("Profile", createProfilePanel());
        tabbedPane.addTab("Attendance", createAttendancePanel());
        tabbedPane.addTab("Payroll", createPayrollPanel());
        tabbedPane.addTab("Leave Management", createLeavePanel());

        add(tabbedPane);
    }

    private JPanel createProfilePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Personal Information Section
        JPanel personalInfo = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        addField(personalInfo, gbc, "Employee ID:", employee.getEmployeeId());
        addField(personalInfo, gbc, "Name:", employee.getFirstName() + " " + employee.getLastName());
        addField(personalInfo, gbc, "Email:", employee.getEmail());
        addField(personalInfo, gbc, "Phone:", employee.getPhoneNumber());
        addField(personalInfo, gbc, "Birthday:", employee.getBirthday());
        
        // Government IDs Section
        JPanel govtIds = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        addField(govtIds, gbc, "SSS Number:", employee.getSssNum());
        addField(govtIds, gbc, "PhilHealth:", employee.getPhilHealthNum());
        addField(govtIds, gbc, "TIN:", employee.getTinNum());
        addField(govtIds, gbc, "Pag-IBIG:", employee.getPagibigNum());

        // Add sections to panel
        panel.add(personalInfo, BorderLayout.NORTH);
        panel.add(govtIds, BorderLayout.CENTER);

        return new JScrollPane(panel);
    }

    private JPanel createAttendancePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Time In/Out buttons
        JPanel actionPanel = new JPanel(new FlowLayout());
        JButton timeInButton = new JButton("Time In");
        JButton timeOutButton = new JButton("Time Out");

        timeInButton.addActionListener(e -> handleTimeIn());
        timeOutButton.addActionListener(e -> handleTimeOut());

        actionPanel.add(timeInButton);
        actionPanel.add(timeOutButton);
        panel.add(actionPanel, BorderLayout.NORTH);

        // Attendance history table
        String[] columns = {"Date", "Time In", "Time Out", "Hours Worked", "Overtime"};
        JTable attendanceTable = new JTable();
        // Set up table model and load data
        
        panel.add(new JScrollPane(attendanceTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPayrollPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Current period payroll summary
        PayrollEntry currentPayroll = payrollService.getCurrentPayrollPeriod(employee.getEmployeeId());
        if (currentPayroll != null) {
            JPanel summaryPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            addField(summaryPanel, gbc, "Pay Period:", formatDate(currentPayroll.getPayPeriodStart()) + 
                                                    " to " + formatDate(currentPayroll.getPayPeriodEnd()));
            addField(summaryPanel, gbc, "Basic Pay:", formatCurrency(currentPayroll.getBasicPay()));
            addField(summaryPanel, gbc, "Allowances:", formatCurrency(currentPayroll.getAllowances()));
            addField(summaryPanel, gbc, "Overtime:", formatCurrency(currentPayroll.getOvertime()));
            addField(summaryPanel, gbc, "Deductions:", formatCurrency(currentPayroll.getDeductions()));
            addField(summaryPanel, gbc, "Net Pay:", formatCurrency(currentPayroll.getNetPay()));

            panel.add(summaryPanel, BorderLayout.NORTH);
        }

        // Payroll history table
        String[] columns = {"Period", "Gross Pay", "Deductions", "Net Pay"};
        JTable payrollTable = new JTable();
        // Set up table model and load data

        panel.add(new JScrollPane(payrollTable), BorderLayout.CENTER);

        // Action buttons
        JPanel actionPanel = new JPanel(new FlowLayout());
        JButton viewPayslipButton = new JButton("View Payslip");
        viewPayslipButton.addActionListener(e -> handleViewPayslip());
        actionPanel.add(viewPayslipButton);
        panel.add(actionPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createLeavePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Leave request form
        JPanel requestPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Add leave request components
        JButton requestLeaveButton = new JButton("Request Leave");
        requestLeaveButton.addActionListener(e -> handleLeaveRequest());
        requestPanel.add(requestLeaveButton, gbc);
        
        panel.add(requestPanel, BorderLayout.NORTH);

        // Leave history table
        String[] columns = {"Start Date", "End Date", "Type", "Status"};
        JTable leaveTable = new JTable();
        // Set up table model and load data
        
        panel.add(new JScrollPane(leaveTable), BorderLayout.CENTER);
        return panel;
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String label, String value) {
        gbc.gridx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(value), gbc);
        gbc.gridy++;
    }

    private void handleTimeIn() {
        if (attendanceService.recordTimeIn(employee.getEmployeeId())) {
            JOptionPane.showMessageDialog(this, "Time in recorded successfully");
            refreshAttendancePanel();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to record time in");
        }
    }

    private void handleTimeOut() {
        if (attendanceService.recordTimeOut(employee.getEmployeeId())) {
            JOptionPane.showMessageDialog(this, "Time out recorded successfully");
            refreshAttendancePanel();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to record time out");
        }
    }

    private void handleViewPayslip() {
        PayrollEntry currentPayroll = payrollService.getCurrentPayrollPeriod(employee.getEmployeeId());
        if (currentPayroll != null) {
            byte[] payslipPdf = payrollService.generatePayslip(
                employee.getEmployeeId(),
                currentPayroll.getPayPeriodStart(),
                currentPayroll.getPayPeriodEnd()
            );
            // Display PDF using system viewer
            // Implementation details...
        }
    }

    private void handleLeaveRequest() {
        // Show leave request dialog
        // Implementation details...
    }

    private void refreshAttendancePanel() {
        // Refresh attendance table data
        // Implementation details...
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    private String formatCurrency(java.math.BigDecimal amount) {
        return String.format("₱%,.2f", amount);
    }
}
