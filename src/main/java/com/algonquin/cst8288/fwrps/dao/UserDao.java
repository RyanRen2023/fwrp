
package com.algonquin.cst8288.fwrps.dao;

import com.algonquin.cst8288.fwrps.model.User;
import java.util.List;

public interface UserDao {
    void createUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
    User getUserByEmail(String email);
}