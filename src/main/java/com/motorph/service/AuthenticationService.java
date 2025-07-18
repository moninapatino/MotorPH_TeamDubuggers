package com.motorph.service;

import com.motorph.model.UserAccount;

public interface AuthenticationService {
    UserAccount authenticate(String username, String password);
}
