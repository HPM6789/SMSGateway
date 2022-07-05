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
public class ShortcodeListModel {
    private BigDecimal shcodeId;
    
    private String shortcode;
    
    private BigInteger price;
    
    private BigInteger limitedMtNo;
    
    private Date createTime;
    
    private Date updateTime;
   
    private Date approveTime;
    
    private BigInteger status;
    
    private Long creatorId;
    
    private String username;
    private BigDecimal userId;
    private AuthUserModel user;

    public ShortcodeListModel() {
    }

    public ShortcodeListModel(BigDecimal shcodeId) {
        this.shcodeId = shcodeId;
    }

    public ShortcodeListModel(BigDecimal shcodeId, String shortcode) {
        this.shcodeId = shcodeId;
        this.shortcode = shortcode;
    }
    
    public ShortcodeListModel(String shortcode, BigInteger price, BigInteger limitedMtNo, Date createTime, Date updateTime, Date approveTime, BigInteger status, Long creatorId) {
        this.shortcode = shortcode;
        this.price = price;
        this.limitedMtNo = limitedMtNo;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.approveTime = approveTime;
        this.status = status;
        this.creatorId = creatorId;
    }

    public ShortcodeListModel(BigDecimal shcodeId, String shortcode, BigInteger price, BigInteger limitedMtNo, Date createTime, Date updateTime, Date approveTime, BigInteger status, Long creatorId) {
        this.shcodeId = shcodeId;
        this.shortcode = shortcode;
        this.price = price;
        this.limitedMtNo = limitedMtNo;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.approveTime = approveTime;
        this.status = status;
        this.creatorId = creatorId;
    }
    
    public ShortcodeListModel(BigDecimal shcodeId, String shortcode, BigInteger price, BigInteger limitedMtNo, Date createTime, Date updateTime, Date approveTime, BigInteger status, String username, BigDecimal userId) {
        this.shcodeId = shcodeId;
        this.shortcode = shortcode;
        this.price = price;
        this.limitedMtNo = limitedMtNo;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.approveTime = approveTime;
        this.status = status;
        this.username = username;
        this.userId = userId;
    }

    public AuthUserModel getUser() {
        return user;
    }

    public void setUser(AuthUserModel user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
