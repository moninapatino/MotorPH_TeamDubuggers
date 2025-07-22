package com.motorph.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BiConsumer;

/**
 * Login view component with proper separation from business logic.
 * Follows MVC pattern - only handles UI presentation and user input.
 */
public class LoginView extends JFrame {
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton exitButton;
    private JLabel errorLabel;
    private JProgressBar loadingBar;
    
    private BiConsumer<String, String> loginAction;
    private Runnable exitAction;
    
    public LoginView() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        configureWindow();
    }
    
    /**
     * Initialize UI components
     */
    private void initializeComponents() {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("LOGIN");
        exitButton = new JButton("EXIT");
        errorLabel = new JLabel();
        loadingBar = new JProgressBar();
        
        // Configure components
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 12));
        
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        loadingBar.setIndeterminate(true);
        loadingBar.setVisible(false);
        
        // Button styling
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        
        exitButton.setBackground(new Color(220, 53, 69));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
    }
    
    /**
     * Setup the layout of components
     */
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Title
        JLabel titleLabel = new JLabel("MotorPH Employee Portal");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(52, 58, 64));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);
        
        // Username label and field
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(usernameLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(usernameField, gbc);
        
        // Password label and field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(passwordLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(passwordField, gbc);
        
        // Error label
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(errorLabel, gbc);
        
        // Loading bar
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(loadingBar, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(loginButton);
        buttonPanel.add(exitButton);
        
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(buttonPanel, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    /**
     * Setup event handlers for UI components
     */
    private void setupEventHandlers() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginAction != null) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    loginAction.accept(username, password);
                }
            }
        });
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (exitAction != null) {
                    exitAction.run();
                }
            }
        });
        
        // Enter key support
        getRootPane().setDefaultButton(loginButton);
    }
    
    /**
     * Configure window properties
     */
    private void configureWindow() {
        setTitle("MotorPH Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null); // Center on screen
    }
    
    // Public methods for controller interaction
    
    public void setLoginButtonAction(BiConsumer<String, String> action) {
        this.loginAction = action;
    }
    
    public void setExitButtonAction(Runnable action) {
        this.exitAction = action;
    }
    
    public void showErrorMessage(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
    
    public void clearErrorMessage() {
        errorLabel.setText("");
        errorLabel.setVisible(false);
    }
    
    public void setLoading(boolean loading) {
        loadingBar.setVisible(loading);
        loginButton.setEnabled(!loading);
        usernameField.setEnabled(!loading);
        passwordField.setEnabled(!loading);
    }
    
    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        clearErrorMessage();
    }
    
    public void close() {
        dispose();
    }
}
