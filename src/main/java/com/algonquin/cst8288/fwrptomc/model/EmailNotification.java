package com.algonquin.cst8288.fwrptomc.model;

import java.time.LocalDateTime;

/**
 * Model class representing an email notification.
 * 
 * This class holds data related to an email notification such as the recipient's email address,
 * subject, message, and the time the email was sent. It provides constructors, getters, setters,
 * and a toString method for better readability.
 * 
 * Author: Xihai Ren
 */
public class EmailNotification {

    private int notificationId;
    private String recipientEmail;
    private String subject;
    private String message;
    private LocalDateTime sentAt;

    /**
     * Default constructor.
     */
    public EmailNotification() {
    }

    /**
     * Constructs a new EmailNotification object with the specified values.
     *
     * @param recipientEmail the recipient's email address
     * @param subject the subject of the email
     * @param message the message content of the email
     */
    public EmailNotification(String recipientEmail, String subject, String message) {
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.message = message;
        this.sentAt = LocalDateTime.now(); // Set sentAt to the current date and time
    }

    /**
     * Gets the notification ID.
     *
     * @return the notification ID
     */
    public int getNotificationId() {
        return notificationId;
    }

    /**
     * Sets the notification ID.
     *
     * @param notificationId the notification ID to set
     */
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    /**
     * Gets the recipient's email address.
     *
     * @return the recipient's email address
     */
    public String getRecipientEmail() {
        return recipientEmail;
    }

    /**
     * Sets the recipient's email address.
     *
     * @param recipientEmail the recipient's email address to set
     */
    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    /**
     * Gets the subject of the email.
     *
     * @return the subject of the email
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the email.
     *
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets the message content of the email.
     *
     * @return the message content of the email
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message content of the email.
     *
     * @param message the message content to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the time when the email was sent.
     *
     * @return the time when the email was sent
     */
    public LocalDateTime getSentAt() {
        return sentAt;
    }

    /**
     * Sets the time when the email was sent.
     *
     * @param sentAt the time to set
     */
    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    /**
     * Returns a string representation of the EmailNotification object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "EmailNotification{"
                + "notificationId=" + notificationId
                + ", recipientEmail='" + recipientEmail + '\''
                + ", subject='" + subject + '\''
                + ", message='" + message + '\''
                + ", sentAt=" + sentAt
                + '}';
    }
}