package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.RatingAndFeedbackViewDao;
import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedbackView;

import java.util.List;

/**
 * Service class that handles operations related to viewing ratings and feedback.
 * 
 * <p>
 * This service provides methods for retrieving ratings and feedback specifically associated with a retailer.
 * It interacts with the {@link RatingAndFeedbackViewDao} to perform database operations.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     RatingAndFeedbackViewService feedbackViewService = new RatingAndFeedbackViewService();
 *     List&lt;RatingAndFeedbackView&gt; feedback = feedbackViewService.getRatingAndFeedbackByRetailerId(retailerId);
 * </pre>
 * </p>
 * 
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link RatingAndFeedbackViewDao}: Data access object for performing operations on rating and feedback view data.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * The class interacts with the {@link RatingAndFeedbackViewDao} to perform all database operations
 * related to viewing ratings and feedback.
 * </p>
 * 
 * @author Xihai Ren
 */
public class RatingAndFeedbackViewService {

    private RatingAndFeedbackViewDao feedbackViewDao;

    /**
     * Constructs a new RatingAndFeedbackViewService and initializes the RatingAndFeedbackViewDao.
     */
    public RatingAndFeedbackViewService() {
        this.feedbackViewDao = new RatingAndFeedbackViewDao();
    }

    /**
     * Retrieves ratings and feedback associated with a specific retailer ID.
     *
     * @param retailerId the ID of the retailer
     * @return a list of RatingAndFeedbackView objects associated with the retailer
     */
    public List<RatingAndFeedbackView> getRatingAndFeedbackByRetailerId(int retailerId) {
        return feedbackViewDao.getRatingAndFeedbackByRetailerId(retailerId);
    }
}