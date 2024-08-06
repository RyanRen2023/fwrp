/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.OrdersDao;
import com.algonquin.cst8288.fwrptomc.dao.RatingAndFeedbackDao;
import com.algonquin.cst8288.fwrptomc.dao.FoodDao;
import com.algonquin.cst8288.fwrptomc.model.ConsumerDashboardStatistics;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ConsumerDashboardService class.
 */
public class ConsumerDashboardServiceTest {

    @Mock
    private OrdersDao ordersDao;

    @Mock
    private RatingAndFeedbackDao feedbackDao;

    @Mock
    private FoodDao foodDao;

    @InjectMocks
    private ConsumerDashboardService consumerDashboardService;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getConsumerDashboardStatistics method, of class ConsumerDashboardService.
     */
    @Test
    public void testGetConsumerDashboardStatistics() {
        int userId = 1;
        int totalOrders = 5;
        double totalExpenditure = 150.75;
        int totalFeedback = 3;
        String favoriteFoodItem = "Pizza";

        when(ordersDao.getTotalOrdersByUserId(userId)).thenReturn(totalOrders);
        when(ordersDao.getTotalExpenditureByUserId(userId)).thenReturn(totalExpenditure);
        when(feedbackDao.getTotalFeedbackByUserId(userId)).thenReturn(totalFeedback);
        when(ordersDao.getFavoriteFoodItemByUserId(userId)).thenReturn(favoriteFoodItem);

        ConsumerDashboardStatistics result = consumerDashboardService.getConsumerDashboardStatistics(userId);

        assertNotNull(result);
        assertEquals(totalOrders, result.getTotalOrders());
        assertEquals(totalExpenditure, result.getTotalExpenditure());
        assertEquals(totalFeedback, result.getTotalFeedback());
        assertEquals(favoriteFoodItem, result.getFavoriteFoodItem());

        verify(ordersDao).getTotalOrdersByUserId(userId);
        verify(ordersDao).getTotalExpenditureByUserId(userId);
        verify(feedbackDao).getTotalFeedbackByUserId(userId);
        verify(ordersDao).getFavoriteFoodItemByUserId(userId);
    }
}

