package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.service.AuthService;
import com.algonquin.cst8288.fwrptomc.service.AuthServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;

/**
 * Servlet that handles logout requests. It invalidates the current session and redirects
 * the user to the login page after logging out.
 * 
 * <p>
 * URL Patterns:
 * <ul>
 * <li>/logout</li>
 * </ul>
 * </p>
 * 
 * @author Xihai Ren
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    private AuthService authService = new AuthServiceImpl();

    /**
     * Handles the HTTP POST method for processing logout requests.
     * Invalidates the current session and forwards the user to the login page.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        authService.logout();
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP GET method by forwarding the request to the doPost method.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}