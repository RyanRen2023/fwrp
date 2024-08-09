package com.algonquin.cst8288.fwrptomc.filter;

import com.algonquin.cst8288.fwrptomc.service.AuthService;
import com.algonquin.cst8288.fwrptomc.service.AuthServiceImpl;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Authentication filter for intercepting requests and ensuring users are authenticated.
 * Allows certain paths to bypass authentication.
 * 
 * @author Xihai Ren
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    private AuthService authService;

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    private static final Set<String> ALLOWED_PATHS = new HashSet<>(Arrays.asList(
            "/login", "/logout", "/jsp/login.jsp", "/sign-up", "/jsp/sign-up.jsp", "/css/", "/js/", "/images/"
    ));

    /**
     * Initializes the filter and sets up the authentication service.
     * 
     * @param filterConfig the filter configuration
     * @throws ServletException if an error occurs during initialization
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        authService = new AuthServiceImpl();
        logger.info("AuthFilter initialized with allowed paths: {}", ALLOWED_PATHS);
    }

    /**
     * Filters incoming requests, allowing access to certain paths without authentication
     * and redirecting unauthenticated users to the login page for other paths.
     * 
     * @param servletRequest the servlet request
     * @param servletResponse the servlet response
     * @param chain the filter chain
     * @throws IOException if an I/O error occurs during the process
     * @throws ServletException if an error occurs during the process
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");

        boolean loggedIn = session != null && session.getAttribute("loggedInUser") != null;
        boolean allowedPath = ALLOWED_PATHS.stream().anyMatch(path::startsWith);

        logger.info("Filtering request: {}", request.getRequestURI());
        logger.info("Logged in: {}", loggedIn);
        logger.info("Allowed path: {}", allowedPath);

        if (loggedIn || allowedPath) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
        }
    }

    /**
     * Destroys the filter and performs any necessary cleanup.
     */
    @Override
    public void destroy() {
        logger.info("AuthFilter destroyed.");
    }
}