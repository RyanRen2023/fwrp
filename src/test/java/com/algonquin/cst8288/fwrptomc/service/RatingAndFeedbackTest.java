/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.RatingAndFeedbackDao;
import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedback;

import java.time.LocalDateTime;


import java.util.ArrayList;
import org.junit.jupiter.api.*;
import org.mockito.*;



import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author amart
 */
public class RatingAndFeedbackTest {

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
        
       
        ratingAndFeedback.setRatingID(1);
        ratingAndFeedback.setUserID(1);
        ratingAndFeedback.setFoodID(1);
        ratingAndFeedback.setRating(2);
        ratingAndFeedback.setReview("test review");
        ratingAndFeedback.setCreatedAt(LocalDateTime.now());    


        
        doNothing().when(ratingAndFeedbackDao).addRatingAndFeedback(ratingAndFeedback);

        ratingAndFeedbackService.addRatingAndFeedback(ratingAndFeedback);

        verify(ratingAndFeedbackDao).addRatingAndFeedback(ratingAndFeedback);    }

    /**
     * Test of updateRatingAndFeedback method, of class RatingAndFeedbackService.
     */
    @Test
    public void testUpdateRatingAndFeedback() {
        RatingAndFeedback ratingAndFeedback = new RatingAndFeedback();
        
        ratingAndFeedback.setRatingID(1);
        ratingAndFeedback.setUserID(1);
        ratingAndFeedback.setFoodID(1);
        ratingAndFeedback.setRating(2);
        ratingAndFeedback.setReview("test update review");
        ratingAndFeedback.setCreatedAt(LocalDateTime.now());  

        doNothing().when(ratingAndFeedbackDao).updateRatingAndFeedback(ratingAndFeedback);

        ratingAndFeedbackService.updateRatingAndFeedback(ratingAndFeedback);

        verify(ratingAndFeedbackDao).updateRatingAndFeedback(ratingAndFeedback);
    }

    /**
     * Test of deleteRatingAndFeedback method, of class RatingAndFeedbackService.
     */
    @Test
    public void testDeleteRatingAndFeedback() {
        int id = 2;

        doNothing().when(ratingAndFeedbackDao).deleteRatingAndFeedback(id);

        ratingAndFeedbackService.deleteRatingAndFeedback(id);

        verify(ratingAndFeedbackDao).deleteRatingAndFeedback(id);
    }

    /**
     * Test of getRatingAndFeedbackById method, of class RatingAndFeedbackService.
     */
    @Test
    public void testGetRatingAndFeedbackById() {
        int ratingAndFeedbackId = 1;
        RatingAndFeedback ratingAndFeedback = new RatingAndFeedback();
        
        ratingAndFeedback.setRatingID(1);
        ratingAndFeedback.setUserID(1);
        ratingAndFeedback.setFoodID(1);
        ratingAndFeedback.setRating(2);
        ratingAndFeedback.setReview("test review");
        ratingAndFeedback.setCreatedAt(LocalDateTime.now());  
       
        
        
        when(ratingAndFeedbackDao.getRatingAndFeedbackById(ratingAndFeedbackId)).thenReturn(ratingAndFeedback);

        RatingAndFeedback result = ratingAndFeedbackService.getRatingAndFeedbackById(ratingAndFeedbackId);

        assertNotNull(result);
        assertEquals(ratingAndFeedback, result);
        verify(ratingAndFeedbackDao).getRatingAndFeedbackById(ratingAndFeedbackId);
    }

    /**
     * Test of getAllRatingsAndFeedback method, of class RatingAndFeedbackService.
     */
    @Test
    public void testGetAllRatingsAndFeedback() {
        List<RatingAndFeedback> ratingsAndFeedback = new ArrayList<>();
        
        RatingAndFeedback ratingAndFeedback1 = new RatingAndFeedback();
        
        ratingAndFeedback1.setRatingID(1);
        ratingAndFeedback1.setUserID(1);
        ratingAndFeedback1.setFoodID(1);
        ratingAndFeedback1.setRating(2);
        ratingAndFeedback1.setReview("test review 1");
        ratingAndFeedback1.setCreatedAt(LocalDateTime.now());  
       
        
        
        RatingAndFeedback ratingAndFeedback2 = new RatingAndFeedback();
        
        ratingAndFeedback2.setRatingID(2);
        ratingAndFeedback2.setUserID(2);
        ratingAndFeedback2.setFoodID(2);
        ratingAndFeedback2.setRating(3);
        ratingAndFeedback2.setReview("test review 2");
        ratingAndFeedback2.setCreatedAt(LocalDateTime.now());  

        

        ratingsAndFeedback.add(ratingAndFeedback1);
        ratingsAndFeedback.add(ratingAndFeedback2);

        when(ratingAndFeedbackDao.getAllRatingsAndFeedback()).thenReturn(ratingsAndFeedback);

        List<RatingAndFeedback> result = ratingAndFeedbackService.getAllRatingsAndFeedback();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ratingAndFeedbackDao).getAllRatingsAndFeedback();

    }
}

