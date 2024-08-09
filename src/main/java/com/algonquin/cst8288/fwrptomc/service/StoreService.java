package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.StoreDao;
import com.algonquin.cst8288.fwrptomc.model.Store;
import java.util.List;

/**
 * Service class that handles operations related to stores.
 *
 * <p>
 * This service provides methods for adding, updating, deleting, and retrieving
 * stores. It interacts with the {@link StoreDao} to perform database
 * operations.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     StoreService storeService = new StoreService();
 *     List&lt;Store&gt; allStores = storeService.getAllStores();
 * </pre>
 * </p>
 *
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link StoreDao}: Data access object for performing operations on store
 * data.</li>
 * </ul>
 * </p>
 *
 * <p>
 * The class interacts with the {@link StoreDao} to perform all database
 * operations related to stores.
 * </p>
 *
 * @author Xihai Ren
 */
public class StoreService {

    private StoreDao storeDao;

    /**
     * Constructs a new StoreService and initializes the StoreDao.
     */
    public StoreService() {
        this.storeDao = new StoreDao();
    }

    /**
     * Adds a new store.
     *
     * @param store the store to be added
     */
    public void addStore(Store store) {
        storeDao.addStore(store);
    }

    /**
     * Updates an existing store.
     *
     * @param store the store to be updated
     */
    public void updateStore(Store store) {
        storeDao.updateStore(store);
    }

    /**
     * Deletes a store by its ID.
     *
     * @param storeId the ID of the store to be deleted
     */
    public void deleteStore(int storeId) {
        storeDao.deleteStore(storeId);
    }

    /**
     * Retrieves a store by its ID.
     *
     * @param storeId the ID of the store to be retrieved
     * @return the Store object corresponding to the specified ID
     */
    public Store getStoreById(int storeId) {
        return storeDao.getStoreById(storeId);
    }

    /**
     * Retrieves all stores.
     *
     * @return a list of all Store objects
     */
    public List<Store> getAllStores() {
        return storeDao.getAllStores();
    }
}
