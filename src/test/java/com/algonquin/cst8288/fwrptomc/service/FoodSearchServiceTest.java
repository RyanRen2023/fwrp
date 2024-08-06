/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.FoodSearchDao;
import com.algonquin.cst8288.fwrptomc.model.FoodSearch;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the FoodSearchService class.
 */
public class FoodSearchServiceTest {

    @Mock
    private FoodSearchDao foodSearchDao;

    @InjectMocks
    private FoodSearchService foodSearchService;

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
     * Test of getAllFoodSearch method, of class FoodSearchService.
     */
    @Test
    public void testGetAllFoodSearch() {
        List<FoodSearch> foodSearchList = Arrays.asList(new FoodSearch(), new FoodSearch());
        when(foodSearchDao.getAllFoodSearch()).thenReturn(foodSearchList);
        List<FoodSearch> result = foodSearchService.getAllFoodSearch();
        assertNotNull(result);
        assertEquals(foodSearchList, result);
        verify(foodSearchDao).getAllFoodSearch();
    }

    /**
     * Test of searchFood method, of class FoodSearchService.
     */
    @Test
    public void testSearchFood() {
        String query = "apple";
        List<FoodSearch> foodSearchList = Arrays.asList(new FoodSearch(), new FoodSearch());
        when(foodSearchDao.searchFood(query)).thenReturn(foodSearchList);
        List<FoodSearch> result = foodSearchService.searchFood(query);
        assertNotNull(result);
        assertEquals(foodSearchList, result);
        verify(foodSearchDao).searchFood(query);
    }

    /**
     * Test of search method with multiple parameters, of class FoodSearchService.
     */
    @Test
    public void testSearch() {
        String searchQuery = "apple";
        String foodType = "fruit";
        String priceRange = "0-10";
        String expiration = "2024-12-31";
        String supplier = "local";
        String location = "Toronto";
        List<FoodSearch> foodSearchList = Arrays.asList(new FoodSearch(), new FoodSearch());
        when(foodSearchDao.search(searchQuery, foodType, priceRange, expiration, supplier, location)).thenReturn(foodSearchList);
        List<FoodSearch> result = foodSearchService.search(searchQuery, foodType, priceRange, expiration, supplier, location);
        assertNotNull(result);
        assertEquals(foodSearchList, result);
        verify(foodSearchDao).search(searchQuery, foodType, priceRange, expiration, supplier, location);
    }
}
