package com.motorph.dao;

import com.motorph.model.UserAccount;

public interface UserAccountDAO {
    UserAccount findByUsernameAndPassword(String username, String password);
}
