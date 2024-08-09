package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.OrdersDao;
import com.algonquin.cst8288.fwrptomc.model.Orders;
import java.util.List;

/**
 * Service class that handles operations related to orders.
 *
 * <p>
 * This service provides methods for adding, updating, deleting, and retrieving
 * orders. It interacts with the {@link OrdersDao} to perform database
 * operations.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     OrdersService ordersService = new OrdersService();
 *     List&lt;Orders&gt; allOrders = ordersService.getAllOrders();
 * </pre>
 * </p>
 *
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link OrdersDao}: Data access object for performing operations on order
 * data.</li>
 * </ul>
 * </p>
 *
 * <p>
 * The class interacts with the {@link OrdersDao} to perform all database
 * operations related to orders.
 * </p>
 *
 * @author Xihai Ren
 */
public class OrdersService {

    private OrdersDao ordersDao;

    /**
     * Constructs a new OrdersService and initializes the OrdersDao.
     */
    public OrdersService() {
        this.ordersDao = new OrdersDao();
    }

    /**
     * Adds a new order.
     *
     * @param order the order to be added
     */
    public void addOrder(Orders order) {
        ordersDao.addOrder(order);
    }

    /**
     * Updates an existing order.
     *
     * @param order the order to be updated
     */
    public void updateOrder(Orders order) {
        ordersDao.updateOrder(order);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param oid the ID of the order to be deleted
     */
    public void deleteOrder(int oid) {
        ordersDao.deleteOrder(oid);
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param oid the ID of the order to be retrieved
     * @return the Orders object corresponding to the specified ID
     */
    public Orders getOrderById(int oid) {
        return ordersDao.getOrderById(oid);
    }

    /**
     * Retrieves all orders.
     *
     * @return a list of all Orders objects
     */
    public List<Orders> getAllOrders() {
        return ordersDao.getAllOrders();
    }

    /**
     * Retrieves all orders associated with a specific user ID.
     *
     * @param userId the ID of the user
     * @return a list of Orders objects associated with the user
     */
    public List<Orders> getAllOrdersByUserId(int userId) {
        return ordersDao.getAllOrdersUserId(userId);
    }
}
