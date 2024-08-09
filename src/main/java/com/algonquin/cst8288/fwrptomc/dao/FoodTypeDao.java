package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.FoodType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing food types in the database.
 *
 * <p>
 * This class provides methods for adding, updating, deleting, and retrieving
 * food type records from the database. It interacts with the database using
 * JDBC.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     FoodTypeDao foodTypeDao = new FoodTypeDao();
 *     List<FoodType> foodTypes = foodTypeDao.getAllFoodTypes();
 * </pre>
 * </p>
 *
 * <p>
 * Note: Ensure that the JDBCClient class is correctly implemented to provide a
 * valid database connection.
 * </p>
 *
 * @author Xihai Ren
 */
public class FoodTypeDao {

    private JDBCClient jdbcClient;

    /**
     * Constructs a new FoodTypeDao and initializes the JDBCClient.
     */
    public FoodTypeDao() {
        this.jdbcClient = new JDBCClient();
    }

    /**
     * Adds a new food type to the database.
     *
     * @param foodType the food type to be added
     */
    public void addFoodType(FoodType foodType) {
        String sql = "INSERT INTO food_type (name) VALUES (?)";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, foodType.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing food type in the database.
     *
     * @param foodType the food type to be updated
     */
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

    /**
     * Deletes a food type from the database by its ID.
     *
     * @param id the ID of the food type to be deleted
     */
    public void deleteFoodType(int id) {
        String sql = "DELETE FROM food_type WHERE id = ?";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a food type from the database by its ID.
     *
     * @param id the ID of the food type to be retrieved
     * @return the FoodType object, or null if not found
     */
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

    /**
     * Retrieves all food types from the database.
     *
     * @return a list of FoodType objects
     */
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
