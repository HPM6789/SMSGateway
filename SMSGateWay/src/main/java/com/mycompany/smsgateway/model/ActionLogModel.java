/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Minh Hieu Pham
 */
public class ActionLogModel {
    
    private BigDecimal actionlogId;
    
    private BigInteger userId;
    private BigDecimal userIdDec;
    private String userName;
    private String userFullname;
    
    
    private String actionlogName;
    
    private String actionlogObjectType;
    
    private BigInteger actionlogObjectId;
    
    private String actionlogIp;
    
    private String actionlogDevice;
    
    private String actionlogOs;
    
    private String actionlogApp;
    
    private Date actionlogTime;
    
    private String actionlogResult;
    
    private String actionlogDesc;
    
    private String actionlogData;
    
    private String actionlogMsisdn;

    public ActionLogModel() {
    }

    public ActionLogModel(BigDecimal actionlogId) {
        this.actionlogId = actionlogId;
    }

    public ActionLogModel(BigDecimal actionlogId, BigInteger userId) {
        this.actionlogId = actionlogId;
        this.userId = userId;
    }

    public ActionLogModel(BigDecimal actionlogId, BigDecimal userIdDec, String userName, String userFullname, String actionlogName, String actionlogObjectType, BigInteger actionlogObjectId, String actionlogIp, String actionlogDevice, String actionlogOs, String actionlogApp, Date actionlogTime, String actionlogResult, String actionlogDesc, String actionlogData, String actionlogMsisdn) {
        this.actionlogId = actionlogId;
        this.userIdDec = userIdDec;
        this.userName = userName;
        this.userFullname = userFullname;
        this.actionlogName = actionlogName;
        this.actionlogObjectType = actionlogObjectType;
        this.actionlogObjectId = actionlogObjectId;
        this.actionlogIp = actionlogIp;
        this.actionlogDevice = actionlogDevice;
        this.actionlogOs = actionlogOs;
        this.actionlogApp = actionlogApp;
        this.actionlogTime = actionlogTime;
        this.actionlogResult = actionlogResult;
        this.actionlogDesc = actionlogDesc;
        this.actionlogData = actionlogData;
        this.actionlogMsisdn = actionlogMsisdn;
    }

    public BigDecimal getUserIdDec() {
        return userIdDec;
    }

    public void setUserIdDec(BigDecimal userIdDec) {
        this.userIdDec = userIdDec;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public BigDecimal getActionlogId() {
        return actionlogId;
    }

    public void setActionlogId(BigDecimal actionlogId) {
        this.actionlogId = actionlogId;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

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
}
