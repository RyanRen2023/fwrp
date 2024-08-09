/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ConsumerDashboardStatistics class.
 */
public class ConsumerDashboardStatisticsTest {

    @Test
    public void testConstructor() {
        int totalOrders = 10;
        double totalExpenditure = 200.50;
        int totalFeedback = 5;
        String favoriteFoodItem = "Pizza";

        ConsumerDashboardStatistics stats = new ConsumerDashboardStatistics(totalOrders, totalExpenditure, totalFeedback, favoriteFoodItem);

        assertEquals(totalOrders, stats.getTotalOrders());
        assertEquals(totalExpenditure, stats.getTotalExpenditure());
        assertEquals(totalFeedback, stats.getTotalFeedback());
        assertEquals(favoriteFoodItem, stats.getFavoriteFoodItem());
    }

    @Test
    public void testSettersAndGetters() {
        ConsumerDashboardStatistics stats = new ConsumerDashboardStatistics(0, 0, 0, "");

        int totalOrders = 10;
        double totalExpenditure = 200.50;
        int totalFeedback = 5;
        String favoriteFoodItem = "Pizza";

        stats.setTotalOrders(totalOrders);
        stats.setTotalExpenditure(totalExpenditure);
        stats.setTotalFeedback(totalFeedback);
        stats.setFavoriteFoodItem(favoriteFoodItem);

        assertEquals(totalOrders, stats.getTotalOrders());
        assertEquals(totalExpenditure, stats.getTotalExpenditure());
        assertEquals(totalFeedback, stats.getTotalFeedback());
        assertEquals(favoriteFoodItem, stats.getFavoriteFoodItem());
    }

    @Test
    public void testToString() {
        int totalOrders = 10;
        double totalExpenditure = 200.50;
        int totalFeedback = 5;
        String favoriteFoodItem = "Pizza";

        ConsumerDashboardStatistics stats = new ConsumerDashboardStatistics(totalOrders, totalExpenditure, totalFeedback, favoriteFoodItem);

        String expected = "ConsumerDashboardStatistics{"
                + "totalOrders=" + totalOrders
                + ", totalExpenditure=" + totalExpenditure
                + ", totalFeedback=" + totalFeedback
                + ", favoriteFoodItem='" + favoriteFoodItem + '\''
                + '}';

        assertEquals(expected, stats.toString());
    }
}

