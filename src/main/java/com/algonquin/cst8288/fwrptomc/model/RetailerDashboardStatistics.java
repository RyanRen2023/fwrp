package com.algonquin.cst8288.fwrptomc.model;

public class RetailerDashboardStatistics {
    private int totalListedItems;
    private int totalSoldItems;
    private double totalRevenue;
    private String mostPopularItem;
    private int totalDonatedItems; // 新添加的字段

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