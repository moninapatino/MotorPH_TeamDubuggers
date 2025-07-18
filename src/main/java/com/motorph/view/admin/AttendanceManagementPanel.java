package com.motorph.view.admin;

import com.motorph.model.Attendance;
import com.motorph.service.AdminService;
import com.motorph.service.AttendanceService;

import net.sourceforge.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

public class AttendanceManagementPanel extends JPanel {
    private final AdminService adminService;
    private final AttendanceService attendanceService;
    
    private JTable attendanceTable;
    private JDatePicker datePicker;
    private JButton viewButton;
    private JButton editButton;
    private JButton exportButton;
    private JComboBox<String> filterCombo;

    public AttendanceManagementPanel(AdminService adminService, AttendanceService attendanceService) {
        this.adminService = adminService;
        this.attendanceService = attendanceService;
        initializeUI();
        setupListeners();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Top control panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePicker = new JDatePicker(); // You'll need to implement or use a third-party date picker
        filterCombo = new JComboBox<>(new String[]{"All", "Present", "Late", "Absent"});
        controlPanel.add(new JLabel("Date:"));
        controlPanel.add(datePicker);
        controlPanel.add(new JLabel("Status:"));
        controlPanel.add(filterCombo);
        add(controlPanel, BorderLayout.NORTH);

        // Attendance table
        attendanceTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        viewButton = new JButton("View Details");
        editButton = new JButton("Edit Record");
        exportButton = new JButton("Export Report");
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(exportButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        viewButton.addActionListener(e -> viewSelectedAttendance());
        editButton.addActionListener(e -> editSelectedAttendance());
        exportButton.addActionListener(e -> exportAttendanceReport());
        
        filterCombo.addActionListener(e -> refreshData());
    }

    public void refreshData() {
        try {
            LocalDate selectedDate = (LocalDate) datePicker.getModel().getValue();
            if (selectedDate == null) {
                selectedDate = LocalDate.now();
            }

            List<Attendance> attendances;
            String filter = (String) filterCombo.getSelectedItem();
            
            switch (filter) {
                case "Late":
                    attendances = attendanceService.getLateAttendances(
                        selectedDate.atStartOfDay());
                    break;
                case "Absent":
                    List<String> absentIds = attendanceService.getAbsentEmployees(
                        selectedDate.atStartOfDay());
                    // Convert absent IDs to attendance records
                    break;
                default:
                    attendances = adminService.getAttendanceReport(
                        selectedDate.atStartOfDay(),
                        selectedDate.plusDays(1).atStartOfDay());
            }
            
            updateAttendanceTable(attendances);
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

    private void viewSelectedAttendance() {
        int selectedRow = attendanceTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this,
                "Please select an attendance record to view",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Show attendance details dialog
        // Implementation here
    }

    private void editSelectedAttendance() {
        int selectedRow = attendanceTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this,
                "Please select an attendance record to edit",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Show edit dialog
        // Implementation here
    }

    private void exportAttendanceReport() {
        try {
            LocalDate selectedDate = (LocalDate) datePicker.getModel().getValue();
            if (selectedDate == null) {
                selectedDate = LocalDate.now();
            }

            Map<String, Object> report = adminService.generateAttendanceReport(
                selectedDate.atStartOfDay(),
                selectedDate.plusDays(1).atStartOfDay()
            );

            // Show export dialog
            // Implementation here
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error exporting report: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
