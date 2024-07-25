package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RatingAndFeedbackDao {

    private JDBCClient jdbcClient;

    public RatingAndFeedbackDao() {
        this.jdbcClient = new JDBCClient();
    }

    /**
     * Add a new rating and feedback to the database
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
     * Update an existing rating and feedback in the database
     *
     * @param ratingAndFeedback the rating and feedback to be updated
     */
    public void updateRatingAndFeedback(RatingAndFeedback ratingAndFeedback) {
        String sql = "UPDATE RatingAndFeedback SET UserID = ?, FoodID = ?, Rating = ?, Review = ?, CreatedAt = ? WHERE RatingID = ?";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ratingAndFeedback.getUserID());
            pstmt.setInt(2, ratingAndFeedback.getFoodID());
            pstmt.setInt(3, ratingAndFeedback.getRating());
            pstmt.setString(4, ratingAndFeedback.getReview());
            pstmt.setTimestamp(5, Timestamp.valueOf(ratingAndFeedback.getCreatedAt()));
            pstmt.setInt(6, ratingAndFeedback.getRatingID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a rating and feedback from the database by its ID
     *
     * @param ratingID the rating ID of the rating and feedback to be deleted
     */
    public void deleteRatingAndFeedback(int ratingID) {
        String sql = "DELETE FROM RatingAndFeedback WHERE RatingID = ?";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ratingID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve a rating and feedback from the database by its ID
     *
     * @param ratingID the rating ID of the rating and feedback to be retrieved
     * @return the RatingAndFeedback object
     */
    public RatingAndFeedback getRatingAndFeedbackById(int ratingID) {
        String sql = "SELECT * FROM RatingAndFeedback WHERE RatingID = ?";
        RatingAndFeedback ratingAndFeedback = null;

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ratingID);
            ResultSet rs = pstmt.executeQuery();

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
     * Retrieve all ratings and feedback from the database
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
}