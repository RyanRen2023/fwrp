package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.Store;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing store data in the database.
 * 
 * This class provides methods for adding, updating, deleting, and retrieving 
 * store records from the database. It interacts with the database using JDBC.
 * 
 * Note: Ensure that the JDBCClient class is correctly implemented to provide
 * a valid database connection.
 * 
 * Author: Xihai Ren
 */
public class StoreDao {

    private JDBCClient jdbcClient;

    /**
     * Constructs a new StoreDao and initializes the JDBCClient.
     */
    public StoreDao() {
        this.jdbcClient = new JDBCClient();
    }

    /**
     * Add a new store to the database
     *
     * @param store the store to be added
     */
    public void addStore(Store store) {
        String sql = "INSERT INTO store (store_name, city, uid) VALUES (?, ?, ?)";

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, store.getStoreName());
            pstmt.setString(2, store.getCity());
            pstmt.setInt(3, store.getUid());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update an existing store in the database
     *
     * @param store the store to be updated
     */
    public void updateStore(Store store) {
        String sql = "UPDATE store SET store_name = ?, city = ?, uid = ? WHERE store_id = ?";

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, store.getStoreName());
            pstmt.setString(2, store.getCity());
            pstmt.setInt(3, store.getUid());
            pstmt.setInt(4, store.getStoreId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a store from the database by its ID
     *
     * @param storeId the store ID of the store to be deleted
     */
    public void deleteStore(int storeId) {
        String sql = "DELETE FROM store WHERE store_id = ?";

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, storeId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve a store from the database by its ID
     *
     * @param storeId the store ID of the store to be retrieved
     * @return the Store object
     */
    public Store getStoreById(int storeId) {
        String sql = "SELECT * FROM store WHERE store_id = ?";
        Store store = null;

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, storeId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                store = new Store();
                store.setStoreId(rs.getInt("store_id"));
                store.setStoreName(rs.getString("store_name"));
                store.setCity(rs.getString("city"));
                store.setUid(rs.getInt("uid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return store;
    }

    /**
     * Retrieve all stores from the database
     *
     * @return a list of Store objects
     */
    public List<Store> getAllStores() {
        String sql = "SELECT * FROM store";
        List<Store> stores = new ArrayList<>();

        try (Connection conn = jdbcClient.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql); 
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Store store = new Store();
                store.setStoreId(rs.getInt("store_id"));
                store.setStoreName(rs.getString("store_name"));
                store.setCity(rs.getString("city"));
                store.setUid(rs.getInt("uid"));
                stores.add(store);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stores;
    }
}