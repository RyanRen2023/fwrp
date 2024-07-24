/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrps.service;

import com.algonquin.cst8288.fwrps.model.User;
import java.util.List;

/**
 *
 * @author renxihai
 */
public interface UserService {
    
    void createUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
    User getUserByEmail(String email);
    boolean validateUser(String email, String password);
    
}
