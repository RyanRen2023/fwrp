package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.FoodDao;
import com.algonquin.cst8288.fwrptomc.model.Food;
import java.util.List;

public class FoodService {

    private FoodDao foodDao;

    public FoodService() {
        this.foodDao = new FoodDao();
    }

    /**
     * Add a new food item
     *
     * @param food the food item to be added
     */
    public void addFood(Food food) {
        foodDao.addFood(food);
    }

    /**
     * Update an existing food item
     *
     * @param food the food item to be updated
     */
    public void updateFood(Food food) {
        foodDao.updateFood(food);
    }

    /**
     * Delete a food item by its ID
     *
     * @param fid the food ID of the item to be deleted
     */
    public void deleteFood(int fid) {
        foodDao.deleteFood(fid);
    }

    /**
     * Retrieve a food item by its ID
     *
     * @param fid the food ID of the item to be retrieved
     * @return the Food object
     */
    public Food getFoodById(int fid) {
        return foodDao.getFoodById(fid);
    }

    /**
     * Retrieve all food items
     *
     * @return a list of Food objects
     */
    public List<Food> getAllFoods() {
        return foodDao.getAllFoods();
    }
}
