package com.motorph.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Database connection manager with proper resource management.
 * Implements singleton pattern for connection management.
 */
public class DatabaseConnectionManager {
    
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionManager.class);
    
    private static DatabaseConnectionManager instance;
    private final String url;
    private final String username;
    private final String password;
    
    // Default constructor for testing/configuration
    public DatabaseConnectionManager() {
        this("jdbc:mysql://localhost:3306/payrollsystem_db?useSSL=false", "root", "enaxor");
    }
    
    // Constructor with custom connection parameters
    public DatabaseConnectionManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.info("MySQL JDBC driver loaded successfully");
        } catch (ClassNotFoundException e) {
            logger.error("Failed to load MySQL JDBC driver", e);
            throw new RuntimeException("Database driver not found", e);
        }
    }
    
    /**
     * Get singleton instance with default configuration
     */
    public static synchronized DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }
    
    /**
     * Get database connection
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public Connection getConnection() throws SQLException {
        try {
            Properties props = new Properties();
            props.setProperty("user", username);
            props.setProperty("password", password);
            props.setProperty("useSSL", "false");
            props.setProperty("allowPublicKeyRetrieval", "true");
            props.setProperty("serverTimezone", "UTC");
            
            Connection connection = DriverManager.getConnection(url, props);
            logger.debug("Database connection established successfully");
            return connection;
            
        } catch (SQLException e) {
            logger.error("Failed to establish database connection", e);
            throw e;
        }
    }
    
    /**
     * Test database connectivity
     * @return true if connection is successful
     */
    public boolean testConnection() {
        try (Connection connection = getConnection()) {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            logger.error("Database connection test failed", e);
            return false;
        }
    }
    
    /**
     * Close connection safely
     * @param connection the connection to close
     */
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                logger.debug("Database connection closed successfully");
            } catch (SQLException e) {
                logger.error("Error closing database connection", e);
            }
        }
    }
    
    // Getters for configuration (useful for testing)
    public String getUrl() {
        return url;
    }
    
    public String getUsername() {
        return username;
    }
}
