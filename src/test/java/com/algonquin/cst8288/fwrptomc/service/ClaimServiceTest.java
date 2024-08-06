/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.ClaimDao;
import com.algonquin.cst8288.fwrptomc.model.Claim;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ClaimService class.
 */
public class ClaimServiceTest {

    @Mock
    private ClaimDao claimDao;

    @InjectMocks
    private ClaimService claimService;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addClaim method, of class ClaimService.
     */
    @Test
    public void testAddClaim() {
        Claim claim = new Claim();
        doNothing().when(claimDao).addClaim(claim);
        claimService.addClaim(claim);
        verify(claimDao).addClaim(claim);
    }

    /**
     * Test of updateClaim method, of class ClaimService.
     */
    @Test
    public void testUpdateClaim() {
        Claim claim = new Claim();
        doNothing().when(claimDao).updateClaim(claim);
        claimService.updateClaim(claim);
        verify(claimDao).updateClaim(claim);
    }

    /**
     * Test of deleteClaim method, of class ClaimService.
     */
    @Test
    public void testDeleteClaim() {
        int claimId = 1;
        doNothing().when(claimDao).deleteClaim(claimId);
        claimService.deleteClaim(claimId);
        verify(claimDao).deleteClaim(claimId);
    }

    /**
     * Test of getClaimById method, of class ClaimService.
     */
    @Test
    public void testGetClaimById() {
        int claimId = 1;
        Claim claim = new Claim();
        when(claimDao.getClaimById(claimId)).thenReturn(claim);
        Claim result = claimService.getClaimById(claimId);
        assertNotNull(result);
        assertEquals(claim, result);
        verify(claimDao).getClaimById(claimId);
    }

    /**
     * Test of getAllClaims method, of class ClaimService.
     */
    @Test
    public void testGetAllClaims() {
        List<Claim> claims = Arrays.asList(new Claim(), new Claim());
        when(claimDao.getAllClaims()).thenReturn(claims);
        List<Claim> result = claimService.getAllClaims();
        assertNotNull(result);
        assertEquals(claims, result);
        verify(claimDao).getAllClaims();
    }

    /**
     * Test of getClaimsByOrganizationId method, of class ClaimService.
     */
    @Test
    public void testGetClaimsByOrganizationId() {
        int oid = 1;
        List<Claim> claims = Arrays.asList(new Claim(), new Claim());
        when(claimDao.getAllClaimsByOid(oid)).thenReturn(claims);
        List<Claim> result = claimService.getClaimsByOrganizationId(oid);
        assertNotNull(result);
        assertEquals(claims, result);
        verify(claimDao).getAllClaimsByOid(oid);
    }
}

