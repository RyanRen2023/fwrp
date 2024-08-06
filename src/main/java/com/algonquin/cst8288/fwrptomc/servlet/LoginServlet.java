/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author renxihai
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private AuthService authService = new AuthServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (authService.login(email, password)) {
            User user = authService.getLoggedInUser();
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user);       
            
            request.setAttribute("message", "login success!");         
            String redirectUrl = String.format("/%s-dashboard", user.getUserType());
            request.getRequestDispatcher(redirectUrl).forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Invalid email or password.");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
