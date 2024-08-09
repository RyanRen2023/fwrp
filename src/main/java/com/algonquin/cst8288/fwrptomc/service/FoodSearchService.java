package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.FoodSearchDao;
import com.algonquin.cst8288.fwrptomc.model.FoodSearch;
import java.util.List;

/**
 * Service class that handles operations related to food searches.
 * 
 * <p>
 * This service provides methods to retrieve and search for food items
 * from the food_search view. It interacts with the {@link FoodSearchDao}
 * to perform database operations.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     FoodSearchService searchService = new FoodSearchService();
 *     List&lt;FoodSearch&gt; results = searchService.searchFood("apple");
 * </pre>
 * </p>
 * 
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link FoodSearchDao}: Data access object for performing search operations on the food data.</li>
 * </ul>
 * </p>
 * 
 * @author Xihai Ren
 */
public class FoodSearchService {

    private FoodSearchDao foodSearchDao;

    /**
     * Constructs a new FoodSearchService and initializes the FoodSearchDao.
     */
    public FoodSearchService() {
        this.foodSearchDao = new FoodSearchDao();
    }

    /**
     * Retrieves all food items from the food_search view.
     *
     * @return a list of FoodSearch objects
     */
    public List<FoodSearch> getAllFoodSearch() {
        return foodSearchDao.getAllFoodSearch();
    }

    /**
     * Retrieves food items from the food_search view based on a search query.
     *
     * @param query the search query
     * @return a list of FoodSearch objects matching the query
     */
    public List<FoodSearch> searchFood(String query) {
        return foodSearchDao.searchFood(query);
    }

    /**
     * Searches for food items based on multiple criteria including search query, food type, price range, expiration, supplier, and location.
     *
     * @param searchQuery the search query
     * @param foodType    the type of food to filter by
     * @param priceRange  the price range to filter by
     * @param expiration  the expiration date to filter by
     * @param supplier    the supplier to filter by
     * @param location    the location to filter by
     * @return a list of FoodSearch objects matching the criteria
     */
    public List<FoodSearch> search(String searchQuery, String foodType, String priceRange, String expiration, String supplier, String location) {
        return foodSearchDao.search(searchQuery, foodType, priceRange, expiration, supplier, location);
    }
}