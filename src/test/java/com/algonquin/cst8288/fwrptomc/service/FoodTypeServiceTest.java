/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.FoodTypeDao;
import com.algonquin.cst8288.fwrptomc.model.FoodType;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the FoodTypeService class.
 */
public class FoodTypeServiceTest {

    @Mock
    private FoodTypeDao foodTypeDao;

    @InjectMocks
    private FoodTypeService foodTypeService;

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
     * Test of addFoodType method, of class FoodTypeService.
     */
    @Test
    public void testAddFoodType() {
        FoodType foodType = new FoodType();
        doNothing().when(foodTypeDao).addFoodType(foodType);
        foodTypeService.addFoodType(foodType);
        verify(foodTypeDao).addFoodType(foodType);
    }

    /**
     * Test of updateFoodType method, of class FoodTypeService.
     */
    @Test
    public void testUpdateFoodType() {
        FoodType foodType = new FoodType();
        doNothing().when(foodTypeDao).updateFoodType(foodType);
        foodTypeService.updateFoodType(foodType);
        verify(foodTypeDao).updateFoodType(foodType);
    }

    /**
     * Test of deleteFoodType method, of class FoodTypeService.
     */
    @Test
    public void testDeleteFoodType() {
        int id = 1;
        doNothing().when(foodTypeDao).deleteFoodType(id);
        foodTypeService.deleteFoodType(id);
        verify(foodTypeDao).deleteFoodType(id);
    }

    /**
     * Test of getFoodTypeById method, of class FoodTypeService.
     */
    @Test
    public void testGetFoodTypeById() {
        int id = 1;
        FoodType foodType = new FoodType();
        when(foodTypeDao.getFoodTypeById(id)).thenReturn(foodType);
        FoodType result = foodTypeService.getFoodTypeById(id);
        assertNotNull(result);
        assertEquals(foodType, result);
        verify(foodTypeDao).getFoodTypeById(id);
    }

    /**
     * Test of getAllFoodTypes method, of class FoodTypeService.
     */
    @Test
    public void testGetAllFoodTypes() {
        List<FoodType> foodTypes = Arrays.asList(new FoodType(), new FoodType());
        when(foodTypeDao.getAllFoodTypes()).thenReturn(foodTypes);
        List<FoodType> result = foodTypeService.getAllFoodTypes();
        assertNotNull(result);
        assertEquals(foodTypes, result);
        verify(foodTypeDao).getAllFoodTypes();
    }
}

