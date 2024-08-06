/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.ClaimDao;
import com.algonquin.cst8288.fwrptomc.model.OrganizationDashboardStatistics;

public class OrganizationDashboardService {
    private ClaimDao claimDao;

    public OrganizationDashboardService() {
        this.claimDao = new ClaimDao();
    }

    public OrganizationDashboardStatistics getStatistics(int organizationId) {
        OrganizationDashboardStatistics stats = new OrganizationDashboardStatistics();
        stats.setTotalClaims(claimDao.getTotalClaimsByOrganizationId(organizationId));
        stats.setMostClaimedFoodItem(claimDao.getMostClaimedFoodItemByOrganizationId(organizationId));
        return stats;
    }
}