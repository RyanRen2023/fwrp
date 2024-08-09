package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.model.Food;
import com.algonquin.cst8288.fwrptomc.service.FoodService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Servlet for handling inventory management. This servlet processes both GET
 * and POST requests to manage food inventory, including adding, updating, and
 * deleting food items.
 *
 * <p>
 * URL Patterns:
 * <ul>
 * <li>/inventory</li>
 * </ul>
 * </p>
 *
 * @author Alexis Trinh
 */
@WebServlet(name = "InventoryServlet", urlPatterns = {"/inventory"})
public class InventoryServlet extends HttpServlet {

    private FoodService foodService;

    /**
     * Initializes the servlet and sets up the FoodService.
     *
     * @throws ServletException if an error occurs during initialization
     */
    @Override
    public void init() throws ServletException {
        super.init();
        foodService = new FoodService(); // Initialize the service
    }

    /**
     * Processes HTTP POST requests. Depending on the "actionUser" parameter, it
     * can add, update, or delete food items.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionUser = request.getParameter("actionUser");
        if (actionUser == null) {
            actionUser = "";
        }

        switch (actionUser) {
            case "add":
                performAdd(request, response);
                break;
            case "update":
                performUpdate(request, response);
                break;
            case "delete":
                performDelete(request, response);
                break;
            default:
                break;
        }
    }

    /**
     * Processes HTTP GET requests. Depending on the "actionUser" parameter, it
     * can show forms for adding or updating food items, or list food items
     * based on expiration date.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionUser = request.getParameter("actionUser");
        if (actionUser == null) {
            actionUser = "";
        }
        String expiration = request.getParameter("expiration");
        request.setAttribute("expiration", expiration);
        switch (actionUser) {
            case "add":
                showAddForm(request, response);
                break;
            case "update":
                showUpdateForm(request, response);
                break;
            default:
                try {
                    listFood(request, response, expiration);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    /**
     * Displays the form for adding a new food item.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     */
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/jsp/add.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a food item from the inventory based on the food ID.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     */
    private void performDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("fid"));
        Food food = foodService.getFoodById(id);
        foodService.deleteFood(food);
        try {
            response.sendRedirect("inventory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new food item to the inventory.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     */
    private void performAdd(HttpServletRequest request, HttpServletResponse response) {
        String fname = request.getParameter("fname");
        String expirationStr = request.getParameter("expiration");
        String priceStr = request.getParameter("price");
        String inventoryStr = request.getParameter("inventory");
        String discountStr = request.getParameter("discount");
        String ftidStr = request.getParameter("ftid");
        String isDonateStr = request.getParameter("is_donate");
        String storeIdStr = request.getParameter("store_id");
        LocalDate expiration = LocalDate.parse(expirationStr);
        BigDecimal price = new BigDecimal(priceStr);
        int inventory = Integer.parseInt(inventoryStr);
        Double discount = Double.parseDouble(discountStr);
        int ftid = Integer.parseInt(ftidStr);
        int isDonate = Integer.parseInt(isDonateStr);
        int storeId = Integer.parseInt(storeIdStr);
        Food food = new Food(fname, expiration, price, inventory, discount, ftid, isDonate, storeId);
        foodService.add(food);
        try {
            response.sendRedirect("inventory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lists all food items in the inventory, optionally filtered by expiration
     * date.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     * @param search the search criteria for filtering food items by expiration
     * date
     * @throws SQLException if a database access error occurs
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void listFood(HttpServletRequest request, HttpServletResponse response, String search)
            throws SQLException, ServletException, IOException {
        List<Food> foodList = foodService.getAllFoods(search);
        request.setAttribute("foodList", foodList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/inventory.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Displays the form for updating an existing food item.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Food food = foodService.getFoodById(id);
        request.setAttribute("food", food);
        try {
            request.getRequestDispatcher("/jsp/update.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing food item in the inventory.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made to the servlet
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns to the client
     * @throws IOException if an I/O error occurs
     */
    private void performUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int fid = Integer.parseInt(request.getParameter("fid"));
        String fname = request.getParameter("fname");
        String expirationStr = request.getParameter("expiration");
        String priceStr = request.getParameter("price");
        String inventoryStr = request.getParameter("inventory");
        String discountStr = request.getParameter("discount");
        String ftidStr = request.getParameter("ftid");
        String isDonateStr = request.getParameter("is_donate");
        String storeIdStr = request.getParameter("store_id");

        LocalDate expiration = LocalDate.parse(expirationStr);
        BigDecimal price = new BigDecimal(priceStr);
        int inventory = Integer.parseInt(inventoryStr);
        Double discount = Double.parseDouble(discountStr);
        int ftid = Integer.parseInt(ftidStr);
        int isDonate = Integer.parseInt(isDonateStr);
        int storeId = Integer.parseInt(storeIdStr);

        Food food = new Food(fid, fname, expiration, price, inventory, discount, ftid, isDonate, storeId);

        try {
            foodService.updateFood(food);
            response.sendRedirect("inventory");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
