package com.motorph.view;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.motorph.model.PasswordRecovery;
import com.motorph.service.PasswordRecoveryService;
import com.motorph.service.PasswordRecoveryServiceImpl;

public class ForgotPasswordFrame extends JFrame {
    private final PasswordRecoveryService passwordRecoveryService;
    private PasswordRecovery currentRecovery;
    private JTextField emailField;
    private JPasswordField codeField;
    private JButton sendButton;
    private JButton verifyButton;

    public ForgotPasswordFrame() {
        this.passwordRecoveryService = new PasswordRecoveryServiceImpl();
        initComponents();
        setupFrame();
    }

    private void setupFrame() {
        setTitle("Password Recovery");
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
        JLabel title = new JLabel("Password Recovery");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(250, 250, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(title, gbc);

        // Email field
        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel emailLabel = new JLabel("Enter email address");
        emailLabel.setForeground(Color.WHITE);
        mainPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = new JTextField(20);
        mainPanel.add(emailField, gbc);

        // Send button
        gbc.gridy++;
        sendButton = new JButton("Send Code");
        sendButton.addActionListener(e -> handleSendCode());
        mainPanel.add(sendButton, gbc);

        // Verification code field
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel codeLabel = new JLabel("Enter verification code");
        codeLabel.setForeground(Color.WHITE);
        mainPanel.add(codeLabel, gbc);

        gbc.gridx = 1;
        codeField = new JPasswordField(20);
        mainPanel.add(codeField, gbc);

        // Verify button
        gbc.gridy++;
        verifyButton = new JButton("Verify");
        verifyButton.addActionListener(e -> handleVerifyCode());
        mainPanel.add(verifyButton, gbc);

        add(mainPanel);
        pack();
    }

    private void handleSendCode() {
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an email address");
            return;
        }

        currentRecovery = passwordRecoveryService.initiatePasswordRecovery(email);
        if (currentRecovery != null) {
            JOptionPane.showMessageDialog(this, "Verification code has been sent to your email");
            codeField.setEnabled(true);
            verifyButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to send verification code. Please check your email address.");
        }
    }

    private void handleVerifyCode() {
        if (currentRecovery == null) {
            JOptionPane.showMessageDialog(this, "Please request a verification code first");
            return;
        }

        String enteredCode = new String(codeField.getPassword());
        if (currentRecovery.getVerificationCode().equals(enteredCode)) {
            JOptionPane.showMessageDialog(this, "Code verified successfully");
            openNewPasswordFrame();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid verification code");
        }
    }

    private void openNewPasswordFrame() {
        if (currentRecovery != null) {
            new NewPasswordFrame(currentRecovery.getEmployeeId());
            dispose();
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(ForgotPasswordFrame::new);
    }
}
