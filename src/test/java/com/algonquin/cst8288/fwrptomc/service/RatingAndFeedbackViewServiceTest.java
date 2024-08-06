/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.RatingAndFeedbackViewDao;
import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedbackView;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the RatingAndFeedbackViewService class.
 */
public class RatingAndFeedbackViewServiceTest {

    @Mock
    private RatingAndFeedbackViewDao feedbackViewDao;

    @InjectMocks
    private RatingAndFeedbackViewService feedbackViewService;

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
     * Test of getRatingAndFeedbackByRetailerId method, of class RatingAndFeedbackViewService.
     */
    @Test
    public void testGetRatingAndFeedbackByRetailerId() {
        int retailerId = 1;
        List<RatingAndFeedbackView> feedbackList = Arrays.asList(new RatingAndFeedbackView(), new RatingAndFeedbackView());
        when(feedbackViewDao.getRatingAndFeedbackByRetailerId(retailerId)).thenReturn(feedbackList);
        List<RatingAndFeedbackView> result = feedbackViewService.getRatingAndFeedbackByRetailerId(retailerId);
        assertNotNull(result);
        assertEquals(feedbackList, result);
        verify(feedbackViewDao).getRatingAndFeedbackByRetailerId(retailerId);
    }
}
