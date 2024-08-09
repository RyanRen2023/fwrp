package com.algonquin.cst8288.fwrptomc.model;

/**
 * Represents a store in the Food Waste Reduction Platform.
 * 
 * <p>
 * This class encapsulates the store's details, such as its ID, name, 
 * city location, and the user ID of the owner.
 * </p>
 * 
 * Author: Xihai Ren
 */
public class Store {
    private int storeId;
    private String storeName;
    private String city;
    private int uid;

    // Getters and Setters
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}