package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing food items in the database.
 *
 * <p>
 * This class provides methods for adding, updating, deleting, and retrieving
 * food records from the database. It interacts with the database using JDBC.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     FoodDao foodDao = new FoodDao();
 *     Food food = foodDao.getFoodById(1);
 * </pre>
 * </p>
 *
 * <p>
 * Note: Ensure that the JDBCClient class is correctly implemented to provide a
 * valid database connection.
 * </p>
 *
 * @author Alexis Trinh
 * @author Xihai Ren

 */
public class FoodDao {

    private JDBCClient jdbcClient;

    /**
     * Constructs a new FoodDao and initializes the JDBCClient.
     */
    public FoodDao() {
        this.jdbcClient = new JDBCClient(); // Initialize JDBC client
    }

    /**
     * Adds a new food item to the database.
     *
     * @param food the food item to be added
     */
    public void add(Food food) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = jdbcClient.getConnection().prepareStatement(
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

    /**
     * Updates an existing food item in the database.
     *
     * @param food the food item to be updated
     */
    public void update(Food food) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = jdbcClient.getConnection().prepareStatement(
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

    /**
     * Deletes a food item from the database.
     *
     * @param food the food item to be deleted
     */
    public void delete(Food food) {
        String sql = "DELETE FROM food WHERE fid = ?";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, food.getFid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    /**
     * Retrieves a food item from the database by its ID.
     *
     * @param fid the ID of the food item to be retrieved
     * @return the Food object, or null if not found
     */
    public Food getFoodById(int fid) {
        Food food = null;
        String sql = "SELECT * FROM food WHERE fid = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = jdbcClient.getConnection();
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
                food.setIsSurplus(rs.getInt("is_surplus"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return food;
    }

    /**
     * Retrieves all food items from the database, optionally filtered by
     * expiration date.
     *
     * @param search an optional search filter based on expiration date
     * @return a list of Food objects
     */
    public List<Food> getAllFoods(String search) {
        List<Food> foodList = new ArrayList<>();
        String sql = "SELECT * FROM food";
        if (search != null && !search.trim().isEmpty()) {
            sql += " WHERE expiration BETWEEN DATE_SUB(CURDATE(), INTERVAL 7 DAY) AND CURDATE()";
        }
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
                food.setIsSurplus(rs.getInt("is_surplus"));
                foodList.add(food);

            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception
        }
        return foodList;
    }

    /**
     * Retrieves all food items from the database.
     *
     * @return a list of Food objects
     */
    public List<Food> getAllFoods() {
        List<Food> foodList = new ArrayList<>();
        String sql = "SELECT * FROM food";

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
                food.setIsSurplus(rs.getInt("is_surplus"));
                foodList.add(food);

            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception
        }
        return foodList;
    }

    /**
     * Retrieves food items from the database based on donation status.
     *
     * @param isDonated true to retrieve donated food, false for non-donated
     * food
     * @return a list of Food objects
     */
    public List<Food> getFoodsByDonatestate(boolean isDonated) {
        String sql = "SELECT * FROM food where is_donate = '0' ";

        if (isDonated) {
            sql = "SELECT * FROM food where is_donate = '1'";
        }
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
                food.setIsSurplus(rs.getInt("is_surplus"));
                foods.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foods;
    }

    /**
     * Updates the surplus and donation status of a food item in the database.
     *
     * @param foodId the ID of the food item to be updated
     * @param isSurplus the surplus status to set (1 for surplus, 0 for not
     * surplus)
     * @param isDonate the donation status to set (1 for donated, 0 for not
     * donated)
     */
    public void updateFoodSurplusStatus(int foodId, int isSurplus, int isDonate) {

        String sql = "UPDATE food SET is_donate = ?, is_surplus = ? where fid = ?";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, isDonate);
            pstmt.setInt(2, isSurplus);
            pstmt.setInt(3, foodId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all food items available for donation from the database.
     *
     * @return a list of Food objects that are available for donation
     */
    public List<Food> getAllFoodsForDonation() {
        return this.getFoodsByDonatestate(true);
    }

    /**
     * Retrieves all food items available for purchase from the database.
     *
     * @return a list of Food objects that are available for purchase
     */
    public List<Food> getAllFoodsForPurchase() {
        return this.getFoodsByDonatestate(false);
    }

    /**
     * Retrieves food items associated with a user's orders.
     *
     * @param userId the ID of the user
     * @return a list of Food objects that the user has ordered
     */
    public List<Food> getFoodsFromOrdersByUserId(int userId) {
        List<Food> foodList = new ArrayList<>();
        String sql = "SELECT f.fid, f.fname FROM food f JOIN orders o ON f.fid = o.fid WHERE o.uid = ?";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Food food = new Food();
                food.setFid(rs.getInt("fid"));
                food.setFname(rs.getString("fname"));
                foodList.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception
        }
        return foodList;
    }

    /**
     * Retrieves food items associated with an organization's claims.
     *
     * @param organizationId the ID of the organization
     * @return a list of Food objects that the organization has claimed
     */
    public List<Food> getFoodsFromClaimsByOrganizationId(int organizationId) {
        List<Food> foodList = new ArrayList<>();
        String sql = "SELECT f.fid, f.fname FROM food f JOIN claims c ON f.fid = c.food_id WHERE c.organization_id = ?";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, organizationId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Food food = new Food();
                food.setFid(rs.getInt("fid"));
                food.setFname(rs.getString("fname"));
                foodList.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception
        }
        return foodList;
    }

    /**
     * Retrieves the total number of listed food items by a retailer.
     *
     * @param retailerId the ID of the retailer
     * @return the total number of listed food items
     */
    public int getTotalListedItemsByRetailerId(int retailerId) {
        String sql = "SELECT COUNT(*) FROM food f JOIN store s ON f.store_id = s.store_id WHERE s.uid = ?";
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, retailerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
