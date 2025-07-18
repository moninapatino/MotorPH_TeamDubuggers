package com.motorph.service;

import com.motorph.dao.UserAccountDAO;
import com.motorph.dao.UserAccountDAOImpl;
import com.motorph.model.UserAccount;

public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserAccountDAO userAccountDAO = new UserAccountDAOImpl();

    @Override
    public UserAccount authenticate(String username, String password) {
        return userAccountDAO.findByUsernameAndPassword(username, password);
    }
}
