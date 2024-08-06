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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    private AuthService authService;

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        authService = new AuthServiceImpl();
        logger.info("AuthFilter initialized.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        String loginURI = request.getContextPath() + "/jsp/login.jsp";
        boolean loggedIn = session != null && session.getAttribute("loggedInUser") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI) || request.getRequestURI().equals(request.getContextPath() + "/login");

        logger.info("Filtering request: {}", request.getRequestURI());
        logger.info("Logged in: {}", loggedIn);
        logger.info("Login request: {}", loginRequest);

        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {
        logger.info("AuthFilter destroyed.");
    }
}
