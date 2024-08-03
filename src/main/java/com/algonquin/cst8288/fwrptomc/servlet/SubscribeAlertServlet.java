package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.model.Subscribe;
import com.algonquin.cst8288.fwrptomc.service.SubscribeService;
import java.io.BufferedReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "SubscribeAlertServlet", urlPatterns = {"/subscribe-alerts"})
public class SubscribeAlertServlet extends HttpServlet {

    private SubscribeService subscribeService;

    public SubscribeAlertServlet() {
        this.subscribeService = new SubscribeService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/subscribe-alerts.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String alertType = request.getParameter("alertType");
        String email = request.getParameter("email");

        Subscribe subscribe = new Subscribe();
        subscribe.setUid(1);  // Set the user ID, modify as per your logic
        subscribe.setFid(1);  // Set the food item ID, modify as per your logic
        subscribe.setCreateTime(LocalDate.now());
        subscribe.setAlertType(alertType);
        subscribe.setEmail(email);

        subscribeService.addSubscribe(subscribe);

        response.sendRedirect(request.getContextPath() + "/subscribe-alerts");
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String body = reader.readLine();
        String[] params = body.split("&");

        int sid = -1;
        String alertType = null;
        String email = null;

        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue[0].equals("sid")) {
                sid = Integer.parseInt(keyValue[1]);
            } else if (keyValue[0].equals("alertType")) {
                alertType = keyValue[1];
            } else if (keyValue[0].equals("email")) {
                email = keyValue[1];
            }
        }

        if (sid != -1 && alertType != null && email != null) {
            Subscribe subscribe = subscribeService.getSubscribeById(sid);
            if (subscribe != null) {
                subscribe.setAlertType(alertType);
                subscribe.setEmail(email);
                subscribeService.updateSubscribe(subscribe);
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    BufferedReader reader = request.getReader();
    String body = reader.readLine();
    String[] params = body.split("=");
    if (params.length == 2 && params[0].equals("sid")) {
        try {
            int sid = Integer.parseInt(params[1]);
            subscribeService.deleteSubscribe(sid);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);  // Indicate success with no content
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);  // Indicate bad request
        }
    } else {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);  // Indicate bad request
    }
}

    @Override
    public String getServletInfo() {
        return "SubscribeAlertServlet handles subscription requests";
    }
}
