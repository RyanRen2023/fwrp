/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.FoodSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the FoodSearchDao class.
 */
public class FoodSearchDaoTest {

    @Mock
    private JDBCClient jdbcClient;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private FoodSearchDao foodSearchDao;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(jdbcClient.getConnection()).thenReturn(connection);
    }

    @Test
    public void testGetAllFoodSearch() throws SQLException {
        String sql = "SELECT * FROM food_search";

        List<FoodSearch> expectedFoodSearchList = new ArrayList<>();
        FoodSearch foodSearch1 = new FoodSearch();
        foodSearch1.setFid(1);
        foodSearch1.setFname("Apple");
        foodSearch1.setExpiration(LocalDate.of(2024, 12, 31));
        foodSearch1.setPrice(BigDecimal.valueOf(1.99));
        foodSearch1.setInventory(100);
        foodSearch1.setDiscount(0.1);
        foodSearch1.setIsDonate(0);
        foodSearch1.setFoodType("Fruit");
        foodSearch1.setStoreName("Local Market");
        foodSearch1.setCity("Toronto");
        expectedFoodSearchList.add(foodSearch1);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("fid")).thenReturn(1);
        when(resultSet.getString("fname")).thenReturn("Apple");
        when(resultSet.getDate("expiration")).thenReturn(Date.valueOf(LocalDate.of(2024, 12, 31)));
        when(resultSet.getBigDecimal("price")).thenReturn(BigDecimal.valueOf(1.99));
        when(resultSet.getInt("inventory")).thenReturn(100);
        when(resultSet.getDouble("discount")).thenReturn(0.1);
        when(resultSet.getInt("is_donate")).thenReturn(0);
        when(resultSet.getString("food_type")).thenReturn("Fruit");
        when(resultSet.getString("store_name")).thenReturn("Local Market");
        when(resultSet.getString("city")).thenReturn("Toronto");

        List<FoodSearch> foodSearchList = foodSearchDao.getAllFoodSearch();

        assertNotNull(foodSearchList);
        assertEquals(expectedFoodSearchList.size(), foodSearchList.size());
        for (int i = 0; i < foodSearchList.size(); i++) {
            assertEquals(expectedFoodSearchList.get(i).getFid(), foodSearchList.get(i).getFid());
            assertEquals(expectedFoodSearchList.get(i).getFname(), foodSearchList.get(i).getFname());
            assertEquals(expectedFoodSearchList.get(i).getExpiration(), foodSearchList.get(i).getExpiration());
            assertEquals(expectedFoodSearchList.get(i).getPrice(), foodSearchList.get(i).getPrice());
            assertEquals(expectedFoodSearchList.get(i).getInventory(), foodSearchList.get(i).getInventory());
            assertEquals(expectedFoodSearchList.get(i).getDiscount(), foodSearchList.get(i).getDiscount());
            assertEquals(expectedFoodSearchList.get(i).getIsDonate(), foodSearchList.get(i).getIsDonate());
            assertEquals(expectedFoodSearchList.get(i).getFoodType(), foodSearchList.get(i).getFoodType());
            assertEquals(expectedFoodSearchList.get(i).getStoreName(), foodSearchList.get(i).getStoreName());
            assertEquals(expectedFoodSearchList.get(i).getCity(), foodSearchList.get(i).getCity());
        }
    }

    @Test
    public void testSearchFood() throws SQLException {
        String query = "Apple";
        String sql = "SELECT * FROM food_search WHERE fname LIKE ? OR food_type LIKE ? OR store_name LIKE ? OR city LIKE ?";

        List<FoodSearch> expectedFoodSearchList = new ArrayList<>();
        FoodSearch foodSearch1 = new FoodSearch();
        foodSearch1.setFid(1);
        foodSearch1.setFname("Apple");
        foodSearch1.setExpiration(LocalDate.of(2024, 12, 31));
        foodSearch1.setPrice(BigDecimal.valueOf(1.99));
        foodSearch1.setInventory(100);
        foodSearch1.setDiscount(0.1);
        foodSearch1.setIsDonate(0);
        foodSearch1.setFoodType("Fruit");
        foodSearch1.setStoreName("Local Market");
        foodSearch1.setCity("Toronto");
        expectedFoodSearchList.add(foodSearch1);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("fid")).thenReturn(1);
        when(resultSet.getString("fname")).thenReturn("Apple");
        when(resultSet.getDate("expiration")).thenReturn(Date.valueOf(LocalDate.of(2024, 12, 31)));
        when(resultSet.getBigDecimal("price")).thenReturn(BigDecimal.valueOf(1.99));
        when(resultSet.getInt("inventory")).thenReturn(100);
        when(resultSet.getDouble("discount")).thenReturn(0.1);
        when(resultSet.getInt("is_donate")).thenReturn(0);
        when(resultSet.getString("food_type")).thenReturn("Fruit");
        when(resultSet.getString("store_name")).thenReturn("Local Market");
        when(resultSet.getString("city")).thenReturn("Toronto");

        List<FoodSearch> foodSearchList = foodSearchDao.searchFood(query);

        assertNotNull(foodSearchList);
        assertEquals(expectedFoodSearchList.size(), foodSearchList.size());
        for (int i = 0; i < foodSearchList.size(); i++) {
            assertEquals(expectedFoodSearchList.get(i).getFid(), foodSearchList.get(i).getFid());
            assertEquals(expectedFoodSearchList.get(i).getFname(), foodSearchList.get(i).getFname());
            assertEquals(expectedFoodSearchList.get(i).getExpiration(), foodSearchList.get(i).getExpiration());
            assertEquals(expectedFoodSearchList.get(i).getPrice(), foodSearchList.get(i).getPrice());
            assertEquals(expectedFoodSearchList.get(i).getInventory(), foodSearchList.get(i).getInventory());
            assertEquals(expectedFoodSearchList.get(i).getDiscount(), foodSearchList.get(i).getDiscount());
            assertEquals(expectedFoodSearchList.get(i).getIsDonate(), foodSearchList.get(i).getIsDonate());
            assertEquals(expectedFoodSearchList.get(i).getFoodType(), foodSearchList.get(i).getFoodType());
            assertEquals(expectedFoodSearchList.get(i).getStoreName(), foodSearchList.get(i).getStoreName());
            assertEquals(expectedFoodSearchList.get(i).getCity(), foodSearchList.get(i).getCity());
        }
    }

}

