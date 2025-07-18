package com.motorph;

import com.motorph.view.LoginFrame;
import com.motorph.service.*;
import com.motorph.util.ServiceLocator;
import com.motorph.dao.*;
import com.motorph.dao.impl.AttendanceDAOImpl;

import javax.swing.*;
import java.awt.*;

public class MotorPhPortal {
    private static void setupServices() {
        // Initialize DAOs
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        PayrollDAOImpl payrollDAO = new PayrollDAOImpl();
        AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
        LeaveRecordDAO leaveDAO = new LeaveRecordDAOImpl();
        PasswordRecoveryDAO passwordRecoveryDAO = new PasswordRecoveryDAOImpl();

        // Initialize Services
        EmployeeService employeeService = new EmployeeServiceImpl();
        PayrollService payrollService = new PayrollServiceImpl(payrollDAO);
        AttendanceService attendanceService = new AttendanceServiceImpl(attendanceDAO);
        LeaveManagementService leaveService = new LeaveManagementServiceImpl(leaveDAO);
        PasswordRecoveryService passwordService = new PasswordRecoveryServiceImpl(passwordRecoveryDAO);

        // Register services in ServiceLocator
        ServiceLocator.registerService(EmployeeService.class, employeeService);
        ServiceLocator.registerService(PayrollService.class, payrollService);
        ServiceLocator.registerService(AttendanceService.class, attendanceService);
        ServiceLocator.registerService(LeaveManagementService.class, leaveService);
        ServiceLocator.registerService(PasswordRecoveryService.class, passwordService);
    }

    private static void setupLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Fallback to default look and feel
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Setup application
        setupLookAndFeel();
        setupServices();

        // Start application
        SwingUtilities.invokeLater(() -> {
            try {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                    "Error starting application: " + e.getMessage(),
                    "Application Error",
                    JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
}
