package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.Subscribe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SubscribeDao {

    private JDBCClient jdbcClient;

    public SubscribeDao() {
        this.jdbcClient = new JDBCClient();
    }

    /**
     * Add a new subscription to the database
     *
     * @param subscribe the subscription to be added
     */
    public void addSubscribe(Subscribe subscribe) {
        String sql = "INSERT INTO subscribe (uid, fid,create_time,alert_type, email) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
           
            pstmt.setInt(1, subscribe.getUid());
            pstmt.setInt(2, subscribe.getFid());
            pstmt.setDate(3, Date.valueOf(subscribe.getCreateTime()));
            pstmt.setString(4, subscribe.getAlertType());
            pstmt.setString(5, subscribe.getEmail());
             
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update an existing subscription in the database
     *
     * @param subscribe the subscription to be updated
     */
    public void updateSubscribe(Subscribe subscribe) {
        String sql = "UPDATE subscribe "
                + "SET alert_type = ? , email = ?"
                + "WHERE sid = ?";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, subscribe.getAlertType());
            pstmt.setString(2, subscribe.getEmail());
            pstmt.setInt(3, subscribe.getSid());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a subscription from the database by its ID
     *
     * @param sid the subscription ID of the subscription to be deleted
     */
    public void deleteSubscribe(int sid) {
        String sql = "DELETE FROM subscribe WHERE sid = ?";

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, sid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve a subscription from the database by its ID
     *
     * @param sid the subscription ID of the subscription to be retrieved
     * @return the Subscribe object
     */
    public Subscribe getSubscribeById(int sid) {
        String sql = "SELECT * FROM subscribe WHERE sid = ?";
        Subscribe subscribe = null;

        try (Connection conn = jdbcClient.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, sid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                subscribe = new Subscribe();
                subscribe.setUid(rs.getInt("uid"));
                subscribe.setFid(rs.getInt("fid"));
                subscribe.setCreateTime(rs.getDate("create_time").toLocalDate());
                subscribe.setAlertType(rs.getString("alert_type"));
                subscribe.setEmail(rs.getString("email"));
                subscribe.setCreateTime(rs.getDate("create_time").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subscribe;
    }

    /**
     * Retrieve all subscriptions from the database
     *
     * @return a list of Subscribe objects
     */
    public List<Subscribe> getAllSubscribes() {
        String sql = "SELECT * FROM subscribe";
        List<Subscribe> subscribes = new ArrayList<>();

        try (
                Connection conn = jdbcClient.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(sql); 
                ResultSet rs = pstmt.executeQuery()) {
           
            while (rs.next()) {
                Subscribe subscribe = new Subscribe();
                
                subscribe.setSid(rs.getInt("sid"));
                subscribe.setUid(rs.getInt("uid"));
                subscribe.setFid(rs.getInt("fid"));
                subscribe.setCreateTime(rs.getDate("create_time").toLocalDate());
                subscribe.setAlertType(rs.getString("alert_type"));
                subscribe.setEmail(rs.getString("email"));
                subscribes.add(subscribe);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subscribes;
    }
    
    public List<Subscribe> findByAlertType(String alertType) {
        List<Subscribe> subscribes = new ArrayList<>();
        String sql = "SELECT * FROM subscribes WHERE alertType LIKE ?";

        try (Connection conn = jdbcClient.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + alertType + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Subscribe subscribe = new Subscribe();
                    subscribe.setSid(rs.getInt("sid"));
                    subscribe.setAlertType(rs.getString("alertType"));
                    subscribe.setEmail(rs.getString("email"));
                    subscribes.add(subscribe);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subscribes;
    }
}