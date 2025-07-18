package com.motorph.dao.impl;

import com.motorph.dao.AdminDAO;
import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    private final Connection conn;

    public AdminDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Admin_Class findById(String adminId) {
        String sql = "SELECT * FROM admin WHERE employee_id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, adminId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return mapRowToAdmin(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Admin_Class findByUsername(String username) {
        String sql = "SELECT * FROM admin WHERE username = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return mapRowToAdmin(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Admin_Class> findAll() {
        List<Admin_Class> admins = new ArrayList<>();
        String sql = "SELECT * FROM admin";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                admins.add(mapRowToAdmin(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public void save(Admin_Class admin) {
        String sql = "INSERT INTO admin (employee_id, first_name, last_name, email, birthday, address_id, street, barangay, city, province, postalcode, phone_number, sss_num, philhealth_num, tin_num, pagibig_num, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            setAdminParams(pst, admin);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Admin_Class admin) {
        String sql = "UPDATE admin SET first_name=?, last_name=?, email=?, birthday=?, address_id=?, street=?, barangay=?, city=?, province=?, postalcode=?, phone_number=?, sss_num=?, philhealth_num=?, tin_num=?, pagibig_num=?, username=?, password=? WHERE employee_id=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            setAdminParams(pst, admin);
            pst.setString(18, admin.getEmployeeID());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String adminId) {
        String sql = "DELETE FROM admin WHERE employee_id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, adminId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Admin_Class mapRowToAdmin(ResultSet rs) throws SQLException {
        return new Admin_Class(
            rs.getString("employee_id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("email"),
            rs.getString("birthday"),
            rs.getString("address_id"),
            rs.getString("street"),
            rs.getString("barangay"),
            rs.getString("city"),
            rs.getString("province"),
            rs.getString("postalcode"),
            rs.getString("phone_number"),
            rs.getString("sss_num"),
            rs.getString("philhealth_num"),
            rs.getString("tin_num"),
            rs.getString("pagibig_num"),
            rs.getString("username"),
            rs.getString("password")
        );
    }

    private void setAdminParams(PreparedStatement pst, Admin_Class admin) throws SQLException {
        pst.setString(1, admin.getEmployeeID());
        pst.setString(2, admin.getFirstName());
        pst.setString(3, admin.getLastName());
        pst.setString(4, admin.getEmail());
        pst.setString(5, admin.getBirthday());
        pst.setString(6, admin.getAddressID());
        pst.setString(7, admin.getStreet());
        pst.setString(8, admin.getBarangay());
        pst.setString(9, admin.getCity());
        pst.setString(10, admin.getProvince());
        pst.setString(11, admin.getPostalcode());
        pst.setString(12, admin.getPhoneNumber());
        pst.setString(13, admin.getSssNum());
        pst.setString(14, admin.getPhilHealthNum());
        pst.setString(15, admin.getTinNum());
        pst.setString(16, admin.getPagibigNum());
        pst.setString(17, admin.getUsername());
        pst.setString(18, admin.getPassword());
    }
}
