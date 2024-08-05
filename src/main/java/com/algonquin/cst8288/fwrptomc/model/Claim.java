package com.algonquin.cst8288.fwrptomc.model;

import java.time.LocalDateTime;

/**
 * Represents a claim made by an organization for a food item.
 * This class contains details about the claim such as the food item ID, 
 * the organization ID that made the claim, and the date and time when the claim was made.
 * 
 * @author renxihai
 */
public class Claim {

    private int claimId;
    private int foodId;
    private int quantity;
    private int organizationId;
    private LocalDateTime claimDate;

    // Default constructor
    public Claim() {
    }

    // Parameterized constructor
    public Claim(int foodId, int quantity, int organizationId) {
        this.foodId = foodId;
        this.quantity = quantity;
        this.organizationId = organizationId;
        this.claimDate = LocalDateTime.now(); // Set claim date to current date and time
    }

    // Getters and Setters
    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public LocalDateTime getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDateTime claimDate) {
        this.claimDate = claimDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    

    // Override toString() for better readability when printing Claim objects
    @Override
    public String toString() {
        return "Claim{" +
                "claimId=" + claimId +
                ", foodId=" + foodId +
                ", organizationId=" + organizationId +
                ", claimDate=" + claimDate +
                '}';
    }
}