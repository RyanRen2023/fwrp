/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.model.EmailNotification;
import com.algonquin.cst8288.fwrptomc.service.NotificationService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author renxihai
 */
@WebServlet(name = "NotificationServlet", urlPatterns = {"/notifications"})
public class NotificationServlet extends HttpServlet {

    private NotificationService emailNotificationService;

    @Override
    public void init() throws ServletException {
        super.init();
        emailNotificationService = new NotificationService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.performNotificatinList(request, response);

    }

    private void performNotificatinList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EmailNotification> notifications = emailNotificationService.getAllNotifications();
        request.setAttribute("notifications", notifications);
        request.getRequestDispatcher("jsp/notifications.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.performNotificatinList(request, response);

    }

    @Override
    public String getServletInfo() {
        return "NotificationServlet";
    }

}
