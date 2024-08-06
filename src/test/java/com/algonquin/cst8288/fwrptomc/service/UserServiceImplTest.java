/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.UserDao;
import com.algonquin.cst8288.fwrptomc.model.User;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("password123");
        user.setUserType("user");
        user.setLastLogin(LocalDateTime.now());

        doNothing().when(userDao).addUser(user);

        userService.createUser(user);

        verify(userDao).addUser(user);
    }

    @Test
    public void testGetUserById() {
        int id = 1;
        User user = new User();
        user.setUid(id);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("password123");
        user.setUserType("user");
        user.setLastLogin(LocalDateTime.now());

        when(userDao.getUserById(id)).thenReturn(user);

        User result = userService.getUserById(id);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userDao).getUserById(id);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setUid(1);
        user1.setName("John Doe");
        user1.setEmail("john@example.com");
        user1.setPassword("password123");
        user1.setUserType("user");
        user1.setLastLogin(LocalDateTime.now());

        User user2 = new User();
        user2.setUid(2);
        user2.setName("Jane Doe");
        user2.setEmail("jane@example.com");
        user2.setPassword("password456");
        user2.setUserType("admin");
        user2.setLastLogin(LocalDateTime.now());

        List<User> users = Arrays.asList(user1, user2);

        when(userDao.getAllUsers()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userDao).getAllUsers();
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUid(1);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("password123");
        user.setUserType("user");
        user.setLastLogin(LocalDateTime.now());

        doNothing().when(userDao).updateUser(user);

        userService.updateUser(user);

        verify(userDao).updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        int id = 1;

        doNothing().when(userDao).deleteUser(id);

        userService.deleteUser(id);

        verify(userDao).deleteUser(id);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "john@example.com";
        User user = new User();
        user.setUid(1);
        user.setName("John Doe");
        user.setEmail(email);
        user.setPassword("password123");
        user.setUserType("user");
        user.setLastLogin(LocalDateTime.now());

        when(userDao.getUserByEmail(email)).thenReturn(user);

        User result = userService.getUserByEmail(email);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userDao).getUserByEmail(email);
    }

    @Test
    public void testValidateUser() {
        String email = "john@example.com";
        String password = "password123";
        User user = new User();
        user.setUid(1);
        user.setName("John Doe");
        user.setEmail(email);
        user.setPassword(password);
        user.setUserType("user");
        user.setLastLogin(LocalDateTime.now());

        when(userDao.getUserByEmail(email)).thenReturn(user);

        boolean result = userService.validateUser(email, password);

        assertTrue(result);
        verify(userDao).getUserByEmail(email);
    }

    @Test
    public void testValidateUserInvalidPassword() {
        String email = "john@example.com";
        String password = "wrongpassword";
        User user = new User();
        user.setUid(1);
        user.setName("John Doe");
        user.setEmail(email);
        user.setPassword("password123");
        user.setUserType("user");
        user.setLastLogin(LocalDateTime.now());

        when(userDao.getUserByEmail(email)).thenReturn(user);

        boolean result = userService.validateUser(email, password);

        assertFalse(result);
        verify(userDao).getUserByEmail(email);
    }

    @Test
    public void testValidateUserInvalidEmail() {
        String email = "invalid@example.com";
        String password = "password123";

        when(userDao.getUserByEmail(email)).thenReturn(null);

        boolean result = userService.validateUser(email, password);

        assertFalse(result);
        verify(userDao).getUserByEmail(email);
    }
}
