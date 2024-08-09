/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.Claim;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ClaimDao class.
 */
public class ClaimDaoTest {

    @Mock
    private JDBCClient jdbcClient;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ClaimDao claimDao;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(jdbcClient.getConnection()).thenReturn(connection);
    }

    @Test
    public void testUpdateClaim() throws SQLException {
        Claim claim = new Claim();
        claim.setClaimId(1);
        claim.setFoodId(2);
        claim.setOrganizationId(3);
        claim.setClaimDate(LocalDateTime.now());
        claim.setQuantity(15);

        String sql = "UPDATE claims SET food_id = ?, organization_id = ?, claim_date = ?, quantity = ? WHERE claim_id = ?";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        claimDao.updateClaim(claim);

        verify(preparedStatement).setInt(1, claim.getFoodId());
        verify(preparedStatement).setInt(2, claim.getOrganizationId());
        verify(preparedStatement).setTimestamp(3, java.sql.Timestamp.valueOf(claim.getClaimDate()));
        verify(preparedStatement).setInt(4, claim.getQuantity());
        verify(preparedStatement).setInt(5, claim.getClaimId());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testDeleteClaim() throws SQLException {
        int claimId = 1;
        String sql = "DELETE FROM claims WHERE claim_id = ?";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        claimDao.deleteClaim(claimId);

        verify(preparedStatement).setInt(1, claimId);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testGetAllClaims() throws SQLException {
        String sql = "SELECT * FROM claims";

        List<Claim> expectedClaims = new ArrayList<>();
        Claim claim1 = new Claim();
        claim1.setClaimId(1);
        claim1.setFoodId(2);
        claim1.setQuantity(10);
        claim1.setOrganizationId(3);
        claim1.setClaimDate(LocalDateTime.of(2023, 8, 6, 12, 0));
        expectedClaims.add(claim1);

        Claim claim2 = new Claim();
        claim2.setClaimId(2);
        claim2.setFoodId(3);
        claim2.setQuantity(20);
        claim2.setOrganizationId(4);
        claim2.setClaimDate(LocalDateTime.of(2023, 8, 7, 13, 0));
        expectedClaims.add(claim2);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("claim_id")).thenReturn(1, 2);
        when(resultSet.getInt("food_id")).thenReturn(2, 3);
        when(resultSet.getInt("quantity")).thenReturn(10, 20);
        when(resultSet.getInt("organization_id")).thenReturn(3, 4);
        when(resultSet.getTimestamp("claim_date")).thenReturn(
                java.sql.Timestamp.valueOf(LocalDateTime.of(2023, 8, 6, 12, 0)),
                java.sql.Timestamp.valueOf(LocalDateTime.of(2023, 8, 7, 13, 0))
        );

        List<Claim> claims = claimDao.getAllClaims();

        assertNotNull(claims);
        assertEquals(expectedClaims.size(), claims.size());
        for (int i = 0; i < claims.size(); i++) {
            assertEquals(expectedClaims.get(i).getClaimId(), claims.get(i).getClaimId());
            assertEquals(expectedClaims.get(i).getFoodId(), claims.get(i).getFoodId());
            assertEquals(expectedClaims.get(i).getQuantity(), claims.get(i).getQuantity());
            assertEquals(expectedClaims.get(i).getOrganizationId(), claims.get(i).getOrganizationId());
            assertEquals(expectedClaims.get(i).getClaimDate(), claims.get(i).getClaimDate());
        }
    }

    @Test
    public void testGetTotalClaimsByOrganizationId() throws SQLException {
        int organizationId = 1;
        String sql = "SELECT COUNT(*) FROM claims WHERE organization_id = ?";
        int expectedTotalClaims = 10;

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(expectedTotalClaims);

        int totalClaims = claimDao.getTotalClaimsByOrganizationId(organizationId);

        assertEquals(expectedTotalClaims, totalClaims);
    }

    @Test
    public void testGetMostClaimedFoodItemByOrganizationId() throws SQLException {
        int organizationId = 1;
        String sql = "SELECT f.fname, SUM(c.quantity) AS total_quantity "
                + "FROM claims c "
                + "JOIN food f ON c.food_id = f.fid "
                + "WHERE c.organization_id = ? "
                + "GROUP BY f.fname "
                + "ORDER BY total_quantity DESC "
                + "LIMIT 1";
        String expectedMostClaimedFoodItem = "Apple";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("fname")).thenReturn(expectedMostClaimedFoodItem);

        String mostClaimedFoodItem = claimDao.getMostClaimedFoodItemByOrganizationId(organizationId);

        assertEquals(expectedMostClaimedFoodItem, mostClaimedFoodItem);
    }

    @Test
    public void testGetTotalDonatedItemsByRetailerId() throws SQLException {
        int retailerId = 1;
        String sql = "SELECT COUNT(*) FROM claims WHERE organization_id IN (SELECT uid FROM user WHERE user_type = 'retailer' AND uid = ?)";
        int expectedTotalDonatedItems = 5;

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(expectedTotalDonatedItems);

        int totalDonatedItems = claimDao.getTotalDonatedItemsByRetailerId(retailerId);

        assertEquals(expectedTotalDonatedItems, totalDonatedItems);
    }
}


