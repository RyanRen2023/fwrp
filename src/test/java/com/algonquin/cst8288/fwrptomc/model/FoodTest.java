/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Food class.
 */
public class FoodTest {

    @Test
    public void testDefaultConstructor() {
        Food food = new Food();
        assertEquals(0, food.getFid());
        assertNull(food.getFname());
        assertNull(food.getExpiration());
        assertNull(food.getPrice());
        assertEquals(0, food.getInventory());
        assertNull(food.getDiscount());
        assertEquals(0, food.getFtid());
        assertEquals(0, food.getIsDonate());
        assertEquals(0, food.getStoreId());
        assertEquals(0, food.isIsSurplus());
    }

    @Test
    public void testParameterizedConstructorWithFid() {
        int fid = 1;
        String fname = "Apple";
        LocalDate expiration = LocalDate.of(2024, 12, 31);
        BigDecimal price = new BigDecimal("1.99");
        int inventory = 100;
        Double discount = 0.1;
        int ftid = 2;
        int isDonate = 1;
        int storeId = 3;
        
        Food food = new Food(fid, fname, expiration, price, inventory, discount, ftid, isDonate, storeId);
        
        assertEquals(fid, food.getFid());
        assertEquals(fname, food.getFname());
        assertEquals(expiration, food.getExpiration());
        assertEquals(price, food.getPrice());
        assertEquals(inventory, food.getInventory());
        assertEquals(discount, food.getDiscount());
        assertEquals(ftid, food.getFtid());
        assertEquals(isDonate, food.getIsDonate());
        assertEquals(storeId, food.getStoreId());
    }

    @Test
    public void testParameterizedConstructorWithoutFid() {
        String fname = "Apple";
        LocalDate expiration = LocalDate.of(2024, 12, 31);
        BigDecimal price = new BigDecimal("1.99");
        int inventory = 100;
        Double discount = 0.1;
        int ftid = 2;
        int isDonate = 1;
        int storeId = 3;
        
        Food food = new Food(fname, expiration, price, inventory, discount, ftid, isDonate, storeId);
        
        assertEquals(0, food.getFid());
        assertEquals(fname, food.getFname());
        assertEquals(expiration, food.getExpiration());
        assertEquals(price, food.getPrice());
        assertEquals(inventory, food.getInventory());
        assertEquals(discount, food.getDiscount());
        assertEquals(ftid, food.getFtid());
        assertEquals(isDonate, food.getIsDonate());
        assertEquals(storeId, food.getStoreId());
    }

    @Test
    public void testSettersAndGetters() {
        Food food = new Food();
        
        int fid = 1;
        String fname = "Apple";
        LocalDate expiration = LocalDate.of(2024, 12, 31);
        BigDecimal price = new BigDecimal("1.99");
        int inventory = 100;
        Double discount = 0.1;
        int ftid = 2;
        int isDonate = 1;
        int storeId = 3;
        int isSurplus = 1;
        
        food.setFid(fid);
        food.setFname(fname);
        food.setExpiration(expiration);
        food.setPrice(price);
        food.setInventory(inventory);
        food.setDiscount(discount);
        food.setFtid(ftid);
        food.setIsDonate(isDonate);
        food.setStoreId(storeId);
        food.setIsSurplus(isSurplus);
        
        assertEquals(fid, food.getFid());
        assertEquals(fname, food.getFname());
        assertEquals(expiration, food.getExpiration());
        assertEquals(price, food.getPrice());
        assertEquals(inventory, food.getInventory());
        assertEquals(discount, food.getDiscount());
        assertEquals(ftid, food.getFtid());
        assertEquals(isDonate, food.getIsDonate());
        assertEquals(storeId, food.getStoreId());
        assertEquals(isSurplus, food.isIsSurplus());
    }

    @Test
    public void testToString() {
        int fid = 1;
        String fname = "Apple";
        LocalDate expiration = LocalDate.of(2024, 12, 31);
        BigDecimal price = new BigDecimal("1.99");
        int inventory = 100;
        Double discount = 0.1;
        int ftid = 2;
        int isDonate = 1;
        int storeId = 3;
        int isSurplus = 1;
        
        Food food = new Food(fid, fname, expiration, price, inventory, discount, ftid, isDonate, storeId);
        food.setIsSurplus(isSurplus);
        
        String expected = "Food{fid=" + fid + ", fname=" + fname + ", expiration=" + expiration + ", price=" + price + ", inventory=" + inventory + ", discount=" + discount + ", ftid=" + ftid + ", isDonate=" + isDonate + ", storeId=" + storeId + ", isSurplus=" + isSurplus + '}';
        
        assertEquals(expected, food.toString());
    }
}
