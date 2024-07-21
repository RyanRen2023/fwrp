/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrps.dao;

import com.algonquin.cst8288.fwrps.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author renxihai
 */
public class UserDaoImpl implements UserDao {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;

    @Override
    public void createUser(User user) {
        em.persist(user);
    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            // Handle no result or other exceptions
        }
        return user;
    }

}
