package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.service.StatisticsService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that handles requests for retrieving statistical data.
 * 
 * <p>
 * This servlet processes HTTP GET requests to provide JSON-formatted statistical
 * data via an API endpoint.
 * </p>
 * 
 * <p>
 * URL Patterns:
 * <ul>
 * <li>/api/statistics</li>
 * </ul>
 * </p>
 * 
 * @author Xihai Ren
 */
@WebServlet(name = "StatisticsServlet", urlPatterns = {"/api/statistics"})
public class StatisticsServlet extends HttpServlet {

    private StatisticsService statisticsService = new StatisticsService();

    /**
     * Handles the HTTP GET method to retrieve statistical data.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject statistics = statisticsService.getStatistics();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(statistics));
    }
}