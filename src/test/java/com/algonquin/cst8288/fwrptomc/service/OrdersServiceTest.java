/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.OrdersDao;
import com.algonquin.cst8288.fwrptomc.model.Orders;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.math.BigDecimal;

import java.util.ArrayList;
import org.junit.jupiter.api.*;
import org.mockito.*;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author amart
 */
public class OrdersServiceTest {

   @Mock
    private OrdersDao ordersDao;
   
   @InjectMocks
    private OrdersService ordersService;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() {
        
    }

    /**
     * Test of addOrders method, of class OrdersService.
     */
    @Test
    public void testAddOrder() {
        Orders order = new Orders();
      
        order.setOid(1);
        order.setUid(1);
        order.setFid(1);
        order.setMoney(new BigDecimal("10.50"));
        order.setNum(1);
        order.setPurchaseDate(LocalDate.now());
        
        doNothing().when(ordersDao).addOrder(order);

        ordersService.addOrder(order);

        verify(ordersDao).addOrder(order);    }

    /**
     * Test of updateOrder method, of class OrdersService.
     */
    @Test
    public void testUpdateOrder() {
        Orders order = new Orders();
        order.setOid(1);
        order.setUid(2);
        order.setFid(2);
        order.setMoney(new BigDecimal("10.30"));
        order.setNum(2);
        order.setPurchaseDate(LocalDate.now());
        

        doNothing().when(ordersDao).updateOrder(order);

        ordersService.updateOrder(order);

        verify(ordersDao).updateOrder(order);
    }

    /**
     * Test of deleteOrder method, of class OrderService.
     */
    @Test
    public void testDeleteOrder() {
        int id = 2;

        doNothing().when(ordersDao).deleteOrder(id);

        ordersService.deleteOrder(id);

        verify(ordersDao).deleteOrder(id);
    }

    /**
     * Test of getOrderById method, of class OrdersService.
     */
    @Test
    public void testGetOrderById() {
        int orderId = 1;
        Orders order = new Orders();
       
        order.setOid(1);
        order.setUid(1);
        order.setFid(1);
        order.setMoney(new BigDecimal("10.50"));
        order.setNum(1);
        order.setPurchaseDate(LocalDate.now());
        
        when(ordersDao.getOrderById(orderId)).thenReturn(order);

        Orders result = ordersService.getOrderById(orderId);

        assertNotNull(result);
        assertEquals(order, result);
        verify(ordersDao).getOrderById(orderId);
    }

    /**
     * Test of getAllOrders method, of class OrdersService.
     */
    @Test
    public void testGetAllOrders() {
        List<Orders> orders = new ArrayList<>();
        
        Orders order1 = new Orders();
        order1.setOid(1);
        order1.setUid(1);
        order1.setFid(1);
        order1.setMoney(new BigDecimal("10.50"));
        order1.setNum(1);
        order1.setPurchaseDate(LocalDate.now());
        
        Orders order2 = new Orders();
    
        order2.setOid(2);
        order2.setUid(2);
        order2.setFid(2);
        order2.setMoney(new BigDecimal("11.50"));
        order2.setNum(2);
        order2.setPurchaseDate(LocalDate.now());
        

        orders.add(order1);
        orders.add(order2);

        when(ordersDao.getAllOrders()).thenReturn(orders);

        List<Orders> result = ordersService.getAllOrders();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ordersDao).getAllOrders();

    }
}

