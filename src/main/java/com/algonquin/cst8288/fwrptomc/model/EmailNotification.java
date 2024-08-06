/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.model;

import java.time.LocalDateTime;

public class EmailNotification {

    private int notificationId;
    private String recipientEmail;
    private String subject;
    private String message;
    private LocalDateTime sentAt;

    // Default constructor
    public EmailNotification() {
    }

    // Parameterized constructor
    public EmailNotification(String recipientEmail, String subject, String message) {
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.message = message;
        this.sentAt = LocalDateTime.now(); // Set sentAt to the current date and time
    }

    // Getters and setters
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    // Override toString() for better readability
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
