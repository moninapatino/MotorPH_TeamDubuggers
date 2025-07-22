package com.motorph.controller;

import com.motorph.model.Employee;
import com.motorph.service.AuthenticationService;
import com.motorph.service.LeaveRequestService;
import com.motorph.view.EmployeeDashboardView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller for employee dashboard operations.
 * Handles employee-specific functionality with proper separation of concerns.
 */
public class EmployeeDashboardController {
    
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDashboardController.class);
    private final AuthenticationService authenticationService;
    private final LeaveRequestService leaveRequestService;
    private final EmployeeDashboardView dashboardView;
    private final Employee currentUser;
    private final String sessionToken;
    
    public EmployeeDashboardController(AuthenticationService authenticationService,
                                     EmployeeDashboardView dashboardView,
                                     Employee currentUser,
                                     String sessionToken) {
        this.authenticationService = authenticationService;
        this.dashboardView = dashboardView;
        this.currentUser = currentUser;
        this.sessionToken = sessionToken;
        
        // Initialize services (in real implementation, these would be injected)
        this.leaveRequestService = null; // TODO: Inject via dependency injection
        
        setupEventHandlers();
        loadDashboardData();
    }
    
    /**
     * Set up event handlers for the dashboard view
     */
    private void setupEventHandlers() {
        dashboardView.setLogoutAction(this::handleLogout);
        dashboardView.setProfileAction(this::handleProfile);
        dashboardView.setLeaveRequestAction(this::handleLeaveRequest);
        dashboardView.setAttendanceAction(this::handleAttendance);
        dashboardView.setPayrollAction(this::handlePayroll);
    }
    
    /**
     * Load initial dashboard data
     */
    private void loadDashboardData() {
        try {
            // Set welcome message
            dashboardView.setWelcomeMessage("Welcome, " + currentUser.getFullName());
            
            // Load employee-specific data
            dashboardView.setEmployeeInfo(currentUser);
            
            // Load recent leave requests (placeholder)
            dashboardView.setRecentLeaveRequests(0); // TODO: Get from service
            dashboardView.setPendingLeaveRequests(0); // TODO: Get from service
            
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
     * Handle profile management action
     */
    private void handleProfile() {
        logger.info("Opening employee profile for: {}", currentUser.getEmployeeId());
        // TODO: Open employee profile view
        dashboardView.showInfoMessage("Employee Profile - Coming Soon");
    }
    
    /**
     * Handle leave request action
     */
    private void handleLeaveRequest() {
        logger.info("Opening leave request for: {}", currentUser.getEmployeeId());
        // TODO: Open leave request view
        dashboardView.showInfoMessage("Leave Request - Coming Soon");
    }
    
    /**
     * Handle attendance action
     */
    private void handleAttendance() {
        logger.info("Opening attendance for: {}", currentUser.getEmployeeId());
        // TODO: Open attendance view
        dashboardView.showInfoMessage("Attendance - Coming Soon");
    }
    
    /**
     * Handle payroll action
     */
    private void handlePayroll() {
        logger.info("Opening payroll for: {}", currentUser.getEmployeeId());
        // TODO: Open payroll view
        dashboardView.showInfoMessage("Payroll - Coming Soon");
    }
}
