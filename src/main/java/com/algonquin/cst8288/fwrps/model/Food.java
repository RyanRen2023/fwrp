
package com.algonquin.cst8288.fwrps.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author renxihai
 */
@Entity
@Table(name = "food")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f"),
    @NamedQuery(name = "Food.findByFid", query = "SELECT f FROM Food f WHERE f.fid = :fid"),
    @NamedQuery(name = "Food.findByFname", query = "SELECT f FROM Food f WHERE f.fname = :fname"),
    @NamedQuery(name = "Food.findByExpiration", query = "SELECT f FROM Food f WHERE f.expiration = :expiration"),
    @NamedQuery(name = "Food.findByPrice", query = "SELECT f FROM Food f WHERE f.price = :price"),
    @NamedQuery(name = "Food.findByInventory", query = "SELECT f FROM Food f WHERE f.inventory = :inventory"),
    @NamedQuery(name = "Food.findByDiscount", query = "SELECT f FROM Food f WHERE f.discount = :discount"),
    @NamedQuery(name = "Food.findByFtid", query = "SELECT f FROM Food f WHERE f.ftid = :ftid"),
    @NamedQuery(name = "Food.findByIsDonate", query = "SELECT f FROM Food f WHERE f.isDonate = :isDonate"),
    @NamedQuery(name = "Food.findByStoreId", query = "SELECT f FROM Food f WHERE f.storeId = :storeId")})
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fid")
    private Integer fid;
    @Size(max = 50)
    @Column(name = "fname")
    private String fname;
    @Column(name = "expiration")
    @Temporal(TemporalType.DATE)
    private Date expiration;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Column(name = "inventory")
    private Integer inventory;
    @Column(name = "discount")
    private Double discount;
    @Column(name = "ftid")
    private Integer ftid;
    @Column(name = "is_donate")
    private Integer isDonate;
    @Column(name = "store_id")
    private Integer storeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodID")
    private List<RatingAndFeedback> ratingAndFeedbackList;

    public Food() {
    }

    public Food(Integer fid) {
        this.fid = fid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getFtid() {
        return ftid;
    }

    public void setFtid(Integer ftid) {
        this.ftid = ftid;
    }

    public Integer getIsDonate() {
        return isDonate;
    }

    public void setIsDonate(Integer isDonate) {
        this.isDonate = isDonate;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    @XmlTransient
    public List<RatingAndFeedback> getRatingAndFeedbackList() {
        return ratingAndFeedbackList;
    }

    public void setRatingAndFeedbackList(List<RatingAndFeedback> ratingAndFeedbackList) {
        this.ratingAndFeedbackList = ratingAndFeedbackList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fid != null ? fid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Food)) {
            return false;
        }
        Food other = (Food) object;
        if ((this.fid == null && other.fid != null) || (this.fid != null && !this.fid.equals(other.fid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.algonquin.cst8288.fwrps.model.Food[ fid=" + fid + " ]";
    }
    
}
