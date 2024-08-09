/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the OrdersDao class.
 */
public class OrdersDaoTest {

    @Mock
    private JDBCClient jdbcClient;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private OrdersDao ordersDao;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(jdbcClient.getConnection()).thenReturn(connection);
    }

    @Test
    public void testAddOrder() throws SQLException {
        Orders order = new Orders();
        order.setUid(1);
        order.setFid(1);
        order.setMoney(new BigDecimal("10.50"));
        order.setNum(2);

        String sql = "INSERT INTO orders (uid, fid, money, num) VALUES (?, ?, ?, ?)";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        ordersDao.addOrder(order);

        verify(preparedStatement).setInt(1, order.getUid());
        verify(preparedStatement).setInt(2, order.getFid());
        verify(preparedStatement).setBigDecimal(3, order.getMoney());
        verify(preparedStatement).setInt(4, order.getNum());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testUpdateOrder() throws SQLException {
        Orders order = new Orders();
        order.setOid(1);
        order.setUid(1);
        order.setFid(1);
        order.setMoney(new BigDecimal("10.50"));
        order.setNum(2);

        String sql = "UPDATE orders SET uid = ?, fid = ?, money = ?, num = ? WHERE oid = ?";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        ordersDao.updateOrder(order);

        verify(preparedStatement).setInt(1, order.getUid());
        verify(preparedStatement).setInt(2, order.getFid());
        verify(preparedStatement).setBigDecimal(3, order.getMoney());
        verify(preparedStatement).setInt(4, order.getNum());
        verify(preparedStatement).setInt(5, order.getOid());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testDeleteOrder() throws SQLException {
        int oid = 1;
        String sql = "DELETE FROM orders WHERE oid = ?";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        ordersDao.deleteOrder(oid);

        verify(preparedStatement).setInt(1, oid);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testGetOrderById() throws SQLException {
        int oid = 1;
        String sql = "SELECT * FROM orders WHERE oid = ?";
        Orders expectedOrder = new Orders();
        expectedOrder.setOid(oid);
        expectedOrder.setUid(1);
        expectedOrder.setFid(1);
        expectedOrder.setMoney(new BigDecimal("10.50"));
        expectedOrder.setNum(2);
        expectedOrder.setPurchaseDate(LocalDate.now());

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("oid")).thenReturn(oid);
        when(resultSet.getInt("uid")).thenReturn(1);
        when(resultSet.getInt("fid")).thenReturn(1);
        when(resultSet.getBigDecimal("money")).thenReturn(new BigDecimal("10.50"));
        when(resultSet.getInt("num")).thenReturn(2);
        when(resultSet.getDate("purchase_date")).thenReturn(Date.valueOf(LocalDate.now()));

        Orders order = ordersDao.getOrderById(oid);

        assertNotNull(order);
        assertEquals(expectedOrder.getOid(), order.getOid());
        assertEquals(expectedOrder.getUid(), order.getUid());
        assertEquals(expectedOrder.getFid(), order.getFid());
        assertEquals(expectedOrder.getMoney(), order.getMoney());
        assertEquals(expectedOrder.getNum(), order.getNum());
        assertEquals(expectedOrder.getPurchaseDate(), order.getPurchaseDate());
    }

    @Test
    public void testGetAllOrders() throws SQLException {
        String sql = "SELECT * FROM orders";
        List<Orders> expectedOrdersList = new ArrayList<>();
        Orders order1 = new Orders();
        order1.setOid(1);
        order1.setUid(1);
        order1.setFid(1);
        order1.setMoney(new BigDecimal("10.50"));
        order1.setNum(2);
        order1.setPurchaseDate(LocalDate.now());
        expectedOrdersList.add(order1);

        Orders order2 = new Orders();
        order2.setOid(2);
        order2.setUid(2);
        order2.setFid(2);
        order2.setMoney(new BigDecimal("20.50"));
        order2.setNum(3);
        order2.setPurchaseDate(LocalDate.now());
        expectedOrdersList.add(order2);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("oid")).thenReturn(1, 2);
        when(resultSet.getInt("uid")).thenReturn(1, 2);
        when(resultSet.getInt("fid")).thenReturn(1, 2);
        when(resultSet.getBigDecimal("money")).thenReturn(new BigDecimal("10.50"), new BigDecimal("20.50"));
        when(resultSet.getInt("num")).thenReturn(2, 3);
        when(resultSet.getDate("purchase_date")).thenReturn(Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()));

        List<Orders> ordersList = ordersDao.getAllOrders();

        assertNotNull(ordersList);
        assertEquals(expectedOrdersList.size(), ordersList.size());
        for (int i = 0; i < ordersList.size(); i++) {
            assertEquals(expectedOrdersList.get(i).getOid(), ordersList.get(i).getOid());
            assertEquals(expectedOrdersList.get(i).getUid(), ordersList.get(i).getUid());
            assertEquals(expectedOrdersList.get(i).getFid(), ordersList.get(i).getFid());
            assertEquals(expectedOrdersList.get(i).getMoney(), ordersList.get(i).getMoney());
            assertEquals(expectedOrdersList.get(i).getNum(), ordersList.get(i).getNum());
            assertEquals(expectedOrdersList.get(i).getPurchaseDate(), ordersList.get(i).getPurchaseDate());
        }
    }

    @Test
    public void testGetAllOrdersUserId() throws SQLException {
        int userId = 1;
        String sql = "SELECT * FROM orders where uid = " + userId;
        List<Orders> expectedOrdersList = new ArrayList<>();
        Orders order1 = new Orders();
        order1.setOid(1);
        order1.setUid(1);
        order1.setFid(1);
        order1.setMoney(new BigDecimal("10.50"));
        order1.setNum(2);
        order1.setPurchaseDate(LocalDate.now());
        expectedOrdersList.add(order1);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("oid")).thenReturn(1);
        when(resultSet.getInt("uid")).thenReturn(1);
        when(resultSet.getInt("fid")).thenReturn(1);
        when(resultSet.getBigDecimal("money")).thenReturn(new BigDecimal("10.50"));
        when(resultSet.getInt("num")).thenReturn(2);
        when(resultSet.getDate("purchase_date")).thenReturn(Date.valueOf(LocalDate.now()));

        List<Orders> ordersList = ordersDao.getAllOrdersUserId(userId);

        assertNotNull(ordersList);
        assertEquals(expectedOrdersList.size(), ordersList.size());
        for (int i = 0; i < ordersList.size(); i++) {
            assertEquals(expectedOrdersList.get(i).getOid(), ordersList.get(i).getOid());
            assertEquals(expectedOrdersList.get(i).getUid(), ordersList.get(i).getUid());
            assertEquals(expectedOrdersList.get(i).getFid(), ordersList.get(i).getFid());
            assertEquals(expectedOrdersList.get(i).getMoney(), ordersList.get(i).getMoney());
            assertEquals(expectedOrdersList.get(i).getNum(), ordersList.get(i).getNum());
            assertEquals(expectedOrdersList.get(i).getPurchaseDate(), ordersList.get(i).getPurchaseDate());
        }
    }

    @Test
    public void testGetTotalOrdersByUserId() throws SQLException {
        int userId = 1;
        String sql = "SELECT COUNT(*) FROM orders WHERE uid = ?";
        
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(5);

        int totalOrders = ordersDao.getTotalOrdersByUserId(userId);

        assertEquals(5, totalOrders);
    }

    @Test
    public void testGetTotalExpenditureByUserId() throws SQLException {
        int userId = 1;
        String sql = "SELECT SUM(money) FROM orders WHERE uid = ?";
        
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getDouble(1)).thenReturn(150.75);

        double totalExpenditure = ordersDao.getTotalExpenditureByUserId(userId);

        assertEquals(150.75, totalExpenditure);
    }

    @Test
    public void testGetFavoriteFoodItemByUserId() throws SQLException {
        int userId = 1;
        String sql = "SELECT f.fname, COUNT(o.fid) AS frequency "
                + "FROM orders o "
                + "JOIN food f ON o.fid = f.fid "
                + "WHERE o.uid = ? "
                + "GROUP BY o.fid "
                + "ORDER BY frequency DESC "
                + "LIMIT 1";
        
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("fname")).thenReturn("Apple");

        String favoriteFoodItem = ordersDao.getFavoriteFoodItemByUserId(userId);

        assertEquals("Apple", favoriteFoodItem);
    }

    @Test
    public void testGetTotalSoldItemsByRetailerId() throws SQLException {
        int retailerId = 1;
        String sql = "SELECT SUM(o.num) AS total_sold_items "
                + "FROM orders o "
                + "JOIN food f ON o.fid = f.fid "
                + "JOIN store s ON f.store_id = s.store_id "
                + "WHERE s.uid = ?";
        
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("total_sold_items")).thenReturn(100);

        int totalSoldItems = ordersDao.getTotalSoldItemsByRetailerId(retailerId);

        assertEquals(100, totalSoldItems);
    }

    @Test
    public void testGetTotalRevenueByRetailerId() throws SQLException {
        int retailerId = 1;
        String sql = "SELECT SUM(o.money) AS total_revenue "
                + "FROM orders o "
                + "JOIN food f ON o.fid = f.fid "
                + "JOIN store s ON f.store_id = s.store_id "
                + "WHERE s.uid = ?";
        
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getDouble("total_revenue")).thenReturn(1000.50);

        double totalRevenue = ordersDao.getTotalRevenueByRetailerId(retailerId);

        assertEquals(1000.50, totalRevenue);
    }

    @Test
    public void testGetMostPopularItemByRetailerId() throws SQLException {
        int retailerId = 1;
        String sql = "SELECT f.fname, COUNT(o.fid) AS frequency "
                + "FROM orders o "
                + "JOIN food f ON o.fid = f.fid "
                + "JOIN store s ON f.store_id = s.store_id "
                + "WHERE s.uid = ? "
                + "GROUP BY f.fid "
                + "ORDER BY frequency DESC "
                + "LIMIT 1";
        
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("fname")).thenReturn("Banana");

        String mostPopularItem = ordersDao.getMostPopularItemByRetailerId(retailerId);

        assertEquals("Banana", mostPopularItem);
    }
}
