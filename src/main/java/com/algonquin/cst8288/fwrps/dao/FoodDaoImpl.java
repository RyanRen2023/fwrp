package com.algonquin.cst8288.fwrps.dao;

import com.algonquin.cst8288.fwrps.model.Food;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;


@Dependent
public class FoodDaoImpl implements FoodDao, Serializable {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private transient EntityManager em;

    @Override
    public void createFood(Food food) {
        em.persist(food);
    }

    @Override
    public Food getFoodById(int id) {
        return em.find(Food.class, id);
    }

    @Override
    public List<Food> getAllFoods() {
        TypedQuery<Food> query = em.createNamedQuery("Food.findAll", Food.class);
        return query.getResultList();
    }

    @Override
    public void updateFood(Food food) {
        em.merge(food);
    }

    @Override
    public void deleteFood(int id) {
        Food food = em.find(Food.class, id);
        if (food != null) {
            em.remove(food);
        }
    }
    
   
    
}
