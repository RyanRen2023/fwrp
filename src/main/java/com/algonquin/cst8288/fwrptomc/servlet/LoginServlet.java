package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.model.User;
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
 * Servlet that handles login requests. Processes user login information and
 * directs the user to their respective dashboard upon successful
 * authentication.
 *
 * <p>
 * URL Patterns:
 * <ul>
 * <li>/login</li>
 * </ul>
 * </p>
 *
 * @author Xihai Ren
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private AuthService authService = new AuthServiceImpl();

    /**
     * Handles the HTTP POST method for processing login requests. Authenticates
     * the user based on provided credentials and redirects them to their
     * respective dashboard upon successful login.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (authService.login(email, password)) {
            User user = authService.getLoggedInUser();
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user);

            request.setAttribute("message", "Login success!");
            String redirectUrl = String.format("/%s-dashboard", user.getUserType());
            request.getRequestDispatcher(redirectUrl).forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Invalid email or password.");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP GET method by forwarding the request to the doPost
     * method.
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
        doPost(request, response);
    }
}
