/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrps.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author renxihai
 */
@Entity
@Table(name = "subscribe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subscribe.findAll", query = "SELECT s FROM Subscribe s"),
    @NamedQuery(name = "Subscribe.findBySid", query = "SELECT s FROM Subscribe s WHERE s.sid = :sid"),
    @NamedQuery(name = "Subscribe.findByUid", query = "SELECT s FROM Subscribe s WHERE s.uid = :uid"),
    @NamedQuery(name = "Subscribe.findByFid", query = "SELECT s FROM Subscribe s WHERE s.fid = :fid"),
    @NamedQuery(name = "Subscribe.findByCreateTime", query = "SELECT s FROM Subscribe s WHERE s.createTime = :createTime")})
public class Subscribe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sid")
    private Integer sid;
    @Column(name = "uid")
    private Integer uid;
    @Column(name = "fid")
    private Integer fid;
    @Column(name = "create_time")
    @Temporal(TemporalType.DATE)
    private Date createTime;

    public Subscribe() {
    }

    public Subscribe(Integer sid) {
        this.sid = sid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sid != null ? sid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subscribe)) {
            return false;
        }
        Subscribe other = (Subscribe) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.algonquin.cst8288.fwrps.model.Subscribe[ sid=" + sid + " ]";
    }
    
}
