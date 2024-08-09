package com.algonquin.cst8288.fwrptomc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Model class representing a food item.
 * 
 * This class holds data related to a food item such as its ID, name, expiration date,
 * price, inventory count, discount, food type ID, donation status, store ID, and surplus status.
 * It provides constructors, getters, setters, and a toString method for better readability.
 * 
 * Author: Xihai Ren
 */
public class Food {

    private int fid;
    private String fname;
    private LocalDate expiration;
    private BigDecimal price;
    private int inventory;
    private Double discount;
    private int ftid;
    private int isDonate;
    private int storeId;
    private int isSurplus;

    /**
     * Default constructor.
     */
    public Food() {
    }

    /**
     * Constructs a new Food object with the specified values.
     *
     * @param fid the ID of the food item
     * @param fname the name of the food item
     * @param expiration the expiration date of the food item
     * @param price the price of the food item
     * @param inventory the inventory count of the food item
     * @param discount the discount applied to the food item
     * @param ftid the food type ID
     * @param isDonate the donation status (1 if the item is for donation, 0 otherwise)
     * @param storeId the ID of the store associated with the food item
     */
    public Food(int fid, String fname, LocalDate expiration, BigDecimal price, int inventory, Double discount, int ftid, int isDonate, int storeId) {
        this.fid = fid;
        this.fname = fname;
        this.expiration = expiration;
        this.price = price;
        this.inventory = inventory;
        this.discount = discount;
        this.ftid = ftid;
        this.isDonate = isDonate;
        this.storeId = storeId;
    }

    /**
     * Constructs a new Food object without specifying the food ID.
     *
     * @param fname the name of the food item
     * @param expiration the expiration date of the food item
     * @param price the price of the food item
     * @param inventory the inventory count of the food item
     * @param discount the discount applied to the food item
     * @param ftid the food type ID
     * @param isDonate the donation status (1 if the item is for donation, 0 otherwise)
     * @param storeId the ID of the store associated with the food item
     */
    public Food(String fname, LocalDate expiration, BigDecimal price, int inventory, Double discount, int ftid, int isDonate, int storeId) {
        this.fname = fname;
        this.expiration = expiration;
        this.price = price;
        this.inventory = inventory;
        this.discount = discount;
        this.ftid = ftid;
        this.isDonate = isDonate;
        this.storeId = storeId;
    }

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

    public int getFtid() {
        return ftid;
    }

    public void setFtid(int ftid) {
        this.ftid = ftid;
    }

    public int getIsDonate() {
        return isDonate;
    }

    public void setIsDonate(int isDonate) {
        this.isDonate = isDonate;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getIsSurplus() {
        return isSurplus;
    }

    public void setIsSurplus(int isSurplus) {
        this.isSurplus = isSurplus;
    }

    /**
     * Returns a string representation of the Food object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Food{" + "fid=" + fid + ", fname=" + fname + ", expiration=" + expiration + ", price=" + price + ", inventory=" + inventory + ", discount=" + discount + ", ftid=" + ftid + ", isDonate=" + isDonate + ", storeId=" + storeId + ", isSurplus=" + isSurplus + '}';
    }
}