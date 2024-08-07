/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.Food;
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
 * Unit tests for the FoodDao class.
 */
public class FoodDaoTest {

    @Mock
    private JDBCClient jdbcClient;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private FoodDao foodDao;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(jdbcClient.getConnection()).thenReturn(connection);
    }

    @Test
    public void testAdd() throws SQLException {
        Food food = new Food();
        food.setFname("Apple");
        food.setExpiration(LocalDate.now());
        food.setPrice(BigDecimal.valueOf(1.99));
        food.setInventory(100);
        food.setDiscount(0.1);
        food.setFtid(1);
        food.setIsDonate(0);
        food.setStoreId(1);

        String sql = "INSERT INTO food (fname, expiration, price, inventory, discount, ftid, is_donate, store_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        foodDao.add(food);

        verify(preparedStatement).setString(1, food.getFname());
        verify(preparedStatement).setDate(2, java.sql.Date.valueOf(food.getExpiration()));
        verify(preparedStatement).setBigDecimal(3, food.getPrice());
        verify(preparedStatement).setInt(4, food.getInventory());
        verify(preparedStatement).setDouble(5, food.getDiscount());
        verify(preparedStatement).setInt(6, food.getFtid());
        verify(preparedStatement).setInt(7, food.getIsDonate());
        verify(preparedStatement).setInt(8, food.getStoreId());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testUpdate() throws SQLException {
        Food food = new Food();
        food.setFid(1);
        food.setFname("Apple");
        food.setExpiration(LocalDate.now());
        food.setPrice(BigDecimal.valueOf(1.99));
        food.setInventory(100);
        food.setDiscount(0.1);
        food.setFtid(1);
        food.setIsDonate(0);
        food.setStoreId(1);

        String sql = "UPDATE food SET fname = ?, expiration = ?, price = ?, inventory = ?, discount = ?, ftid = ?, is_donate = ?, store_id = ? WHERE fid = ?";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        foodDao.update(food);

        verify(preparedStatement).setString(1, food.getFname());
        verify(preparedStatement).setDate(2, java.sql.Date.valueOf(food.getExpiration()));
        verify(preparedStatement).setBigDecimal(3, food.getPrice());
        verify(preparedStatement).setInt(4, food.getInventory());
        verify(preparedStatement).setDouble(5, food.getDiscount());
        verify(preparedStatement).setInt(6, food.getFtid());
        verify(preparedStatement).setInt(7, food.getIsDonate());
        verify(preparedStatement).setInt(8, food.getStoreId());
        verify(preparedStatement).setInt(9, food.getFid());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testDelete() throws SQLException {
        Food food = new Food();
        food.setFid(1);

        String sql = "DELETE FROM food WHERE fid = ?";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        foodDao.delete(food);

        verify(preparedStatement).setInt(1, food.getFid());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testGetFoodById() throws SQLException {
        int fid = 1;
        String sql = "SELECT * FROM food WHERE fid = ?";

        Food expectedFood = new Food();
        expectedFood.setFid(fid);
        expectedFood.setFname("Apple");
        expectedFood.setExpiration(LocalDate.of(2023, 8, 6));
        expectedFood.setPrice(BigDecimal.valueOf(1.99));
        expectedFood.setInventory(100);
        expectedFood.setDiscount(0.1);
        expectedFood.setFtid(1);
        expectedFood.setIsDonate(0);
        expectedFood.setStoreId(1);
        expectedFood.setIsSurplus(0);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("fid")).thenReturn(fid);
        when(resultSet.getString("fname")).thenReturn("Apple");
        when(resultSet.getDate("expiration")).thenReturn(java.sql.Date.valueOf(LocalDate.of(2023, 8, 6)));
        when(resultSet.getBigDecimal("price")).thenReturn(BigDecimal.valueOf(1.99));
        when(resultSet.getInt("inventory")).thenReturn(100);
        when(resultSet.getDouble("discount")).thenReturn(0.1);
        when(resultSet.getInt("ftid")).thenReturn(1);
        when(resultSet.getInt("is_donate")).thenReturn(0);
        when(resultSet.getInt("store_id")).thenReturn(1);
        when(resultSet.getInt("is_surplus")).thenReturn(0);

        Food food = foodDao.getFoodById(fid);

        assertNotNull(food);
        assertEquals(expectedFood.getFid(), food.getFid());
        assertEquals(expectedFood.getFname(), food.getFname());
        assertEquals(expectedFood.getExpiration(), food.getExpiration());
        assertEquals(expectedFood.getPrice(), food.getPrice());
        assertEquals(expectedFood.getInventory(), food.getInventory());
        assertEquals(expectedFood.getDiscount(), food.getDiscount());
        assertEquals(expectedFood.getFtid(), food.getFtid());
        assertEquals(expectedFood.getIsDonate(), food.getIsDonate());
        assertEquals(expectedFood.getStoreId(), food.getStoreId());
    }

    @Test
    public void testGetAllFoods() throws SQLException {
        String sql = "SELECT * FROM food";

        List<Food> expectedFoods = new ArrayList<>();
        Food food1 = new Food();
        food1.setFid(1);
        food1.setFname("Apple");
        food1.setExpiration(LocalDate.of(2023, 8, 6));
        food1.setPrice(BigDecimal.valueOf(1.99));
        food1.setInventory(100);
        food1.setDiscount(0.1);
        food1.setFtid(1);
        food1.setIsDonate(0);
        food1.setStoreId(1);
        food1.setIsSurplus(0);
        expectedFoods.add(food1);

        Food food2 = new Food();
        food2.setFid(2);
        food2.setFname("Banana");
        food2.setExpiration(LocalDate.of(2023, 8, 7));
        food2.setPrice(BigDecimal.valueOf(0.99));
        food2.setInventory(200);
        food2.setDiscount(0.2);
        food2.setFtid(1);
        food2.setIsDonate(0);
        food2.setStoreId(2);
        food2.setIsSurplus(0);
        expectedFoods.add(food2);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("fid")).thenReturn(1, 2);
        when(resultSet.getString("fname")).thenReturn("Apple", "Banana");
        when(resultSet.getDate("expiration")).thenReturn(
                java.sql.Date.valueOf(LocalDate.of(2023, 8, 6)),
                java.sql.Date.valueOf(LocalDate.of(2023, 8, 7))
        );
        when(resultSet.getBigDecimal("price")).thenReturn(BigDecimal.valueOf(1.99), BigDecimal.valueOf(0.99));
        when(resultSet.getInt("inventory")).thenReturn(100, 200);
        when(resultSet.getDouble("discount")).thenReturn(0.1, 0.2);
        when(resultSet.getInt("ftid")).thenReturn(1, 1);
        when(resultSet.getInt("is_donate")).thenReturn(0, 0);
        when(resultSet.getInt("store_id")).thenReturn(1, 2);
        when(resultSet.getInt("is_surplus")).thenReturn(0, 0);

        List<Food> foods = foodDao.getAllFoods();

        assertNotNull(foods);
        assertEquals(expectedFoods.size(), foods.size());
        for (int i = 0; i < foods.size(); i++) {
            assertEquals(expectedFoods.get(i).getFid(), foods.get(i).getFid());
            assertEquals(expectedFoods.get(i).getFname(), foods.get(i).getFname());
            assertEquals(expectedFoods.get(i).getExpiration(), foods.get(i).getExpiration());
            assertEquals(expectedFoods.get(i).getPrice(), foods.get(i).getPrice());
            assertEquals(expectedFoods.get(i).getInventory(), foods.get(i).getInventory());
            assertEquals(expectedFoods.get(i).getDiscount(), foods.get(i).getDiscount());
            assertEquals(expectedFoods.get(i).getFtid(), foods.get(i).getFtid());
            assertEquals(expectedFoods.get(i).getIsDonate(), foods.get(i).getIsDonate());
            assertEquals(expectedFoods.get(i).getStoreId(), foods.get(i).getStoreId());
        }
    }

    @Test
    public void testGetFoodsByDonatestate() throws SQLException {
        String sql = "SELECT * FROM food where is_donate = '1'";

        List<Food> expectedFoods = new ArrayList<>();
        Food food1 = new Food();
        food1.setFid(1);
        food1.setFname("Apple");
        food1.setExpiration(LocalDate.of(2023, 8, 6));
        food1.setPrice(BigDecimal.valueOf(1.99));
        food1.setInventory(100);
        food1.setDiscount(0.1);
        food1.setFtid(1);
        food1.setIsDonate(1);
        food1.setStoreId(1);
        food1.setIsSurplus(0);
        expectedFoods.add(food1);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("fid")).thenReturn(1);
        when(resultSet.getString("fname")).thenReturn("Apple");
        when(resultSet.getDate("expiration")).thenReturn(java.sql.Date.valueOf(LocalDate.of(2023, 8, 6)));
        when(resultSet.getBigDecimal("price")).thenReturn(BigDecimal.valueOf(1.99));
        when(resultSet.getInt("inventory")).thenReturn(100);
        when(resultSet.getDouble("discount")).thenReturn(0.1);
        when(resultSet.getInt("ftid")).thenReturn(1);
        when(resultSet.getInt("is_donate")).thenReturn(1);
        when(resultSet.getInt("store_id")).thenReturn(1);
        when(resultSet.getInt("is_surplus")).thenReturn(0);

        List<Food> foods = foodDao.getFoodsByDonatestate(true);

        assertNotNull(foods);
        assertEquals(expectedFoods.size(), foods.size());
        for (int i = 0; i < foods.size(); i++) {
            assertEquals(expectedFoods.get(i).getFid(), foods.get(i).getFid());
            assertEquals(expectedFoods.get(i).getFname(), foods.get(i).getFname());
            assertEquals(expectedFoods.get(i).getExpiration(), foods.get(i).getExpiration());
            assertEquals(expectedFoods.get(i).getPrice(), foods.get(i).getPrice());
            assertEquals(expectedFoods.get(i).getInventory(), foods.get(i).getInventory());
            assertEquals(expectedFoods.get(i).getDiscount(), foods.get(i).getDiscount());
            assertEquals(expectedFoods.get(i).getFtid(), foods.get(i).getFtid());
            assertEquals(expectedFoods.get(i).getIsDonate(), foods.get(i).getIsDonate());
            assertEquals(expectedFoods.get(i).getStoreId(), foods.get(i).getStoreId());
        }
    }

    @Test
    public void testUpdateFoodSurplusStatus() throws SQLException {
        int foodId = 1;
        int isSurplus = 1;
        int isDonate = 0;

        String sql = "UPDATE food SET is_donate = ?, is_surplus = ? where fid = ?";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        foodDao.updateFoodSurplusStatus(foodId, isSurplus, isDonate);

        verify(preparedStatement).setInt(1, isDonate);
        verify(preparedStatement).setInt(2, isSurplus);
        verify(preparedStatement).setInt(3, foodId);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testGetTotalListedItemsByRetailerId() throws SQLException {
        int retailerId = 1;
        String sql = "SELECT COUNT(*) FROM food f JOIN store s ON f.store_id = s.store_id WHERE s.uid = ?";

        int expectedTotalListedItems = 10;

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(expectedTotalListedItems);

        int totalListedItems = foodDao.getTotalListedItemsByRetailerId(retailerId);

        assertEquals(expectedTotalListedItems, totalListedItems);
    }
}
     
