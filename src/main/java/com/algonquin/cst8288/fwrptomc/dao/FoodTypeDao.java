/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.dao;


import com.algonquin.cst8288.fwrptomc.model.FoodType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodTypeDao {

    private JDBCClient jdbcClient;

    public FoodTypeDao() {
        this.jdbcClient = new JDBCClient();
    }

    public void addFoodType(FoodType foodType) {
        String sql = "INSERT INTO food_type (name) VALUES (?)";
        
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, foodType.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFoodType(FoodType foodType) {
        String sql = "UPDATE food_type SET name = ? WHERE id = ?";
        
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, foodType.getName());
            pstmt.setInt(2, foodType.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFoodType(int id) {
        String sql = "DELETE FROM food_type WHERE id = ?";
        
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public FoodType getFoodTypeById(int id) {
        String sql = "SELECT * FROM food_type WHERE id = ?";
        FoodType foodType = null;
        
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                foodType = new FoodType();
                foodType.setId(rs.getInt("id"));
                foodType.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return foodType;
    }

    public List<FoodType> getAllFoodTypes() {
        String sql = "SELECT * FROM food_type";
        List<FoodType> foodTypes = new ArrayList<>();
        
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                FoodType foodType = new FoodType();
                foodType.setId(rs.getInt("id"));
                foodType.setName(rs.getString("name"));
                foodTypes.add(foodType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return foodTypes;
    }
}