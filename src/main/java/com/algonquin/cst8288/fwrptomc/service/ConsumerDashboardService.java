package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.OrdersDao;
import com.algonquin.cst8288.fwrptomc.dao.FoodDao;
import com.algonquin.cst8288.fwrptomc.dao.RatingAndFeedbackDao;
import com.algonquin.cst8288.fwrptomc.model.ConsumerDashboardStatistics;

/**
 * Service class that handles operations related to the consumer dashboard.
 * 
 * <p>
 * This service provides methods to retrieve statistical data for the consumer dashboard, 
 * including total orders, total expenditure, total feedback, and the user's favorite food item.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     ConsumerDashboardService dashboardService = new ConsumerDashboardService();
 *     ConsumerDashboardStatistics stats = dashboardService.getConsumerDashboardStatistics(userId);
 * </pre>
 * </p>
 * 
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link OrdersDao}: Data access object for retrieving order data.</li>
 * <li>{@link RatingAndFeedbackDao}: Data access object for retrieving feedback data.</li>
 * <li>{@link FoodDao}: Data access object for retrieving food data.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * This class can be instantiated with default DAOs or with specific DAOs, which 
 * allows for better testability and flexibility.
 * </p>
 * 
 * @author Xihai Ren
 */
public class ConsumerDashboardService {

    private OrdersDao ordersDao;
    private RatingAndFeedbackDao feedbackDao;
    private FoodDao foodDao;

    /**
     * Constructs a new ConsumerDashboardService with default DAOs.
     */
    public ConsumerDashboardService() {
        this.ordersDao = new OrdersDao();
        this.feedbackDao = new RatingAndFeedbackDao();
        this.foodDao = new FoodDao();
    }

    /**
     * Constructs a new ConsumerDashboardService with the specified DAOs.
     * 
     * @param ordersDao   the OrdersDao instance for retrieving order data
     * @param feedbackDao the RatingAndFeedbackDao instance for retrieving feedback data
     * @param foodDao     the FoodDao instance for retrieving food data
     */
    public ConsumerDashboardService(OrdersDao ordersDao, RatingAndFeedbackDao feedbackDao, FoodDao foodDao) {
        this.ordersDao = ordersDao;
        this.feedbackDao = feedbackDao;
        this.foodDao = foodDao;
    }

    /**
     * Retrieves consumer dashboard statistics for a given user.
     * 
     * @param userId the ID of the user
     * @return a ConsumerDashboardStatistics object containing the statistics
     */
    public ConsumerDashboardStatistics getConsumerDashboardStatistics(int userId) {
        int totalOrders = ordersDao.getTotalOrdersByUserId(userId);
        double totalExpenditure = ordersDao.getTotalExpenditureByUserId(userId);
        int totalFeedback = feedbackDao.getTotalFeedbackByUserId(userId);
        String favoriteFoodItem = ordersDao.getFavoriteFoodItemByUserId(userId);

        return new ConsumerDashboardStatistics(totalOrders, totalExpenditure, totalFeedback, favoriteFoodItem);
    }
}