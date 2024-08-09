package com.algonquin.cst8288.fwrptomc.model;

import java.time.LocalDateTime;

/**
 * Represents a user in the Food Waste Reduction Platform.
 *
 * <p>
 * This class captures the essential details of a user, including their unique
 * ID, name, email, password, user type (such as retailer or consumer), and the
 * timestamp of their last login.
 * </p>
 *
 * Author: Xihai Ren
 */
public class User {

    private int uid;
    private String name;
    private String email;
    private String password;
    private String userType;
    private LocalDateTime lastLogin;

    public User() {
    }

    public User(String name, String email, String password, String userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    // Getters and Setters
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
}
