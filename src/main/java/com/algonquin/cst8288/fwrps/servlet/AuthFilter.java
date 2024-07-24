/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package com.algonquin.cst8288.fwrps.servlet;

import com.algonquin.cst8288.fwrps.service.AuthService;
import jakarta.inject.Inject;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Logger;

/**
 *
 * @author renxihai
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    private static final boolean debug = true;

    @Inject
    private AuthService authService;

    private static final Logger logger = Logger.getLogger(AuthFilter.class.getName());

    public AuthFilter() {
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

        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
            return; // 
        }

    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {

    }

}
