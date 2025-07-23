package com.mmdc.motor_ph_portal.AdminAccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeProfileTest {

    private EmployeeProfile employeeProfile;

    @BeforeEach
    void setUp() {
        System.setProperty("java.awt.headless", "true"); // Prevents actual GUI windows in test
        employeeProfile = new EmployeeProfile();
    }

    @Test
    void testAddEmployeeButtonClick() {
        employeeProfile.getFirstnameField().setText("Juan");
        employeeProfile.getLastnameField().setText("Cruz");
        employeeProfile.getEmailField().setText("juancruz@test.com");
        employeeProfile.getBdayField().setText("1990-01-01");
        employeeProfile.getStreetField().setText("123 Street");
        employeeProfile.getBrgyField().setText("Barangay Uno");
        employeeProfile.getCityField().setText("Taguig");
        employeeProfile.getProvinceField().setText("Metro Manila");
        employeeProfile.getPostalcodeField().setText("1600");
        employeeProfile.getContactField().setText("09171234567");
        employeeProfile.getSssField().setText("123456789012");
        employeeProfile.getPhhealthField().setText("987654321098");
        employeeProfile.getPagibigField().setText("456789123456");
        employeeProfile.getTinField().setText("789123456789");

        employeeProfile.getAddBtn().doClick();

        // Example assertion: check if fields are cleared after adding
        assertEquals("", employeeProfile.getFirstnameField().getText()); // adjust based on actual behavior
    }

    @Test
    void testUpdateEmployeePhoneNumber() {
        employeeProfile.getSearchIdField().setText("10035");
        employeeProfile.getContactField().setText("09998887777");

        employeeProfile.getUpdateBtn().doClick();

        // Optionally, you can check if a message/dialog appeared or confirm update
    }

    @Test
    void testSearchEmployeePopulatesFields() {
        employeeProfile.searchEmployee("10026");

        assertEquals("Percival", employeeProfile.getFirstnameField().getText().trim());
        assertEquals("Gutierrez", employeeProfile.getLastnameField().getText().trim());
        assertEquals("GutierrezPercival@email.com", employeeProfile.getEmailField().getText().trim());
        assertEquals("1970-12-18", employeeProfile.getBdayField().getText().trim());
        assertEquals("512899876", employeeProfile.getContactField().getText().trim());
        assertEquals("4095046578", employeeProfile.getSssField().getText().trim());
    }
}
