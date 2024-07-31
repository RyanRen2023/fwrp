/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.filter;

import com.algonquin.cst8288.fwrptomc.service.AuthService;
import com.algonquin.cst8288.fwrptomc.service.AuthServiceImpl;
import java.io.IOException;
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
import java.util.logging.Logger;

/**
 *
 * @author renxihai
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    private static final boolean debug = true;

    private AuthService authService;

    private static final Logger logger = Logger.getLogger(AuthFilter.class.getName());

    public AuthFilter() {
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    private void initialize() {
        if (this.authService == null) {
            this.authService = new AuthServiceImpl();
        }
    }

    /**
     *
     * @param servletRequest
     * @param servletResponse
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain chain)
            throws IOException, ServletException {

        initialize(); // Ensure authService is initialized

        if (debug) {
            logger.info("AuthFilter:doFilter()");

        }

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        String loginRequestURI = request.getContextPath() + "/login";

        String loginURI = request.getContextPath() + "/jsp/login.jsp";

        boolean loggedIn = session != null && authService.isUserLoggedIn();
        boolean loginRequest = request.getRequestURI().equals(loginURI) || request.getRequestURI().equals(loginRequestURI);

        logger.info("Filtering request: " + request.getRequestURI());
        logger.info("Logged in: " + loggedIn);
        logger.info("Login request: " + loginRequest);
        chain.doFilter(request, response);

//        if (loggedIn || loginRequest) {
//            chain.doFilter(request, response);
//        } else {
//            response.sendRedirect(loginURI);
//            return; // 
//        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

}
