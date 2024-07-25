package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.StoreDao;
import com.algonquin.cst8288.fwrptomc.model.Store;
import java.util.List;

public class StoreService {

    private StoreDao storeDao;

    public StoreService() {
        this.storeDao = new StoreDao();
    }

    /**
     * Add a new store
     *
     * @param store the store to be added
     */
    public void addStore(Store store) {
        storeDao.addStore(store);
    }

    /**
     * Update an existing store
     *
     * @param store the store to be updated
     */
    public void updateStore(Store store) {
        storeDao.updateStore(store);
    }

    /**
     * Delete a store by its ID
     *
     * @param storeId the store ID of the item to be deleted
     */
    public void deleteStore(int storeId) {
        storeDao.deleteStore(storeId);
    }

    /**
     * Retrieve a store by its ID
     *
     * @param storeId the store ID of the item to be retrieved
     * @return the Store object
     */
    public Store getStoreById(int storeId) {
        return storeDao.getStoreById(storeId);
    }

    /**
     * Retrieve all stores
     *
     * @return a list of Store objects
     */
    public List<Store> getAllStores() {
        return storeDao.getAllStores();
    }
}