package com.algonquin.cst8288.fwrps.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private List<RatingAndFeedback> ratingAndFeedbackList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Integer uid;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "user_type", length = 20)
    private String userType;

    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    // Getters and Setters
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public int hashCode() {
        return uid != null ? uid.hashCode() : 0;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return uid != null && uid.equals(other.uid);
    }

    @Override
    public String toString() {
        return "User[ uid=" + uid + " ]";
    }

    public User() {
    }

    @XmlTransient
    public List<RatingAndFeedback> getRatingAndFeedbackList() {
        return ratingAndFeedbackList;
    }

    public void setRatingAndFeedbackList(List<RatingAndFeedback> ratingAndFeedbackList) {
        this.ratingAndFeedbackList = ratingAndFeedbackList;
    }
}