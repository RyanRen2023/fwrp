package com.algonquin.cst8288.fwrps.service;

import com.algonquin.cst8288.fwrps.model.Food;
import java.util.List;


public interface FoodService {
    
    void createFood(Food food);
    Food getFoodById(int id);
    List<Food> getAllFoods();
    void updateFood(Food food);
    void deleteFood(int id);
    //boolean validateFood();
    
}
