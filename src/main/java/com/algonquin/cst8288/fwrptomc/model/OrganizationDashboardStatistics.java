package com.algonquin.cst8288.fwrptomc.model;

/**
 * Represents the statistics for an organization's dashboard.
 *
 * <p>
 * This class holds information about various metrics related to an
 * organization's activities, including the total number of claims, total
 * donations received, and the most claimed food item.
 * </p>
 *
 * <p>
 * The {@code OrganizationDashboardStatistics} class provides getters and
 * setters for these metrics, allowing easy access and modification of the data.
 * </p>
 *
 * Author: Xihai Ren
 */
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
