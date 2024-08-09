/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.FoodDao;
import com.algonquin.cst8288.fwrptomc.dao.OrdersDao;
import com.algonquin.cst8288.fwrptomc.dao.ClaimDao;
import com.algonquin.cst8288.fwrptomc.model.RetailerDashboardStatistics;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the RetailerDashboardService class.
 */
public class RetailerDashboardServiceTest {

    @Mock
    private FoodDao foodDao;

    @Mock
    private OrdersDao ordersDao;

    @Mock
    private ClaimDao claimDao;

    @InjectMocks
    private RetailerDashboardService retailerDashboardService;

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
     * Test of getStatistics method, of class RetailerDashboardService.
     */
    @Test
    public void testGetStatistics() {
        int retailerId = 1;
        RetailerDashboardStatistics stats = new RetailerDashboardStatistics();
        stats.setTotalListedItems(10);
        stats.setTotalSoldItems(20);
        stats.setTotalRevenue(3000.00);
        stats.setMostPopularItem("Apple");
        stats.setTotalDonatedItems(5);

        when(foodDao.getTotalListedItemsByRetailerId(retailerId)).thenReturn(10);
        when(ordersDao.getTotalSoldItemsByRetailerId(retailerId)).thenReturn(20);
        when(ordersDao.getTotalRevenueByRetailerId(retailerId)).thenReturn(3000.00);
        when(ordersDao.getMostPopularItemByRetailerId(retailerId)).thenReturn("Apple");
        when(claimDao.getTotalDonatedItemsByRetailerId(retailerId)).thenReturn(5);

        RetailerDashboardStatistics result = retailerDashboardService.getStatistics(retailerId);

        assertNotNull(result);
        assertEquals(10, result.getTotalListedItems());
        assertEquals(20, result.getTotalSoldItems());
        assertEquals(3000.00, result.getTotalRevenue());
        assertEquals("Apple", result.getMostPopularItem());
        assertEquals(5, result.getTotalDonatedItems());

        verify(foodDao).getTotalListedItemsByRetailerId(retailerId);
        verify(ordersDao).getTotalSoldItemsByRetailerId(retailerId);
        verify(ordersDao).getTotalRevenueByRetailerId(retailerId);
        verify(ordersDao).getMostPopularItemByRetailerId(retailerId);
        verify(claimDao).getTotalDonatedItemsByRetailerId(retailerId);
    }
}

