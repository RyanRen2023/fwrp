package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.RatingAndFeedbackDao;
import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedback;
import java.util.List;

public class RatingAndFeedbackService {

    private RatingAndFeedbackDao ratingAndFeedbackDao;

    public RatingAndFeedbackService() {
        this.ratingAndFeedbackDao = new RatingAndFeedbackDao();
    }

    /**
     * Add a new rating and feedback
     *
     * @param ratingAndFeedback the rating and feedback to be added
     */
    public void addRatingAndFeedback(RatingAndFeedback ratingAndFeedback) {
        ratingAndFeedbackDao.addRatingAndFeedback(ratingAndFeedback);
    }

    /**
     * Update an existing rating and feedback
     *
     * @param ratingAndFeedback the rating and feedback to be updated
     */
    public void updateRatingAndFeedback(RatingAndFeedback ratingAndFeedback) {
        ratingAndFeedbackDao.updateRatingAndFeedback(ratingAndFeedback);
    }

    /**
     * Delete a rating and feedback by its ID
     *
     * @param ratingID the rating ID of the rating and feedback to be deleted
     */
    public void deleteRatingAndFeedback(int ratingID) {
        ratingAndFeedbackDao.deleteRatingAndFeedback(ratingID);
    }

    /**
     * Retrieve a rating and feedback by its ID
     *
     * @param ratingID the rating ID of the rating and feedback to be retrieved
     * @return the RatingAndFeedback object
     */
    public RatingAndFeedback getRatingAndFeedbackById(int ratingID) {
        return ratingAndFeedbackDao.getRatingAndFeedbackById(ratingID);
    }

    /**
     * Retrieve all ratings and feedback
     *
     * @return a list of RatingAndFeedback objects
     */
    public List<RatingAndFeedback> getAllRatingsAndFeedback() {
        return ratingAndFeedbackDao.getAllRatingsAndFeedback();
    }

    public List<RatingAndFeedback> getAllRatingsAndFeedbackByUserId(int userId) {
        return ratingAndFeedbackDao.getAllRatingsAndFeedbackByUserId(userId);
    }
}
