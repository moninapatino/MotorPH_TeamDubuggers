package com.mmdc.motor_ph_portal.dao;
public interface LoginDAO {
    boolean authenticateUser (String username, String password);
}