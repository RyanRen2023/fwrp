package com.algonquin.cst8288.fwrptomc.servlet;

import com.algonquin.cst8288.fwrptomc.model.Food;
import com.algonquin.cst8288.fwrptomc.model.Subscribe;
import com.algonquin.cst8288.fwrptomc.service.FoodService;
import com.algonquin.cst8288.fwrptomc.service.NotificationService;
import com.algonquin.cst8288.fwrptomc.service.SubscribeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "SurplusFoodServlet", urlPatterns = {"/surplus-food"})
public class SurplusFoodServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(SurplusFoodServlet.class);

    private FoodService foodService;
    private NotificationService noticeService;
    private SubscribeService subscribeService;
    private ExecutorService executorService;

    @Override
    public void init() throws ServletException {
        super.init();
        foodService = new FoodService(); // Initialize the service
        noticeService = new NotificationService();
        subscribeService = new SubscribeService();
        executorService = Executors.newFixedThreadPool(10);
        logger.info("SurplusFoodServlet initialized.");

    }

    @Override
    public void destroy() {
        super.destroy();
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    logger.error("ExecutorService did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            logger.error("ExecutorService interrupted during shutdown", ie);
        }
        logger.info("SurplusFoodServlet destroyed.");
    }

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
        if (null == action || action.isBlank()) {
            request.getRequestDispatcher("/jsp/surplus-food.jsp").forward(request, response);
        } else if (action.equals("identify")) {
            this.performIdentify(request, response);
        } else if (action.equals("list")) {
            this.performShowList(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void performShowList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch the list of food items from the service
        List<Food> foodList = foodService.getAllFoods();
        logger.debug("Fetched food list: {}", foodList);
        // Create Gson instance with LocalDate handling
        JsonSerializer<LocalDate> serializer = (src, typeOfSrc, context) -> src == null ? null : context.serialize(src.format(DateTimeFormatter.ISO_LOCAL_DATE));
        JsonDeserializer<LocalDate> deserializer = (json, typeOfT, context) -> json == null ? null : LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, serializer)
                .registerTypeAdapter(LocalDate.class, deserializer)
                .create();

        // Convert the list to JSON
        String jsonFoodList = gson.toJson(foodList);

        // Set the list as a response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonFoodList);
    }

    public void performIdentify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int foodId = Integer.parseInt(request.getParameter("foodId"));
        String isSurplus = request.getParameter("isSurplus");
        String isDonate = request.getParameter("isDonate");
        int donate = 0;
        int surplus = 0;
        if (isDonate != null && isDonate.equals("on")) {
            donate = 1;
        }
        if (isSurplus != null && isSurplus.equals("on")) {
            surplus = 1;
        }
        foodService.markFoodAsSurplus(foodId, surplus, donate);
        logger.info("Marked food as surplus: foodId={}, surplus={}, donate={}", foodId, surplus, donate);
        if (surplus == 1) {
//            executorService.submit(() -> performNotification(foodId));
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("status", "success");
        responseJson.addProperty("message", "Inventory update successful!");

        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(responseJson));
        out.flush();
    }

    protected void performNotification(int foodId) {
        logger.debug("Performing notification for foodId={}", foodId);
        List<Subscribe> subscribeList = subscribeService.searchSubscribesByAlertType("surplus");
        if (subscribeList != null && !subscribeList.isEmpty()) {
            subscribeList.forEach((subscribe) -> {
                Food food = foodService.getFoodById(foodId);
                String subject = "New Surplus Notification";
                String messageText = String.format("A new surplus food item has been listed: \n%s", food.toString());
                noticeService.sendNotification(subscribe, subject, messageText);
                logger.info("Notification sent to subscriber: {}", subscribe.getEmail());
            });
        } else {
            logger.info("No subscrbers");
        }

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
        return "Short description";
    }
}
