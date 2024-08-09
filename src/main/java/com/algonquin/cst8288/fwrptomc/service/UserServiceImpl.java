package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.model.User;
import com.algonquin.cst8288.fwrptomc.dao.UserDao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;

/**
 * Implementation of the {@link UserService} interface that provides business
 * logic for user management.
 *
 * <p>
 * This class handles the creation, retrieval, update, and deletion of users, as
 * well as validation of user credentials. It interacts with the {@link UserDao}
 * to perform database operations. Exceptions during database operations are
 * logged.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     UserServiceImpl userService = new UserServiceImpl();
 *     userService.createUser(new User("John Doe", "john.doe@example.com", "password"));
 * </pre>
 * </p>
 *
 * <p>
 * The class uses the `javax.transaction.Transactional` annotation to manage
 * transactions for update and delete operations.
 * </p>
 *
 * <p>
 * This class also includes basic error handling by logging exceptions that
 * occur during database operations.
 * </p>
 *
 * @author Xihai Ren
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDao();

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    /**
     * Creates a new user.
     *
     * @param user the user to be created
     */
    @Override
    public void createUser(User user) {
        try {
            userDao.addUser(user);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating user: {0}", e.getMessage());
            // Handle or rethrow the exception as needed
        }
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to be retrieved
     * @return the User object corresponding to the specified ID
     */
    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all User objects
     */
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    /**
     * Updates an existing user. This method is transactional.
     *
     * @param user the user to be updated
     */
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

    /**
     * Deletes a user by their ID. This method is transactional.
     *
     * @param id the ID of the user to be deleted
     */
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

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user to be retrieved
     * @return the User object corresponding to the specified email address
     */
    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    /**
     * Validates a user's credentials.
     *
     * @param email the email address of the user
     * @param password the password of the user
     * @return true if the credentials are valid, false otherwise
     */
    @Override
    public boolean validateUser(String email, String password) {
        User user = userDao.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
