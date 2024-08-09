package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.SubscribeDao;
import com.algonquin.cst8288.fwrptomc.model.Subscribe;
import java.util.List;

/**
 * Service class that handles operations related to subscriptions.
 * 
 * <p>
 * This service provides methods for adding, updating, deleting, and retrieving
 * subscriptions. It interacts with the {@link SubscribeDao} to perform database operations.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     SubscribeService subscribeService = new SubscribeService();
 *     List&lt;Subscribe&gt; allSubscribes = subscribeService.getAllSubscribes();
 * </pre>
 * </p>
 * 
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link SubscribeDao}: Data access object for performing operations on subscription data.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * The class interacts with the {@link SubscribeDao} to perform all database operations
 * related to subscriptions.
 * </p>
 * 
 * @author Angela
 */
public class SubscribeService {

    private SubscribeDao subscribeDao;

    /**
     * Constructs a new SubscribeService and initializes the SubscribeDao.
     */
    public SubscribeService() {
        this.subscribeDao = new SubscribeDao();
    }

    /**
     * Adds a new subscription.
     *
     * @param subscribe the subscription to be added
     * @return the added Subscribe object
     */
    public Subscribe addSubscribe(Subscribe subscribe) {
        return subscribeDao.addSubscribe(subscribe);
    }

    /**
     * Updates an existing subscription.
     *
     * @param subscribe the subscription to be updated
     */
    public void updateSubscribe(Subscribe subscribe) {
        subscribeDao.updateSubscribe(subscribe);
    }

    /**
     * Deletes a subscription by its ID.
     *
     * @param sid the ID of the subscription to be deleted
     */
    public void deleteSubscribe(int sid) {
        subscribeDao.deleteSubscribe(sid);
    }

    /**
     * Retrieves a subscription by its ID.
     *
     * @param sid the ID of the subscription to be retrieved
     * @return the Subscribe object corresponding to the specified ID
     */
    public Subscribe getSubscribeById(int sid) {
        return subscribeDao.getSubscribeById(sid);
    }

    /**
     * Retrieves all subscriptions.
     *
     * @return a list of all Subscribe objects
     */
    public List<Subscribe> getAllSubscribes() {
        return subscribeDao.getAllSubscribes();
    }

    /**
     * Searches for subscriptions by alert type.
     *
     * @param alertType the type of alert to search for
     * @return a list of Subscribe objects matching the alert type
     */
    public List<Subscribe> searchSubscribesByAlertType(String alertType) {
        return subscribeDao.findByAlertType(alertType);
    } 
}