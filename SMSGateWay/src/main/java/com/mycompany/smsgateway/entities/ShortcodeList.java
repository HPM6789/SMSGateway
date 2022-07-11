/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Minh Hieu Pham
 */
@Entity
@Table(name = "SHORTCODE_LIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShortcodeList.findAll", query = "SELECT s FROM ShortcodeList s"),
    @NamedQuery(name = "ShortcodeList.findByShcodeId", query = "SELECT s FROM ShortcodeList s WHERE s.shcodeId = :shcodeId"),
    @NamedQuery(name = "ShortcodeList.findByShortcode", query = "SELECT s FROM ShortcodeList s WHERE s.shortcode = :shortcode"),
    @NamedQuery(name = "ShortcodeList.findByPrice", query = "SELECT s FROM ShortcodeList s WHERE s.price = :price"),
    @NamedQuery(name = "ShortcodeList.findByLimitedMtNo", query = "SELECT s FROM ShortcodeList s WHERE s.limitedMtNo = :limitedMtNo"),
    @NamedQuery(name = "ShortcodeList.findByCreateTime", query = "SELECT s FROM ShortcodeList s WHERE s.createTime = :createTime"),
    @NamedQuery(name = "ShortcodeList.findByUpdateTime", query = "SELECT s FROM ShortcodeList s WHERE s.updateTime = :updateTime"),
    @NamedQuery(name = "ShortcodeList.findByApproveTime", query = "SELECT s FROM ShortcodeList s WHERE s.approveTime = :approveTime"),
    @NamedQuery(name = "ShortcodeList.findByStatus", query = "SELECT s FROM ShortcodeList s WHERE s.status = :status"),
//    @NamedQuery(name = "ShortcodeList.findByCreatorId", query = "SELECT s FROM ShortcodeList s WHERE s.creatorId = :creatorId")
})
public class ShortcodeList implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SHCODE_ID")
    private BigDecimal shcodeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SHORTCODE")
    private String shortcode;
    @Column(name = "PRICE")
    private BigInteger price;
    @Column(name = "LIMITED_MT_NO")
    private BigInteger limitedMtNo;
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Column(name = "APPROVE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approveTime;
    @Column(name = "STATUS")
    private BigInteger status;
//    @Column(name = "CREATOR_ID")
//    private Long creatorId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR_ID")
    private AuthUser user;
    
    @OneToMany(mappedBy = "shortcode")
    List<ShortcodeCp> shortcodeCps;
    
    @OneToOne(mappedBy = "shortcodeInConfigMt")
    private ConfigMt configMtShcodeMapped;
    
    public ShortcodeList() {
    }

    public ShortcodeList(BigDecimal shcodeId) {
        this.shcodeId = shcodeId;
    }

    public ShortcodeList(BigDecimal shcodeId, String shortcode) {
        this.shcodeId = shcodeId;
        this.shortcode = shortcode;
    }

    public ConfigMt getConfigMtShcodeMapped() {
        return configMtShcodeMapped;
    }

    public void setConfigMtShcodeMapped(ConfigMt configMtShcodeMapped) {
        this.configMtShcodeMapped = configMtShcodeMapped;
    }

    public List<ShortcodeCp> getShortcodeCps() {
        return shortcodeCps;
    }

    public void setShortcodeCps(List<ShortcodeCp> shortcodeCps) {
        this.shortcodeCps = shortcodeCps;
    }
    

    public AuthUser getUser() {
        return user;
    }

    public void setUser(AuthUser user) {
        this.user = user;
    }
    
    

    public BigDecimal getShcodeId() {
        return shcodeId;
    }

    public void setShcodeId(BigDecimal shcodeId) {
        this.shcodeId = shcodeId;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public BigInteger getLimitedMtNo() {
        return limitedMtNo;
    }

    public void setLimitedMtNo(BigInteger limitedMtNo) {
        this.limitedMtNo = limitedMtNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public BigInteger getStatus() {
        return status;
    }

    public void setStatus(BigInteger status) {
        this.status = status;
    }

//    public Long getCreatorId() {
//        return creatorId;
//    }
//
//    public void setCreatorId(Long creatorId) {
//        this.creatorId = creatorId;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shcodeId != null ? shcodeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShortcodeList)) {
            return false;
        }
        ShortcodeList other = (ShortcodeList) object;
        if ((this.shcodeId == null && other.shcodeId != null) || (this.shcodeId != null && !this.shcodeId.equals(other.shcodeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.ShortcodeList[ shcodeId=" + shcodeId + " ]";
    }
    
}
