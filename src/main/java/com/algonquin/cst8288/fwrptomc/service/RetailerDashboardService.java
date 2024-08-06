package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.FoodDao;
import com.algonquin.cst8288.fwrptomc.dao.OrdersDao;
import com.algonquin.cst8288.fwrptomc.dao.ClaimDao;
import com.algonquin.cst8288.fwrptomc.model.RetailerDashboardStatistics;

public class RetailerDashboardService {
    private FoodDao foodDao;
    private OrdersDao ordersDao;
    private ClaimDao claimDao; // 添加 ClaimDao 实例

    public RetailerDashboardService() {
        this.foodDao = new FoodDao();
        this.ordersDao = new OrdersDao();
        this.claimDao = new ClaimDao(); // 初始化 ClaimDao 实例
    }

    public RetailerDashboardStatistics getStatistics(int retailerId) {
        RetailerDashboardStatistics stats = new RetailerDashboardStatistics();
        stats.setTotalListedItems(foodDao.getTotalListedItemsByRetailerId(retailerId));
        stats.setTotalSoldItems(ordersDao.getTotalSoldItemsByRetailerId(retailerId));
        stats.setTotalRevenue(ordersDao.getTotalRevenueByRetailerId(retailerId));
        stats.setMostPopularItem(ordersDao.getMostPopularItemByRetailerId(retailerId));
        stats.setTotalDonatedItems(claimDao.getTotalDonatedItemsByRetailerId(retailerId)); // 使用 ClaimDao 获取 totalDonatedItems
        return stats;
    }
}