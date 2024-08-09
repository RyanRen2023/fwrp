package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.ClaimDao;
import com.algonquin.cst8288.fwrptomc.model.Claim;
import java.util.List;

/**
 * Service class that handles operations related to claims.
 *
 * <p>
 * This service provides methods for adding, updating, deleting, and retrieving
 * claims. It interacts with the {@link ClaimDao} to perform database
 * operations.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     ClaimService claimService = new ClaimService();
 *     Claim claim = new Claim();
 *     claimService.addClaim(claim);
 * </pre>
 * </p>
 *
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link ClaimDao}: Data access object for performing operations on the
 * claim data.</li>
 * </ul>
 * </p>
 *
 * @author Xihai Ren
 */
public class ClaimService {

    private ClaimDao claimDao;

    /**
     * Constructs a new ClaimService and initializes the ClaimDao.
     */
    public ClaimService() {
        this.claimDao = new ClaimDao();
    }

    /**
     * Adds a new claim.
     *
     * @param claim the claim to be added
     */
    public void addClaim(Claim claim) {
        claimDao.addClaim(claim);
    }

    /**
     * Updates an existing claim.
     *
     * @param claim the claim to be updated
     */
    public void updateClaim(Claim claim) {
        claimDao.updateClaim(claim);
    }

    /**
     * Deletes a claim by its ID.
     *
     * @param claimId the ID of the claim to be deleted
     */
    public void deleteClaim(int claimId) {
        claimDao.deleteClaim(claimId);
    }

    /**
     * Retrieves a claim by its ID.
     *
     * @param claimId the ID of the claim to be retrieved
     * @return the Claim object
     */
    public Claim getClaimById(int claimId) {
        return claimDao.getClaimById(claimId);
    }

    /**
     * Retrieves all claims.
     *
     * @return a list of Claim objects
     */
    public List<Claim> getAllClaims() {
        return claimDao.getAllClaims();
    }

    /**
     * Retrieves all claims associated with a specific organization ID.
     *
     * @param oid the organization ID
     * @return a list of Claim objects associated with the organization
     */
    public List<Claim> getClaimsByOrganizationId(int oid) {
        return claimDao.getAllClaimsByOid(oid);
    }
}
