package com.algonquin.cst8288.fwrps.service;

import com.algonquin.cst8288.fwrps.dao.StoreDao;
import com.algonquin.cst8288.fwrps.model.Store;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StoreServiceImpl implements StoreService{
    
    @Inject
    private StoreDao storeDao;

    private static final Logger LOGGER = Logger.getLogger(StoreServiceImpl.class.getName());

    @Override
    @Transactional
    public void createStore(Store store) {
        try {
            storeDao.createStore(store);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating store: {0}", e.getMessage());
            // Handle or rethrow the exception as needed
        }
    }

    @Override
    public Store getStoreById(int id) {
        return storeDao.getStoreById(id);
    }

    @Override
    public List<Store> getAllStores() {
        return storeDao.getAllStores();
    }

    @Override
    @Transactional
    public void updateStore(Store store) {
        try {
            storeDao.updateStore(store);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating store: {0}", e.getMessage());
            // Handle or rethrow the exception as needed
        }
    }

    @Override
    @Transactional
    public void deleteStore(int id) {
        try {
            storeDao.deleteStore(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting store: {0}", e.getMessage());
            // Handle or rethrow the exception as needed
        }
    }

    
}
