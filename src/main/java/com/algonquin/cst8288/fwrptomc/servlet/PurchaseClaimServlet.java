/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.model.Claim;
import com.algonquin.cst8288.fwrptomc.model.Food;
import com.algonquin.cst8288.fwrptomc.model.Orders;
import com.algonquin.cst8288.fwrptomc.model.User;
import com.algonquin.cst8288.fwrptomc.service.ClaimService;
import com.algonquin.cst8288.fwrptomc.service.FoodService;
import com.algonquin.cst8288.fwrptomc.service.OrdersService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author renxihai
 */
@WebServlet(name = "PurchaseClaimServlet", urlPatterns = {"/purchase-claim"})
public class PurchaseClaimServlet extends HttpServlet {

    ClaimService claimService = new ClaimService();
    FoodService foodService = new FoodService();
    OrdersService ordersService = new OrdersService();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            return;
        }

        // Check user type and action
        if (currentUser.getUserType().equals("organization")) {
            if ("claim".equals(action)) {
                processClaim(request, response, currentUser);
            } else if ("viewClaims".equals(action)) {
                processViewClaims(request, response, currentUser.getUid());
            } else {
                processDonation(request, response);
            }
        } else if (currentUser.getUserType().equals("consumer")) {
            if ("purchase".equals(action)) {
                processPurchase(request, response, currentUser);
            } else if ("viewPurchase".equals(action)) {
                processViewPurchase(request, response, currentUser.getUid());
            } else {
                processAvailableFood(request, response);
            }
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid user type.");
        }
    }

    private void processViewPurchase(HttpServletRequest request, HttpServletResponse response, int uid) throws IOException {
        List<Orders> orders = ordersService.getAllOrdersByUserId(uid);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonSerializer<LocalDate> serializer = (src, typeOfSrc, context)
                -> src == null ? null : context.serialize(src.format(DateTimeFormatter.ISO_LOCAL_DATE));
        JsonDeserializer<LocalDate> deserializer = (json, typeOfT, context)
                -> json == null ? null : LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, serializer)
                .registerTypeAdapter(LocalDate.class, deserializer)
                .create();
        response.getWriter().write(gson.toJson(orders));
    }

    private void processViewClaims(HttpServletRequest request, HttpServletResponse response, int oid) throws IOException {
        List<Claim> claims = claimService.getClaimsByOrganizationId(oid);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonSerializer<LocalDate> serializer = (src, typeOfSrc, context)
                -> src == null ? null : context.serialize(src.format(DateTimeFormatter.ISO_LOCAL_DATE));
        JsonDeserializer<LocalDate> deserializer = (json, typeOfT, context)
                -> json == null ? null : LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, serializer)
                .registerTypeAdapter(LocalDate.class, deserializer)
                .create();
        response.getWriter().write(gson.toJson(claims));
    }

    private void processDonation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Food> foodList = foodService.getFoodForDonation();
        request.setAttribute("foodList", foodList);
        request.getRequestDispatcher("/jsp/purchase-claim.jsp").forward(request, response);
    }

    private void processAvailableFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Food> foodList = foodService.getFoodForPurchase();
        request.setAttribute("foodList", foodList);
        request.getRequestDispatcher("/jsp/purchase-claim.jsp").forward(request, response);
    }

    private void processClaim(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        int foodId = Integer.parseInt(request.getParameter("foodId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Claim claim = new Claim(foodId, quantity, user.getUid());
        claimService.addClaim(claim);
        Food food = foodService.getFoodById(foodId);
        int newInventory = food.getInventory() - quantity;
        food.setInventory(newInventory);
        foodService.updateFood(food);

        processDonation(request, response);
    }

    private void processPurchase(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        int foodId = Integer.parseInt(request.getParameter("purchase-foodId"));
        Food food = foodService.getFoodById(foodId);
        Orders order = new Orders();
        BigDecimal price = food.getPrice();
        BigDecimal discount = BigDecimal.valueOf(food.getDiscount());
        BigDecimal quantity = BigDecimal.valueOf(Integer.parseInt(request.getParameter("purchase-quantity")));

        BigDecimal discountedPrice = price.multiply(BigDecimal.ONE.subtract(discount));
        BigDecimal money = discountedPrice.multiply(quantity);
        
        order.setFid(foodId);
        order.setMoney(money);
        order.setNum(quantity.intValue());
        order.setUid(user.getUid());
        ordersService.addOrder(order);
        
        int newInventory = food.getInventory() - quantity.intValue();
        food.setInventory(newInventory);
        foodService.updateFood(food);
        
        

        // Add purchase logic here, similar to claim process
//        foodService.updateInventoryAfterPurchase(foodId);
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
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
        return "Short description";
    }// </editor-fold>
}
