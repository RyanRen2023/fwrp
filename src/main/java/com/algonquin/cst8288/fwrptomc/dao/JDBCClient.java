package com.algonquin.cst8288.fwrptomc.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCClient {

    private static final String PROPERTIES_FILE = "database.properties";
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;
    private String jdbcDriver;

    public JDBCClient() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + PROPERTIES_FILE);
                return;
            }
            // Load properties file
            properties.load(input);

            // Get properties
            jdbcUrl = properties.getProperty("jdbc.url");
            jdbcUser = properties.getProperty("jdbc.user");
            jdbcPassword = properties.getProperty("jdbc.password");
            jdbcDriver = properties.getProperty("jdbc.driver");

            // Load JDBC driver
            Class.forName(jdbcDriver);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Get a connection to the database
     * @return a Connection object
     * @throws SQLException if a database access error occurs
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    public static void main(String[] args) {
        JDBCClient client = new JDBCClient();
        try (Connection conn = client.getConnection()) {
            System.out.println("Connection established successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}