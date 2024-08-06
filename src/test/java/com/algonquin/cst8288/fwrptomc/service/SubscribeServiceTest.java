/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.model.Subscribe;
import com.algonquin.cst8288.fwrptomc.dao.SubscribeDao;
import org.mockito.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author amart
 */
public class SubscribeServiceTest {
    
    @Mock
    private SubscribeDao subscribeDao;
    
    @InjectMocks
    private SubscribeService subscribeService;
    
    
    
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
     * Test of addSubscribe method, of class SubscribeService.
     */
    @Test
    public void testAddSubscribe() {
        Subscribe subscribe = new Subscribe();
        subscribe.setUid(1);
        subscribe.setFid(2);
        subscribe.setCreateTime(LocalDate.now());
        subscribe.setAlertType("Test Alert");
        subscribe.setEmail("test@example.com");

        when(subscribeDao.addSubscribe(subscribe)).thenReturn(subscribe);

        Subscribe result = subscribeService.addSubscribe(subscribe);

        assertNotNull(result);
        assertEquals(subscribe, result);
        verify(subscribeDao).addSubscribe(subscribe);
    }

    /**
     * Test of updateSubscribe method, of class SubscribeService.
     */
    @Test
    public void testUpdateSubscribe() {
        Subscribe subscribe = new Subscribe();
        subscribe.setSid(1);
        subscribe.setAlertType("Updated Alert");
        subscribe.setEmail("updated@example.com");

        doNothing().when(subscribeDao).updateSubscribe(subscribe);

        subscribeService.updateSubscribe(subscribe);

        verify(subscribeDao).updateSubscribe(subscribe);

    }

    /**
     * Test of deleteSubscribe method, of class SubscribeService.
     */
    @Test
    public void testDeleteSubscribe() {
        int sid = 1;

        doNothing().when(subscribeDao).deleteSubscribe(sid);

        subscribeService.deleteSubscribe(sid);

        verify(subscribeDao).deleteSubscribe(sid);
    }

    /**
     * Test of getSubscribeById method, of class SubscribeService.
     */
    @Test
    public void testGetSubscribeById() {
        int sid = 1;
        Subscribe subscribe = new Subscribe();
        subscribe.setSid(sid);
        subscribe.setUid(1);
        subscribe.setFid(2);
        subscribe.setCreateTime(LocalDate.now());
        subscribe.setAlertType("Test Alert");
        subscribe.setEmail("test@example.com");

        when(subscribeDao.getSubscribeById(sid)).thenReturn(subscribe);

        Subscribe result = subscribeService.getSubscribeById(sid);

        assertNotNull(result);
        assertEquals(subscribe, result);
        verify(subscribeDao).getSubscribeById(sid);
    }

    /**
     * Test of getAllSubscribes method, of class SubscribeService.
     */
    @Test
    public void testGetAllSubscribes() {
        List<Subscribe> subscribes = new ArrayList<>();
        Subscribe subscribe1 = new Subscribe();
        subscribe1.setSid(1);
        subscribe1.setUid(1);
        subscribe1.setFid(2);
        subscribe1.setCreateTime(LocalDate.now());
        subscribe1.setAlertType("Test Alert 1");
        subscribe1.setEmail("test1@example.com");

        Subscribe subscribe2 = new Subscribe();
        subscribe2.setSid(2);
        subscribe2.setUid(2);
        subscribe2.setFid(3);
        subscribe2.setCreateTime(LocalDate.now());
        subscribe2.setAlertType("Test Alert 2");
        subscribe2.setEmail("test2@example.com");

        subscribes.add(subscribe1);
        subscribes.add(subscribe2);

        when(subscribeDao.getAllSubscribes()).thenReturn(subscribes);

        List<Subscribe> result = subscribeService.getAllSubscribes();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(subscribeDao).getAllSubscribes();
    }

    /**
     * Test of searchSubscribesByAlertType method, of class SubscribeService.
     */
    @Test
    public void testSearchSubscribesByAlertType() {
        String alertType = "Test Alert";
        List<Subscribe> subscribes = new ArrayList<>();
        Subscribe subscribe = new Subscribe();
        subscribe.setSid(1);
        subscribe.setAlertType(alertType);
        subscribe.setEmail("test@example.com");
        subscribes.add(subscribe);

        when(subscribeDao.findByAlertType(alertType)).thenReturn(subscribes);

        List<Subscribe> result = subscribeService.searchSubscribesByAlertType(alertType);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(alertType, result.get(0).getAlertType());
        verify(subscribeDao).findByAlertType(alertType);
 
    }
    
}
