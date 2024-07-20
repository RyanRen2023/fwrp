/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrps.service;

import com.algonquin.cst8288.fwrps.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author renxihai
 */
public class UserService {
    @PersistenceContext
    private EntityManager em;

    public UserService(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    public void createUser(String username, String password, String email, String role) {
        em.getTransaction().begin();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public User getUserById(int userId) {
        return em.find(User.class, userId);
    }

    public User getUserByEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                     .setParameter("email", email)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    public void updateUser(int userId, String username, String password, String email, String role) {
        em.getTransaction().begin();
        User user = em.find(User.class, userId);
        if (user != null) {
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setRole(role);
            em.merge(user);
        }
        em.getTransaction().commit();
    }

    public void deleteUser(int userId) {
        em.getTransaction().begin();
        User user = em.find(User.class, userId);
        if (user != null) {
            em.remove(user);
        }
        em.getTransaction().commit();
    }

    public boolean validatePassword(String email, String password) {
        User user = getUserByEmail(email);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
    
}
