package com.motorph.view.employee;

import com.motorph.model.Attendance;
import com.motorph.service.AttendanceService;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Map;

public class AttendancePanel extends JPanel {
    private final AttendanceService attendanceService;
    private final String employeeId;
    
    private JButton timeInButton;
    private JButton timeOutButton;
    private JLabel statusLabel;
    private JTable attendanceTable;
    private JLabel totalHoursLabel;
    private JDatePicker monthPicker;

    public AttendancePanel(AttendanceService attendanceService, String employeeId) {
        this.attendanceService = attendanceService;
        this.employeeId = employeeId;
        initializeUI();
        setupListeners();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Top Panel - Time In/Out Controls
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        timeInButton = new JButton("Time In");
        timeOutButton = new JButton("Time Out");
        statusLabel = new JLabel("Not Logged In");
        
        topPanel.add(timeInButton);
        topPanel.add(timeOutButton);
        topPanel.add(statusLabel);
        add(topPanel, BorderLayout.NORTH);

        // Center Panel - Attendance History
        JPanel centerPanel = new JPanel(new BorderLayout());
        
        // Month selector
        JPanel monthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        monthPicker = new JDatePicker(); // Month view only
        monthPanel.add(new JLabel("Month:"));
        monthPanel.add(monthPicker);
        centerPanel.add(monthPanel, BorderLayout.NORTH);

        // Attendance table
        attendanceTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Summary panel
        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalHoursLabel = new JLabel("Total Hours: 0");
        summaryPanel.add(totalHoursLabel);
        centerPanel.add(summaryPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void setupListeners() {
        timeInButton.addActionListener(e -> recordTimeIn());
        timeOutButton.addActionListener(e -> recordTimeOut());
        monthPicker.addActionListener(e -> refreshData());
    }

    private void recordTimeIn() {
        try {
            attendanceService.recordAttendance(employeeId, LocalDateTime.now());
            updateStatus("Logged In");
            refreshData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error recording time in: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void recordTimeOut() {
        try {
            attendanceService.recordTimeOut(employeeId, LocalDateTime.now());
            updateStatus("Logged Out");
            refreshData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error recording time out: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStatus(String status) {
        statusLabel.setText(status);
    }

    public void refreshData() {
        try {
            LocalDate selectedMonth = (LocalDate) monthPicker.getModel().getValue();
            if (selectedMonth == null) {
                selectedMonth = LocalDate.now();
            }

            LocalDateTime startDate = selectedMonth.withDayOfMonth(1).atStartOfDay();
            LocalDateTime endDate = selectedMonth.plusMonths(1).withDayOfMonth(1).atStartOfDay();

            // Get attendance records
            List<Attendance> attendances = attendanceService.getAttendanceByEmployee(employeeId);

            // Calculate hours
            Map<String, Double> hoursWorked = attendanceService.calculateHoursWorked(
                employeeId, startDate, endDate);

            updateAttendanceTable(attendances);
            updateTotalHours(hoursWorked);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error loading attendance data: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateAttendanceTable(List<Attendance> attendances) {
        // Update table model with attendance data
        // Implementation here
    }

    private void updateTotalHours(Map<String, Double> hoursWorked) {
        double total = hoursWorked.get("total");
        double regular = hoursWorked.get("regular");
        double overtime = hoursWorked.get("overtime");

        totalHoursLabel.setText(String.format(
            "Total Hours: %.2f (Regular: %.2f, Overtime: %.2f)",
            total, regular, overtime));
    }
}
