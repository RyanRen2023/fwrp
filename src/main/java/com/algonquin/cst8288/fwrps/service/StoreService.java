package com.algonquin.cst8288.fwrps.service;

import com.algonquin.cst8288.fwrps.model.Store;
import java.util.List;


public interface StoreService {
    
    void createStore(Store store);
    Store getStoreById(int id);
    List<Store> getAllStores();
    void updateStore(Store store);
    void deleteStore(int id);
    //boolean validateFood();
    
}
