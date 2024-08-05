/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author renxihai
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

    public Food() {
    }

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

    @Override
    public String toString() {
        return "Food{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                ", expiration=" + expiration +
                ", price=" + price +
                ", inventory=" + inventory +
                ", discount=" + discount +
                ", ftid=" + ftid +
                ", isDonate=" + isDonate +
                ", storeId=" + storeId +
                '}';
    }
}
