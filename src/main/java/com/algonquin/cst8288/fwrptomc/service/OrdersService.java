package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.OrdersDao;
import com.algonquin.cst8288.fwrptomc.model.Orders;
import java.util.List;

public class OrdersService {

    private OrdersDao ordersDao;

    public OrdersService() {
        this.ordersDao = new OrdersDao();
    }

    /**
     * Add a new order
     *
     * @param order the order to be added
     */
    public void addOrder(Orders order) {
        ordersDao.addOrder(order);
    }

    /**
     * Update an existing order
     *
     * @param order the order to be updated
     */
    public void updateOrder(Orders order) {
        ordersDao.updateOrder(order);
    }

    /**
     * Delete an order by its ID
     *
     * @param oid the order ID of the item to be deleted
     */
    public void deleteOrder(int oid) {
        ordersDao.deleteOrder(oid);
    }

    /**
     * Retrieve an order by its ID
     *
     * @param oid the order ID of the item to be retrieved
     * @return the Orders object
     */
    public Orders getOrderById(int oid) {
        return ordersDao.getOrderById(oid);
    }

    /**
     * Retrieve all orders
     *
     * @return a list of Orders objects
     */
    public List<Orders> getAllOrders() {
        return ordersDao.getAllOrders();
    }
}