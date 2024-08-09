package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.model.OrganizationDashboardStatistics;
import com.algonquin.cst8288.fwrptomc.model.User;
import com.algonquin.cst8288.fwrptomc.service.OrganizationDashboardService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet that handles requests for the organization dashboard.
 * 
 * <p>
 * This servlet processes both GET and POST requests to display dashboard statistics
 * for organizations. It ensures that only logged-in users with the "organization"
 * user type can access the dashboard.
 * </p>
 * 
 * <p>
 * URL Patterns:
 * <ul>
 * <li>/organization-dashboard</li>
 * </ul>
 * </p>
 * 
 * @author Xihai Ren
 */
@WebServlet(name = "OrganizationDashboardServlet", urlPatterns = {"/organization-dashboard"})
public class OrganizationDashboardServlet extends HttpServlet {
    private OrganizationDashboardService dashboardService;

    /**
     * Initializes the servlet and sets up the OrganizationDashboardService.
     * 
     * @throws ServletException if an error occurs during initialization
     */
    @Override
    public void init() throws ServletException {
        super.init();
        dashboardService = new OrganizationDashboardService();
    }

    /**
     * Processes requests for both HTTP GET and POST methods.
     * Retrieves the logged-in user's organization dashboard statistics and forwards
     * the request to the organization dashboard JSP page.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null || !"organization".equals(loggedInUser.getUserType())) {
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
            return;
        }

        int organizationId = loggedInUser.getUid();
        OrganizationDashboardStatistics statistics = dashboardService.getStatistics(organizationId);

        request.setAttribute("statistics", statistics);
        request.getRequestDispatcher("/jsp/organization-dashboard.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP GET method by calling the processRequest method.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP POST method by calling the processRequest method.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * 
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Organization Dashboard Servlet";
    }
}