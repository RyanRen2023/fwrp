/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.RatingAndFeedbackDao;
import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedback;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the RatingAndFeedbackService class.
 */
public class RatingAndFeedbackServiceTest {

    @Mock
    private RatingAndFeedbackDao ratingAndFeedbackDao;

    @InjectMocks
    private RatingAndFeedbackService ratingAndFeedbackService;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addRatingAndFeedback method, of class RatingAndFeedbackService.
     */
    @Test
    public void testAddRatingAndFeedback() {
        RatingAndFeedback ratingAndFeedback = new RatingAndFeedback();
        doNothing().when(ratingAndFeedbackDao).addRatingAndFeedback(ratingAndFeedback);
        ratingAndFeedbackService.addRatingAndFeedback(ratingAndFeedback);
        verify(ratingAndFeedbackDao).addRatingAndFeedback(ratingAndFeedback);
    }

    /**
     * Test of updateRatingAndFeedback method, of class RatingAndFeedbackService.
     */
    @Test
    public void testUpdateRatingAndFeedback() {
        RatingAndFeedback ratingAndFeedback = new RatingAndFeedback();
        doNothing().when(ratingAndFeedbackDao).updateRatingAndFeedback(ratingAndFeedback);
        ratingAndFeedbackService.updateRatingAndFeedback(ratingAndFeedback);
        verify(ratingAndFeedbackDao).updateRatingAndFeedback(ratingAndFeedback);
    }

    /**
     * Test of deleteRatingAndFeedback method, of class RatingAndFeedbackService.
     */
    @Test
    public void testDeleteRatingAndFeedback() {
        int ratingID = 1;
        doNothing().when(ratingAndFeedbackDao).deleteRatingAndFeedback(ratingID);
        ratingAndFeedbackService.deleteRatingAndFeedback(ratingID);
        verify(ratingAndFeedbackDao).deleteRatingAndFeedback(ratingID);
    }

    /**
     * Test of getRatingAndFeedbackById method, of class RatingAndFeedbackService.
     */
    @Test
    public void testGetRatingAndFeedbackById() {
        int ratingID = 1;
        RatingAndFeedback ratingAndFeedback = new RatingAndFeedback();
        when(ratingAndFeedbackDao.getRatingAndFeedbackById(ratingID)).thenReturn(ratingAndFeedback);
        RatingAndFeedback result = ratingAndFeedbackService.getRatingAndFeedbackById(ratingID);
        assertNotNull(result);
        assertEquals(ratingAndFeedback, result);
        verify(ratingAndFeedbackDao).getRatingAndFeedbackById(ratingID);
    }

    /**
     * Test of getAllRatingsAndFeedback method, of class RatingAndFeedbackService.
     */
    @Test
    public void testGetAllRatingsAndFeedback() {
        List<RatingAndFeedback> ratingsAndFeedback = Arrays.asList(new RatingAndFeedback(), new RatingAndFeedback());
        when(ratingAndFeedbackDao.getAllRatingsAndFeedback()).thenReturn(ratingsAndFeedback);
        List<RatingAndFeedback> result = ratingAndFeedbackService.getAllRatingsAndFeedback();
        assertNotNull(result);
        assertEquals(ratingsAndFeedback, result);
        verify(ratingAndFeedbackDao).getAllRatingsAndFeedback();
    }

    /**
     * Test of getAllRatingsAndFeedbackByUserId method, of class RatingAndFeedbackService.
     */
    @Test
    public void testGetAllRatingsAndFeedbackByUserId() {
        int userId = 1;
        List<RatingAndFeedback> ratingsAndFeedback = Arrays.asList(new RatingAndFeedback(), new RatingAndFeedback());
        when(ratingAndFeedbackDao.getAllRatingsAndFeedbackByUserId(userId)).thenReturn(ratingsAndFeedback);
        List<RatingAndFeedback> result = ratingAndFeedbackService.getAllRatingsAndFeedbackByUserId(userId);
        assertNotNull(result);
        assertEquals(ratingsAndFeedback, result);
        verify(ratingAndFeedbackDao).getAllRatingsAndFeedbackByUserId(userId);
    }
}
