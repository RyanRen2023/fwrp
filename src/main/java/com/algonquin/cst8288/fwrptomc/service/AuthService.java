package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.model.User;

/**
 * Interface defining the contract for authentication services.
 *
 * <p>
 * This interface provides methods for logging in, logging out, and managing the
 * current logged-in user within the application.
 * </p>
 *
 * <p>
 * Implementations of this interface are responsible for handling the
 * authentication logic and managing user sessions.
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
 * @author Xihai Ren
 */
public interface AuthService {

    /**
     * Logs in a user with the provided email and password.
     *
     * @param email the email address of the user attempting to log in
     * @param password the password of the user attempting to log in
     * @return true if the login is successful, false otherwise
     */
    boolean login(String email, String password);

    /**
     * Logs out the currently logged-in user.
     */
    void logout();

    /**
     * Retrieves the currently logged-in user.
     *
     * @return the User object representing the currently logged-in user, or
     * null if no user is logged in
     */
    User getLoggedInUser();

    /**
     * Checks if a user is currently logged in.
     *
     * @return true if a user is logged in, false otherwise
     */
    boolean isUserLoggedIn();
}
