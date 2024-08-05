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

@WebServlet(name = "InventoryServlet", urlPatterns = {"/inventory"})
public class InventoryServlet extends HttpServlet {

    private FoodService foodService;

    @Override
    public void init() throws ServletException {
        super.init();
        foodService = new FoodService(); // Initialize the service
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionUser = request.getParameter("actionUser");
        if (actionUser == null) {
            actionUser = "";
        }

        switch (actionUser) {
            case "add":
                performAdd (request, response);
                break;
            case "update":
                performUpdate (request, response);
                break;
            case "delete":
                performDelete (request, response);
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionUser = request.getParameter("actionUser");
        if (actionUser == null) {
            actionUser = "";
        }
        String expiration = request.getParameter("expiration");
        request.setAttribute("expiration",expiration);
        switch (actionUser) {
            case "add":
                showAddForm (request, response);
                break;
            case "update":
               showUpdateForm(request, response);
                break;
            default:
                try {
                    listFood (request, response,expiration);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/view/add.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
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
    private void listFood(HttpServletRequest request, HttpServletResponse response,String search)
            throws SQLException, ServletException, IOException {
        List<Food> foodList = foodService.getAllFoods(search);
        request.setAttribute("foodList", foodList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/inventory.jsp");
        dispatcher.forward(request, response);
    }
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
