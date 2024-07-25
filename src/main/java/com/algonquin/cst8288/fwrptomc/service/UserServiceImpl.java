/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;


import com.algonquin.cst8288.fwrptomc.model.User;
import com.algonquin.cst8288.fwrptomc.dao.UserDao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;

/**
 *
 * @author renxihai
 */
public class UserServiceImpl implements UserService{
    
//    @Inject
    private UserDao userDao = new UserDao();

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public void createUser(User user) {
        try {
            userDao.addUser(user);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating user: {0}", e.getMessage());
            // Handle or rethrow the exception as needed
        }
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        try {
            userDao.updateUser(user);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating user: {0}", e.getMessage());
            // Handle or rethrow the exception as needed
        }
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        try {
            userDao.deleteUser(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting user: {0}", e.getMessage());
            // Handle or rethrow the exception as needed
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public boolean validateUser(String email, String password) {
        User user = userDao.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
    
}
