package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.model.Food;
import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedback;
import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedbackView;
import com.algonquin.cst8288.fwrptomc.model.User;
import com.algonquin.cst8288.fwrptomc.service.FoodService;
import com.algonquin.cst8288.fwrptomc.service.RatingAndFeedbackService;
import com.algonquin.cst8288.fwrptomc.service.RatingAndFeedbackViewService;
import com.google.gson.Gson;
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

@WebServlet(name = "FeedbackServlet", urlPatterns = {"/feedback"})
public class FeedbackServlet extends HttpServlet {

    private RatingAndFeedbackService feedbackService;
    private FoodService foodService;
    private RatingAndFeedbackViewService viewService;

    @Override
    public void init() throws ServletException {
        super.init();
        feedbackService = new RatingAndFeedbackService();
        foodService = new FoodService();
        viewService = new RatingAndFeedbackViewService();
    }

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

    private void handleFeedbackSubmission(HttpServletRequest request, HttpServletResponse response, User user)
            throws IOException, ServletException {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            int foodId = Integer.parseInt(request.getParameter("foodItem"));
            int rating = Integer.parseInt(request.getParameter("rating"));
            String comments = request.getParameter("comments");

            RatingAndFeedback feedback = new RatingAndFeedback();
            feedback.setFoodID(foodId);
            feedback.setRating(rating);
            feedback.setReview(comments);
            feedback.setUserID(user.getUid());
            feedback.setCreatedAt(LocalDateTime.now());
            feedbackService.addRatingAndFeedback(feedback);

            // Fetch the food item's name
            FoodService foodService = new FoodService();
            Food food = foodService.getFoodById(foodId);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("status", "success");
            jsonResponse.addProperty("foodName", food.getFname()); // Include food name in the response
            out.print(jsonResponse);
            out.flush();

        } else {
            List<Food> items = null;
            if ("consumer".equals(user.getUserType())) {
                items = foodService.getFoodsFromOrdersByUserId(user.getUid());
            } else if ("organization".equals(user.getUserType())) {
                items = foodService.getFoodsFromClaimsByOrganizationId(user.getUid());
            }
            request.setAttribute("items", items);
            request.getRequestDispatcher("/jsp/feedback.jsp").forward(request, response);
        }
    }

    private void handleFeedbackViewing(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        List<RatingAndFeedbackView> feedbackList = viewService.getRatingAndFeedbackByRetailerId(user.getUid());
        request.setAttribute("feedbackList", feedbackList);
        request.getRequestDispatcher("/jsp/feedbackList.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "FeedbackServlet";
    }
}
