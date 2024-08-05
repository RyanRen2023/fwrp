/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.dao;


import com.algonquin.cst8288.fwrptomc.model.Food;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {

    private JDBCClient jdbcClient;

    public FoodDao() {
        this.jdbcClient = new JDBCClient();
    }

    public void addFood(Food food) {
        String sql = "INSERT INTO food (fname, expiration, price, inventory, discount, ftid, is_donate, store_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, food.getFname());
            pstmt.setDate(2, java.sql.Date.valueOf(food.getExpiration()));
            pstmt.setBigDecimal(3, food.getPrice());
            pstmt.setInt(4, food.getInventory());
            pstmt.setDouble(5, food.getDiscount());
            pstmt.setInt(6, food.getFtid());
            pstmt.setInt(7, food.getIsDonate());
            pstmt.setInt(8, food.getStoreId());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFood(Food food) {
        String sql = "UPDATE food SET fname = ?, expiration = ?, price = ?, inventory = ?, discount = ?, ftid = ?, is_donate = ?, store_id = ? WHERE fid = ?";
        
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, food.getFname());
            pstmt.setDate(2, java.sql.Date.valueOf(food.getExpiration()));
            pstmt.setBigDecimal(3, food.getPrice());
            pstmt.setInt(4, food.getInventory());
            pstmt.setDouble(5, food.getDiscount());
            pstmt.setInt(6, food.getFtid());
            pstmt.setInt(7, food.getIsDonate());
            pstmt.setInt(8, food.getStoreId());
            pstmt.setInt(9, food.getFid());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFood(int fid) {
        String sql = "DELETE FROM food WHERE fid = ?";
        
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, fid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Food getFoodById(int fid) {
        String sql = "SELECT * FROM food WHERE fid = ?";
        Food food = null;
        
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, fid);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                food = new Food();
                food.setFid(rs.getInt("fid"));
                food.setFname(rs.getString("fname"));
                food.setExpiration(rs.getDate("expiration").toLocalDate());
                food.setPrice(rs.getBigDecimal("price"));
                food.setInventory(rs.getInt("inventory"));
                food.setDiscount(rs.getDouble("discount"));
                food.setFtid(rs.getInt("ftid"));
                food.setIsDonate(rs.getInt("is_donate"));
                food.setStoreId(rs.getInt("store_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return food;
    }

    public List<Food> getAllFoods() {
        String sql = "SELECT * FROM food";
        List<Food> foods = new ArrayList<>();
        
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Food food = new Food();
                food.setFid(rs.getInt("fid"));
                food.setFname(rs.getString("fname"));
                food.setExpiration(rs.getDate("expiration").toLocalDate());
                food.setPrice(rs.getBigDecimal("price"));
                food.setInventory(rs.getInt("inventory"));
                food.setDiscount(rs.getDouble("discount"));
                food.setFtid(rs.getInt("ftid"));
                food.setIsDonate(rs.getInt("is_donate"));
                food.setStoreId(rs.getInt("store_id"));
                foods.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return foods;
    }
}
