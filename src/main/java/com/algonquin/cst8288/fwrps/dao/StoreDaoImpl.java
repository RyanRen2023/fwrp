package com.algonquin.cst8288.fwrps.dao;

import com.algonquin.cst8288.fwrps.model.Store;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;


@Dependent
public class StoreDaoImpl implements StoreDao, Serializable {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private transient EntityManager em;

    @Override
    public void createStore(Store store) {
        em.persist(store);
    }

    @Override
    public Store getStoreById(int id) {
        return em.find(Store.class, id);
    }

    @Override
    public List<Store> getAllStores() {
        TypedQuery<Store> query = em.createNamedQuery("Store.findAll", Store.class);
        return query.getResultList();
    }

    @Override
    public void updateStore(Store store) {
        em.merge(store);
    }

    @Override
    public void deleteStore(int id) {
        Store store = em.find(Store.class, id);
        if (store != null) {
            em.remove(store);
        }
    }
    
   
    
}
