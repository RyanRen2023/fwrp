package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.FoodSearchDao;
import com.algonquin.cst8288.fwrptomc.model.FoodSearch;
import java.util.List;

public class FoodSearchService {

    private FoodSearchDao foodSearchDao;

    public FoodSearchService() {
        this.foodSearchDao = new FoodSearchDao();
    }

    /**
     * Retrieve all food items from the food_search view
     *
     * @return a list of FoodSearch objects
     */
    public List<FoodSearch> getAllFoodSearch() {
        return foodSearchDao.getAllFoodSearch();
    }

    /**
     * Retrieve food items from the food_search view based on a search query
     *
     * @param query the search query
     * @return a list of FoodSearch objects
     */
    public List<FoodSearch> searchFood(String query) {
        return foodSearchDao.searchFood(query);
    }
}