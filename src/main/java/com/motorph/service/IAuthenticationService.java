package com.motorph.service;

import com.motorph.model.UserAccount;

public interface IAuthenticationService {
    UserAccount authenticate(String username, String password);
}
