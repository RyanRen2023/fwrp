package com.algonquin.cst8288.fwrptomc.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * BaseRepository class that provides basic functionality for managing database connections.
 * 
 * <p>
 * This class handles the creation, retrieval, and closure of database connections using a 
 * ThreadLocal variable to ensure that each thread has its own database connection.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     Connection connection = BaseRepository.getConnection();
 *     // Perform database operations
 *     BaseRepository.closeConnection();
 * </pre>
 * </p>
 * 
 * <p>
 * The database connection is configured to use MySQL with specific credentials and URL. 
 * Modify the {@code jdbcURL}, {@code jdbcUsername}, and {@code jdbcPassword} fields to 
 * match your database configuration.
 * </p>
 * 
 * <p>
 * Note: Ensure that the MySQL JDBC driver is included in your project's classpath.
 * </p>
 * 
 * <p>
 * The {@link #getConnection()} method initializes the database connection if it does not exist,
 * and the {@link #closeConnection()} method ensures that the connection is properly closed
 * and removed from the ThreadLocal storage.
 * </p>
 * 
 * @author Alexis Trinh
 */
public class BaseRepository {

    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();
    private static String jdbcURL = "jdbc:mysql://localhost:3306/fwrp";
    private static String jdbcUsername = "fwrp";
    private static String jdbcPassword = "fwrpfwrp";
    private static Connection connection;

    /**
     * Retrieves the database connection associated with the current thread.
     * 
     * <p>
     * If no connection exists, a new connection is created and stored in the ThreadLocal variable.
     * </p>
     * 
     * @return the database connection
     */
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                connectionHolder.set(connection);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * Closes the database connection associated with the current thread.
     * 
     * <p>
     * If a connection exists, it is closed and removed from the ThreadLocal variable.
     * </p>
     */
    public static void closeConnection() {
        Connection conn = connectionHolder.get();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connectionHolder.remove();
            }
        }
    }
}