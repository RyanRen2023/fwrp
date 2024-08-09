package com.algonquin.cst8288.fwrptomc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a food item in the search results.
 * 
 * <p>
 * This class holds information about a food item, such as its ID, name, expiration date,
 * price, inventory, discount, donation status, food type, store name, and city.
 * </p>
 * 
 * <p>
 * The {@code FoodSearch} class provides getters and setters for these attributes,
 * allowing easy access and modification of the data.
 * </p>
 * 
 * Author: Xihai Ren
 */
public class FoodSearch {
    private int fid;
    private String fname;
    private LocalDate expiration;
    private BigDecimal price;
    private int inventory;
    private Double discount;
    private int isDonate;
    private String foodType;
    private String storeName;
    private String city;

    // Getters and Setters
    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getIsDonate() {
        return isDonate;
    }

    public void setIsDonate(int isDonate) {
        this.isDonate = isDonate;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
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
}