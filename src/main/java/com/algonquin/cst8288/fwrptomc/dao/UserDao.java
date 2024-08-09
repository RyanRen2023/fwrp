package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing user data in the database.
 * 
 * This class provides methods for adding, updating, deleting, and retrieving 
 * user records from the database. It interacts with the database using JDBC.
 * 
 * Author: Xihai Ren
 */
public class UserDao {

    private JDBCClient jdbcClient;

    /**
     * Constructs a new UserDao and initializes the JDBCClient.
     */
    public UserDao() {
        this.jdbcClient = new JDBCClient();
    }

    /**
     * Add a new user to the database
     *
     * @param user the user to be added
     */
    public void addUser(User user) {
        String sql = "INSERT INTO user (name, email, password, user_type, last_login) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getUserType());
            pstmt.setObject(5, user.getLastLogin());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update an existing user in the database
     *
     * @param user the user to be updated
     */
    public void updateUser(User user) {
        String sql = "UPDATE user SET name = ?, email = ?, password = ?, user_type = ?, last_login = ? WHERE uid = ?";

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getUserType());
            pstmt.setObject(5, user.getLastLogin());
            pstmt.setInt(6, user.getUid());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a user from the database by their ID
     *
     * @param uid the user ID of the user to be deleted
     */
    public void deleteUser(int uid) {
        String sql = "DELETE FROM user WHERE uid = ?";

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, uid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve a user from the database by their ID
     *
     * @param uid the user ID of the user to be retrieved
     * @return the User object
     */
    public User getUserById(int uid) {
        String sql = "SELECT * FROM user WHERE uid = ?";
        User user = null;

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, uid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUid(rs.getInt("uid"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getString("user_type"));
                user.setLastLogin(rs.getObject("last_login", LocalDateTime.class));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Retrieve all users from the database
     *
     * @return a list of User objects
     */
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        List<User> users = new ArrayList<>();

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql); 
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUid(rs.getInt("uid"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getString("user_type"));
                user.setLastLogin(rs.getObject("last_login", LocalDateTime.class));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Retrieve a user from the database by their email
     *
     * @param email the email of the user to be retrieved
     * @return the User object
     */
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        User user = null;

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUid(rs.getInt("uid"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getString("user_type"));
                user.setLastLogin(rs.getObject("last_login", LocalDateTime.class));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}