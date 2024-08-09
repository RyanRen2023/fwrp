package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedbackView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing rating and feedback view in the database.
 * 
 * This class provides methods for retrieving rating and feedback data related to a specific retailer from the database.
 * It interacts with the database using JDBC.
 * 
 * Note: Ensure that the JDBCClient class is correctly implemented to provide
 * a valid database connection.
 * 
 * Author: Xihai Ren
 */
public class RatingAndFeedbackViewDao {

    private JDBCClient jdbcClient;

    /**
     * Constructs a new RatingAndFeedbackViewDao and initializes the JDBCClient.
     */
    public RatingAndFeedbackViewDao() {
        this.jdbcClient = new JDBCClient();
    }

    /**
     * Retrieves rating and feedback data by retailer ID from the database.
     *
     * @param retailerId the retailer ID to filter rating and feedback
     * @return a list of RatingAndFeedbackView objects
     */
    public List<RatingAndFeedbackView> getRatingAndFeedbackByRetailerId(int retailerId) {
        String sql = "SELECT food.fid, food.fname, rf.Rating, rf.Review, rf.CreatedAt, u2.user_type " +
                     "FROM food " +
                     "INNER JOIN store ON store.store_id = food.store_id " +
                     "INNER JOIN User ru ON store.uid = ru.uid " +
                     "INNER JOIN RatingAndFeedback rf ON rf.FoodID = food.fid " +
                     "INNER JOIN User u2 ON rf.userId = u2.uid " +
                     "WHERE ru.uid = ?";

        List<RatingAndFeedbackView> results = new ArrayList<>();

        try (Connection conn = jdbcClient.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, retailerId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                RatingAndFeedbackView result = new RatingAndFeedbackView();
                result.setFid(rs.getInt("fid"));
                result.setFname(rs.getString("fname"));
                result.setRating(rs.getInt("Rating"));
                result.setReview(rs.getString("Review"));
                result.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                result.setUserType(rs.getString("user_type"));
                results.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
}