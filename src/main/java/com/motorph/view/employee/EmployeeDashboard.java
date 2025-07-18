package com.motorph.view.employee;

import com.motorph.model.Employee;
import com.motorph.model.Attendance;
import com.motorph.service.EmployeeService;
import com.motorph.service.AttendanceService;
import com.motorph.util.ServiceLocator;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class EmployeeDashboard extends JFrame {
    private final EmployeeService employeeService;
    private final AttendanceService attendanceService;
    private final String employeeId;
    
    private JTabbedPane tabbedPane;
    private ProfilePanel profilePanel;
    private AttendancePanel attendancePanel;
    private PayrollPanel payrollPanel;
    private LeaveRequestPanel leavePanel;

    public EmployeeDashboard(String employeeId) {
        this.employeeId = employeeId;
        this.employeeService = ServiceLocator.getService(EmployeeService.class);
        this.attendanceService = ServiceLocator.getService(AttendanceService.class);
        
        initializeUI();
        setupPanels();
        loadData();
    }

    private void initializeUI() {
        setTitle("MotorPH Employee Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        add(tabbedPane);
    }

    private void setupPanels() {
        profilePanel = new ProfilePanel(employeeService, employeeId);
        attendancePanel = new AttendancePanel(attendanceService, employeeId);
        payrollPanel = new PayrollPanel(employeeService, employeeId);
        leavePanel = new LeaveRequestPanel(employeeService, employeeId);

        tabbedPane.addTab("My Profile", profilePanel);
        tabbedPane.addTab("Attendance", attendancePanel);
        tabbedPane.addTab("Payroll", payrollPanel);
        tabbedPane.addTab("Leave Requests", leavePanel);
    }

    private void loadData() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                profilePanel.refreshData();
                attendancePanel.refreshData();
                payrollPanel.refreshData();
                leavePanel.refreshData();
                return null;
            }
        };
        worker.execute();
    }
}
