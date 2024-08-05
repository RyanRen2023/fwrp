/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.google.gson.JsonObject;

public class StatisticsService {

    public JsonObject getStatistics() {
        JsonObject statistics = new JsonObject();
        // Assuming you have methods to get these values from your database or other sources
        statistics.addProperty("totalFoods", getTotalFoods());
        statistics.addProperty("surplusFoods", getSurplusFoods());
        statistics.addProperty("donatedFoods", getDonatedFoods());
        statistics.addProperty("pendingClaims", getPendingClaims());
        statistics.addProperty("totalPurchases", getTotalPurchases());
        statistics.addProperty("favoriteFoods", getFavoriteFoods());
        statistics.addProperty("ratingsGiven", getRatingsGiven());
        statistics.addProperty("totalClaims", getTotalClaims());
        statistics.addProperty("successfulClaims", getSuccessfulClaims());
        // Add more statistics as needed

        // Add trend data for charts
        statistics.add("inventory", getInventoryTrend());
        statistics.add("purchaseTrend", getPurchaseTrend());
        statistics.add("claimsTrend", getClaimsTrend());

        return statistics;
    }

    private int getTotalFoods() {
        // Replace with actual implementation
        return 100;
    }

    private int getSurplusFoods() {
        // Replace with actual implementation
        return 20;
    }

    private int getDonatedFoods() {
        // Replace with actual implementation
        return 30;
    }

    private int getPendingClaims() {
        // Replace with actual implementation
        return 10;
    }

    private int getTotalPurchases() {
        // Replace with actual implementation
        return 50;
    }

    private int getFavoriteFoods() {
        // Replace with actual implementation
        return 15;
    }

    private int getRatingsGiven() {
        // Replace with actual implementation
        return 25;
    }

    private int getTotalClaims() {
        // Replace with actual implementation
        return 40;
    }

    private int getSuccessfulClaims() {
        // Replace with actual implementation
        return 35;
    }

    private JsonObject getInventoryTrend() {
        JsonObject trend = new JsonObject();
        trend.addProperty("labels", "['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun']");
        trend.addProperty("data", "[10, 20, 30, 40, 50, 60]");
        return trend;
    }

    private JsonObject getPurchaseTrend() {
        JsonObject trend = new JsonObject();
        trend.addProperty("labels", "['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun']");
        trend.addProperty("data", "[5, 10, 15, 20, 25, 30]");
        return trend;
    }

    private JsonObject getClaimsTrend() {
        JsonObject trend = new JsonObject();
        trend.addProperty("labels", "['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun']");
        trend.addProperty("data", "[3, 6, 9, 12, 15, 18]");
        return trend;
    }
}