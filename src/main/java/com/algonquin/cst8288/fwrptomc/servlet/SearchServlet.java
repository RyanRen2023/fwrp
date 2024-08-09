package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.model.FoodSearch;
import com.algonquin.cst8288.fwrptomc.model.FoodType;
import com.algonquin.cst8288.fwrptomc.service.FoodSearchService;
import com.algonquin.cst8288.fwrptomc.service.FoodTypeService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that handles search requests for food items.
 * 
 * <p>
 * This servlet processes both GET and POST requests to perform searches for food items
 * based on various criteria such as food type, price range, expiration date, supplier, and location.
 * </p>
 * 
 * <p>
 * URL Patterns:
 * <ul>
 * <li>/search</li>
 * </ul>
 * </p>
 * 
 * @author Xihai Ren
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

    private FoodSearchService foodSearchService = new FoodSearchService();
    private FoodTypeService foodTypeService = new FoodTypeService();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("search".equals(action)) {
            // Perform search
            performSearch(request, response);
        } else {
            // Show search page
            List<FoodType> foodTypeList = foodTypeService.getAllFoodTypes();
            request.setAttribute("foodTypeList", foodTypeList);

            request.setAttribute("searchPage", "searchPage");
            request.getRequestDispatcher("/jsp/search.jsp").forward(request, response);
        }
    }

    /**
     * Performs the search for food items based on the criteria provided in the request.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    private void performSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get search parameters
        String searchQuery = request.getParameter("searchQuery");
        String foodType = request.getParameter("foodType");
        String priceRange = request.getParameter("priceRange");
        String expiration = request.getParameter("expiration");
        String supplier = request.getParameter("supplier");
        String location = request.getParameter("location");

        List<FoodType> foodTypeList = foodTypeService.getAllFoodTypes();
        request.setAttribute("foodTypeList", foodTypeList);

        // Perform search using FoodSearchService
        List<FoodSearch> searchResults = foodSearchService.search(searchQuery, foodType, priceRange, expiration, supplier, location);
        
        // Set search results as request attribute and forward to JSP
        request.setAttribute("searchResults", searchResults);
        request.getRequestDispatcher("/jsp/search.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method by calling the processRequest method.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method by calling the processRequest method.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
        return "Servlet that handles food item searches based on various criteria.";
    }
}