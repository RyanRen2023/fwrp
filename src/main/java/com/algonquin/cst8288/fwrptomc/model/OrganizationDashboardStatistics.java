/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.model;

public class OrganizationDashboardStatistics {

    private int totalClaims;
    private int totalDonationsReceived;
    private String mostClaimedFoodItem;

    // Getters and Setters
    public int getTotalClaims() {
        return totalClaims;
    }

    public void setTotalClaims(int totalClaims) {
        this.totalClaims = totalClaims;
    }

    public int getTotalDonationsReceived() {
        return totalDonationsReceived;
    }

    public void setTotalDonationsReceived(int totalDonationsReceived) {
        this.totalDonationsReceived = totalDonationsReceived;
    }

    public String getMostClaimedFoodItem() {
        return mostClaimedFoodItem;
    }

    public void setMostClaimedFoodItem(String mostClaimedFoodItem) {
        this.mostClaimedFoodItem = mostClaimedFoodItem;
    }
}
