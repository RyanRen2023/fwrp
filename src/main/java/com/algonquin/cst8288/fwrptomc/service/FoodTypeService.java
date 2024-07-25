package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.FoodTypeDao;
import com.algonquin.cst8288.fwrptomc.model.FoodType;
import java.util.List;

public class FoodTypeService {

    private FoodTypeDao foodTypeDao;

    public FoodTypeService() {
        this.foodTypeDao = new FoodTypeDao();
    }

    /**
     * Add a new food type
     *
     * @param foodType the food type to be added
     */
    public void addFoodType(FoodType foodType) {
        foodTypeDao.addFoodType(foodType);
    }

    /**
     * Update an existing food type
     *
     * @param foodType the food type to be updated
     */
    public void updateFoodType(FoodType foodType) {
        foodTypeDao.updateFoodType(foodType);
    }

    /**
     * Delete a food type by its ID
     *
     * @param id the food type ID of the item to be deleted
     */
    public void deleteFoodType(int id) {
        foodTypeDao.deleteFoodType(id);
    }

    /**
     * Retrieve a food type by its ID
     *
     * @param id the food type ID of the item to be retrieved
     * @return the FoodType object
     */
    public FoodType getFoodTypeById(int id) {
        return foodTypeDao.getFoodTypeById(id);
    }

    /**
     * Retrieve all food types
     *
     * @return a list of FoodType objects
     */
    public List<FoodType> getAllFoodTypes() {
        return foodTypeDao.getAllFoodTypes();
    }
}