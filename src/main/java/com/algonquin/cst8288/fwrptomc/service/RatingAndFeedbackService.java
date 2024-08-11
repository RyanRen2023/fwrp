package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.RatingAndFeedbackDao;
import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedback;
import java.util.List;

/**
 * Service class that handles operations related to ratings and feedback.
 *
 * @author Sam Doiron
 */
public class RatingAndFeedbackService {

    private RatingAndFeedbackDao ratingsAndFeedbackDao;

    /**
     * Constructs a new RatingAndFeedbackService and initializes the
     * RatingAndFeedbackDao.
     */
    public RatingAndFeedbackService() {
        this.ratingsAndFeedbackDao = new RatingAndFeedbackDao();
    }

    /**
     * Adds a new rating and feedback.
     *
     * @param ratingAndFeedback the rating and feedback to be added
     */
    public void addRatingAndFeedback(RatingAndFeedback ratingAndFeedback) {
        ratingsAndFeedbackDao.addRatingAndFeedback(ratingAndFeedback);
    }

    /**
     * Updates an existing rating and feedback.
     *
     * @param ratingAndFeedback the rating and feedback to be updated
     */
    public void updateRatingAndFeedback(RatingAndFeedback ratingAndFeedback) {
        ratingsAndFeedbackDao.updateRatingAndFeedback(ratingAndFeedback);
    }

    /**
     * Deletes a rating and feedback by its ID.
     *
     * @param ratingID the ID of the rating and feedback to be deleted
     */
    public void deleteRatingAndFeedback(int ratingID) {
        ratingsAndFeedbackDao.deleteRatingAndFeedback(ratingID);
    }

    /**
     * Retrieves a rating and feedback by its ID.
     *
     * @param ratingID the ID of the rating and feedback to be retrieved
     * @return the RatingAndFeedback object corresponding to the specified ID
     */
    public RatingAndFeedback getRatingAndFeedbackById(int ratingID) {
        return ratingsAndFeedbackDao.getRatingAndFeedbackById(ratingID);
    }

    /**
     * Retrieves all ratings and feedback.
     *
     * @return a list of all RatingAndFeedback objects
     */
    public List<RatingAndFeedback> getAllRatingsAndFeedback() {
        return ratingsAndFeedbackDao.getAllRatingsAndFeedback();
    }

    /**
     * Retrieves all ratings and feedback associated with a specific user ID.
     *
     * @param userId the ID of the user
     * @return a list of RatingAndFeedback objects associated with the user
     */
    public List<RatingAndFeedback> getAllRatingsAndFeedbackByUserId(int userId) {
        return ratingsAndFeedbackDao.getAllRatingsAndFeedbackByUserId(userId);
    }
}
