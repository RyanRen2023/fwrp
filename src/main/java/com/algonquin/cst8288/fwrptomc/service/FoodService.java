package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.FoodDao;
import com.algonquin.cst8288.fwrptomc.model.Food;

import java.util.List;

/**
 * Service class that handles operations related to food items.
 *
 * <p>
 * This service provides methods for adding, updating, deleting, and retrieving
 * food items. It also includes methods for managing food donations, purchases,
 * and surplus status.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     FoodService foodService = new FoodService();
 *     List&lt;Food&gt; allFoods = foodService.getAllFoods();
 * </pre>
 * </p>
 *
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link FoodDao}: Data access object for performing operations on food
 * data.</li>
 * </ul>
 * </p>
 *
 * <p>
 * The class interacts with the {@link FoodDao} to perform all database
 * operations related to food items.
 * </p>
 *
 * @author Xihai Ren
 */
public class FoodService {

    private FoodDao foodDao;

    /**
     * Constructs a new FoodService and initializes the FoodDao.
     */
    public FoodService() {
        this.foodDao = new FoodDao(); // Initialize the DAO
    }

    /**
     * Adds a new food item.
     *
     * @param food the food item to be added
     */
    public void add(Food food) {
        foodDao.add(food);
    }

    /**
     * Updates an existing food item.
     *
     * @param food the food item to be updated
     */
    public void updateFood(Food food) {
        foodDao.update(food);
    }

    /**
     * Deletes an existing food item.
     *
     * @param food the food item to be deleted
     */
    public void deleteFood(Food food) {
        foodDao.delete(food);
    }

    /**
     * Retrieves a food item by its ID.
     *
     * @param fid the ID of the food item to be retrieved
     * @return the Food object corresponding to the specified ID
     */
    public Food getFoodById(int fid) {
        return foodDao.getFoodById(fid);
    }

    /**
     * Retrieves all food items that match a specific search query.
     *
     * @param search the search query used to filter the food items
     * @return a list of Food objects that match the search criteria
     */
    public List<Food> getAllFoods(String search) {
        return foodDao.getAllFoods(search);
    }

    /**
     * Retrieves all food items.
     *
     * @return a list of all Food objects
     */
    public List<Food> getAllFoods() {
        return foodDao.getAllFoods();
    }

    /**
     * Retrieves all food items available for donation.
     *
     * @return a list of Food objects available for donation
     */
    public List<Food> getFoodForDonation() {
        return foodDao.getAllFoodsForDonation();
    }

    /**
     * Retrieves all food items available for purchase.
     *
     * @return a list of Food objects available for purchase
     */
    public List<Food> getFoodForPurchase() {
        return foodDao.getAllFoodsForPurchase();
    }

    /**
     * Marks a food item as surplus or for donation.
     *
     * @param foodId the ID of the food item to be marked
     * @param isSurplus the surplus status (1 for surplus, 0 otherwise)
     * @param isDonation the donation status (1 for donation, 0 otherwise)
     */
    public void markFoodAsSurplus(int foodId, int isSurplus, int isDonation) {
        foodDao.updateFoodSurplusStatus(foodId, isSurplus, isDonation);
    }

    /**
     * Retrieves food items associated with a user's orders.
     *
     * @param userId the ID of the user
     * @return a list of Food objects associated with the user's orders
     */
    public List<Food> getFoodsFromOrdersByUserId(int userId) {
        return foodDao.getFoodsFromOrdersByUserId(userId);
    }

    /**
     * Retrieves food items associated with an organization's claims.
     *
     * @param organizationId the ID of the organization
     * @return a list of Food objects associated with the organization's claims
     */
    public List<Food> getFoodsFromClaimsByOrganizationId(int organizationId) {
        return foodDao.getFoodsFromClaimsByOrganizationId(organizationId);
    }
}
