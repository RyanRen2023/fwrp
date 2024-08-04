package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.model.Subscribe;
import com.algonquin.cst8288.fwrptomc.service.SubscribeService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "SubscribeAlertServlet", urlPatterns = {"/subscribe-alerts"})
public class SubscribeAlertServlet extends HttpServlet {

    private final SubscribeService subscribeService;

    public SubscribeAlertServlet() {
        this.subscribeService = new SubscribeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Subscribe> subscriptions = subscribeService.getAllSubscribes();
        request.setAttribute("subscriptions", subscriptions);
        request.getRequestDispatcher("/jsp/subscribe-alerts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String alertType = request.getParameter("alertType");
        String email = URLDecoder.decode(request.getParameter("email"), "UTF-8");
        response.setContentType("application/json");
        JsonObject responseJson = new JsonObject();
        if (alertType != null && email != null) {
            Subscribe subscribe = new Subscribe();
            subscribe.setUid(1);  // Set the user ID, modify as per your logic
            subscribe.setFid(1);  // Set the food item ID, modify as per your logic
            subscribe.setCreateTime(LocalDate.now());
            subscribe.setAlertType(alertType);
            subscribe.setEmail(email);
            subscribeService.addSubscribe(subscribe);
            responseJson.addProperty("status", "success");
            responseJson.addProperty("sid", subscribe.getSid());
            responseJson.addProperty("alertType", alertType);
            responseJson.addProperty("email", email);
            response.setStatus(HttpServletResponse.SC_OK);

        } else {
            responseJson.addProperty("message", "Subscribe not found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        response.getWriter().write(responseJson.toString());

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }

        String[] params = body.toString().split("&");
        int sid = -1;
        String alertType = null;
        String email = null;

        for (String param : params) {
            String[] keyValue = param.split("=");
            switch (keyValue[0]) {
                case "sid":
                    sid = Integer.parseInt(keyValue[1]);
                    break;
                case "alertType":
                    alertType = keyValue[1];
                    break;
                case "email":

                    email = keyValue[1];
                    email = URLDecoder.decode(email, "UTF-8");
                    break;
            }
        }

        response.setContentType("application/json");
        JsonObject responseJson = new JsonObject();

        if (sid != -1 && alertType != null && email != null) {
            Subscribe subscribe = subscribeService.getSubscribeById(sid);
            if (subscribe != null) {
                subscribe.setAlertType(alertType);
                subscribe.setEmail(email);
                subscribe.setSid(sid);
                subscribeService.updateSubscribe(subscribe);

                responseJson.addProperty("status", "success");
                responseJson.addProperty("sid", sid);
                responseJson.addProperty("alertType", alertType);
                responseJson.addProperty("email", email);

                response.setStatus(HttpServletResponse.SC_OK);

            } else {
                responseJson.addProperty("message", "Subscribe not found.");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            responseJson.addProperty("message", "Missing or invalid parameters.");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        response.getWriter().write(responseJson.toString());
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String body = reader.readLine();
        String[] params = body.split("=");
        if (params.length == 2 && "sid".equals(params[0])) {
            try {
                int sid = Integer.parseInt(params[1]);
                subscribeService.deleteSubscribe(sid);
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public String getServletInfo() {
        return "SubscribeAlertServlet handles subscription requests";
    }
}
