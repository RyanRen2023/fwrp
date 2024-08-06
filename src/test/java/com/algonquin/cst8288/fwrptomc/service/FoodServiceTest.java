/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.FoodDao;
import com.algonquin.cst8288.fwrptomc.model.Food;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the FoodService class.
 */
public class FoodServiceTest {

    @Mock
    private FoodDao foodDao;

    @InjectMocks
    private FoodService foodService;

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
     * Test of add method, of class FoodService.
     */
    @Test
    public void testAdd() {
        Food food = new Food();
        doNothing().when(foodDao).add(food);
        foodService.add(food);
        verify(foodDao).add(food);
    }

    /**
     * Test of updateFood method, of class FoodService.
     */
    @Test
    public void testUpdateFood() {
        Food food = new Food();
        doNothing().when(foodDao).update(food);
        foodService.updateFood(food);
        verify(foodDao).update(food);
    }

    /**
     * Test of deleteFood method, of class FoodService.
     */
    @Test
    public void testDeleteFood() {
        Food food = new Food();
        doNothing().when(foodDao).delete(food);
        foodService.deleteFood(food);
        verify(foodDao).delete(food);
    }

    /**
     * Test of getFoodById method, of class FoodService.
     */
    @Test
    public void testGetFoodById() {
        int fid = 1;
        Food food = new Food();
        when(foodDao.getFoodById(fid)).thenReturn(food);
        Food result = foodService.getFoodById(fid);
        assertNotNull(result);
        assertEquals(food, result);
        verify(foodDao).getFoodById(fid);
    }

    /**
     * Test of getAllFoods method, of class FoodService.
     */
    @Test
    public void testGetAllFoods() {
        List<Food> foods = Arrays.asList(new Food(), new Food());
        when(foodDao.getAllFoods()).thenReturn(foods);
        List<Food> result = foodService.getAllFoods();
        assertNotNull(result);
        assertEquals(foods, result);
        verify(foodDao).getAllFoods();
    }

    /**
     * Test of getAllFoods method with search parameter, of class FoodService.
     */
    @Test
    public void testGetAllFoodsWithSearch() {
        String search = "apple";
        List<Food> foods = Arrays.asList(new Food(), new Food());
        when(foodDao.getAllFoods(search)).thenReturn(foods);
        List<Food> result = foodService.getAllFoods(search);
        assertNotNull(result);
        assertEquals(foods, result);
        verify(foodDao).getAllFoods(search);
    }

    /**
     * Test of getFoodForDonation method, of class FoodService.
     */
    @Test
    public void testGetFoodForDonation() {
        List<Food> foods = Arrays.asList(new Food(), new Food());
        when(foodDao.getAllFoodsForDonation()).thenReturn(foods);
        List<Food> result = foodService.getFoodForDonation();
        assertNotNull(result);
        assertEquals(foods, result);
        verify(foodDao).getAllFoodsForDonation();
    }

    /**
     * Test of getFoodForPurchase method, of class FoodService.
     */
    @Test
    public void testGetFoodForPurchase() {
        List<Food> foods = Arrays.asList(new Food(), new Food());
        when(foodDao.getAllFoodsForPurchase()).thenReturn(foods);
        List<Food> result = foodService.getFoodForPurchase();
        assertNotNull(result);
        assertEquals(foods, result);
        verify(foodDao).getAllFoodsForPurchase();
    }

    /**
     * Test of markFoodAsSurplus method, of class FoodService.
     */
    @Test
    public void testMarkFoodAsSurplus() {
        int foodId = 1;
        int isSurplus = 1;
        int isDonation = 0;
        doNothing().when(foodDao).updateFoodSurplusStatus(foodId, isSurplus, isDonation);
        foodService.markFoodAsSurplus(foodId, isSurplus, isDonation);
        verify(foodDao).updateFoodSurplusStatus(foodId, isSurplus, isDonation);
    }

    /**
     * Test of getFoodsFromOrdersByUserId method, of class FoodService.
     */
    @Test
    public void testGetFoodsFromOrdersByUserId() {
        int userId = 1;
        List<Food> foods = Arrays.asList(new Food(), new Food());
        when(foodDao.getFoodsFromOrdersByUserId(userId)).thenReturn(foods);
        List<Food> result = foodService.getFoodsFromOrdersByUserId(userId);
        assertNotNull(result);
        assertEquals(foods, result);
        verify(foodDao).getFoodsFromOrdersByUserId(userId);
    }

    /**
     * Test of getFoodsFromClaimsByOrganizationId method, of class FoodService.
     */
    @Test
    public void testGetFoodsFromClaimsByOrganizationId() {
        int organizationId = 1;
        List<Food> foods = Arrays.asList(new Food(), new Food());
        when(foodDao.getFoodsFromClaimsByOrganizationId(organizationId)).thenReturn(foods);
        List<Food> result = foodService.getFoodsFromClaimsByOrganizationId(organizationId);
        assertNotNull(result);
        assertEquals(foods, result);
        verify(foodDao).getFoodsFromClaimsByOrganizationId(organizationId);
    }
}
