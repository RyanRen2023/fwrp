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

public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final String username = "renxihai@gmail.com";
    private final String password = "jhnk qxnv ivdp segn";

    private EmailNotificationDao emailNotificationDao;

    public NotificationService() {
        emailNotificationDao = new EmailNotificationDao();
    }

    public void sendNotification(Subscribe subscriber, String subject, String messageText) {
        if (subscriber.getEmail() != null) {
            sendEmail(subscriber.getEmail(), subject, messageText);
        }
    }

    private void sendEmail(String to, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");  // SMTP服务器地址
        props.put("mail.smtp.port", "587");  // 端口
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

    private void logEmailNotification(String to, String subject, String text) {
        EmailNotification emailNotification = new EmailNotification(to, subject, text);
        emailNotificationDao.addEmailNotification(emailNotification);
        logger.info("Logged email notification for: {}", to);
    }
    
    public List<EmailNotification> getAllNotifications(){
        
        return emailNotificationDao.getAllEmailNotifications();
        
    }
}
