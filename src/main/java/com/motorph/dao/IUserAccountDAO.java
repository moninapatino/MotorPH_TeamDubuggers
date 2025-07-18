package com.motorph.dao;

import com.motorph.model.UserAccount;

public interface IUserAccountDAO {
    UserAccount findByUsernameAndPassword(String username, String password);
}
