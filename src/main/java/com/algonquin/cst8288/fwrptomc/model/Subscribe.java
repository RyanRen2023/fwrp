package com.algonquin.cst8288.fwrptomc.model;

import java.time.LocalDate;

/**
 * Represents a subscription in the Food Waste Reduction Platform.
 * 
 * <p>
 * This class captures the details of a subscription, including the subscriber's 
 * user ID, the subscribed food item ID, the creation time of the subscription, 
 * the alert type, and the email address for receiving notifications.
 * </p>
 * 
 * Author: Xihai Ren
 */
public class Subscribe {
    private int sid;
    private int uid;
    private int fid;
    private LocalDate createTime;
    private String alertType;
    private String email;

    // Getters and Setters
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}