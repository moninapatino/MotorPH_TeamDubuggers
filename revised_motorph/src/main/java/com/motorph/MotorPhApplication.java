package com.motorph;

import com.motorph.controller.LoginController;
import com.motorph.dao.impl.EmployeeDAOImpl;
import com.motorph.service.impl.AuthenticationServiceImpl;
import com.motorph.service.impl.EmployeeServiceImpl;
import com.motorph.util.DatabaseConnectionManager;
import com.motorph.view.LoginView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Main application class for the revised MotorPH system.
 * Demonstrates proper dependency injection and application startup.
 */
public class MotorPhApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(MotorPhApplication.class);
    
    public static void main(String[] args) {
        logger.info("Starting MotorPH Application...");
        
        // Set up look and feel
        setupLookAndFeel();
        
        // Start the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                startApplication();
            } catch (Exception e) {
                logger.error("Failed to start application", e);
                System.exit(1);
            }
        });
    }
    
    /**
     * Configure the application look and feel
     */
    private static void setupLookAndFeel() {
        try {
            // Try to use system look and feel, fallback to Nimbus
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception ex) {
                logger.warn("Could not set look and feel, using default", ex);
            }
        }
    }
    
    /**
     * Start the application with proper dependency injection
     */
    private static void startApplication() {
        logger.info("Initializing application components...");
        
        try {
            // Initialize database connection manager
            DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
            
            // Test database connection
            if (!connectionManager.testConnection()) {
                logger.warn("Database connection test failed. Application will continue but may have limited functionality.");
            }
            
            // Initialize DAOs
            EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl(connectionManager);
            
            // Initialize Services
            EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeDAO);
            AuthenticationServiceImpl authenticationService = new AuthenticationServiceImpl(employeeService);
            
            // Initialize Views
            LoginView loginView = new LoginView();
            
            // Initialize Controllers
            LoginController loginController = new LoginController(authenticationService, loginView);
            
            // Show the login screen
            loginController.showLogin();
            
            logger.info("MotorPH Application started successfully");
            
        } catch (Exception e) {
            logger.error("Error during application startup", e);
            throw new RuntimeException("Failed to start application", e);
        }
    }
}
