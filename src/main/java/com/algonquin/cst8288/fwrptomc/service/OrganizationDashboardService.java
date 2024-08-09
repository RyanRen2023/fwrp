package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.ClaimDao;
import com.algonquin.cst8288.fwrptomc.model.OrganizationDashboardStatistics;

/**
 * Service class that handles operations related to the organization dashboard.
 * 
 * <p>
 * This service provides methods to retrieve statistical data for an organization's dashboard,
 * including total claims and the most claimed food item. It interacts with the {@link ClaimDao}
 * to perform database operations.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     OrganizationDashboardService dashboardService = new OrganizationDashboardService();
 *     OrganizationDashboardStatistics stats = dashboardService.getStatistics(organizationId);
 * </pre>
 * </p>
 * 
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link ClaimDao}: Data access object for performing operations on claim data.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * The class interacts with the {@link ClaimDao} to perform all database operations
 * related to the organization's claims.
 * </p>
 * 
 * @author Xihai Ren
 */
public class OrganizationDashboardService {

    private ClaimDao claimDao;

    /**
     * Constructs a new OrganizationDashboardService and initializes the ClaimDao.
     */
    public OrganizationDashboardService() {
        this.claimDao = new ClaimDao();
    }

    /**
     * Retrieves statistical data for the organization's dashboard.
     *
     * @param organizationId the ID of the organization
     * @return an OrganizationDashboardStatistics object containing the statistics
     */
    public OrganizationDashboardStatistics getStatistics(int organizationId) {
        OrganizationDashboardStatistics stats = new OrganizationDashboardStatistics();
        stats.setTotalClaims(claimDao.getTotalClaimsByOrganizationId(organizationId));
        stats.setMostClaimedFoodItem(claimDao.getMostClaimedFoodItemByOrganizationId(organizationId));
        return stats;
    }
}