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
public class SmsMtLogModel {

    private BigDecimal seqMtId;

    private BigInteger moId;

    private String msisdn;

    private String message;

    private Date sendTime;

    private Date createDate;

    private Short status;

    private String shortCode;

    private String cpCode;

    private String serviceCode;

    private String packageCode;

    private BigInteger price;

    private String errorDesc;

    private String errorCode;

    private BigInteger mtId;

    public SmsMtLogModel() {
    }

    public SmsMtLogModel(BigDecimal seqMtId) {
        this.seqMtId = seqMtId;
    }

    public SmsMtLogModel(BigDecimal seqMtId, BigInteger moId, String msisdn, String message, 
            Date createDate, String shortCode, BigInteger price, String errorDesc, String errorCode) {
        this.seqMtId = seqMtId;
        this.moId = moId;
        this.msisdn = msisdn;
        this.message = message;
        this.createDate = createDate;
        this.shortCode = shortCode;
        this.price = price;
        this.errorDesc = errorDesc;
        this.errorCode = errorCode;
    }
    

    public SmsMtLogModel(BigDecimal seqMtId, BigInteger moId, String msisdn, String message,
            Date sendTime, Date createDate, Short status, String shortCode, String cpCode,
            String serviceCode, String packageCode, BigInteger price, String errorDesc,
            String errorCode, BigInteger mtId) {
        this.seqMtId = seqMtId;
        this.moId = moId;
        this.msisdn = msisdn;
        this.message = message;
        this.sendTime = sendTime;
        this.createDate = createDate;
        this.status = status;
        this.shortCode = shortCode;
        this.cpCode = cpCode;
        this.serviceCode = serviceCode;
        this.packageCode = packageCode;
        this.price = price;
        this.errorDesc = errorDesc;
        this.errorCode = errorCode;
        this.mtId = mtId;
    }

    public BigDecimal getSeqMtId() {
        return seqMtId;
    }

    public void setSeqMtId(BigDecimal seqMtId) {
        this.seqMtId = seqMtId;
    }

    public BigInteger getMoId() {
        return moId;
    }

    public void setMoId(BigInteger moId) {
        this.moId = moId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public BigInteger getMtId() {
        return mtId;
    }

    public void setMtId(BigInteger mtId) {
        this.mtId = mtId;
    }

}
