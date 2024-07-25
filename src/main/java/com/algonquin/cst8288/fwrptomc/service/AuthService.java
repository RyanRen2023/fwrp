/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.model.User;


/**
 *
 * @author renxihai
 */
public interface AuthService {
    
    boolean login(String email, String password);
    void logout();
    User getLoggedInUser();
    boolean isUserLoggedIn();
    
}
