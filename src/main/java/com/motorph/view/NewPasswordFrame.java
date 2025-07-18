package com.motorph.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import com.motorph.service.PasswordRecoveryService;
import com.motorph.service.PasswordRecoveryServiceImpl;

public class NewPasswordFrame extends JFrame {
    private final PasswordRecoveryService passwordRecoveryService;
    private final String employeeId;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JLabel employeeIdLabel;
    private JButton resetButton;
    private JButton backButton;

    public NewPasswordFrame(String employeeId) {
        this.employeeId = employeeId;
        this.passwordRecoveryService = new PasswordRecoveryServiceImpl();
        initComponents();
        setupFrame();
    }

    private void setupFrame() {
        setTitle("Password Recovery - New Password");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(30, 43, 93));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title
        JLabel title = new JLabel("Reset Password");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(250, 250, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(title, gbc);

        // Employee ID display
        gbc.gridy++;
        employeeIdLabel = new JLabel("Employee ID: " + employeeId);
        employeeIdLabel.setForeground(Color.WHITE);
        mainPanel.add(employeeIdLabel, gbc);

        // New Password field
        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel newPassLabel = new JLabel("New Password:");
        newPassLabel.setForeground(Color.WHITE);
        mainPanel.add(newPassLabel, gbc);

        gbc.gridx = 1;
        newPasswordField = new JPasswordField(20);
        mainPanel.add(newPasswordField, gbc);

        // Confirm Password field
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setForeground(Color.WHITE);
        mainPanel.add(confirmLabel, gbc);

        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField(20);
        mainPanel.add(confirmPasswordField, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);

        resetButton = new JButton("Reset Password");
        resetButton.addActionListener(e -> handleResetPassword());
        buttonPanel.add(resetButton);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> handleBack());
        buttonPanel.add(backButton);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);
        pack();
    }

    private void handleResetPassword() {
        String newPass = new String(newPasswordField.getPassword());
        String confirmPass = new String(confirmPasswordField.getPassword());

        if (newPass.isEmpty() || confirmPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields");
            return;
        }

        if (!newPass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match");
            return;
        }

        if (newPass.length() < 8) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long");
            return;
        }

        if (passwordRecoveryService.updatePassword(employeeId, newPass)) {
            JOptionPane.showMessageDialog(this, "Password updated successfully");
            openLoginFrame();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update password");
        }
    }

    private void handleBack() {
        openLoginFrame();
    }

    private void openLoginFrame() {
        new LoginFrame();
        dispose();
    }
}
