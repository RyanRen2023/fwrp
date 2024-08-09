package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.Orders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing orders in the database.
 * 
 * <p>
 * This class provides methods for adding, updating, deleting, and retrieving
 * order records from the database. It interacts with the database using JDBC.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     OrdersDao ordersDao = new OrdersDao();
 *     List<Orders> ordersList = ordersDao.getAllOrders();
 * </pre>
 * </p>
 * 
 * <p>
 * Note: Ensure that the JDBCClient class is correctly implemented to provide
 * a valid database connection.
 * </p>
 * 
 * @author Xihai Ren
 */
public class OrdersDao {

    private JDBCClient jdbcClient;
    
    /**
     * Constructs a new OrdersDao and initializes the JDBCClient.
     */

    public OrdersDao() {
        this.jdbcClient = new JDBCClient();
    }

    /**
     * Add a new order to the database
     *
     * @param order the order to be added
     */
    public void addOrder(Orders order) {
        String sql = "INSERT INTO orders (uid, fid, money, num) VALUES (?, ?, ?, ?)";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getUid());
            pstmt.setInt(2, order.getFid());
            pstmt.setBigDecimal(3, order.getMoney());
            pstmt.setInt(4, order.getNum());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update an existing order in the database
     *
     * @param order the order to be updated
     */
    public void updateOrder(Orders order) {
        String sql = "UPDATE orders SET uid = ?, fid = ?, money = ?, num = ? WHERE oid = ?";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getUid());
            pstmt.setInt(2, order.getFid());
            pstmt.setBigDecimal(3, order.getMoney());
            pstmt.setInt(4, order.getNum());
            pstmt.setInt(5, order.getOid());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete an order from the database by its ID
     *
     * @param oid the order ID of the order to be deleted
     */
    public void deleteOrder(int oid) {
        String sql = "DELETE FROM orders WHERE oid = ?";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, oid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve an order from the database by its ID
     *
     * @param oid the order ID of the order to be retrieved
     * @return the Orders object
     */
    public Orders getOrderById(int oid) {
        String sql = "SELECT * FROM orders WHERE oid = ?";
        Orders order = null;

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, oid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                order = new Orders();
                order.setOid(rs.getInt("oid"));
                order.setUid(rs.getInt("uid"));
                order.setFid(rs.getInt("fid"));
                order.setMoney(rs.getBigDecimal("money"));
                order.setNum(rs.getInt("num"));
                order.setPurchaseDate(rs.getDate("purchase_date").toLocalDate());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    /**
     * Retrieve all orders from the database
     *
     * @return a list of Orders objects
     */
    public List<Orders> getAllOrders() {
        String sql = "SELECT * FROM orders";
        List<Orders> ordersList = new ArrayList<>();

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Orders order = new Orders();
                order.setOid(rs.getInt("oid"));
                order.setUid(rs.getInt("uid"));
                order.setFid(rs.getInt("fid"));
                order.setMoney(rs.getBigDecimal("money"));
                order.setNum(rs.getInt("num"));
                order.setPurchaseDate(rs.getDate("purchase_date").toLocalDate());
                ordersList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordersList;
    }
/**
     * Retrieves all orders by a specific user ID.
     *
     * @param userId the user ID to filter orders
     * @return a list of Orders objects
     */
    public List<Orders> getAllOrdersUserId(int userId) {
        String sql = "SELECT * FROM orders where uid = " + userId;
        List<Orders> ordersList = new ArrayList<>();

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Orders order = new Orders();
                order.setOid(rs.getInt("oid"));
                order.setUid(rs.getInt("uid"));
                order.setFid(rs.getInt("fid"));
                order.setMoney(rs.getBigDecimal("money"));
                order.setNum(rs.getInt("num"));
                order.setPurchaseDate(rs.getDate("purchase_date").toLocalDate());
                ordersList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordersList;
    }

    public int getTotalOrdersByUserId(int userId) {
        String sql = "SELECT COUNT(*) FROM orders WHERE uid = ?";
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
/**
     * Retrieves the total number of orders by a specific user ID.
     *
     * @param userId the user ID to filter orders
     * @return the total number of orders by the user
     */
    public double getTotalExpenditureByUserId(int userId) {
        String sql = "SELECT SUM(money) FROM orders WHERE uid = ?";
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    /**
     * Get favorite food item by user ID
     *
     * @param userId the user ID to filter orders
     * @return the most frequently purchased food item by the user
     */
    public String getFavoriteFoodItemByUserId(int userId) {
        String sql = "SELECT f.fname, COUNT(o.fid) AS frequency "
                + "FROM orders o "
                + "JOIN food f ON o.fid = f.fid "
                + "WHERE o.uid = ? "
                + "GROUP BY o.fid "
                + "ORDER BY frequency DESC "
                + "LIMIT 1";
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("fname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the total number of sold items by retailer ID
     *
     * @param retailerId the retailer ID to filter orders
     * @return the total number of sold items by the retailer
     */
    public int getTotalSoldItemsByRetailerId(int retailerId) {
        String sql = "SELECT SUM(o.num) AS total_sold_items "
                + "FROM orders o "
                + "JOIN food f ON o.fid = f.fid "
                + "JOIN store s ON f.store_id = s.store_id "
                + "WHERE s.uid = ?";
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, retailerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total_sold_items");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get the total revenue generated by retailer ID
     *
     * @param retailerId the retailer ID to filter orders
     * @return the total revenue generated by the retailer
     */
    public double getTotalRevenueByRetailerId(int retailerId) {
        String sql = "SELECT SUM(o.money) AS total_revenue "
                + "FROM orders o "
                + "JOIN food f ON o.fid = f.fid "
                + "JOIN store s ON f.store_id = s.store_id "
                + "WHERE s.uid = ?";
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, retailerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total_revenue");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    /**
     * Get the most popular food item by retailer ID
     *
     * @param retailerId the retailer ID to filter orders
     * @return the most popular food item sold by the retailer
     */
    public String getMostPopularItemByRetailerId(int retailerId) {
        String sql = "SELECT f.fname, COUNT(o.fid) AS frequency "
                + "FROM orders o "
                + "JOIN food f ON o.fid = f.fid "
                + "JOIN store s ON f.store_id = s.store_id "
                + "WHERE s.uid = ? "
                + "GROUP BY f.fid "
                + "ORDER BY frequency DESC "
                + "LIMIT 1";
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, retailerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("fname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
