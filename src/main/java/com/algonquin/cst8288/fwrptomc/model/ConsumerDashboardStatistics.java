package com.algonquin.cst8288.fwrptomc.model;

/**
 * Model class representing the statistics for the consumer dashboard.
 * 
 * This class holds data related to a consumer's dashboard such as total orders,
 * total expenditure, total feedback, and the consumer's favorite food item.
 * It provides constructors, getters, setters, and a toString method.
 * 
 * Author: Xihai Ren
 */
public class ConsumerDashboardStatistics {

    private int totalOrders;
    private double totalExpenditure;
    private int totalFeedback;
    private String favoriteFoodItem;

    /**
     * Constructs a new ConsumerDashboardStatistics object with the specified values.
     *
     * @param totalOrders the total number of orders
     * @param totalExpenditure the total expenditure made by the consumer
     * @param totalFeedback the total feedback given by the consumer
     * @param favoriteFoodItem the favorite food item of the consumer
     */
    public ConsumerDashboardStatistics(int totalOrders, double totalExpenditure, int totalFeedback, String favoriteFoodItem) {
        this.totalOrders = totalOrders;
        this.totalExpenditure = totalExpenditure;
        this.totalFeedback = totalFeedback;
        this.favoriteFoodItem = favoriteFoodItem;
    }

    /**
     * Gets the total number of orders.
     *
     * @return the total number of orders
     */
    public int getTotalOrders() {
        return totalOrders;
    }

    /**
     * Sets the total number of orders.
     *
     * @param totalOrders the total number of orders to set
     */
    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    /**
     * Gets the total expenditure made by the consumer.
     *
     * @return the total expenditure
     */
    public double getTotalExpenditure() {
        return totalExpenditure;
    }

    /**
     * Sets the total expenditure made by the consumer.
     *
     * @param totalExpenditure the total expenditure to set
     */
    public void setTotalExpenditure(double totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    /**
     * Gets the total feedback given by the consumer.
     *
     * @return the total feedback
     */
    public int getTotalFeedback() {
        return totalFeedback;
    }

    /**
     * Sets the total feedback given by the consumer.
     *
     * @param totalFeedback the total feedback to set
     */
    public void setTotalFeedback(int totalFeedback) {
        this.totalFeedback = totalFeedback;
    }

    /**
     * Gets the favorite food item of the consumer.
     *
     * @return the favorite food item
     */
    public String getFavoriteFoodItem() {
        return favoriteFoodItem;
    }

    /**
     * Sets the favorite food item of the consumer.
     *
     * @param favoriteFoodItem the favorite food item to set
     */
    public void setFavoriteFoodItem(String favoriteFoodItem) {
        this.favoriteFoodItem = favoriteFoodItem;
    }

    /**
     * Returns a string representation of the ConsumerDashboardStatistics object.
     *
     * @return a string representation of the object
     */
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