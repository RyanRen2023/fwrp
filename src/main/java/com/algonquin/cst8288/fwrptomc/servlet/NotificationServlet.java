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
 * Servlet that handles requests for listing email notifications.
 *
 * <p>
 * The servlet retrieves all email notifications and forwards the data to a JSP
 * page for rendering.
 * </p>
 *
 * <p>
 * URL Patterns:
 * <ul>
 * <li>/notifications</li>
 * </ul>
 * </p>
 *
 * @author Xihai Ren
 */
@WebServlet(name = "NotificationServlet", urlPatterns = {"/notifications"})
public class NotificationServlet extends HttpServlet {

    private NotificationService emailNotificationService;

    /**
     * Initializes the servlet and sets up the NotificationService.
     *
     * @throws ServletException if an error occurs during initialization
     */
    @Override
    public void init() throws ServletException {
        super.init();
        emailNotificationService = new NotificationService();
    }

    /**
     * Handles the HTTP GET method by listing all email notifications.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.performNotificationList(request, response);
    }

    /**
     * Handles the HTTP POST method by listing all email notifications.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.performNotificationList(request, response);
    }

    /**
     * Retrieves a list of email notifications and forwards them to the
     * notifications JSP page.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void performNotificationList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EmailNotification> notifications = emailNotificationService.getAllNotifications();
        request.setAttribute("notifications", notifications);
        request.getRequestDispatcher("jsp/notifications.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "NotificationServlet handles listing of email notifications.";
    }
}
