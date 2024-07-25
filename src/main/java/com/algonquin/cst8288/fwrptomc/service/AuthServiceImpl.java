/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;


import com.algonquin.cst8288.fwrptomc.model.User;
import com.algonquin.cst8288.fwrptomc.dao.UserDao;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author renxihai
 */
@SessionScoped
public class AuthServiceImpl implements AuthService, Serializable{
    
//    @Inject
    private UserDao userDao = new UserDao();

    private User loggedInUser;

    @Override
    public boolean login(String email, String password) {
         User user = userDao.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        loggedInUser = null;
    }

    @Override
    public User getLoggedInUser() {
                return loggedInUser;

    }

    @Override
    public boolean isUserLoggedIn() {
                return loggedInUser != null;

    }
    
}
