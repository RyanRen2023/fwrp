package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.EmailNotification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing email notifications in the database.
 * 
 * <p>
 * This class provides methods for adding, updating, deleting, and retrieving 
 * email notification records from the database. It interacts with the database using JDBC.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     EmailNotificationDao emailNotificationDao = new EmailNotificationDao();
 *     EmailNotification emailNotification = emailNotificationDao.getEmailNotificationById(1);
 * </pre>
 * </p>
 * 
 * <p>
 * Note: Ensure that the JDBCClient class is correctly implemented to provide
 * a valid database connection.
 * </p>
 * 
 * @author Xihai Ren
 */
public class EmailNotificationDao {

    private JDBCClient jdbcClient;

    /**
     * Constructs a new EmailNotificationDao and initializes the JDBCClient.
     */
    public EmailNotificationDao() {
        this.jdbcClient = new JDBCClient();
    }

    /**
     * Adds a new email notification to the database.
     *
     * @param emailNotification the email notification to be added
     */
    public void addEmailNotification(EmailNotification emailNotification) {
        String sql = "INSERT INTO email_notifications (recipient_email, subject, message, sent_at) VALUES (?, ?, ?, ?)";

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, emailNotification.getRecipientEmail());
            pstmt.setString(2, emailNotification.getSubject());
            pstmt.setString(3, emailNotification.getMessage());
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(emailNotification.getSentAt()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves an email notification from the database by its ID.
     *
     * @param notificationId the ID of the email notification to be retrieved
     * @return the EmailNotification object, or null if not found
     */
    public EmailNotification getEmailNotificationById(int notificationId) {
        String sql = "SELECT * FROM email_notifications WHERE notification_id = ?";
        EmailNotification emailNotification = null;

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, notificationId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                emailNotification = new EmailNotification();
                emailNotification.setNotificationId(rs.getInt("notification_id"));
                emailNotification.setRecipientEmail(rs.getString("recipient_email"));
                emailNotification.setSubject(rs.getString("subject"));
                emailNotification.setMessage(rs.getString("message"));
                emailNotification.setSentAt(rs.getTimestamp("sent_at").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emailNotification;
    }

    /**
     * Retrieves all email notifications from the database.
     *
     * @return a list of EmailNotification objects
     */
    public List<EmailNotification> getAllEmailNotifications() {
        String sql = "SELECT * FROM email_notifications";
        List<EmailNotification> emailNotifications = new ArrayList<>();

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql); 
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                EmailNotification emailNotification = new EmailNotification();
                emailNotification.setNotificationId(rs.getInt("notification_id"));
                emailNotification.setRecipientEmail(rs.getString("recipient_email"));
                emailNotification.setSubject(rs.getString("subject"));
                emailNotification.setMessage(rs.getString("message"));
                emailNotification.setSentAt(rs.getTimestamp("sent_at").toLocalDateTime());
                emailNotifications.add(emailNotification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emailNotifications;
    }
}