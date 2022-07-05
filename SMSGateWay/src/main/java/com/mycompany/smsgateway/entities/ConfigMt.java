/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "CONFIG_MT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfigMt.findAll", query = "SELECT c FROM ConfigMt c"),
    @NamedQuery(name = "ConfigMt.findByShortcode", query = "SELECT c FROM ConfigMt c WHERE c.shortcode = :shortcode"),
    @NamedQuery(name = "ConfigMt.findByMtContent", query = "SELECT c FROM ConfigMt c WHERE c.mtContent = :mtContent"),
    @NamedQuery(name = "ConfigMt.findByMtCode", query = "SELECT c FROM ConfigMt c WHERE c.mtCode = :mtCode"),
    @NamedQuery(name = "ConfigMt.findByMtId", query = "SELECT c FROM ConfigMt c WHERE c.mtId = :mtId"),
    @NamedQuery(name = "ConfigMt.findByCreateTime", query = "SELECT c FROM ConfigMt c WHERE c.createTime = :createTime"),
    @NamedQuery(name = "ConfigMt.findByUpdateTime", query = "SELECT c FROM ConfigMt c WHERE c.updateTime = :updateTime")})
public class ConfigMt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SHORTCODE")
    private String shortcode;
    @Size(max = 500)
    @Column(name = "MT_CONTENT")
    private String mtContent;
    @Size(max = 20)
    @Column(name = "MT_CODE")
    private String mtCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MT_ID")
    private BigDecimal mtId;
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public ConfigMt() {
    }

    public ConfigMt(BigDecimal mtId) {
        this.mtId = mtId;
    }

    public ConfigMt(BigDecimal mtId, String shortcode) {
        this.mtId = mtId;
        this.shortcode = shortcode;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public String getMtContent() {
        return mtContent;
    }

    public void setMtContent(String mtContent) {
        this.mtContent = mtContent;
    }

    public String getMtCode() {
        return mtCode;
    }

    public void setMtCode(String mtCode) {
        this.mtCode = mtCode;
    }

    public BigDecimal getMtId() {
        return mtId;
    }

    public void setMtId(BigDecimal mtId) {
        this.mtId = mtId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mtId != null ? mtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfigMt)) {
            return false;
        }
        ConfigMt other = (ConfigMt) object;
        if ((this.mtId == null && other.mtId != null) || (this.mtId != null && !this.mtId.equals(other.mtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.ConfigMt[ mtId=" + mtId + " ]";
    }
    
}
