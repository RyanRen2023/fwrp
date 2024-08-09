package com.algonquin.cst8288.fwrptomc.model;

/**
 * Represents statistics for a retailer's dashboard.
 * 
 * <p>
 * This class encapsulates various metrics related to a retailer's performance, 
 * such as the total listed items, total sold items, total revenue, the most popular item, 
 * and the total number of donated items.
 * </p>
 * 
 * Author: Xihai Ren
 */
public class RetailerDashboardStatistics {
    private int totalListedItems;
    private int totalSoldItems;
    private double totalRevenue;
    private String mostPopularItem;
    private int totalDonatedItems;

    // Getters and Setters

    public int getTotalListedItems() {
        return totalListedItems;
    }

    public void setTotalListedItems(int totalListedItems) {
        this.totalListedItems = totalListedItems;
    }

    public int getTotalSoldItems() {
        return totalSoldItems;
    }

    public void setTotalSoldItems(int totalSoldItems) {
        this.totalSoldItems = totalSoldItems;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public String getMostPopularItem() {
        return mostPopularItem;
    }

    public void setMostPopularItem(String mostPopularItem) {
        this.mostPopularItem = mostPopularItem;
    }

    public int getTotalDonatedItems() {
        return totalDonatedItems;
    }

    public void setTotalDonatedItems(int totalDonatedItems) {
        this.totalDonatedItems = totalDonatedItems;
    }
}