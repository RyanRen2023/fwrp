/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.StoreDao;
import com.algonquin.cst8288.fwrptomc.model.Store;


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
public class StoreServiceTest {

   @Mock
    private StoreDao storeDao;
   
   @InjectMocks
    private StoreService storeService;

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
     * Test of addStore method, of class StoreService.
     */
    @Test
    public void testAddStore() {
        Store store = new Store();
        
        store.setStoreId(1);
        store.setStoreName("store test");
        store.setCity("city test");
        store.setUid(2);
        
        doNothing().when(storeDao).addStore(store);

        storeService.addStore(store);

        verify(storeDao).addStore(store);    }

    /**
     * Test of updateStore method, of class StoreService.
     */
    @Test
    public void testUpdateStore() {
        Store store = new Store();
        store.setStoreId(1);
        store.setStoreName("store update test");
        store.setCity("city update test");
        store.setUid(5);

        doNothing().when(storeDao).updateStore(store);

        storeService.updateStore(store);

        verify(storeDao).updateStore(store);
    }

    /**
     * Test of deleteStore method, of class StoreService.
     */
    @Test
    public void testDeleteStore() {
        int id = 2;

        doNothing().when(storeDao).deleteStore(id);

        storeService.deleteStore(id);

        verify(storeDao).deleteStore(id);
    }

    /**
     * Test of getStoreById method, of class StoreService.
     */
    @Test
    public void testGetStoreById() {
        int storeId = 1;
        Store store = new Store();
       
        store.setStoreId(1);
        store.setStoreName("store update test");
        store.setCity("city update test");
        store.setUid(5);
        
        when(storeDao.getStoreById(storeId)).thenReturn(store);

        Store result = storeService.getStoreById(storeId);

        assertNotNull(result);
        assertEquals(store, result);
        verify(storeDao).getStoreById(storeId);
    }

    /**
     * Test of getAllStores method, of class StoreService.
     */
    @Test
    public void testGetAllStores() {
        List<Store> stores = new ArrayList<>();
        
        Store store1 = new Store();
        store1.setStoreId(1);
        store1.setStoreName("store test 1");
        store1.setCity("city test 1");
        store1.setUid(5);
        
        Store store2 = new Store();
        
        store2.setStoreId(2);
        store2.setStoreName("another store");
        store2.setCity("another city");
        store2.setUid(6);

        

        stores.add(store1);
        stores.add(store2);

        when(storeDao.getAllStores()).thenReturn(stores);

        List<Store> result = storeService.getAllStores();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(storeDao).getAllStores();

    }
}

