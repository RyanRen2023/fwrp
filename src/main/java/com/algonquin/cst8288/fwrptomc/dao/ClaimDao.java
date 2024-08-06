/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.Claim;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClaimDao {

    private JDBCClient jdbcClient;

    public ClaimDao() {
        this.jdbcClient = new JDBCClient();
    }

    /**
     * Add a new claim to the database
     *
     * @param claim the claim to be added
     */
    public void addClaim(Claim claim) {
        String sql = "INSERT INTO claims (food_id, organization_id, claim_date, quantity) VALUES (?, ?, ?,?)";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, claim.getFoodId());
            pstmt.setInt(2, claim.getOrganizationId());
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(claim.getClaimDate()));
            pstmt.setInt(4, claim.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update an existing claim in the database
     *
     * @param claim the claim to be updated
     */
    public void updateClaim(Claim claim) {
        String sql = "UPDATE claims SET food_id = ?, organization_id = ?, claim_date = ?, quantity = ? WHERE claim_id = ?";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, claim.getFoodId());
            pstmt.setInt(2, claim.getOrganizationId());
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(claim.getClaimDate()));
            pstmt.setInt(4, claim.getQuantity());
            pstmt.setInt(5, claim.getClaimId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a claim from the database by its ID
     *
     * @param claimId the claim ID of the claim to be deleted
     */
    public void deleteClaim(int claimId) {
        String sql = "DELETE FROM claims WHERE claim_id = ?";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, claimId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve a claim from the database by its ID
     *
     * @param claimId the claim ID of the claim to be retrieved
     * @return the Claim object
     */
    public Claim getClaimById(int claimId) {
        String sql = "SELECT * FROM claims WHERE claim_id = ?";
        Claim claim = null;

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, claimId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                claim = new Claim();
                claim.setClaimId(rs.getInt("claim_id"));
                claim.setFoodId(rs.getInt("food_id"));
                claim.setOrganizationId(rs.getInt("organization_id"));
                claim.setClaimDate(rs.getTimestamp("claim_date").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return claim;
    }

    /**
     * Retrieve all claims from the database
     *
     * @return a list of Claim objects
     */
    public List<Claim> getAllClaims() {
        String sql = "SELECT * FROM claims";
        List<Claim> claims = new ArrayList<>();

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Claim claim = new Claim();
                claim.setClaimId(rs.getInt("claim_id"));
                claim.setFoodId(rs.getInt("food_id"));
                claim.setQuantity(rs.getInt("quantity"));
                claim.setOrganizationId(rs.getInt("organization_id"));
                claim.setClaimDate(rs.getTimestamp("claim_date").toLocalDateTime());
                claims.add(claim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return claims;
    }

    public List<Claim> getAllClaimsByOid(int oid) {
        String sql = "SELECT * FROM claims where organization_id = " + oid;
        List<Claim> claims = new ArrayList<>();

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Claim claim = new Claim();
                claim.setClaimId(rs.getInt("claim_id"));
                claim.setFoodId(rs.getInt("food_id"));
                claim.setQuantity(rs.getInt("quantity"));
                claim.setOrganizationId(rs.getInt("organization_id"));
                claim.setClaimDate(rs.getTimestamp("claim_date").toLocalDateTime());
                claims.add(claim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return claims;
    }

    /**
     * Retrieve the total number of claims by organization ID
     *
     * @param organizationId the organization ID
     * @return the total number of claims
     */
    public int getTotalClaimsByOrganizationId(int organizationId) {
        String sql = "SELECT COUNT(*) FROM claims WHERE organization_id = ?";
        int totalClaims = 0;

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, organizationId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                totalClaims = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalClaims;
    }

    /**
     * Retrieve the most claimed food item by organization ID
     *
     * @param organizationId the organization ID
     * @return the most claimed food item name
     */
    public String getMostClaimedFoodItemByOrganizationId(int organizationId) {
        String sql = "SELECT f.fname, SUM(c.quantity) AS total_quantity "
                + "FROM claims c "
                + "JOIN food f ON c.food_id = f.fid "
                + "WHERE c.organization_id = ? "
                + "GROUP BY f.fname "
                + "ORDER BY total_quantity DESC "
                + "LIMIT 1";
        String mostClaimedFoodItem = null;

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, organizationId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                mostClaimedFoodItem = rs.getString("fname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mostClaimedFoodItem;
    }
    
    public int getTotalDonatedItemsByRetailerId(int retailerId) {
        String sql = "SELECT COUNT(*) FROM claims WHERE organization_id IN (SELECT uid FROM user WHERE user_type = 'retailer' AND uid = ?)";
        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, retailerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
