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
 * Servlet that handles user sign-up requests.
 *
 * <p>
 * This servlet processes both GET and POST requests to register a new user. It
 * validates the user input, checks for existing email addresses, and creates a
 * new user account if the input is valid.
 * </p>
 *
 * <p>
 * URL Patterns:
 * <ul>
 * <li>/sign-up</li>
 * </ul>
 * </p>
 *
 * @author Xihai Ren
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/sign-up"})
public class SignUpServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * Handles the HTTP POST method to process user sign-up requests.
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

    /**
     * Parses a JSON object from the request body.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @return a JsonObject containing the parsed JSON data
     * @throws IOException if an I/O error occurs while reading the request body
     * @throws JsonSyntaxException if the JSON data is not well-formed
     */
    private JsonObject requestJsonObj(HttpServletRequest request) throws IOException, JsonSyntaxException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonData = sb.toString();
        return JsonParser.parseString(jsonData).getAsJsonObject();
    }

    /**
     * Validates the sign-up request, checking for missing fields, email
     * duplication, and password confirmation mismatch.
     *
     * @param email the email address provided by the user
     * @param password the password provided by the user
     * @param username the username provided by the user
     * @param confirmPassword the confirmation password provided by the user
     * @return a Map containing error messages if validation fails, or an empty
     * Map if validation passes
     */
    private Map<String, String> validateRequest(String email, String password, String username, String confirmPassword) {

        Map<String, String> errors = new HashMap<>();

        if (email.isBlank() || password.isBlank() || username.isBlank() || confirmPassword.isBlank()) {
            errors.put("isBlank", "Email, username, password, or confirm password should not be null!");
        }
        User user = userService.getUserByEmail(email);
        if (user != null) {
            errors.put("email", "Email has been used! ");
        }
        if (!password.equals(confirmPassword)) {
            errors.put("password", "Password and confirm password do not match!");
        }
        return errors;
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
