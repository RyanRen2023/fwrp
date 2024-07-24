package com.algonquin.cst8288.fwrps.service;

import com.algonquin.cst8288.fwrps.dao.FoodDao;
import com.algonquin.cst8288.fwrps.model.Food;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FoodServiceImpl implements FoodService{
    
    @Inject
    private FoodDao foodDao;

    private static final Logger LOGGER = Logger.getLogger(FoodServiceImpl.class.getName());

    @Override
    @Transactional
    public void createFood(Food food) {
        try {
            foodDao.createFood(food);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating food: {0}", e.getMessage());
            // Handle or rethrow the exception as needed
        }
    }

    @Override
    public Food getFoodById(int id) {
        return foodDao.getFoodById(id);
    }

    @Override
    public List<Food> getAllFoods() {
        return foodDao.getAllFoods();
    }

    @Override
    @Transactional
    public void updateFood(Food food) {
        try {
            foodDao.updateFood(food);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating food: {0}", e.getMessage());
            // Handle or rethrow the exception as needed
        }
    }

    @Override
    @Transactional
    public void deleteFood(int id) {
        try {
            foodDao.deleteFood(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting food: {0}", e.getMessage());
            // Handle or rethrow the exception as needed
        }
    }

    
}
