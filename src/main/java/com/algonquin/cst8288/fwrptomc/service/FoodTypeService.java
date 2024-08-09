package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.FoodTypeDao;
import com.algonquin.cst8288.fwrptomc.model.FoodType;
import java.util.List;

/**
 * Service class that handles operations related to food types.
 * 
 * <p>
 * This service provides methods for adding, updating, deleting, and retrieving
 * food types. It interacts with the {@link FoodTypeDao} to perform database operations.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     FoodTypeService foodTypeService = new FoodTypeService();
 *     List&lt;FoodType&gt; allFoodTypes = foodTypeService.getAllFoodTypes();
 * </pre>
 * </p>
 * 
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link FoodTypeDao}: Data access object for performing operations on food type data.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * The class interacts with the {@link FoodTypeDao} to perform all database operations
 * related to food types.
 * </p>
 * 
 * @author Xihai Ren
 */
public class FoodTypeService {

    private FoodTypeDao foodTypeDao;

    /**
     * Constructs a new FoodTypeService and initializes the FoodTypeDao.
     */
    public FoodTypeService() {
        this.foodTypeDao = new FoodTypeDao();
    }

    /**
     * Adds a new food type.
     *
     * @param foodType the food type to be added
     */
    public void addFoodType(FoodType foodType) {
        foodTypeDao.addFoodType(foodType);
    }

    /**
     * Updates an existing food type.
     *
     * @param foodType the food type to be updated
     */
    public void updateFoodType(FoodType foodType) {
        foodTypeDao.updateFoodType(foodType);
    }

    /**
     * Deletes a food type by its ID.
     *
     * @param id the ID of the food type to be deleted
     */
    public void deleteFoodType(int id) {
        foodTypeDao.deleteFoodType(id);
    }

    /**
     * Retrieves a food type by its ID.
     *
     * @param id the ID of the food type to be retrieved
     * @return the FoodType object corresponding to the specified ID
     */
    public FoodType getFoodTypeById(int id) {
        return foodTypeDao.getFoodTypeById(id);
    }

    /**
     * Retrieves all food types.
     *
     * @return a list of all FoodType objects
     */
    public List<FoodType> getAllFoodTypes() {
        return foodTypeDao.getAllFoodTypes();
    }
}