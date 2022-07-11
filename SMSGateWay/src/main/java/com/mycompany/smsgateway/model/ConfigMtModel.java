/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Minh Hieu Pham
 */
public class ConfigMtModel {

    private BigDecimal mtId;

    private BigDecimal shcodeId;
    private String shortcode;

    private String mtContent;

    private String mtCode;

    private Date createTime;

    private Date updateTime;

    public ConfigMtModel() {
    }

    public ConfigMtModel(BigDecimal mtId) {
        this.mtId = mtId;
    }

    public ConfigMtModel(BigDecimal mtId, String shortcode) {
        this.mtId = mtId;
        this.shortcode = shortcode;
    }

    public ConfigMtModel(BigDecimal mtId, BigDecimal shcodeId, String shortcode, 
            String mtContent, String mtCode, Date createTime, Date updateTime) {
        this.mtId = mtId;
        this.shcodeId = shcodeId;
        this.shortcode = shortcode;
        this.mtContent = mtContent;
        this.mtCode = mtCode;
        this.createTime = createTime;
        this.updateTime = updateTime;
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
}
