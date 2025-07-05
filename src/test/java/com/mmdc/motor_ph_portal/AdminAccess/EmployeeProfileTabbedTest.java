
package com.mmdc.motor_ph_portal.AdminAccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class EmployeeProfileTabbedTest {
    
    private EmployeeProfileTabbed employeeProfileTabbed;

    @BeforeEach
    void setUp() {
        employeeProfileTabbed = new EmployeeProfileTabbed();
    }

    // Test Scenario for #4 - Junit test by Maricon C.
    @Test
    void testAddEmployeeButton() {
        employeeProfileTabbed.getFirstnameField().setText("Juan Carlos");
        employeeProfileTabbed.getLastnameField().setText("Rojas");
        employeeProfileTabbed.getBdayField().setText("2003-08-14");
        employeeProfileTabbed.getStreetField().setText("04A/43 Rempel Cliffs Suite 830");
        employeeProfileTabbed.getBrgyField().setText("Poblacion");
        employeeProfileTabbed.getCityField().setText("Trece Martires");
        employeeProfileTabbed.getProvinceField().setText("Surigao del Sur");
        employeeProfileTabbed.getPostalcodeField().setText("6870");
        employeeProfileTabbed.getContactField().setText("9148952396");
        employeeProfileTabbed.getEmailField().setText("reuben81@hotmail.com");
        employeeProfileTabbed.getSssField().setText("165113218651");
        employeeProfileTabbed.getPhhealthField().setText("15156051203");
        employeeProfileTabbed.getPagibigField().setText("1561130156");
        employeeProfileTabbed.getTinField().setText("2056456069623");

        employeeProfileTabbed.getAddBtn().doClick();

        // Optional: Add assertion if needed
    }

    // Test Scenario for #5 - Junit test by Maricon C.
    @Test
    void testUpdateEmployeePhoneNumber() {
        System.setProperty("java.awt.headless", "true");

        // You could override showConfirmDialog() in your actual class if needed
        employeeProfileTabbed.getSearchIdField().setText("10035");
        employeeProfileTabbed.getContactField().setText("9659136944");

        employeeProfileTabbed.getUpdateBtn().doClick();

        // No assertion unless internal state tracking exists
    }

    // Test Scenario for #6 - Junit test by Maricon C.
      @Test
    void testSearchEmployeePopulatesFields() {
        employeeProfileTabbed.searchEmployee("10026");

        assertEquals("Percival", employeeProfileTabbed.getFirstnameField().getText().trim());
        assertEquals("Gutierrez", employeeProfileTabbed.getLastnameField().getText().trim());
        assertEquals("GutierrezPercival@email.com", employeeProfileTabbed.getEmailField().getText().trim());
        assertEquals("1970-12-18", employeeProfileTabbed.getBdayField().getText().trim());
        assertEquals("12026", employeeProfileTabbed.getPostalcodeField().getText().trim());
        assertEquals("512899876", employeeProfileTabbed.getContactField().getText().trim());
        assertEquals("4095046578", employeeProfileTabbed.getSssField().getText().trim());
        assertEquals("797639382265", employeeProfileTabbed.getPhhealthField().getText().trim());
        assertEquals("502995671000", employeeProfileTabbed.getTinField().getText().trim());
        assertEquals("210897095686", employeeProfileTabbed.getPagibigField().getText().trim());
    }

}

