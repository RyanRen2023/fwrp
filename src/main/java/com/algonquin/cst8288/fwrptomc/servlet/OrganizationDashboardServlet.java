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

@WebServlet(name = "OrganizationDashboardServlet", urlPatterns = {"/organization-dashboard"})
public class OrganizationDashboardServlet extends HttpServlet {
    private OrganizationDashboardService dashboardService;

    @Override
    public void init() throws ServletException {
        super.init();
        dashboardService = new OrganizationDashboardService();
    }

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Organization Dashboard Servlet";
    }
}