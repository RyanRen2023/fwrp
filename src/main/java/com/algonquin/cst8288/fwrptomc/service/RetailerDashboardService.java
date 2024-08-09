package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.FoodDao;
import com.algonquin.cst8288.fwrptomc.dao.OrdersDao;
import com.algonquin.cst8288.fwrptomc.dao.ClaimDao;
import com.algonquin.cst8288.fwrptomc.model.RetailerDashboardStatistics;

/**
 * Service class that handles operations related to the retailer dashboard.
 *
 * <p>
 * This service provides methods to retrieve statistical data for a retailer's
 * dashboard, including total listed items, total sold items, total revenue,
 * most popular item, and total donated items. It interacts with
 * {@link FoodDao}, {@link OrdersDao}, and {@link ClaimDao} to perform database
 * operations.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     RetailerDashboardService dashboardService = new RetailerDashboardService();
 *     RetailerDashboardStatistics stats = dashboardService.getStatistics(retailerId);
 * </pre>
 * </p>
 *
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link FoodDao}: Data access object for performing operations on food
 * data.</li>
 * <li>{@link OrdersDao}: Data access object for performing operations on order
 * data.</li>
 * <li>{@link ClaimDao}: Data access object for performing operations on claim
 * data.</li>
 * </ul>
 * </p>
 *
 * <p>
 * The class interacts with these DAOs to perform all database operations
 * related to the retailer's dashboard.
 * </p>
 *
 * @author Xihai Ren
 */
public class RetailerDashboardService {

    private FoodDao foodDao;
    private OrdersDao ordersDao;
    private ClaimDao claimDao;

    /**
     * Constructs a new RetailerDashboardService and initializes the DAOs.
     */
    public RetailerDashboardService() {
        this.foodDao = new FoodDao();
        this.ordersDao = new OrdersDao();
        this.claimDao = new ClaimDao();
    }

    /**
     * Retrieves statistical data for the retailer's dashboard.
     *
     * @param retailerId the ID of the retailer
     * @return a RetailerDashboardStatistics object containing the statistics
     */
    public RetailerDashboardStatistics getStatistics(int retailerId) {
        RetailerDashboardStatistics stats = new RetailerDashboardStatistics();
        stats.setTotalListedItems(foodDao.getTotalListedItemsByRetailerId(retailerId));
        stats.setTotalSoldItems(ordersDao.getTotalSoldItemsByRetailerId(retailerId));
        stats.setTotalRevenue(ordersDao.getTotalRevenueByRetailerId(retailerId));
        stats.setMostPopularItem(ordersDao.getMostPopularItemByRetailerId(retailerId));
        stats.setTotalDonatedItems(claimDao.getTotalDonatedItemsByRetailerId(retailerId)); // Retrieve total donated items from ClaimDao
        return stats;
    }
}
