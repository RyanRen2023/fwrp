/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.EmailNotification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the EmailNotificationDao class.
 */
public class EmailNotificationDaoTest {

    @Mock
    private JDBCClient jdbcClient;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private EmailNotificationDao emailNotificationDao;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(jdbcClient.getConnection()).thenReturn(connection);
    }

    @Test
    public void testAddEmailNotification() throws SQLException {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.setRecipientEmail("test@example.com");
        emailNotification.setSubject("Test Subject");
        emailNotification.setMessage("Test Message");
        emailNotification.setSentAt(LocalDateTime.now());

        String sql = "INSERT INTO email_notifications (recipient_email, subject, message, sent_at) VALUES (?, ?, ?, ?)";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        emailNotificationDao.addEmailNotification(emailNotification);

        verify(preparedStatement).setString(1, emailNotification.getRecipientEmail());
        verify(preparedStatement).setString(2, emailNotification.getSubject());
        verify(preparedStatement).setString(3, emailNotification.getMessage());
        verify(preparedStatement).setTimestamp(4, java.sql.Timestamp.valueOf(emailNotification.getSentAt()));
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testGetEmailNotificationById() throws SQLException {
        int notificationId = 1;
        String sql = "SELECT * FROM email_notifications WHERE notification_id = ?";

        EmailNotification expectedEmailNotification = new EmailNotification();
        expectedEmailNotification.setNotificationId(notificationId);
        expectedEmailNotification.setRecipientEmail("test@example.com");
        expectedEmailNotification.setSubject("Test Subject");
        expectedEmailNotification.setMessage("Test Message");
        expectedEmailNotification.setSentAt(LocalDateTime.of(2023, 8, 6, 12, 0));

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("notification_id")).thenReturn(notificationId);
        when(resultSet.getString("recipient_email")).thenReturn("test@example.com");
        when(resultSet.getString("subject")).thenReturn("Test Subject");
        when(resultSet.getString("message")).thenReturn("Test Message");
        when(resultSet.getTimestamp("sent_at")).thenReturn(java.sql.Timestamp.valueOf(LocalDateTime.of(2023, 8, 6, 12, 0)));

        EmailNotification emailNotification = emailNotificationDao.getEmailNotificationById(notificationId);

        assertNotNull(emailNotification);
        assertEquals(expectedEmailNotification.getNotificationId(), emailNotification.getNotificationId());
        assertEquals(expectedEmailNotification.getRecipientEmail(), emailNotification.getRecipientEmail());
        assertEquals(expectedEmailNotification.getSubject(), emailNotification.getSubject());
        assertEquals(expectedEmailNotification.getMessage(), emailNotification.getMessage());
        assertEquals(expectedEmailNotification.getSentAt(), emailNotification.getSentAt());
    }

    @Test
    public void testGetAllEmailNotifications() throws SQLException {
        String sql = "SELECT * FROM email_notifications";

        List<EmailNotification> expectedEmailNotifications = new ArrayList<>();
        EmailNotification emailNotification1 = new EmailNotification();
        emailNotification1.setNotificationId(1);
        emailNotification1.setRecipientEmail("test1@example.com");
        emailNotification1.setSubject("Test Subject 1");
        emailNotification1.setMessage("Test Message 1");
        emailNotification1.setSentAt(LocalDateTime.of(2023, 8, 6, 12, 0));
        expectedEmailNotifications.add(emailNotification1);

        EmailNotification emailNotification2 = new EmailNotification();
        emailNotification2.setNotificationId(2);
        emailNotification2.setRecipientEmail("test2@example.com");
        emailNotification2.setSubject("Test Subject 2");
        emailNotification2.setMessage("Test Message 2");
        emailNotification2.setSentAt(LocalDateTime.of(2023, 8, 7, 13, 0));
        expectedEmailNotifications.add(emailNotification2);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("notification_id")).thenReturn(1, 2);
        when(resultSet.getString("recipient_email")).thenReturn("test1@example.com", "test2@example.com");
        when(resultSet.getString("subject")).thenReturn("Test Subject 1", "Test Subject 2");
        when(resultSet.getString("message")).thenReturn("Test Message 1", "Test Message 2");
        when(resultSet.getTimestamp("sent_at")).thenReturn(
                java.sql.Timestamp.valueOf(LocalDateTime.of(2023, 8, 6, 12, 0)),
                java.sql.Timestamp.valueOf(LocalDateTime.of(2023, 8, 7, 13, 0))
        );

        List<EmailNotification> emailNotifications = emailNotificationDao.getAllEmailNotifications();

        assertNotNull(emailNotifications);
        assertEquals(expectedEmailNotifications.size(), emailNotifications.size());
        for (int i = 0; i < emailNotifications.size(); i++) {
            assertEquals(expectedEmailNotifications.get(i).getNotificationId(), emailNotifications.get(i).getNotificationId());
            assertEquals(expectedEmailNotifications.get(i).getRecipientEmail(), emailNotifications.get(i).getRecipientEmail());
            assertEquals(expectedEmailNotifications.get(i).getSubject(), emailNotifications.get(i).getSubject());
            assertEquals(expectedEmailNotifications.get(i).getMessage(), emailNotifications.get(i).getMessage());
            assertEquals(expectedEmailNotifications.get(i).getSentAt(), emailNotifications.get(i).getSentAt());
        }
    }
}
