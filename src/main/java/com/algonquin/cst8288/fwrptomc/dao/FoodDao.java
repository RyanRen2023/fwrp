package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.Food;
import com.algonquin.cst8288.fwrptomc.repository.impl.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {
    private JDBCClient jdbcClient;

    public FoodDao() {
        this.jdbcClient = new JDBCClient(); // Initialize JDBC client
    }

    public void add(Food food) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "INSERT INTO food (fname, expiration, price, inventory, discount, ftid, is_donate, store_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, food.getFname());
            preparedStatement.setDate(2, java.sql.Date.valueOf(food.getExpiration()));
            preparedStatement.setBigDecimal(3, food.getPrice());
            preparedStatement.setInt(4, food.getInventory());
            preparedStatement.setDouble(5, food.getDiscount());
            preparedStatement.setInt(6, food.getFtid());
            preparedStatement.setInt(7, food.getIsDonate());
            preparedStatement.setInt(8, food.getStoreId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void update(Food food) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "UPDATE food SET fname = ?, expiration = ?, price = ?, inventory = ?, discount = ?, ftid = ?, is_donate = ?, store_id = ? WHERE fid = ?"
            );
            preparedStatement.setString(1, food.getFname());
            preparedStatement.setDate(2, java.sql.Date.valueOf(food.getExpiration()));
            preparedStatement.setBigDecimal(3, food.getPrice());
            preparedStatement.setInt(4, food.getInventory());
            preparedStatement.setDouble(5, food.getDiscount());
            preparedStatement.setInt(6, food.getFtid());
            preparedStatement.setInt(7, food.getIsDonate());
            preparedStatement.setInt(8, food.getStoreId());
            preparedStatement.setInt(9, food.getFid()); // Assuming 'fid' is the unique identifier in Food class
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void delete(Food food) {
        String sql = "DELETE FROM food WHERE fid = ?";

        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, food.getFid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public Food getFoodById(int fid) {
        Food food = null;
        String sql = "SELECT * FROM food WHERE fid = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = BaseRepository.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, fid);
            rs = pstmt.executeQuery();

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
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return food;
    }

    public List<Food> getAllFoods(String search) {
        List<Food> foodList = new ArrayList<>();
        String sql = "SELECT * FROM food";
        if(search != null && !search.trim().isEmpty()){
            sql += " WHERE expiration BETWEEN DATE_SUB(CURDATE(), INTERVAL 7 DAY) AND CURDATE()";
        }
        try (Connection conn = jdbcClient.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

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
                foodList.add(food);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception
        }
        return foodList;
    }
}
