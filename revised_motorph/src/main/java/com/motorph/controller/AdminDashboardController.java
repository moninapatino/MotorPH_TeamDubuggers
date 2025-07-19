package com.motorph.controller;

import com.motorph.model.Employee;
import com.motorph.service.AuthenticationService;
import com.motorph.service.EmployeeService;
import com.motorph.service.LeaveRequestService;
import com.motorph.view.AdminDashboardView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller for admin dashboard operations.
 * Handles admin-specific functionality with proper separation of concerns.
 */
public class AdminDashboardController {
    
    private static final Logger logger = LoggerFactory.getLogger(AdminDashboardController.class);
    private final AuthenticationService authenticationService;
    private final EmployeeService employeeService;
    private final LeaveRequestService leaveRequestService;
    private final AdminDashboardView dashboardView;
    private final Employee currentUser;
    private final String sessionToken;
    
    public AdminDashboardController(AuthenticationService authenticationService,
                                  AdminDashboardView dashboardView,
                                  Employee currentUser,
                                  String sessionToken) {
        this.authenticationService = authenticationService;
        this.dashboardView = dashboardView;
        this.currentUser = currentUser;
        this.sessionToken = sessionToken;
        
        // Initialize services (in real implementation, these would be injected)
        this.employeeService = null; // TODO: Inject via dependency injection
        this.leaveRequestService = null; // TODO: Inject via dependency injection
        
        setupEventHandlers();
        loadDashboardData();
    }
    
    /**
     * Set up event handlers for the dashboard view
     */
    private void setupEventHandlers() {
        dashboardView.setLogoutAction(this::handleLogout);
        dashboardView.setEmployeeManagementAction(this::handleEmployeeManagement);
        dashboardView.setLeaveManagementAction(this::handleLeaveManagement);
        dashboardView.setPayrollManagementAction(this::handlePayrollManagement);
        dashboardView.setReportsAction(this::handleReports);
    }
    
    /**
     * Load initial dashboard data
     */
    private void loadDashboardData() {
        try {
            // Set welcome message
            dashboardView.setWelcomeMessage("Welcome, " + currentUser.getFullName());
            
            // Load dashboard statistics (placeholder)
            dashboardView.setTotalEmployees(0); // TODO: Get from service
            dashboardView.setPendingLeaveRequests(0); // TODO: Get from service
            dashboardView.setActivePayrollPeriods(0); // TODO: Get from service
            
        } catch (Exception e) {
            logger.error("Error loading dashboard data", e);
            dashboardView.showErrorMessage("Error loading dashboard data");
        }
    }
    
    /**
     * Handle logout action
     */
    private void handleLogout() {
        try {
            authenticationService.invalidateSession(sessionToken);
            dashboardView.close();
            
            // Return to login screen
            // TODO: Show login view
            
        } catch (Exception e) {
            logger.error("Error during logout", e);
        }
    }
    
    /**
     * Handle employee management action
     */
    private void handleEmployeeManagement() {
        logger.info("Opening employee management");
        // TODO: Open employee management view
        dashboardView.showInfoMessage("Employee Management - Coming Soon");
    }
    
    /**
     * Handle leave management action
     */
    private void handleLeaveManagement() {
        logger.info("Opening leave management");
        // TODO: Open leave management view
        dashboardView.showInfoMessage("Leave Management - Coming Soon");
    }
    
    /**
     * Handle payroll management action
     */
    private void handlePayrollManagement() {
        logger.info("Opening payroll management");
        // TODO: Open payroll management view
        dashboardView.showInfoMessage("Payroll Management - Coming Soon");
    }
    
    /**
     * Handle reports action
     */
    private void handleReports() {
        logger.info("Opening reports");
        // TODO: Open reports view
        dashboardView.showInfoMessage("Reports - Coming Soon");
    }
}
