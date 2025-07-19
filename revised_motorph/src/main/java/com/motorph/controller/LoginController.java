package com.motorph.controller;

import com.motorph.exception.EmployeeNotFoundException;
import com.motorph.model.Employee;
import com.motorph.service.AuthenticationService;
import com.motorph.view.LoginView;
import com.motorph.view.AdminDashboardView;
import com.motorph.view.EmployeeDashboardView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller for login operations following MVC pattern.
 * Separates business logic from GUI components.
 */
public class LoginController {
    
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final AuthenticationService authenticationService;
    private final LoginView loginView;
    
    public LoginController(AuthenticationService authenticationService, LoginView loginView) {
        this.authenticationService = authenticationService;
        this.loginView = loginView;
        
        // Set up event handlers
        setupEventHandlers();
    }
    
    /**
     * Set up event handlers for the login view
     */
    private void setupEventHandlers() {
        loginView.setLoginButtonAction(this::handleLogin);
        loginView.setExitButtonAction(this::handleExit);
    }
    
    /**
     * Handle login attempt
     * @param username the username
     * @param password the password
     */
    public void handleLogin(String username, String password) {
        logger.info("Login attempt for username: {}", username);
        
        try {
            // Clear any previous error messages
            loginView.clearErrorMessage();
            loginView.setLoading(true);
            
            // Validate input
            if (username == null || username.trim().isEmpty()) {
                loginView.showErrorMessage("Username is required");
                return;
            }
            
            if (password == null || password.trim().isEmpty()) {
                loginView.showErrorMessage("Password is required");
                return;
            }
            
            // Authenticate user
            Employee employee = authenticationService.authenticate(username.trim(), password);
            
            // Create session
            String sessionToken = authenticationService.createSession(employee);
            
            // Determine user role and redirect
            if (authenticationService.isAdmin(employee)) {
                logger.info("Admin login successful for: {}", employee.getFullName());
                openAdminDashboard(employee, sessionToken);
            } else {
                logger.info("Employee login successful for: {}", employee.getFullName());
                openEmployeeDashboard(employee, sessionToken);
            }
            
            // Close login window
            loginView.close();
            
        } catch (EmployeeNotFoundException e) {
            logger.warn("Login failed for username: {}", username);
            loginView.showErrorMessage("Invalid username or password");
        } catch (Exception e) {
            logger.error("Unexpected error during login", e);
            loginView.showErrorMessage("An error occurred during login. Please try again.");
        } finally {
            loginView.setLoading(false);
        }
    }
    
    /**
     * Handle application exit
     */
    public void handleExit() {
        logger.info("Application exit requested");
        System.exit(0);
    }
    
    /**
     * Open admin dashboard
     * @param employee the authenticated admin employee
     * @param sessionToken the session token
     */
    private void openAdminDashboard(Employee employee, String sessionToken) {
        try {
            AdminDashboardView adminDashboard = new AdminDashboardView();
            AdminDashboardController adminController = new AdminDashboardController(
                authenticationService, 
                adminDashboard, 
                employee, 
                sessionToken
            );
            adminDashboard.setVisible(true);
            
        } catch (Exception e) {
            logger.error("Error opening admin dashboard", e);
            loginView.showErrorMessage("Error opening admin dashboard");
        }
    }
    
    /**
     * Open employee dashboard
     * @param employee the authenticated employee
     * @param sessionToken the session token
     */
    private void openEmployeeDashboard(Employee employee, String sessionToken) {
        try {
            EmployeeDashboardView employeeDashboard = new EmployeeDashboardView();
            EmployeeDashboardController employeeController = new EmployeeDashboardController(
                authenticationService, 
                employeeDashboard, 
                employee, 
                sessionToken
            );
            employeeDashboard.setVisible(true);
            
        } catch (Exception e) {
            logger.error("Error opening employee dashboard", e);
            loginView.showErrorMessage("Error opening employee dashboard");
        }
    }
    
    /**
     * Show the login view
     */
    public void showLogin() {
        loginView.setVisible(true);
    }
}
