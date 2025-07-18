package com.motorph.view.admin;
import com.motorph.service.AdminService;
import com.motorph.util.ServiceLocator;
import javax.swing.*;

public class AdminDashboard extends JFrame {
    private final AdminService adminService;
    
    private JTabbedPane tabbedPane;
    private EmployeeManagementPanel employeePanel;
    private AttendanceManagementPanel attendancePanel;
    private ReportsPanel reportsPanel;

    public AdminDashboard(String adminId) {
        this.adminService = ServiceLocator.getService(AdminService.class);
        initializeUI();
        setupPanels();
        loadData();
    }

    private void initializeUI() {
        setTitle("MotorPH Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        add(tabbedPane);
    }

    private void setupPanels() {
        employeePanel = new EmployeeManagementPanel(adminService);
        attendancePanel = new AttendanceManagementPanel(adminService, null);
        reportsPanel = new ReportsPanel(adminService);

        tabbedPane.addTab("Employee Management", employeePanel);
        tabbedPane.addTab("Attendance Management", attendancePanel);
        tabbedPane.addTab("Reports", reportsPanel);
    }

    private void loadData() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                employeePanel.refreshData();
                attendancePanel.refreshData();
                return null;
            }
        };
        worker.execute();
    }
}
