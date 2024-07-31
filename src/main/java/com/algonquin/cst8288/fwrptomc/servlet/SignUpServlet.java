/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.model.User;
import com.algonquin.cst8288.fwrptomc.service.UserService;
import com.algonquin.cst8288.fwrptomc.service.UserServiceImpl;
import java.io.BufferedReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.PrintWriter;

/**
 *
 * @author renxihai
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/sign-up"})
public class SignUpServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        

        JsonObject jsonObject = requestJsonObj(request);

        String username = jsonObject.get("username").getAsString();
        String email = jsonObject.get("email").getAsString();
        String password = jsonObject.get("password").getAsString();
        String confirmPassword = jsonObject.get("confirmPassword").getAsString();
        String userType = jsonObject.get("role").getAsString();

        Map<String, String> errors = validateRequest(email, password, username, confirmPassword);

        JsonObject responseJson = new JsonObject();

        if (!errors.isEmpty()) {
            responseJson.addProperty("status", "failure");
            responseJson.addProperty("message", errors.toString());
            

        } else {
            User userNew = new User(username, email, password, userType);
            userService.createUser(userNew);
            responseJson.addProperty("status", "success");
            responseJson.addProperty("message", "Registration successful!");

        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(responseJson));
        out.flush();
    }

    private JsonObject requestJsonObj(HttpServletRequest request) throws IOException, JsonSyntaxException {
        // Read JSON data from request
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonData = sb.toString();
        // Parse JSON data
        JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
        return jsonObject;
    }

    private Map<String, String> validateRequest(String email, String password, String username, String confirmPassword) {

        Map<String, String> errors = new HashMap();

        if (email.isBlank()
                || password.isBlank() || username.isBlank() || confirmPassword.isBlank()) {
            errors.put("isBlank", "email or username or password or confirmPassword should not be null!");
        }
        User user = userService.getUserByEmail(email);
        if (user
                != null) {
            errors.put("email", "email has been used! ");
        }
        if (!password.equals(confirmPassword)) {
            errors.put("password", "password and confirm password are not nuch! ");

        }
        return errors;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
