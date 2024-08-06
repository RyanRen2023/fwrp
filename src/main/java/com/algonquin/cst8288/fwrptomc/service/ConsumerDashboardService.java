/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.OrdersDao;
import com.algonquin.cst8288.fwrptomc.dao.FoodDao;
import com.algonquin.cst8288.fwrptomc.dao.RatingAndFeedbackDao;
import com.algonquin.cst8288.fwrptomc.model.ConsumerDashboardStatistics;

public class ConsumerDashboardService {

    private OrdersDao ordersDao;
    private RatingAndFeedbackDao feedbackDao;
    private FoodDao foodDao;

    // Constructor
    public ConsumerDashboardService() {
        this.ordersDao = new OrdersDao();
        this.feedbackDao = new RatingAndFeedbackDao();
        this.foodDao = new FoodDao();
    }

    // Constructor with DAOs for better testability and flexibility
    public ConsumerDashboardService(OrdersDao ordersDao, RatingAndFeedbackDao feedbackDao, FoodDao foodDao) {
        this.ordersDao = ordersDao;
        this.feedbackDao = feedbackDao;
        this.foodDao = foodDao;
    }

    // Method to get consumer dashboard statistics
    public ConsumerDashboardStatistics getConsumerDashboardStatistics(int userId) {
        int totalOrders = ordersDao.getTotalOrdersByUserId(userId);
        double totalExpenditure = ordersDao.getTotalExpenditureByUserId(userId);
        int totalFeedback = feedbackDao.getTotalFeedbackByUserId(userId);
        String favoriteFoodItem = ordersDao.getFavoriteFoodItemByUserId(userId);

        return new ConsumerDashboardStatistics(totalOrders, totalExpenditure, totalFeedback, favoriteFoodItem);
    }
}
