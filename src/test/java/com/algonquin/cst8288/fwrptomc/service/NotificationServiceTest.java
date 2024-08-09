/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.model.Subscribe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author renxihai
 */
public class NotificationServiceTest {

    public NotificationServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of sendNotification method, of class NotificationService.
     */
    @Test
    public void testSendNotification() {
        System.out.println("sendNotification");
        Subscribe subscriber = new Subscribe();
        subscriber.setEmail("ren00055@algonquinlive.com");
        String subject = "test";
        String messageText = "test";
        NotificationService instance = new NotificationService();
        instance.sendNotification(subscriber, subject, messageText);
        assertTrue(true);

    }

}
