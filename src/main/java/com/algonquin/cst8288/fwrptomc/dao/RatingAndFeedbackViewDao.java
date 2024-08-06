/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedbackView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingAndFeedbackViewDao {

    private JDBCClient jdbcClient;

    public RatingAndFeedbackViewDao() {
        this.jdbcClient = new JDBCClient();
    }

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