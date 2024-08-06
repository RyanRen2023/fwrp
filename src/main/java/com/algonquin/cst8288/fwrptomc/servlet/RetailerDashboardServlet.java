package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.model.RetailerDashboardStatistics;
import com.algonquin.cst8288.fwrptomc.model.User;
import com.algonquin.cst8288.fwrptomc.service.RetailerDashboardService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RetailerDashboardServlet", urlPatterns = {"/retailer-dashboard"})
public class RetailerDashboardServlet extends HttpServlet {

    private RetailerDashboardService dashboardService;

    @Override
    public void init() throws ServletException {
        super.init();
        dashboardService = new RetailerDashboardService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null || !"retailer".equals(loggedInUser.getUserType())) {
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
            return;
        }

        RetailerDashboardStatistics statistics = dashboardService.getStatistics(loggedInUser.getUid());

        request.setAttribute("statistics", statistics);
        request.getRequestDispatcher("/jsp/retailer-dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Retailer Dashboard Servlet";
    }
}
