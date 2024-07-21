/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrps.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author renxihai
 */
@Entity
@Table(name = "RatingAndFeedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RatingAndFeedback.findAll", query = "SELECT r FROM RatingAndFeedback r"),
    @NamedQuery(name = "RatingAndFeedback.findByRatingID", query = "SELECT r FROM RatingAndFeedback r WHERE r.ratingID = :ratingID"),
    @NamedQuery(name = "RatingAndFeedback.findByRating", query = "SELECT r FROM RatingAndFeedback r WHERE r.rating = :rating"),
    @NamedQuery(name = "RatingAndFeedback.findByCreatedAt", query = "SELECT r FROM RatingAndFeedback r WHERE r.createdAt = :createdAt")})
public class RatingAndFeedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RatingID")
    private Integer ratingID;
    @Column(name = "Rating")
    private Integer rating;
    @Lob
    @Size(max = 65535)
    @Column(name = "Review")
    private String review;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "FoodID", referencedColumnName = "fid")
    @ManyToOne(optional = false)
    private Food foodID;
    @JoinColumn(name = "UserID", referencedColumnName = "uid")
    @ManyToOne(optional = false)
    private User userID;

    public RatingAndFeedback() {
    }

    public RatingAndFeedback(Integer ratingID) {
        this.ratingID = ratingID;
    }

    public Integer getRatingID() {
        return ratingID;
    }

    public void setRatingID(Integer ratingID) {
        this.ratingID = ratingID;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Food getFoodID() {
        return foodID;
    }

    public void setFoodID(Food foodID) {
        this.foodID = foodID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ratingID != null ? ratingID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RatingAndFeedback)) {
            return false;
        }
        RatingAndFeedback other = (RatingAndFeedback) object;
        if ((this.ratingID == null && other.ratingID != null) || (this.ratingID != null && !this.ratingID.equals(other.ratingID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.algonquin.cst8288.fwrps.model.RatingAndFeedback[ ratingID=" + ratingID + " ]";
    }
    
}
