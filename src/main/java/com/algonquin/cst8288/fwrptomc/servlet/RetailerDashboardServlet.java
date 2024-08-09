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

/**
 * Servlet that handles requests for the retailer dashboard.
 * 
 * <p>
 * This servlet processes both GET and POST requests to display dashboard statistics
 * for retailers. It ensures that only logged-in users with the "retailer" user type 
 * can access the dashboard.
 * </p>
 * 
 * <p>
 * URL Patterns:
 * <ul>
 * <li>/retailer-dashboard</li>
 * </ul>
 * </p>
 * 
 * @author Xihai Ren
 */
@WebServlet(name = "RetailerDashboardServlet", urlPatterns = {"/retailer-dashboard"})
public class RetailerDashboardServlet extends HttpServlet {

    private RetailerDashboardService dashboardService;

    /**
     * Initializes the servlet and sets up the RetailerDashboardService.
     * 
     * @throws ServletException if an error occurs during initialization
     */
    @Override
    public void init() throws ServletException {
        super.init();
        dashboardService = new RetailerDashboardService();
    }

    /**
     * Handles the HTTP GET method to retrieve and display retailer dashboard statistics.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
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

    /**
     * Handles the HTTP POST method by forwarding the request to the doGet method.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * 
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Retailer Dashboard Servlet";
    }
}