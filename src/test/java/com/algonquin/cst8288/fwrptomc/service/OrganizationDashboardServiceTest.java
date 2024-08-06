/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.ClaimDao;
import com.algonquin.cst8288.fwrptomc.model.OrganizationDashboardStatistics;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the OrganizationDashboardService class.
 */
public class OrganizationDashboardServiceTest {

    @Mock
    private ClaimDao claimDao;

    @InjectMocks
    private OrganizationDashboardService organizationDashboardService;

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
     * Test of getStatistics method, of class OrganizationDashboardService.
     */
    @Test
    public void testGetStatistics() {
        int organizationId = 1;
        int totalClaims = 50;
        String mostClaimedFoodItem = "Bread";

        when(claimDao.getTotalClaimsByOrganizationId(organizationId)).thenReturn(totalClaims);
        when(claimDao.getMostClaimedFoodItemByOrganizationId(organizationId)).thenReturn(mostClaimedFoodItem);

        OrganizationDashboardStatistics result = organizationDashboardService.getStatistics(organizationId);

        assertNotNull(result);
        assertEquals(totalClaims, result.getTotalClaims());
        assertEquals(mostClaimedFoodItem, result.getMostClaimedFoodItem());

        verify(claimDao).getTotalClaimsByOrganizationId(organizationId);
        verify(claimDao).getMostClaimedFoodItemByOrganizationId(organizationId);
    }
}
