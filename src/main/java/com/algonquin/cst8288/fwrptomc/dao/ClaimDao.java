package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.Claim;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing claims in the database.
 * 
 * <p>
 * This class provides methods for adding, updating, deleting, and retrieving 
 * claim records from the database. It interacts with the database using JDBC.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     ClaimDao claimDao = new ClaimDao();
 *     Claim claim = claimDao.getClaimById(1);
 * </pre>
 * </p>
 * 
 * <p>
 * Note: Ensure that the JDBCClient class is correctly implemented to provide
 * a valid database connection.
 * </p>
 * 
 * @author Xihai Ren
 */
public class ClaimDao {

    private JDBCClient jdbcClient;

    /**
     * Constructs a new ClaimDao and initializes the JDBCClient.
     */
    public ClaimDao() {
        this.jdbcClient = new JDBCClient();
    }

    /**
     * Adds a new claim to the database.
     *
     * @param claim the claim to be added
     */
    public void addClaim(Claim claim) {
        String sql = "INSERT INTO claims (food_id, organization_id, claim_date, quantity) VALUES (?, ?, ?, ?)";

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
     * Updates an existing claim in the database.
     *
     * @param claim the claim to be updated
     */
    public void updateClaim(Claim claim) {
        String sql = "UPDATE claims SET food_id = ?, organization_id = ?, claim_date = ?, quantity = ? WHERE claim_id = ?";

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
     * Deletes a claim from the database by its ID.
     *
     * @param claimId the ID of the claim to be deleted
     */
    public void deleteClaim(int claimId) {
        String sql = "DELETE FROM claims WHERE claim_id = ?";

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, claimId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a claim from the database by its ID.
     *
     * @param claimId the ID of the claim to be retrieved
     * @return the Claim object, or null if not found
     */
    public Claim getClaimById(int claimId) {
        String sql = "SELECT * FROM claims WHERE claim_id = ?";
        Claim claim = null;

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, claimId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                claim = new Claim();
                claim.setClaimId(rs.getInt("claim_id"));
                claim.setFoodId(rs.getInt("food_id"));
                claim.setOrganizationId(rs.getInt("organization_id"));
                claim.setClaimDate(rs.getTimestamp("claim_date").toLocalDateTime());
                claim.setQuantity(rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return claim;
    }

    /**
     * Retrieves all claims from the database.
     *
     * @return a list of Claim objects
     */
    public List<Claim> getAllClaims() {
        String sql = "SELECT * FROM claims";
        List<Claim> claims = new ArrayList<>();

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql); 
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Claim claim = new Claim();
                claim.setClaimId(rs.getInt("claim_id"));
                claim.setFoodId(rs.getInt("food_id"));
                claim.setOrganizationId(rs.getInt("organization_id"));
                claim.setClaimDate(rs.getTimestamp("claim_date").toLocalDateTime());
                claim.setQuantity(rs.getInt("quantity"));
                claims.add(claim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return claims;
    }

    /**
     * Retrieves all claims by organization ID.
     *
     * @param oid the organization ID
     * @return a list of Claim objects for the specified organization
     */
    public List<Claim> getAllClaimsByOid(int oid) {
        String sql = "SELECT * FROM claims WHERE organization_id = " + oid;
        List<Claim> claims = new ArrayList<>();

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql); 
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Claim claim = new Claim();
                claim.setClaimId(rs.getInt("claim_id"));
                claim.setFoodId(rs.getInt("food_id"));
                claim.setOrganizationId(rs.getInt("organization_id"));
                claim.setClaimDate(rs.getTimestamp("claim_date").toLocalDateTime());
                claim.setQuantity(rs.getInt("quantity"));
                claims.add(claim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return claims;
    }

    /**
     * Retrieves the total number of claims by organization ID.
     *
     * @param organizationId the organization ID
     * @return the total number of claims
     */
    public int getTotalClaimsByOrganizationId(int organizationId) {
        String sql = "SELECT COUNT(*) FROM claims WHERE organization_id = ?";
        int totalClaims = 0;

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
     * Retrieves the most claimed food item by organization ID.
     *
     * @param organizationId the organization ID
     * @return the name of the most claimed food item
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

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
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

    /**
     * Retrieves the total number of donated items by retailer ID.
     *
     * @param retailerId the retailer ID
     * @return the total number of donated items
     */
    public int getTotalDonatedItemsByRetailerId(int retailerId) {
        String sql = "SELECT COUNT(*) FROM claims WHERE organization_id IN "
                   + "(SELECT uid FROM user WHERE user_type = 'retailer' AND uid = ?)";
        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
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