package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.User;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDaoTest {

    private UserDao userDao;

    @BeforeAll
    public void setUpClass() {
        userDao = new UserDao();
    }

    @Test
    public void testAddUser() {
        User user = new User("Test User", "testuser@example.com", "password123", "consumer");
        user.setLastLogin(LocalDateTime.now());

        userDao.addUser(user);

        User addedUser = userDao.getUserByEmail("testuser@example.com");
        assertNotNull(addedUser);
        assertEquals("Test User", addedUser.getName());
        assertEquals("testuser@example.com", addedUser.getEmail());
    }

    @Test
    public void testGetUserById() {
        User user = new User("Test User", "testuser2@example.com", "password123", "consumer");
        user.setLastLogin(LocalDateTime.now());

        userDao.addUser(user);

        User addedUser = userDao.getUserByEmail("testuser2@example.com");
        User retrievedUser = userDao.getUserById(addedUser.getUid());
        assertNotNull(retrievedUser);
        assertEquals(addedUser.getUid(), retrievedUser.getUid());
    }

    @Test
    public void testUpdateUser() {
        User user = new User("Test User", "testuser3@example.com", "password123", "consumer");
        user.setLastLogin(LocalDateTime.now());

        userDao.addUser(user);

        User addedUser = userDao.getUserByEmail("testuser3@example.com");
        addedUser.setName("Updated User");
        addedUser.setEmail("updateduser@example.com");
        userDao.updateUser(addedUser);

        User updatedUser = userDao.getUserById(addedUser.getUid());
        assertEquals("Updated User", updatedUser.getName());
        assertEquals("updateduser@example.com", updatedUser.getEmail());
    }

    @Test
    public void testDeleteUser() {
        User user = new User("Test User", "testuser4@example.com", "password123", "consumer");
        user.setLastLogin(LocalDateTime.now());

        userDao.addUser(user);

        User addedUser = userDao.getUserByEmail("testuser4@example.com");
        userDao.deleteUser(addedUser.getUid());

        User deletedUser = userDao.getUserById(addedUser.getUid());
        assertNull(deletedUser);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User("Test User 1", "testuser5@example.com", "password123", "consumer");
        user1.setLastLogin(LocalDateTime.now());

        User user2 = new User("Test User 2", "testuser6@example.com", "password123", "consumer");
        user2.setLastLogin(LocalDateTime.now());

        userDao.addUser(user1);
        userDao.addUser(user2);

        List<User> users = userDao.getAllUsers();
        assertNotNull(users);
        assertTrue(users.size() >= 2);
    }

    @Test
    public void testGetUserByEmail() {
        User user = new User("Test User", "testuser7@example.com", "password123", "consumer");
        user.setLastLogin(LocalDateTime.now());

        userDao.addUser(user);

        User retrievedUser = userDao.getUserByEmail("testuser7@example.com");
        assertNotNull(retrievedUser);
        assertEquals("testuser7@example.com", retrievedUser.getEmail());
    }
}

