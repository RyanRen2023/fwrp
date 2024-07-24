package com.algonquin.cst8288.fwrps.dao;

import com.algonquin.cst8288.fwrps.model.Store;
import java.util.List;

import com.algonquin.cst8288.fwrps.model.Store;
import java.util.List;

public interface StoreDao {
    void createStore(Store store);
    Store getStoreById(int id);
    List<Store> getAllStores();
    void updateStore(Store store);
    void deleteStore(int id);
    
}