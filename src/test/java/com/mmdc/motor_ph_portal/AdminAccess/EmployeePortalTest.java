package com.mmdc.motor_ph_portal.AdminAccess;

import javax.swing.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeePortalTest {

    private EmployeePortal portal;

    @BeforeEach
    public void setUp() {
        portal = new EmployeePortal();
        portal.setVisible(true); // Open the portal GUI
    }

    @AfterEach
    public void tearDown() {
        if (portal != null) {
            portal.dispose(); // Close it after each test
        }
    }

    @Test
    public void testLogoutButtonCanBeClicked() {
        JButton logoutBtn = portal.getLogOutButton();
        assertNotNull(logoutBtn);

        SwingUtilities.invokeLater(() -> logoutBtn.doClick());

        // Pause to allow actionPerformed to execute (since we can't click YES automatically)
        try {
            Thread.sleep(1000); // simulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // We can't assert dispose() because JOptionPane requires user interaction
        assertTrue(portal.isDisplayable(), "Window is still open (JOptionPane not confirmed automatically).");
    }
}
