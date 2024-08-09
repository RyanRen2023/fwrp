package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedback;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RatingAndFeedbackDaoTest {

    @Mock
    private JDBCClient jdbcClient;
    
    @Mock
    private Connection connection;
    
    @Mock
    private PreparedStatement preparedStatement;
    
    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private RatingAndFeedbackDao ratingAndFeedbackDao;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(jdbcClient.getConnection()).thenReturn(connection);
    }

    @Test
    public void testAddRatingAndFeedback() throws SQLException {
        RatingAndFeedback feedback = new RatingAndFeedback();
        feedback.setUserID(1);
        feedback.setFoodID(2);
        feedback.setRating(5);
        feedback.setReview("Excellent!");
        feedback.setCreatedAt(Timestamp.valueOf("2024-08-06 12:30:00").toLocalDateTime());

        String sql = "INSERT INTO RatingAndFeedback (UserID, FoodID, Rating, Review, CreatedAt) VALUES (?, ?, ?, ?, ?)";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        ratingAndFeedbackDao.addRatingAndFeedback(feedback);

        verify(preparedStatement, times(1)).setInt(1, feedback.getUserID());
        verify(preparedStatement, times(1)).setInt(2, feedback.getFoodID());
        verify(preparedStatement, times(1)).setInt(3, feedback.getRating());
        verify(preparedStatement, times(1)).setString(4, feedback.getReview());
        verify(preparedStatement, times(1)).setTimestamp(5, Timestamp.valueOf(feedback.getCreatedAt()));
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateRatingAndFeedback() throws SQLException {
        RatingAndFeedback feedback = new RatingAndFeedback();
        feedback.setRatingID(1);
        feedback.setUserID(1);
        feedback.setFoodID(2);
        feedback.setRating(4);
        feedback.setReview("Good");
        feedback.setCreatedAt(Timestamp.valueOf("2024-08-06 12:30:00").toLocalDateTime());

        String sql = "UPDATE RatingAndFeedback SET UserID = ?, FoodID = ?, Rating = ?, Review = ?, CreatedAt = ? WHERE RatingID = ?";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        ratingAndFeedbackDao.updateRatingAndFeedback(feedback);

        verify(preparedStatement, times(1)).setInt(1, feedback.getUserID());
        verify(preparedStatement, times(1)).setInt(2, feedback.getFoodID());
        verify(preparedStatement, times(1)).setInt(3, feedback.getRating());
        verify(preparedStatement, times(1)).setString(4, feedback.getReview());
        verify(preparedStatement, times(1)).setTimestamp(5, Timestamp.valueOf(feedback.getCreatedAt()));
        verify(preparedStatement, times(1)).setInt(6, feedback.getRatingID());
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteRatingAndFeedback() throws SQLException {
        int ratingID = 1;
        String sql = "DELETE FROM RatingAndFeedback WHERE RatingID = ?";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        ratingAndFeedbackDao.deleteRatingAndFeedback(ratingID);

        verify(preparedStatement, times(1)).setInt(1, ratingID);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testGetRatingAndFeedbackById() throws SQLException {
        int ratingID = 1;
        String sql = "SELECT * FROM RatingAndFeedback WHERE RatingID = ?";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("RatingID")).thenReturn(ratingID);
        when(resultSet.getInt("UserID")).thenReturn(1);
        when(resultSet.getInt("FoodID")).thenReturn(2);
        when(resultSet.getInt("Rating")).thenReturn(5);
        when(resultSet.getString("Review")).thenReturn("Excellent!");
        when(resultSet.getTimestamp("CreatedAt")).thenReturn(Timestamp.valueOf("2024-08-06 12:30:00"));

        RatingAndFeedback feedback = ratingAndFeedbackDao.getRatingAndFeedbackById(ratingID);

        assertNotNull(feedback);
        assertEquals(ratingID, feedback.getRatingID());
        assertEquals(1, feedback.getUserID());
        assertEquals(2, feedback.getFoodID());
        assertEquals(5, feedback.getRating());
        assertEquals("Excellent!", feedback.getReview());
        assertEquals(Timestamp.valueOf("2024-08-06 12:30:00").toLocalDateTime(), feedback.getCreatedAt());
    }

    @Test
    public void testGetAllRatingsAndFeedback() throws SQLException {
        String sql = "SELECT * FROM RatingAndFeedback";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("RatingID")).thenReturn(1, 2);
        when(resultSet.getInt("UserID")).thenReturn(1, 2);
        when(resultSet.getInt("FoodID")).thenReturn(2, 3);
        when(resultSet.getInt("Rating")).thenReturn(5, 4);
        when(resultSet.getString("Review")).thenReturn("Excellent!", "Good");
        when(resultSet.getTimestamp("CreatedAt")).thenReturn(Timestamp.valueOf("2024-08-06 12:30:00"), Timestamp.valueOf("2024-08-06 13:30:00"));

        List<RatingAndFeedback> feedbackList = ratingAndFeedbackDao.getAllRatingsAndFeedback();

        assertNotNull(feedbackList);
        assertEquals(2, feedbackList.size());

        RatingAndFeedback feedback1 = feedbackList.get(0);
        assertEquals(1, feedback1.getRatingID());
        assertEquals(1, feedback1.getUserID());
        assertEquals(2, feedback1.getFoodID());
        assertEquals(5, feedback1.getRating());
        assertEquals("Excellent!", feedback1.getReview());
        assertEquals(Timestamp.valueOf("2024-08-06 12:30:00").toLocalDateTime(), feedback1.getCreatedAt());

        RatingAndFeedback feedback2 = feedbackList.get(1);
        assertEquals(2, feedback2.getRatingID());
        assertEquals(2, feedback2.getUserID());
        assertEquals(3, feedback2.getFoodID());
        assertEquals(4, feedback2.getRating());
        assertEquals("Good", feedback2.getReview());
        assertEquals(Timestamp.valueOf("2024-08-06 13:30:00").toLocalDateTime(), feedback2.getCreatedAt());
    }
}
