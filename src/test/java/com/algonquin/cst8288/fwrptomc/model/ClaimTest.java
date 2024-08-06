/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Claim class.
 */
public class ClaimTest {

    @Test
    public void testDefaultConstructor() {
        Claim claim = new Claim();
        assertNull(claim.getClaimDate());
        assertEquals(0, claim.getClaimId());
        assertEquals(0, claim.getFoodId());
        assertEquals(0, claim.getOrganizationId());
        assertEquals(0, claim.getQuantity());
    }

    @Test
    public void testParameterizedConstructor() {
        int foodId = 1;
        int quantity = 10;
        int organizationId = 100;
        Claim claim = new Claim(foodId, quantity, organizationId);

        assertEquals(foodId, claim.getFoodId());
        assertEquals(quantity, claim.getQuantity());
        assertEquals(organizationId, claim.getOrganizationId());
        assertNotNull(claim.getClaimDate());
    }

    @Test
    public void testSettersAndGetters() {
        Claim claim = new Claim();

        int claimId = 1;
        int foodId = 2;
        int quantity = 5;
        int organizationId = 10;
        LocalDateTime claimDate = LocalDateTime.of(2023, 8, 6, 12, 0);

        claim.setClaimId(claimId);
        claim.setFoodId(foodId);
        claim.setQuantity(quantity);
        claim.setOrganizationId(organizationId);
        claim.setClaimDate(claimDate);

        assertEquals(claimId, claim.getClaimId());
        assertEquals(foodId, claim.getFoodId());
        assertEquals(quantity, claim.getQuantity());
        assertEquals(organizationId, claim.getOrganizationId());
        assertEquals(claimDate, claim.getClaimDate());
    }

    @Test
    public void testToString() {
        int claimId = 1;
        int foodId = 2;
        int organizationId = 10;
        LocalDateTime claimDate = LocalDateTime.of(2023, 8, 6, 12, 0);

        Claim claim = new Claim();
        claim.setClaimId(claimId);
        claim.setFoodId(foodId);
        claim.setQuantity(5);
        claim.setOrganizationId(organizationId);
        claim.setClaimDate(claimDate);

        String expected = "Claim{claimId=1, foodId=2, organizationId=10, claimDate=2023-08-06T12:00}";
        assertEquals(expected, claim.toString());
    }
}
