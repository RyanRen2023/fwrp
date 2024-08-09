package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.model.User;
import java.util.List;

/**
 * Service interface that defines operations related to user management.
 * 
 * <p>
 * This interface provides methods for creating, retrieving, updating, and deleting users,
 * as well as validating user credentials. Implementations of this interface will handle
 * the actual business logic associated with these operations.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     UserService userService = new UserServiceImpl();
 *     User user = userService.getUserByEmail("example@example.com");
 * </pre>
 * </p>
 * 
 * <p>
 * The interface is designed to be implemented by classes that handle the specific
 * details of user management, including interaction with the database.
 * </p>
 * 
 * @author Xihai Ren
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user the user to be created
     */
    void createUser(User user);

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to be retrieved
     * @return the User object corresponding to the specified ID
     */
    User getUserById(int id);

    /**
     * Retrieves all users.
     *
     * @return a list of all User objects
     */
    List<User> getAllUsers();

    /**
     * Updates an existing user.
     *
     * @param user the user to be updated
     */
    void updateUser(User user);

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to be deleted
     */
    void deleteUser(int id);

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user to be retrieved
     * @return the User object corresponding to the specified email address
     */
    User getUserByEmail(String email);

    /**
     * Validates a user's credentials.
     *
     * @param email the email address of the user
     * @param password the password of the user
     * @return true if the credentials are valid, false otherwise
     */
    boolean validateUser(String email, String password);
}