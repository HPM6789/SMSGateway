/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ACTION_LOG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActionLog.findAll", query = "SELECT a FROM ActionLog a"),
    @NamedQuery(name = "ActionLog.findByActionlogId", query = "SELECT a FROM ActionLog a WHERE a.actionlogId = :actionlogId"),
//    @NamedQuery(name = "ActionLog.findByUserId", query = "SELECT a FROM ActionLog a WHERE a.userId = :userId"),
    @NamedQuery(name = "ActionLog.findByActionlogName", query = "SELECT a FROM ActionLog a WHERE a.actionlogName = :actionlogName"),
    @NamedQuery(name = "ActionLog.findByActionlogObjectType", query = "SELECT a FROM ActionLog a WHERE a.actionlogObjectType = :actionlogObjectType"),
    @NamedQuery(name = "ActionLog.findByActionlogObjectId", query = "SELECT a FROM ActionLog a WHERE a.actionlogObjectId = :actionlogObjectId"),
    @NamedQuery(name = "ActionLog.findByActionlogIp", query = "SELECT a FROM ActionLog a WHERE a.actionlogIp = :actionlogIp"),
    @NamedQuery(name = "ActionLog.findByActionlogDevice", query = "SELECT a FROM ActionLog a WHERE a.actionlogDevice = :actionlogDevice"),
    @NamedQuery(name = "ActionLog.findByActionlogOs", query = "SELECT a FROM ActionLog a WHERE a.actionlogOs = :actionlogOs"),
    @NamedQuery(name = "ActionLog.findByActionlogApp", query = "SELECT a FROM ActionLog a WHERE a.actionlogApp = :actionlogApp"),
    @NamedQuery(name = "ActionLog.findByActionlogTime", query = "SELECT a FROM ActionLog a WHERE a.actionlogTime = :actionlogTime"),
    @NamedQuery(name = "ActionLog.findByActionlogResult", query = "SELECT a FROM ActionLog a WHERE a.actionlogResult = :actionlogResult"),
    @NamedQuery(name = "ActionLog.findByActionlogDesc", query = "SELECT a FROM ActionLog a WHERE a.actionlogDesc = :actionlogDesc"),
    @NamedQuery(name = "ActionLog.findByActionlogData", query = "SELECT a FROM ActionLog a WHERE a.actionlogData = :actionlogData"),
    @NamedQuery(name = "ActionLog.findByActionlogMsisdn", query = "SELECT a FROM ActionLog a WHERE a.actionlogMsisdn = :actionlogMsisdn")})
public class ActionLog implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIONLOG_ID")
    private BigDecimal actionlogId;
    @Basic(optional = false)
//    @NotNull
//    @Column(name = "USER_ID")
//    private BigInteger userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private AuthUser user;
    
    @Size(max = 50)
    @Column(name = "ACTIONLOG_NAME")
    private String actionlogName;
    @Size(max = 50)
    @Column(name = "ACTIONLOG_OBJECT_TYPE")
    private String actionlogObjectType;
    @Column(name = "ACTIONLOG_OBJECT_ID")
    private BigInteger actionlogObjectId;
    @Size(max = 20)
    @Column(name = "ACTIONLOG_IP")
    private String actionlogIp;
    @Size(max = 100)
    @Column(name = "ACTIONLOG_DEVICE")
    private String actionlogDevice;
    @Size(max = 50)
    @Column(name = "ACTIONLOG_OS")
    private String actionlogOs;
    @Size(max = 50)
    @Column(name = "ACTIONLOG_APP")
    private String actionlogApp;
    @Column(name = "ACTIONLOG_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actionlogTime;
    @Size(max = 100)
    @Column(name = "ACTIONLOG_RESULT")
    private String actionlogResult;
    @Size(max = 200)
    @Column(name = "ACTIONLOG_DESC")
    private String actionlogDesc;
    @Size(max = 1000)
    @Column(name = "ACTIONLOG_DATA")
    private String actionlogData;
    @Size(max = 20)
    @Column(name = "ACTIONLOG_MSISDN")
    private String actionlogMsisdn;

    public ActionLog() {
    }

    public ActionLog(BigDecimal actionlogId) {
        this.actionlogId = actionlogId;
    }

    public AuthUser getUser() {
        return user;
    }

//    public ActionLog(BigDecimal actionlogId, BigInteger userId) {
//        this.actionlogId = actionlogId;
//        this.userId = userId;
//    }
    public void setUser(AuthUser user) {
        this.user = user;
    }

    public BigDecimal getActionlogId() {
        return actionlogId;
    }

    public void setActionlogId(BigDecimal actionlogId) {
        this.actionlogId = actionlogId;
    }

//    public BigInteger getUserId() {
//        return userId;
//    }
//
//    public void setUserId(BigInteger userId) {
//        this.userId = userId;
//    }

    public String getActionlogName() {
        return actionlogName;
    }

    public void setActionlogName(String actionlogName) {
        this.actionlogName = actionlogName;
    }

    public String getActionlogObjectType() {
        return actionlogObjectType;
    }

    public void setActionlogObjectType(String actionlogObjectType) {
        this.actionlogObjectType = actionlogObjectType;
    }

    public BigInteger getActionlogObjectId() {
        return actionlogObjectId;
    }

    public void setActionlogObjectId(BigInteger actionlogObjectId) {
        this.actionlogObjectId = actionlogObjectId;
    }

    public String getActionlogIp() {
        return actionlogIp;
    }

    public void setActionlogIp(String actionlogIp) {
        this.actionlogIp = actionlogIp;
    }

    public String getActionlogDevice() {
        return actionlogDevice;
    }

    public void setActionlogDevice(String actionlogDevice) {
        this.actionlogDevice = actionlogDevice;
    }

    public String getActionlogOs() {
        return actionlogOs;
    }

    public void setActionlogOs(String actionlogOs) {
        this.actionlogOs = actionlogOs;
    }

    public String getActionlogApp() {
        return actionlogApp;
    }

    public void setActionlogApp(String actionlogApp) {
        this.actionlogApp = actionlogApp;
    }

    public Date getActionlogTime() {
        return actionlogTime;
    }

    public void setActionlogTime(Date actionlogTime) {
        this.actionlogTime = actionlogTime;
    }

    public String getActionlogResult() {
        return actionlogResult;
    }

    public void setActionlogResult(String actionlogResult) {
        this.actionlogResult = actionlogResult;
    }

    public String getActionlogDesc() {
        return actionlogDesc;
    }

    public void setActionlogDesc(String actionlogDesc) {
        this.actionlogDesc = actionlogDesc;
    }

    public String getActionlogData() {
        return actionlogData;
    }

    public void setActionlogData(String actionlogData) {
        this.actionlogData = actionlogData;
    }

    public String getActionlogMsisdn() {
        return actionlogMsisdn;
    }

    public void setActionlogMsisdn(String actionlogMsisdn) {
        this.actionlogMsisdn = actionlogMsisdn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actionlogId != null ? actionlogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActionLog)) {
            return false;
        }
        ActionLog other = (ActionLog) object;
        if ((this.actionlogId == null && other.actionlogId != null) || (this.actionlogId != null && !this.actionlogId.equals(other.actionlogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.ActionLog[ actionlogId=" + actionlogId + " ]";
    }
    
}
