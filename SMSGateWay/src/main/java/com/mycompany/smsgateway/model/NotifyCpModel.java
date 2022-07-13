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
public class NotifyCpModel {
    private BigDecimal notifyId;
    
    private String moReceiveUrl;
    
    private BigInteger cpId;
    private BigDecimal cpIdDec;
    private String cpName;
    private String cpCode;
    
    private String note;
    
    private Date createDate;
    
    private Date lastUpdate;
    
    private BigInteger status;
    
    private BigInteger shcodeId;
    
    private String moReceiveUrlBkp;

    public NotifyCpModel() {
    }

    public NotifyCpModel(BigDecimal notifyId) {
        this.notifyId = notifyId;
    }

    public NotifyCpModel(BigDecimal notifyId, String moReceiveUrl, BigInteger cpId) {
        this.notifyId = notifyId;
        this.moReceiveUrl = moReceiveUrl;
        this.cpId = cpId;
    }

    public NotifyCpModel(BigDecimal notifyId, String moReceiveUrl, BigInteger cpId, 
            BigDecimal cpIdDec, String cpName, String cpCode, String note, Date createDate, 
            Date lastUpdate, BigInteger status, BigInteger shcodeId, String moReceiveUrlBkp) {
        this.notifyId = notifyId;
        this.moReceiveUrl = moReceiveUrl;
        this.cpId = cpId;
        this.cpIdDec = cpIdDec;
        this.cpName = cpName;
        this.cpCode = cpCode;
        this.note = note;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.status = status;
        this.shcodeId = shcodeId;
        this.moReceiveUrlBkp = moReceiveUrlBkp;
    }
    

    public BigDecimal getNotifyId() {
        return notifyId;
    }

    public BigDecimal getCpIdDec() {
        return cpIdDec;
    }

    public void setCpIdDec(BigDecimal cpIdDec) {
        this.cpIdDec = cpIdDec;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public void setNotifyId(BigDecimal notifyId) {
        this.notifyId = notifyId;
    }

    public String getMoReceiveUrl() {
        return moReceiveUrl;
    }

    public void setMoReceiveUrl(String moReceiveUrl) {
        this.moReceiveUrl = moReceiveUrl;
    }

    public BigInteger getCpId() {
        return cpId;
    }

    public void setCpId(BigInteger cpId) {
        this.cpId = cpId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public BigInteger getStatus() {
        return status;
    }

    public void setStatus(BigInteger status) {
        this.status = status;
    }

    public BigInteger getShcodeId() {
        return shcodeId;
    }

    public void setShcodeId(BigInteger shcodeId) {
        this.shcodeId = shcodeId;
    }

    public String getMoReceiveUrlBkp() {
        return moReceiveUrlBkp;
    }

    public void setMoReceiveUrlBkp(String moReceiveUrlBkp) {
        this.moReceiveUrlBkp = moReceiveUrlBkp;
    }

}
