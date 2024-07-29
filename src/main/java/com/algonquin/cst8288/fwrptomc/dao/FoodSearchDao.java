package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.FoodSearch;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodSearchDao {

    private JDBCClient jdbcClient;

    public FoodSearchDao() {
        this.jdbcClient = new JDBCClient();
    }

    /**
     * Retrieve all food items from the food_search view
     *
     * @return a list of FoodSearch objects
     */
    public List<FoodSearch> getAllFoodSearch() {
        String sql = "SELECT * FROM food_search";
        List<FoodSearch> foodSearchList = new ArrayList<>();

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                FoodSearch foodSearch = new FoodSearch();
                foodSearch.setFid(rs.getInt("fid"));
                foodSearch.setFname(rs.getString("fname"));
                foodSearch.setExpiration(rs.getDate("expiration").toLocalDate());
                foodSearch.setPrice(rs.getBigDecimal("price"));
                foodSearch.setInventory(rs.getInt("inventory"));
                foodSearch.setDiscount(rs.getDouble("discount"));
                foodSearch.setIsDonate(rs.getInt("is_donate"));
                foodSearch.setFoodType(rs.getString("food_type"));
                foodSearch.setStoreName(rs.getString("store_name"));
                foodSearch.setCity(rs.getString("city"));
                foodSearchList.add(foodSearch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foodSearchList;
    }

    /**
     * Retrieve food items from the food_search view based on a search query
     *
     * @param query the search query
     * @return a list of FoodSearch objects
     */
    public List<FoodSearch> searchFood(String query) {
        String sql = "SELECT * FROM food_search WHERE fname LIKE ? OR food_type LIKE ? OR store_name LIKE ? OR city LIKE ?";
        List<FoodSearch> foodSearchList = new ArrayList<>();

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String searchQuery = "%" + query + "%";
            pstmt.setString(1, searchQuery);
            pstmt.setString(2, searchQuery);
            pstmt.setString(3, searchQuery);
            pstmt.setString(4, searchQuery);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FoodSearch foodSearch = new FoodSearch();
                foodSearch.setFid(rs.getInt("fid"));
                foodSearch.setFname(rs.getString("fname"));
                foodSearch.setExpiration(rs.getDate("expiration").toLocalDate());
                foodSearch.setPrice(rs.getBigDecimal("price"));
                foodSearch.setInventory(rs.getInt("inventory"));
                foodSearch.setDiscount(rs.getDouble("discount"));
                foodSearch.setIsDonate(rs.getInt("is_donate"));
                foodSearch.setFoodType(rs.getString("food_type"));
                foodSearch.setStoreName(rs.getString("store_name"));
                foodSearch.setCity(rs.getString("city"));
                foodSearchList.add(foodSearch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foodSearchList;
    }

    public List<FoodSearch> search(String searchQuery, String foodType, String priceRange, String expiration, String supplier, String location) {
        List<FoodSearch> foodSearchList = new ArrayList<>();
        String sql = "SELECT f.fid, f.fname, f.expiration, f.price, f.inventory, f.discount, f.is_donate, f.food_type, f.store_name, f.city "
                + "FROM food_search f "
                + "WHERE 1=1 ";

        if (searchQuery != null && !searchQuery.isEmpty()) {
            sql += "AND f.fname LIKE ? ";
        }
        if (foodType != null && !foodType.isEmpty()) {
            sql += "AND f.food_type = ? ";
        }
        if (priceRange != null && !priceRange.isEmpty()) {
            switch (priceRange) {
                case "5":
                    sql += "AND f.price < 5 ";
                    break;
                case "5-10":
                    sql += "AND f.price BETWEEN 5 AND 10 ";
                    break;
                case "10":
                    sql += "AND f.price > 10 ";
                    break;
            }
        }
        if (expiration != null && !expiration.isEmpty()) {
            sql += "AND f.expiration >= DATE_ADD(CURDATE(), INTERVAL ? DAY) ";
        }
        if (supplier != null && !supplier.isEmpty()) {
            sql += "AND f.store_name = ? ";
        }
        if (location != null && !location.isEmpty()) {
            sql += "AND f.city = ? ";
        }

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            int paramIndex = 1;

            if (searchQuery != null && !searchQuery.isEmpty()) {
                stmt.setString(paramIndex++, "%" + searchQuery + "%");
            }
            if (foodType != null && !foodType.isEmpty()) {
                stmt.setString(paramIndex++, foodType);
            }
            if (expiration != null && !expiration.isEmpty()) {
                stmt.setInt(paramIndex++, Integer.parseInt(expiration));
            }
            if (supplier != null && !supplier.isEmpty()) {
                stmt.setString(paramIndex++, supplier);
            }
            if (location != null && !location.isEmpty()) {
                stmt.setString(paramIndex++, location);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FoodSearch foodSearch = new FoodSearch();
                    foodSearch.setFid(rs.getInt("fid"));
                    foodSearch.setFname(rs.getString("fname"));
                    foodSearch.setExpiration(rs.getDate("expiration").toLocalDate());
                    foodSearch.setPrice(rs.getBigDecimal("price"));
                    foodSearch.setInventory(rs.getInt("inventory"));
                    foodSearch.setDiscount(rs.getDouble("discount"));
                    foodSearch.setIsDonate(rs.getInt("is_donate"));
                    foodSearch.setFoodType(rs.getString("food_type"));
                    foodSearch.setStoreName(rs.getString("store_name"));
                    foodSearch.setCity(rs.getString("city"));
                    foodSearchList.add(foodSearch);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foodSearchList;
    }
}
