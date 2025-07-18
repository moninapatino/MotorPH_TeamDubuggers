package com.motorph.view;

import java.awt.Color;
import java.awt.Cursor;
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

import com.motorph.model.UserAccount;
import com.motorph.service.AuthenticationService;
import com.motorph.service.AuthenticationServiceImpl;

public class LoginFrame extends JFrame {
    private JTextField userIDText;
    private JPasswordField passwordText;
    private JButton logInButton;
    private JLabel forgetPassTitle;
    private AuthenticationService authService = new AuthenticationServiceImpl();

    public LoginFrame() {
        initComponents();
        setTitle("Motor PH Employee Portal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 43, 93));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel title = new JLabel("EMPLOYEE PORTAL");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(new Color(250, 250, 255));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(new JLabel("USERNAME"), gbc);
        userIDText = new JTextField(15);
        gbc.gridx = 1;
        panel.add(userIDText, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("PASSWORD"), gbc);
        passwordText = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(passwordText, gbc);

        logInButton = new JButton("LOG IN");
        logInButton.addActionListener(e -> logInButtonActionPerformed());
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        panel.add(logInButton, gbc);

        forgetPassTitle = new JLabel("Forgot Password?");
        forgetPassTitle.setForeground(new Color(250, 250, 255));
        forgetPassTitle.setFont(new Font("Arial", Font.ITALIC, 12));
        forgetPassTitle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgetPassTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Open forgot password frame
                JOptionPane.showMessageDialog(LoginFrame.this, "Forgot Password clicked");
            }
        });
        gbc.gridy++;
        panel.add(forgetPassTitle, gbc);

        add(panel);
        pack();
    }

    private void logInButtonActionPerformed() {
        String username = userIDText.getText();
        String password = new String(passwordText.getPassword());
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Fill Out User ID");
            return;
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Fill Out Password");
            return;
        }
        UserAccount user = authService.authenticate(username, password);
        if (user != null) {
    JOptionPane.showMessageDialog(this, "Welcome, " + user.getFirstName() + "! Role: " + user.getRole());
    dispose(); // Close login window

    // Open the correct dashboard based on role
    if ("ADMIN".equalsIgnoreCase(user.getRole())) {
        new com.motorph.view.admin.AdminDashboard(user.getUsername()).setVisible(true);
    } else {
        new com.motorph.view.employee.EmployeeDashboard(user.getUsername()).setVisible(true);
    }
} else {
    JOptionPane.showMessageDialog(this, "Wrong User ID or Password");
}
        userIDText.setText("");
        passwordText.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
