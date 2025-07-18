package com.motorph.dao;
import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import java.util.List;

public interface AdminDAO {
    Admin_Class findById(String adminId);
    Admin_Class findByUsername(String username);
    List<Admin_Class> findAll();
    void save(Admin_Class admin);
    void update(Admin_Class admin);
    void delete(String adminId);
}
