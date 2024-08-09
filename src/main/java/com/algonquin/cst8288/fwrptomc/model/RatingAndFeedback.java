package com.algonquin.cst8288.fwrptomc.model;

import java.time.LocalDateTime;

/**
 * Represents the Rating and Feedback model.
 * 
 * <p>
 * This class holds information about user ratings and feedback on food items.
 * It includes details such as the rating ID, user ID, food ID, rating value, review text, 
 * and the timestamp when the feedback was created.
 * </p>
 * 
 * <p>
 * The {@code RatingAndFeedback} class provides getters and setters for accessing and modifying these fields.
 * </p>
 * 
 * Author: Xihai Ren
 */
public class RatingAndFeedback {
    private int ratingID;
    private int userID;
    private int foodID;
    private int rating;
    private String review;
    private LocalDateTime createdAt;

    // Getters and Setters
    public int getRatingID() {
        return ratingID;
    }

    public void setRatingID(int ratingID) {
        this.ratingID = ratingID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}