/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents an order in the system.
 * 
 * <p>
 * This class holds information about an order, including the order ID, user ID, 
 * food ID, amount of money, quantity, and the purchase date.
 * </p>
 * 
 * <p>
 * The {@code Orders} class provides getters and setters for these attributes,
 * allowing easy access and modification of the data.
 * </p>
 * 
 * Author: Xihai Ren
 */
public class Orders {
    private int oid;
    private int uid;
    private int fid;
    private BigDecimal money;
    private int num;
    private LocalDate purchaseDate;

    // Getters and Setters
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
    
}
