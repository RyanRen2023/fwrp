package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.model.User;
import com.algonquin.cst8288.fwrptomc.dao.UserDao;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

/**
 * Implementation of the {@link AuthService} interface for handling user authentication.
 * 
 * <p>
 * This class manages the login, logout, and session handling for users within the application.
 * It stores the currently logged-in user in the session context.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     AuthService authService = new AuthServiceImpl();
 *     if (authService.login("user@example.com", "password")) {
 *         User loggedInUser = authService.getLoggedInUser();
 *         // Proceed with authenticated operations
 *     }
 * </pre>
 * </p>
 * 
 * <p>
 * The class is session-scoped, meaning that the logged-in user is tied to the user's session.
 * </p>
 * 
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link UserDao}: Data access object for retrieving user data from the database.</li>
 * </ul>
 * </p>
 * 
 * @author Xihai Ren
 */
@SessionScoped
public class AuthServiceImpl implements AuthService, Serializable {

    private static final long serialVersionUID = 1L;

    // Dependency on UserDao for accessing user data
    private UserDao userDao = new UserDao();

    // Holds the currently logged-in user
    private User loggedInUser;

    /**
     * Logs in a user with the provided email and password.
     * 
     * @param email    the email address of the user attempting to log in
     * @param password the password of the user attempting to log in
     * @return true if the login is successful, false otherwise
     */
    @Override
    public boolean login(String email, String password) {
        User user = userDao.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            return true;
        }
        return false;
    }

    /**
     * Logs out the currently logged-in user by clearing the session.
     */
    @Override
    public void logout() {
        loggedInUser = null;
    }

    /**
     * Retrieves the currently logged-in user.
     * 
     * @return the User object representing the currently logged-in user,
     *         or null if no user is logged in
     */
    @Override
    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Checks if a user is currently logged in.
     * 
     * @return true if a user is logged in, false otherwise
     */
    @Override
    public boolean isUserLoggedIn() {
        return loggedInUser != null;
    }
}