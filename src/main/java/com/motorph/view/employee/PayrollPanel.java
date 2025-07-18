package com.motorph.view.employee;

import com.motorph.service.EmployeeService;

import net.sourceforge.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.math.BigDecimal;

public class PayrollPanel extends JPanel {
    private final EmployeeService employeeService;
    private final String employeeId;
    
    private JDatePicker monthPicker;
    private JTable payrollTable;
    private JLabel totalPayLabel;
    private JLabel netPayLabel;
    private JButton viewPayslipButton;
    private JButton downloadPayslipButton;

    public PayrollPanel(EmployeeService employeeService, String employeeId) {
        this.employeeService = employeeService;
        this.employeeId = employeeId;
        initializeUI();
        setupListeners();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Top Panel - Month Selection
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        monthPicker = new JDatePicker();
        topPanel.add(new JLabel("Pay Period:"));
        topPanel.add(monthPicker);
        add(topPanel, BorderLayout.NORTH);

        // Center Panel - Payroll Details
        JPanel centerPanel = new JPanel(new BorderLayout());
        
        // Payroll table
        payrollTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(payrollTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Summary Panel
        JPanel summaryPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        totalPayLabel = new JLabel("Total Pay: ₱0.00");
        netPayLabel = new JLabel("Net Pay: ₱0.00");
        summaryPanel.add(totalPayLabel);
        summaryPanel.add(netPayLabel);
        centerPanel.add(summaryPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel - Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        viewPayslipButton = new JButton("View Payslip");
        downloadPayslipButton = new JButton("Download Payslip");
        buttonPanel.add(viewPayslipButton);
        buttonPanel.add(downloadPayslipButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        monthPicker.addActionListener(e -> refreshData());
        viewPayslipButton.addActionListener(e -> viewPayslip());
        downloadPayslipButton.addActionListener(e -> downloadPayslip());
    }

    public void refreshData() {
        try {
            LocalDate selectedMonth = (LocalDate) monthPicker.getModel().getValue();
            if (selectedMonth == null) {
                selectedMonth = LocalDate.now();
            }

            // Get payroll data
            PayrollData payroll = employeeService.getPayrollData(employeeId, selectedMonth);
            updatePayrollTable(payroll);
            updateSummary(payroll);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error loading payroll data: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePayrollTable(PayrollData payroll) {
        // Update table with payroll details
        // Implementation here
    }

    private void updateSummary(PayrollData payroll) {
        totalPayLabel.setText(String.format("Total Pay: ₱%.2f", payroll.getTotalPay()));
        netPayLabel.setText(String.format("Net Pay: ₱%.2f", payroll.getNetPay()));
    }

    private void viewPayslip() {
        try {
            LocalDate selectedMonth = (LocalDate) monthPicker.getModel().getValue();
            if (selectedMonth == null) {
                JOptionPane.showMessageDialog(this,
                    "Please select a pay period",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            byte[] payslipPdf = employeeService.generatePayslip(employeeId, selectedMonth);
            // Show PDF viewer dialog
            // Implementation here

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error viewing payslip: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void downloadPayslip() {
        try {
            LocalDate selectedMonth = (LocalDate) monthPicker.getModel().getValue();
            if (selectedMonth == null) {
                JOptionPane.showMessageDialog(this,
                    "Please select a pay period",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File(String.format(
                "payslip_%s_%d_%d.pdf",
                employeeId,
                selectedMonth.getMonthValue(),
                selectedMonth.getYear()
            )));

            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                byte[] payslipPdf = employeeService.generatePayslip(employeeId, selectedMonth);
                // Save PDF file
                // Implementation here
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error downloading payslip: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
