package com.algonquin.cst8288.fwrptomc.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application context listener for managing lifecycle events such as initialization and destruction.
 * Handles resource cleanup tasks to prevent memory leaks, such as deregistering JDBC drivers and 
 * shutting down MySQL cleanup threads.
 * 
 * <p>Usage:</p>
 * <pre>{@code
 * @WebListener
 * public class AppContextListener implements ServletContextListener {
 *     // Implementation details
 * }
 * }</pre>
 * 
 * <p>This listener is automatically registered and invoked by the servlet container during 
 * application startup and shutdown.</p>
 * 
 * @see javax.servlet.ServletContextListener
 * @see javax.servlet.ServletContextEvent
 * @see com.mysql.cj.jdbc.AbandonedConnectionCleanupThread
 * @see java.sql.DriverManager
 * @see org.slf4j.Logger
 * @see org.slf4j.LoggerFactory
 * 
 * @since 1.0
 * @version 1.0
 * 
 * Author: Xihai Ren
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(AppContextListener.class);

    /**
     * Called when the servlet context is initialized (i.e., when the web application is deployed).
     * Can be used to perform initialization tasks.
     * 
     * @param sce the ServletContextEvent containing the ServletContext that is being initialized
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Context initialized.");
        // Initialization code here
    }

    /**
     * Called when the servlet context is destroyed (i.e., when the web application is undeployed or
     * the application server is shut down). This method handles cleanup tasks such as deregistering
     * JDBC drivers and shutting down MySQL cleanup threads to prevent memory leaks.
     * 
     * @param sce the ServletContextEvent containing the ServletContext that is being destroyed
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Context destroyed. Deregistering JDBC drivers.");

        // Deregister JDBC drivers to prevent memory leaks
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.info("Deregistered JDBC driver: {}", driver);
            } catch (SQLException e) {
                logger.error("Error deregistering JDBC driver: {}", driver, e);
            }
        }

        // Shutdown MySQL cleanup thread
        AbandonedConnectionCleanupThread.checkedShutdown();
        logger.info("MySQL AbandonedConnectionCleanupThread shutdown successfully.");
    }
}
