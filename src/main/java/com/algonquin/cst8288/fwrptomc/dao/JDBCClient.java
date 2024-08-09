package com.algonquin.cst8288.fwrptomc.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBCClient class for managing database connections using JDBC.
 * 
 * <p>
 * This class is responsible for loading database configuration from a properties 
 * file, initializing the JDBC driver, and providing a method to establish a 
 * connection to the database.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     JDBCClient client = new JDBCClient();
 *     try (Connection conn = client.getConnection()) {
 *         // Use the connection
 *     } catch (SQLException e) {
 *         e.printStackTrace();
 *     }
 * </pre>
 * </p>
 * 
 * <p>
 * Note: The `database.properties` file should contain the following properties:
 * <ul>
 *     <li>jdbc.url - The JDBC URL for the database connection.</li>
 *     <li>jdbc.user - The database user name.</li>
 *     <li>jdbc.password - The database user's password.</li>
 *     <li>jdbc.driver - The fully qualified name of the JDBC driver class.</li>
 * </ul>
 * </p>
 * 
 * @author Xihai Ren
 */
public class JDBCClient {

    private static final String PROPERTIES_FILE = "database.properties";
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;
    private String jdbcDriver;

    /**
     * Constructs a new JDBCClient and loads the database properties from the
     * specified properties file.
     */
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
     * Establishes a connection to the database using the loaded JDBC properties.
     * 
     * @return a Connection object representing the established database connection
     * @throws SQLException if a database access error occurs
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    
}