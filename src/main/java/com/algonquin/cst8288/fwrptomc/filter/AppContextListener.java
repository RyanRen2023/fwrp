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

@WebListener
public class AppContextListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(AppContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Context initialized.");
        // Initialization code here
    }

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