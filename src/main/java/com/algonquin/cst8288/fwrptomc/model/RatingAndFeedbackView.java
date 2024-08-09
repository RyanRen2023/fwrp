package com.algonquin.cst8288.fwrptomc.model;

import java.time.LocalDateTime;

/**
 * Represents a view of Rating and Feedback information.
 * 
 * <p>
 * This class encapsulates details about food items, their ratings, reviews, 
 * the time when the feedback was created, and the type of user who provided the feedback.
 * </p>
 * 
 * <p>
 * The {@code RatingAndFeedbackView} class provides getters and setters for accessing and modifying these fields.
 * </p>
 * 
 * Author: Xihai Ren
 */
public class RatingAndFeedbackView {

    private int fid;
    private String fname;
    private int rating;
    private String review;
    private LocalDateTime createdAt;
    private String userType;

    // Getters and Setters
    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}