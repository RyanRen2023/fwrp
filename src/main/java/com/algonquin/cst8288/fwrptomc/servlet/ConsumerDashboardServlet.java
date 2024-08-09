package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.model.ConsumerDashboardStatistics;
import com.algonquin.cst8288.fwrptomc.model.User;
import com.algonquin.cst8288.fwrptomc.service.ConsumerDashboardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for handling consumer dashboard requests. This servlet processes
 * both GET and POST requests to display consumer dashboard statistics.
 * 
 * <p>
 * The servlet retrieves user-specific statistics and forwards the request
 * to the consumer dashboard JSP page for rendering.
 * </p>
 * 
 * <p>
 * URL Patterns:
 * <ul>
 * <li>/consumer-dashboard</li>
 * </ul>
 * </p>
 * 
 * @author Xihai Ren
 */
@WebServlet(name = "ConsumerDashboardServlet", urlPatterns = {"/consumer-dashboard"})
public class ConsumerDashboardServlet extends HttpServlet {

    private ConsumerDashboardService dashboardService;

    /**
     * Initializes the servlet and sets up the dashboard service.
     * 
     * @throws ServletException if an error occurs during initialization
     */
    @Override
    public void init() throws ServletException {
        super.init();
        this.dashboardService = new ConsumerDashboardService();
    }

    /**
     * Processes requests for both HTTP GET and POST methods.
     * Retrieves the logged-in user's dashboard statistics and forwards
     * the request to the consumer dashboard JSP page.
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

        ConsumerDashboardStatistics statistics = dashboardService.getConsumerDashboardStatistics(loggedInUser.getUid());
        request.setAttribute("statistics", statistics);

        request.getRequestDispatcher("/jsp/consumer-dashboard.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP GET method by calling processRequest method.
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
     * Handles the HTTP POST method by calling processRequest method.
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
        return "ConsumerDashboardServlet";
    }
}