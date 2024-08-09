package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.EmailNotificationDao;
import com.algonquin.cst8288.fwrptomc.model.EmailNotification;
import com.algonquin.cst8288.fwrptomc.model.Subscribe;
import java.util.List;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class that handles sending email notifications and logging them.
 *
 * <p>
 * This service is responsible for sending email notifications to subscribers
 * and logging these notifications in the database. It uses JavaMail for email
 * sending and relies on {@link EmailNotificationDao} for database operations.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     NotificationService notificationService = new NotificationService();
 *     notificationService.sendNotification(subscriber, "Subject", "Message text");
 * </pre>
 * </p>
 *
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link EmailNotificationDao}: Data access object for logging email
 * notifications.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Note: Email sending is configured for Gmail's SMTP server by default. This
 * can be modified to fit different SMTP configurations.
 * </p>
 *
 * @author Xihai Ren
 */
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final String username = "your email"; // Configure your email here
    private final String password = "your password"; // Configure your email password here

    private EmailNotificationDao emailNotificationDao;

    /**
     * Constructs a new NotificationService and initializes the
     * EmailNotificationDao.
     */
    public NotificationService() {
        emailNotificationDao = new EmailNotificationDao();
    }

    /**
     * Sends a notification email to a subscriber.
     *
     * @param subscriber the subscriber to notify
     * @param subject the subject of the email
     * @param messageText the content of the email
     */
    public void sendNotification(Subscribe subscriber, String subject, String messageText) {
        if (subscriber.getEmail() != null) {
            // Uncomment the line below to enable real email sending
            // sendEmail(subscriber.getEmail(), subject, messageText);
            logger.info("Send notify email to subscriber {}", subscriber.getEmail());
        }
    }

    /**
     * Sends an email with the specified subject and text to the specified
     * recipient.
     *
     * @param to the recipient's email address
     * @param subject the subject of the email
     * @param text the content of the email
     */
    private void sendEmail(String to, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");  // SMTP Server Address
        props.put("mail.smtp.port", "587");  // SMTP port
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            logger.info("Email sent to: {}", to);
            logEmailNotification(to, subject, text);
        } catch (MessagingException e) {
            logger.error("Failed to send email to: {}", to, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Logs the details of a sent email notification in the database.
     *
     * @param to the recipient's email address
     * @param subject the subject of the email
     * @param text the content of the email
     */
    private void logEmailNotification(String to, String subject, String text) {
        EmailNotification emailNotification = new EmailNotification(to, subject, text);
        emailNotificationDao.addEmailNotification(emailNotification);
        logger.info("Logged email notification for: {}", to);
    }

    /**
     * Retrieves all logged email notifications.
     *
     * @return a list of EmailNotification objects
     */
    public List<EmailNotification> getAllNotifications() {
        return emailNotificationDao.getAllEmailNotifications();
    }
}
