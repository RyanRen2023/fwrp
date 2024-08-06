/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.model;

public class ConsumerDashboardStatistics {

    private int totalOrders;
    private double totalExpenditure;
    private int totalFeedback;
    private String favoriteFoodItem;

    // Constructor
    public ConsumerDashboardStatistics(int totalOrders, double totalExpenditure, int totalFeedback, String favoriteFoodItem) {
        this.totalOrders = totalOrders;
        this.totalExpenditure = totalExpenditure;
        this.totalFeedback = totalFeedback;
        this.favoriteFoodItem = favoriteFoodItem;
    }

    // Getters and Setters
    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public double getTotalExpenditure() {
        return totalExpenditure;
    }

    public void setTotalExpenditure(double totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    public int getTotalFeedback() {
        return totalFeedback;
    }

    public void setTotalFeedback(int totalFeedback) {
        this.totalFeedback = totalFeedback;
    }

    public String getFavoriteFoodItem() {
        return favoriteFoodItem;
    }

    public void setFavoriteFoodItem(String favoriteFoodItem) {
        this.favoriteFoodItem = favoriteFoodItem;
    }

    @Override
    public String toString() {
        return "ConsumerDashboardStatistics{"
                + "totalOrders=" + totalOrders
                + ", totalExpenditure=" + totalExpenditure
                + ", totalFeedback=" + totalFeedback
                + ", favoriteFoodItem='" + favoriteFoodItem + '\''
                + '}';
    }
}
