/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the StatisticsService class.
 */
public class StatisticsServiceTest {

    private StatisticsService statisticsService;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        statisticsService = new StatisticsService();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getStatistics method, of class StatisticsService.
     */
    @Test
    public void testGetStatistics() {
        JsonObject result = statisticsService.getStatistics();
        
        assertNotNull(result);
        assertTrue(result.has("totalFoods"));
        assertTrue(result.has("surplusFoods"));
        assertTrue(result.has("donatedFoods"));
        assertTrue(result.has("pendingClaims"));
        assertTrue(result.has("totalPurchases"));
        assertTrue(result.has("favoriteFoods"));
        assertTrue(result.has("ratingsGiven"));
        assertTrue(result.has("totalClaims"));
        assertTrue(result.has("successfulClaims"));
        assertTrue(result.has("inventory"));
        assertTrue(result.has("purchaseTrend"));
        assertTrue(result.has("claimsTrend"));

        // Validate some specific values
        assertEquals(100, result.get("totalFoods").getAsInt());
        assertEquals(20, result.get("surplusFoods").getAsInt());
        assertEquals(30, result.get("donatedFoods").getAsInt());
        assertEquals(10, result.get("pendingClaims").getAsInt());
        assertEquals(50, result.get("totalPurchases").getAsInt());
        assertEquals(15, result.get("favoriteFoods").getAsInt());
        assertEquals(25, result.get("ratingsGiven").getAsInt());
        assertEquals(40, result.get("totalClaims").getAsInt());
        assertEquals(35, result.get("successfulClaims").getAsInt());

        // Validate trend data format
        JsonObject inventoryTrend = result.getAsJsonObject("inventory");
        assertNotNull(inventoryTrend);
        assertTrue(inventoryTrend.has("labels"));
        assertTrue(inventoryTrend.has("data"));

        JsonObject purchaseTrend = result.getAsJsonObject("purchaseTrend");
        assertNotNull(purchaseTrend);
        assertTrue(purchaseTrend.has("labels"));
        assertTrue(purchaseTrend.has("data"));

        JsonObject claimsTrend = result.getAsJsonObject("claimsTrend");
        assertNotNull(claimsTrend);
        assertTrue(claimsTrend.has("labels"));
        assertTrue(claimsTrend.has("data"));
    }
}
