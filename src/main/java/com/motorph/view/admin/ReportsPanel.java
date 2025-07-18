package com.motorph.view.admin;

import com.motorph.service.AdminService;

import net.sourceforge.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Map;

public class ReportsPanel extends JPanel {
    private final AdminService adminService;
    
    private JDatePicker startDatePicker;
    private JDatePicker endDatePicker;
    private JComboBox<String> reportTypeCombo;
    private JButton generateButton;
    private JTextArea previewArea;
    private JButton exportButton;
    private JButton printButton;

    public ReportsPanel(AdminService adminService) {
        this.adminService = adminService;
        initializeUI();
        setupListeners();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Control Panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        startDatePicker = new JDatePicker();
        endDatePicker = new JDatePicker();
        reportTypeCombo = new JComboBox<>(new String[]{
            "Attendance Summary",
            "Payroll Report",
            "Leave Report",
            "Late Employees Report",
            "Department Summary"
        });
        generateButton = new JButton("Generate Report");

        controlPanel.add(new JLabel("Start Date:"));
        controlPanel.add(startDatePicker);
        controlPanel.add(new JLabel("End Date:"));
        controlPanel.add(endDatePicker);
        controlPanel.add(new JLabel("Report Type:"));
        controlPanel.add(reportTypeCombo);
        controlPanel.add(generateButton);

        add(controlPanel, BorderLayout.NORTH);

        // Preview Area
        previewArea = new JTextArea();
        previewArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(previewArea);
        add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exportButton = new JButton("Export");
        printButton = new JButton("Print");
        buttonPanel.add(exportButton);
        buttonPanel.add(printButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        generateButton.addActionListener(e -> generateReport());
        exportButton.addActionListener(e -> exportReport());
        printButton.addActionListener(e -> printReport());
    }

    private void generateReport() {
        try {
            LocalDate startDate = (LocalDate) startDatePicker.getModel().getValue();
            LocalDate endDate = (LocalDate) endDatePicker.getModel().getValue();
            
            if (startDate == null || endDate == null) {
                JOptionPane.showMessageDialog(this,
                    "Please select both start and end dates",
                    "Invalid Date Range",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (startDate.isAfter(endDate)) {
                JOptionPane.showMessageDialog(this,
                    "Start date must be before end date",
                    "Invalid Date Range",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            String reportType = (String) reportTypeCombo.getSelectedItem();
            Map<String, Object> reportData;

            switch (reportType) {
                case "Attendance Summary":
                    reportData = adminService.generateAttendanceReport(
                        startDate.atStartOfDay(),
                        endDate.plusDays(1).atStartOfDay()
                    );
                    break;
                case "Payroll Report":
                    reportData = adminService.generatePayrollReport(
                        startDate.atStartOfDay(),
                        endDate.plusDays(1).atStartOfDay()
                    );
                    break;
                case "Leave Report":
                    reportData = adminService.generateLeaveReport(
                        startDate.atStartOfDay(),
                        endDate.plusDays(1).atStartOfDay()
                    );
                    break;
                default:
                    throw new IllegalStateException("Unsupported report type: " + reportType);
            }

            displayReport(reportData);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error generating report: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayReport(Map<String, Object> reportData) {
        // Format and display report data in preview area
        StringBuilder report = new StringBuilder();
        report.append("Report Generated: ").append(LocalDateTime.now()).append("\n\n");
        
        reportData.forEach((key, value) -> {
            report.append(key).append(":\n");
            report.append(value.toString()).append("\n\n");
        });

        previewArea.setText(report.toString());
    }

    private void exportReport() {
        if (previewArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please generate a report first",
                "No Report",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            // Implement export functionality
            // Could export to PDF, Excel, or other formats
        }
    }

    private void printReport() {
        if (previewArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please generate a report first",
                "No Report",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            previewArea.print();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error printing report: " + e.getMessage(),
                "Print Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
