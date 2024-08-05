/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.ClaimDao;
import com.algonquin.cst8288.fwrptomc.model.Claim;
import java.util.List;

public class ClaimService {

    private ClaimDao claimDao;

    public ClaimService() {
        this.claimDao = new ClaimDao();
    }

    /**
     * Add a new claim
     *
     * @param claim the claim to be added
     */
    public void addClaim(Claim claim) {
        claimDao.addClaim(claim);
    }

    /**
     * Update an existing claim
     *
     * @param claim the claim to be updated
     */
    public void updateClaim(Claim claim) {
        claimDao.updateClaim(claim);
    }

    /**
     * Delete a claim by its ID
     *
     * @param claimId the ID of the claim to be deleted
     */
    public void deleteClaim(int claimId) {
        claimDao.deleteClaim(claimId);
    }

    /**
     * Retrieve a claim by its ID
     *
     * @param claimId the ID of the claim to be retrieved
     * @return the Claim object
     */
    public Claim getClaimById(int claimId) {
        return claimDao.getClaimById(claimId);
    }

    /**
     * Retrieve all claims
     *
     * @return a list of Claim objects
     */
    public List<Claim> getAllClaims() {
        return claimDao.getAllClaims();
    }

    public List<Claim> getClaimsByOrganizationId(int oid) {
        return claimDao.getAllClaimsByOid(oid);
    }
}
