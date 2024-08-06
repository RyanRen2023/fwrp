/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

@WebServlet(name = "ConsumerDashboardServlet", urlPatterns = {"/consumer-dashboard"})
public class ConsumerDashboardServlet extends HttpServlet {

    private ConsumerDashboardService dashboardService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.dashboardService = new ConsumerDashboardService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User loggedInUser = (User) session.getAttribute("loggedInUser");


        ConsumerDashboardStatistics statistics = dashboardService.getConsumerDashboardStatistics(loggedInUser.getUid());
        request.setAttribute("statistics", statistics);

        request.getRequestDispatcher("/jsp/consumer-dashboard.jsp").forward(request, response);
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
        return "ConsumerDashboardServlet";
    }
}
