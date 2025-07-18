package com.motorph.service.impl;

import com.motorph.service.AuthenticationService;
import com.motorph.model.UserAccount;
import com.motorph.dao.UserDAO;
import com.motorph.exception.ServiceException;

public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserDAO userDAO;

    public AuthenticationServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserAccount authenticate(String username, String password) throws ServiceException {
        try {
            UserAccount user = userDAO.findByUsername(username);
            if (user == null) {
                throw new ServiceException("Invalid username or password");
            }
            // In a real application, password should be hashed and compared with stored hash
            if (!password.equals(user.getPassword())) {
                throw new ServiceException("Invalid username or password");
            }
            return user;
        } catch (Exception e) {
            throw new ServiceException("Authentication failed", e);
        }
    }

    // Not part of the interface, so no @Override
    public void logout(String username) {
        // Implement any logout logic here (e.g., session management)
    }

    // Not part of the interface, so no @Override
    public void resetPassword(String username, String email) throws ServiceException {
        // UserAccount does not have email or password update methods.
        // Implement this if you add those fields/methods to UserAccount.
        throw new UnsupportedOperationException("resetPassword not implemented for UserAccount");
    }
}
