package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.FoodDao;
import com.algonquin.cst8288.fwrptomc.model.Food;

import java.util.List;

public class FoodService {

    FoodDao foodDao;

    public FoodService() {
        this.foodDao = new FoodDao(); // Initialize the DAO
    }

    public void add(Food food) {
        foodDao.add(food);
    }

    public void updateFood(Food food) {
        foodDao.update(food);
    }

    public void deleteFood(Food food) {
        foodDao.delete(food);
    }

    public Food getFoodById(int fid) {
        return foodDao.getFoodById(fid);
    }

    public List<Food> getAllFoods(String search) {
        return foodDao.getAllFoods(search);
    }

    public List<Food> getAllFoods() {
        return foodDao.getAllFoods();
    }

    public List<Food> getFoodForDonation() {
        return foodDao.getAllFoodsForDonation();
    }

    public List<Food> getFoodForPurchase() {
        return foodDao.getAllFoodsForPurchase();
    }

    public void markFoodAsSurplus(int foodId, int isSurplus, int isDonation) {
        foodDao.updateFoodSurplusStatus(foodId, isSurplus, isDonation);
    }

    public List<Food> getFoodsFromOrdersByUserId(int userId) {
        return foodDao.getFoodsFromOrdersByUserId(userId);
    }

    public List<Food> getFoodsFromClaimsByOrganizationId(int organizationId) {
        return foodDao.getFoodsFromClaimsByOrganizationId(organizationId);
    }
}
