package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.Orders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao {

    private JDBCClient jdbcClient;

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
}
