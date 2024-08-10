package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.model.Food;
import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedback;
import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedbackView;
import com.algonquin.cst8288.fwrptomc.model.User;
import com.algonquin.cst8288.fwrptomc.service.FoodService;
import com.algonquin.cst8288.fwrptomc.service.RatingAndFeedbackService;
import com.algonquin.cst8288.fwrptomc.service.RatingAndFeedbackViewService;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Servlet for handling feedback requests. This servlet processes both GET and
 * POST requests for submitting and viewing feedback.
 *
 * <p>
 * Depending on the user type (consumer, organization, or retailer), the servlet
 * forwards requests to the appropriate JSP page for rendering or handles
 * feedback submission.
 * </p>
 *
 * <p>
 * URL Patterns:
 * <ul>
 * <li>/feedback</li>
 * </ul>
 * </p>
 *
 * @author Sam
 */
@WebServlet(name = "FeedbackServlet", urlPatterns = {"/feedback"})
public class FeedbackServlet extends HttpServlet {

    private RatingAndFeedbackService feedbackService;
    private FoodService foodService;
    private RatingAndFeedbackViewService viewService;

    /**
     * Initializes the servlet and sets up the services.
     *
     * @throws ServletException if an error occurs during initialization
     */
    @Override
    public void init() throws ServletException {
        super.init();
        feedbackService = new RatingAndFeedbackService();
        foodService = new FoodService();
        viewService = new RatingAndFeedbackViewService();
    }

    /**
     * Processes requests for both HTTP GET and POST methods.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        String userType = loggedInUser.getUserType();
        if ("retailer".equals(userType)) {
            handleFeedbackViewing(request, response, loggedInUser);
        } else {
            handleFeedbackSubmission(request, response, loggedInUser);
        }
    }

    /**
     * Handles feedback submission for consumers and organizations.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     * @param user the User object representing the logged-in user
     * @throws IOException if an I/O error occurs
     * @throws ServletException if a servlet-specific error occurs
     */
    private void handleFeedbackSubmission(HttpServletRequest request, HttpServletResponse response, User user)
            throws IOException, ServletException {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            int idForFood = Integer.parseInt(request.getParameter("foodItem"));
            int rating = Integer.parseInt(request.getParameter("rating"));
            String comments = request.getParameter("comments");

            RatingAndFeedback ratingsAndFeedback = new RatingAndFeedback();
            ratingsAndFeedback.setFoodID(idForFood);
            ratingsAndFeedback.setRating(rating);
            ratingsAndFeedback.setReview(comments);
            ratingsAndFeedback.setUserID(user.getUid());
            ratingsAndFeedback.setCreatedAt(LocalDateTime.now());
            feedbackService.addRatingAndFeedback(ratingsAndFeedback);

            // Fetch the food item's name
            Food foodItem = foodService.getFoodById(idForFood);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("status", "success");
            jsonResponse.addProperty("foodName", foodItem.getFname()); // Include food name in the response
            out.print(jsonResponse);
            out.flush();

        } else {
            List<Food> FoodItems = null;
            if ("consumer".equals(user.getUserType())) {
                FoodItems = foodService.getFoodsFromOrdersByUserId(user.getUid());
            } else if ("organization".equals(user.getUserType())) {
                FoodItems = foodService.getFoodsFromClaimsByOrganizationId(user.getUid());
            }
            request.setAttribute("items", FoodItems);
            request.getRequestDispatcher("/jsp/feedback.jsp").forward(request, response);
        }
    }

    /**
     * Handles feedback viewing for retailers.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     * @param user the User object representing the logged-in user
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void handleFeedbackViewing(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        List<RatingAndFeedbackView> feedbackList = viewService.getRatingAndFeedbackByRetailerId(user.getUid());
        request.setAttribute("feedbackList", feedbackList);
        request.getRequestDispatcher("/jsp/feedbackList.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP GET method by calling processRequest method.
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP POST method by calling processRequest method.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "FeedbackServlet handles requests for submitting and viewing feedback.";
    }
}
