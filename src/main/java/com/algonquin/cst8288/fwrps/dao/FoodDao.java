
package com.algonquin.cst8288.fwrps.dao;

import com.algonquin.cst8288.fwrps.model.Food;
import java.util.List;

import com.algonquin.cst8288.fwrps.model.Food;
import java.util.List;

public interface FoodDao {
    void createFood(Food food);
    Food getFoodById(int id);
    List<Food> getAllFoods();
    void updateFood(Food food);
    void deleteFood(int id);
    
}