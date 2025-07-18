package com.motorph.security;

import com.motorph.service.AuthenticationService;
import com.motorph.exception.ServiceException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoginManager {
    private final AuthenticationService authService;

    public LoginManager(AuthenticationService authService) {
        this.authService = authService;
    }

    public boolean login(String username, String password, JFrame parentFrame) {
        try {
            // Authenticate user
           authService.authenticate(username, password);
            // Removed SecurityContext reference for simplified access control
            
            return true;
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(parentFrame,
                e.getMessage(),
                "Login Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void logout() {
        // Removed SecurityContext reference for simplified access control
    }
}
