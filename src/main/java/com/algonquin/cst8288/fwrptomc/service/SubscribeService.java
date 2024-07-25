package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.SubscribeDao;
import com.algonquin.cst8288.fwrptomc.model.Subscribe;
import java.util.List;

public class SubscribeService {

    private SubscribeDao subscribeDao;

    public SubscribeService() {
        this.subscribeDao = new SubscribeDao();
    }

    /**
     * Add a new subscription
     *
     * @param subscribe the subscription to be added
     */
    public void addSubscribe(Subscribe subscribe) {
        subscribeDao.addSubscribe(subscribe);
    }

    /**
     * Update an existing subscription
     *
     * @param subscribe the subscription to be updated
     */
    public void updateSubscribe(Subscribe subscribe) {
        subscribeDao.updateSubscribe(subscribe);
    }

    /**
     * Delete a subscription by its ID
     *
     * @param sid the subscription ID of the item to be deleted
     */
    public void deleteSubscribe(int sid) {
        subscribeDao.deleteSubscribe(sid);
    }

    /**
     * Retrieve a subscription by its ID
     *
     * @param sid the subscription ID of the item to be retrieved
     * @return the Subscribe object
     */
    public Subscribe getSubscribeById(int sid) {
        return subscribeDao.getSubscribeById(sid);
    }

    /**
     * Retrieve all subscriptions
     *
     * @return a list of Subscribe objects
     */
    public List<Subscribe> getAllSubscribes() {
        return subscribeDao.getAllSubscribes();
    }
}