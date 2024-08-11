package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing rating and feedback in the
 * database.
 *
 * This class provides methods for adding, updating, deleting, and retrieving
 * rating and feedback records from the database. It interacts with the database
 * using JDBC.
 *
 * Note: Ensure that the JDBCClient class is correctly implemented to provide a
 * valid database connection.
 *
 * Author: Sam Doiron
 */
public class RatingAndFeedbackDao {

    private JDBCClient jdbcClient;

    /**
     * Constructs a new RatingAndFeedbackDao and initializes the JDBCClient.
     */
    public RatingAndFeedbackDao() {
        this.jdbcClient = new JDBCClient();
    }

    /**
     * Adds a new rating and feedback to the database.
     *
     * @param ratingAndFeedback the rating and feedback to be added
     */
    public void addRatingAndFeedback(RatingAndFeedback ratingAndFeedback) {
        String sql = "INSERT INTO RatingAndFeedback (UserID, FoodID, Rating, Review, CreatedAt) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ratingAndFeedback.getUserID());
            pstmt.setInt(2, ratingAndFeedback.getFoodID());
            pstmt.setInt(3, ratingAndFeedback.getRating());
            pstmt.setString(4, ratingAndFeedback.getReview());
            pstmt.setTimestamp(5, Timestamp.valueOf(ratingAndFeedback.getCreatedAt()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing rating and feedback in the database.
     *
     * @param ratingAndFeedback the rating and feedback to be updated
     */
    public void updateRatingAndFeedback(RatingAndFeedback ratingAndFeedback) {
        String sql = "UPDATE RatingAndFeedback SET UserID = ?, FoodID = ?, Rating = ?, Review = ?, CreatedAt = ? WHERE RatingID = ?";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement update = conn.prepareStatement(sql)) {
            update.setInt(1, ratingAndFeedback.getUserID());
            update.setInt(2, ratingAndFeedback.getFoodID());
            update.setInt(3, ratingAndFeedback.getRating());
            update.setString(4, ratingAndFeedback.getReview());
            update.setTimestamp(5, Timestamp.valueOf(ratingAndFeedback.getCreatedAt()));
            update.setInt(6, ratingAndFeedback.getRatingID());
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a rating and feedback from the database by its ID.
     *
     * @param ratingID the rating ID of the rating and feedback to be deleted
     */
    public void deleteRatingAndFeedback(int ratingID) {
        String sql = "DELETE FROM RatingAndFeedback WHERE RatingID = ?";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement del = conn.prepareStatement(sql)) {
            del.setInt(1, ratingID);
            del.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a rating and feedback from the database by its ID.
     *
     * @param ratingID the rating ID of the rating and feedback to be retrieved
     * @return the RatingAndFeedback object
     */
    public RatingAndFeedback getRatingAndFeedbackById(int ratingID) {
        String sql = "SELECT * FROM RatingAndFeedback WHERE RatingID = ?";
        RatingAndFeedback ratingAndFeedback = null;

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement getRating = conn.prepareStatement(sql)) {
            getRating.setInt(1, ratingID);
            ResultSet rs = getRating.executeQuery();

            if (rs.next()) {
                ratingAndFeedback = new RatingAndFeedback();
                ratingAndFeedback.setRatingID(rs.getInt("RatingID"));
                ratingAndFeedback.setUserID(rs.getInt("UserID"));
                ratingAndFeedback.setFoodID(rs.getInt("FoodID"));
                ratingAndFeedback.setRating(rs.getInt("Rating"));
                ratingAndFeedback.setReview(rs.getString("Review"));
                ratingAndFeedback.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratingAndFeedback;
    }

    /**
     * Retrieves all ratings and feedback from the database.
     *
     * @return a list of RatingAndFeedback objects
     */
    public List<RatingAndFeedback> getAllRatingsAndFeedback() {
        String sql = "SELECT * FROM RatingAndFeedback";
        List<RatingAndFeedback> ratingsAndFeedback = new ArrayList<>();

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                RatingAndFeedback ratingAndFeedback = new RatingAndFeedback();
                ratingAndFeedback.setRatingID(rs.getInt("RatingID"));
                ratingAndFeedback.setUserID(rs.getInt("UserID"));
                ratingAndFeedback.setFoodID(rs.getInt("FoodID"));
                ratingAndFeedback.setRating(rs.getInt("Rating"));
                ratingAndFeedback.setReview(rs.getString("Review"));
                ratingAndFeedback.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                ratingsAndFeedback.add(ratingAndFeedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratingsAndFeedback;
    }

    /**
     * Retrieves all ratings and feedback by a specific user ID.
     *
     * @param userId the user ID to filter ratings and feedback
     * @return a list of RatingAndFeedback objects
     */
    public List<RatingAndFeedback> getAllRatingsAndFeedbackByUserId(int userId) {
        String sql = "SELECT * FROM RatingAndFeedback where userId = " + userId;
        List<RatingAndFeedback> ratingsAndFeedback = new ArrayList<>();

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement getall = conn.prepareStatement(sql); ResultSet rs = getall.executeQuery()) {
            while (rs.next()) {
                RatingAndFeedback ratingAndFeedback = new RatingAndFeedback();
                ratingAndFeedback.setRatingID(rs.getInt("RatingID"));
                ratingAndFeedback.setUserID(rs.getInt("UserID"));
                ratingAndFeedback.setFoodID(rs.getInt("FoodID"));
                ratingAndFeedback.setRating(rs.getInt("Rating"));
                ratingAndFeedback.setReview(rs.getString("Review"));
                ratingAndFeedback.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                ratingsAndFeedback.add(ratingAndFeedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratingsAndFeedback;
    }

    /**
     * Retrieves the total number of feedback given by a specific user ID.
     *
     * @param userId the user ID to filter ratings and feedback
     * @return the total number of feedback by the user
     */
    public int getTotalFeedbackByUserId(int userId) {
        String sql = "SELECT COUNT(*) AS total_feedback FROM RatingAndFeedback WHERE UserID = ?";
        int totalFeedback = 0;

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement getTotal = conn.prepareStatement(sql)) {
            getTotal.setInt(1, userId);
            ResultSet rs = getTotal.executeQuery();

            if (rs.next()) {
                totalFeedback = rs.getInt("total_feedback");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalFeedback;
    }
}
