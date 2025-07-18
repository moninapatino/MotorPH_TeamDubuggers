package com.motorph.dao;

import com.motorph.model.UserAccount;
import java.util.List;

public interface UserDAO {
    UserAccount findByUsername(String username);
    UserAccount findByEmail(String email);
    UserAccount findById(String employeeId);
    void update(UserAccount user);
    List<UserAccount> findAll();
    void create(UserAccount user);
    void delete(String employeeId);
}
