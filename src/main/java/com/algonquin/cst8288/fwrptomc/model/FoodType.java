package com.algonquin.cst8288.fwrptomc.model;

/**
 * Represents a food type in the system.
 * 
 * <p>
 * This class holds information about a food type, including its ID and name.
 * </p>
 * 
 * <p>
 * The {@code FoodType} class provides getters and setters for these attributes,
 * allowing easy access and modification of the data.
 * </p>
 * 
 * Author: Xihai Ren
 */
public class FoodType {
    private int id;
    private String name;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}