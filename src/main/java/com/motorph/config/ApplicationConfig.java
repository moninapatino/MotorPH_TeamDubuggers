package com.motorph.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {
    private static final Properties properties = new Properties();
    private static ApplicationConfig instance;

    private ApplicationConfig() {
        loadProperties();
    }

    public static ApplicationConfig getInstance() {
        if (instance == null) {
            instance = new ApplicationConfig();
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            System.err.println("Error loading application.properties: " + e.getMessage());
        }
    }

    public String getDatabaseUrl() {
        return properties.getProperty("db.url", "jdbc:mysql://localhost:3306/payrollsystem_db");
    }

    public String getDatabaseUser() {
        return properties.getProperty("db.user", "root");
    }

    public String getDatabasePassword() {
        return properties.getProperty("db.password", "enaxor");
    }

    public String getEmailHost() {
        return properties.getProperty("email.host", "smtp.gmail.com");
    }

    public String getEmailUsername() {
        return properties.getProperty("email.username", "amotorph@gmail.com");
    }

    public String getEmailPassword() {
        return properties.getProperty("email.password", "znvm dejb erhe wink");
    }
}
