/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the EmailNotification class.
 */
public class EmailNotificationTest {

    @Test
    public void testDefaultConstructor() {
        EmailNotification emailNotification = new EmailNotification();

        assertNull(emailNotification.getRecipientEmail());
        assertNull(emailNotification.getSubject());
        assertNull(emailNotification.getMessage());
        assertNull(emailNotification.getSentAt());
        assertEquals(0, emailNotification.getNotificationId());
    }

    @Test
    public void testParameterizedConstructor() {
        String recipientEmail = "test@example.com";
        String subject = "Test Subject";
        String message = "This is a test message.";

        EmailNotification emailNotification = new EmailNotification(recipientEmail, subject, message);

        assertEquals(recipientEmail, emailNotification.getRecipientEmail());
        assertEquals(subject, emailNotification.getSubject());
        assertEquals(message, emailNotification.getMessage());
        assertNotNull(emailNotification.getSentAt());
    }

    @Test
    public void testSettersAndGetters() {
        EmailNotification emailNotification = new EmailNotification();

        int notificationId = 1;
        String recipientEmail = "test@example.com";
        String subject = "Test Subject";
        String message = "This is a test message.";
        LocalDateTime sentAt = LocalDateTime.of(2023, 8, 6, 12, 0);

        emailNotification.setNotificationId(notificationId);
        emailNotification.setRecipientEmail(recipientEmail);
        emailNotification.setSubject(subject);
        emailNotification.setMessage(message);
        emailNotification.setSentAt(sentAt);

        assertEquals(notificationId, emailNotification.getNotificationId());
        assertEquals(recipientEmail, emailNotification.getRecipientEmail());
        assertEquals(subject, emailNotification.getSubject());
        assertEquals(message, emailNotification.getMessage());
        assertEquals(sentAt, emailNotification.getSentAt());
    }

    @Test
    public void testToString() {
        int notificationId = 1;
        String recipientEmail = "test@example.com";
        String subject = "Test Subject";
        String message = "This is a test message.";
        LocalDateTime sentAt = LocalDateTime.of(2023, 8, 6, 12, 0);

        EmailNotification emailNotification = new EmailNotification();
        emailNotification.setNotificationId(notificationId);
        emailNotification.setRecipientEmail(recipientEmail);
        emailNotification.setSubject(subject);
        emailNotification.setMessage(message);
        emailNotification.setSentAt(sentAt);

        String expected = "EmailNotification{"
                + "notificationId=" + notificationId
                + ", recipientEmail='" + recipientEmail + '\''
                + ", subject='" + subject + '\''
                + ", message='" + message + '\''
                + ", sentAt=" + sentAt
                + '}';

        assertEquals(expected, emailNotification.toString());
    }
}
