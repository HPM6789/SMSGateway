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
public class CmdcodeListModel {

    private BigDecimal cmdId;

    private String cmdName;

    private String cmdCode;

    private BigInteger shortcodeCpId;
    private BigDecimal shortcodeCpIdDec;

    private Date updateTime;

    private Date createTime;

    private String status;

    private String typeCode;

    private Date approveTime;

    private Long price;

    private Long creatorId;

    private Long approverId;

    private String description;

    private BigDecimal creatorIdDec;
    private String creatorName;
    
    private BigDecimal approverIdDec;
    private String approverName;


    public CmdcodeListModel() {
    }

    public CmdcodeListModel(BigDecimal cmdId) {
        this.cmdId = cmdId;
    }

    public CmdcodeListModel(BigDecimal cmdId, String cmdCode, BigInteger shortcodeCpId) {
        this.cmdId = cmdId;
        this.cmdCode = cmdCode;
        this.shortcodeCpId = shortcodeCpId;
    }

    public CmdcodeListModel(BigDecimal cmdId, String cmdName, String cmdCode, BigDecimal shortcodeCpIdDec, Date updateTime, Date createTime, String status, String typeCode, Date approveTime, Long price, Long creatorId, Long approverId, String description) {
        this.cmdId = cmdId;
        this.cmdName = cmdName;
        this.cmdCode = cmdCode;
        this.shortcodeCpIdDec = shortcodeCpIdDec;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.status = status;
        this.typeCode = typeCode;
        this.approveTime = approveTime;
        this.price = price;
        this.creatorId = creatorId;
        this.approverId = approverId;
        this.description = description;
    }

    public CmdcodeListModel(BigDecimal cmdId, String cmdName, String cmdCode, BigDecimal shortcodeCpIdDec, Date updateTime, Date createTime, String status, String typeCode, Date approveTime, Long price, String description, BigDecimal creatorIdDec, String creatorName, BigDecimal approverIdDec, String approverName) {
        this.cmdId = cmdId;
        this.cmdName = cmdName;
        this.cmdCode = cmdCode;
        this.shortcodeCpIdDec = shortcodeCpIdDec;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.status = status;
        this.typeCode = typeCode;
        this.approveTime = approveTime;
        this.price = price;
        
        
        this.description = description;
        this.creatorIdDec = creatorIdDec;
        this.creatorName = creatorName;
        this.approverIdDec = approverIdDec;
        this.approverName = approverName;
    }

    public BigDecimal getShortcodeCpIdDec() {
        return shortcodeCpIdDec;
    }

    public void setShortcodeCpIdDec(BigDecimal shortcodeCpIdDec) {
        this.shortcodeCpIdDec = shortcodeCpIdDec;
    }

    
    
    public BigDecimal getCreatorIdDec() {
        return creatorIdDec;
    }

    public void setCreatorIdDec(BigDecimal creatorIdDec) {
        this.creatorIdDec = creatorIdDec;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public BigDecimal getApproverIdDec() {
        return approverIdDec;
    }

    public void setApproverIdDec(BigDecimal approverIdDec) {
        this.approverIdDec = approverIdDec;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    
    

    public BigDecimal getCmdId() {
        return cmdId;
    }

    public void setCmdId(BigDecimal cmdId) {
        this.cmdId = cmdId;
    }

    public String getCmdName() {
        return cmdName;
    }

    public void setCmdName(String cmdName) {
        this.cmdName = cmdName;
    }

    public String getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode;
    }

    public BigInteger getShortcodeCpId() {
        return shortcodeCpId;
    }

    public void setShortcodeCpId(BigInteger shortcodeCpId) {
        this.shortcodeCpId = shortcodeCpId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getApproverId() {
        return approverId;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
