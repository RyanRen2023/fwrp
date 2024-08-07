/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.FoodType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the FoodTypeDao class.
 */
public class FoodTypeDaoTest {

    @Mock
    private JDBCClient jdbcClient;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private FoodTypeDao foodTypeDao;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(jdbcClient.getConnection()).thenReturn(connection);
    }

    @Test
    public void testAddFoodType() throws SQLException {
        FoodType foodType = new FoodType();
        foodType.setName("Fruit");

        String sql = "INSERT INTO food_type (name) VALUES (?)";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        foodTypeDao.addFoodType(foodType);

        verify(preparedStatement).setString(1, foodType.getName());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testUpdateFoodType() throws SQLException {
        FoodType foodType = new FoodType();
        foodType.setId(1);
        foodType.setName("Vegetable");

        String sql = "UPDATE food_type SET name = ? WHERE id = ?";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        foodTypeDao.updateFoodType(foodType);

        verify(preparedStatement).setString(1, foodType.getName());
        verify(preparedStatement).setInt(2, foodType.getId());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testDeleteFoodType() throws SQLException {
        int id = 1;
        String sql = "DELETE FROM food_type WHERE id = ?";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        foodTypeDao.deleteFoodType(id);

        verify(preparedStatement).setInt(1, id);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testGetFoodTypeById() throws SQLException {
        int id = 1;
        String sql = "SELECT * FROM food_type WHERE id = ?";

        FoodType expectedFoodType = new FoodType();
        expectedFoodType.setId(id);
        expectedFoodType.setName("Fruit");

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(id);
        when(resultSet.getString("name")).thenReturn("Fruit");

        FoodType foodType = foodTypeDao.getFoodTypeById(id);

        assertNotNull(foodType);
        assertEquals(expectedFoodType.getId(), foodType.getId());
        assertEquals(expectedFoodType.getName(), foodType.getName());
    }

    @Test
    public void testGetAllFoodTypes() throws SQLException {
        String sql = "SELECT * FROM food_type";

        List<FoodType> expectedFoodTypes = new ArrayList<>();
        FoodType foodType1 = new FoodType();
        foodType1.setId(1);
        foodType1.setName("Fruit");
        expectedFoodTypes.add(foodType1);

        FoodType foodType2 = new FoodType();
        foodType2.setId(2);
        foodType2.setName("Vegetable");
        expectedFoodTypes.add(foodType2);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("id")).thenReturn(1, 2);
        when(resultSet.getString("name")).thenReturn("Fruit", "Vegetable");

        List<FoodType> foodTypes = foodTypeDao.getAllFoodTypes();

        assertNotNull(foodTypes);
        assertEquals(expectedFoodTypes.size(), foodTypes.size());
        for (int i = 0; i < foodTypes.size(); i++) {
            assertEquals(expectedFoodTypes.get(i).getId(), foodTypes.get(i).getId());
            assertEquals(expectedFoodTypes.get(i).getName(), foodTypes.get(i).getName());
        }
    }
}
