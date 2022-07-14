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
@Table(name = "SMS_MT_LOG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmsMtLog.findAll", query = "SELECT s FROM SmsMtLog s"),
    @NamedQuery(name = "SmsMtLog.findBySeqMtId", query = "SELECT s FROM SmsMtLog s WHERE s.seqMtId = :seqMtId"),
    @NamedQuery(name = "SmsMtLog.findByMoId", query = "SELECT s FROM SmsMtLog s WHERE s.moId = :moId"),
    @NamedQuery(name = "SmsMtLog.findByMsisdn", query = "SELECT s FROM SmsMtLog s WHERE s.msisdn = :msisdn"),
    @NamedQuery(name = "SmsMtLog.findByMessage", query = "SELECT s FROM SmsMtLog s WHERE s.message = :message"),
    @NamedQuery(name = "SmsMtLog.findBySendTime", query = "SELECT s FROM SmsMtLog s WHERE s.sendTime = :sendTime"),
    @NamedQuery(name = "SmsMtLog.findByCreateDate", query = "SELECT s FROM SmsMtLog s WHERE s.createDate = :createDate"),
    @NamedQuery(name = "SmsMtLog.findByStatus", query = "SELECT s FROM SmsMtLog s WHERE s.status = :status"),
    @NamedQuery(name = "SmsMtLog.findByShortCode", query = "SELECT s FROM SmsMtLog s WHERE s.shortCode = :shortCode"),
    @NamedQuery(name = "SmsMtLog.findByCpCode", query = "SELECT s FROM SmsMtLog s WHERE s.cpCode = :cpCode"),
    @NamedQuery(name = "SmsMtLog.findByServiceCode", query = "SELECT s FROM SmsMtLog s WHERE s.serviceCode = :serviceCode"),
    @NamedQuery(name = "SmsMtLog.findByPackageCode", query = "SELECT s FROM SmsMtLog s WHERE s.packageCode = :packageCode"),
    @NamedQuery(name = "SmsMtLog.findByPrice", query = "SELECT s FROM SmsMtLog s WHERE s.price = :price"),
    @NamedQuery(name = "SmsMtLog.findByErrorDesc", query = "SELECT s FROM SmsMtLog s WHERE s.errorDesc = :errorDesc"),
    @NamedQuery(name = "SmsMtLog.findByErrorCode", query = "SELECT s FROM SmsMtLog s WHERE s.errorCode = :errorCode"),
    @NamedQuery(name = "SmsMtLog.findByMtId", query = "SELECT s FROM SmsMtLog s WHERE s.mtId = :mtId")})
public class SmsMtLog implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEQ_MT_ID")
    private BigDecimal seqMtId;
    @Column(name = "MO_ID")
    private BigInteger moId;
    @Size(max = 20)
    @Column(name = "MSISDN")
    private String msisdn;
    @Size(max = 500)
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "SEND_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendTime;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "STATUS")
    private Short status;
    @Size(max = 20)
    @Column(name = "SHORT_CODE")
    private String shortCode;
    @Size(max = 100)
    @Column(name = "CP_CODE")
    private String cpCode;
    @Size(max = 100)
    @Column(name = "SERVICE_CODE")
    private String serviceCode;
    @Size(max = 100)
    @Column(name = "PACKAGE_CODE")
    private String packageCode;
    @Column(name = "PRICE")
    private BigInteger price;
    @Size(max = 500)
    @Column(name = "ERROR_DESC")
    private String errorDesc;
    @Size(max = 20)
    @Column(name = "ERROR_CODE")
    private String errorCode;
    @Column(name = "MT_ID")
    private BigInteger mtId;

    public SmsMtLog() {
    }

    public SmsMtLog(BigDecimal seqMtId) {
        this.seqMtId = seqMtId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seqMtId != null ? seqMtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SmsMtLog)) {
            return false;
        }
        SmsMtLog other = (SmsMtLog) object;
        if ((this.seqMtId == null && other.seqMtId != null) || (this.seqMtId != null && !this.seqMtId.equals(other.seqMtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.SmsMtLog[ seqMtId=" + seqMtId + " ]";
    }
    
}
