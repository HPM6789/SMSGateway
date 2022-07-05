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
@Table(name = "SMS_MO_QUEUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmsMoQueue.findAll", query = "SELECT s FROM SmsMoQueue s"),
    @NamedQuery(name = "SmsMoQueue.findByMoId", query = "SELECT s FROM SmsMoQueue s WHERE s.moId = :moId"),
    @NamedQuery(name = "SmsMoQueue.findByMsisdn", query = "SELECT s FROM SmsMoQueue s WHERE s.msisdn = :msisdn"),
    @NamedQuery(name = "SmsMoQueue.findByMessage", query = "SELECT s FROM SmsMoQueue s WHERE s.message = :message"),
    @NamedQuery(name = "SmsMoQueue.findByShortcode", query = "SELECT s FROM SmsMoQueue s WHERE s.shortcode = :shortcode"),
    @NamedQuery(name = "SmsMoQueue.findByReceiveTime", query = "SELECT s FROM SmsMoQueue s WHERE s.receiveTime = :receiveTime"),
    @NamedQuery(name = "SmsMoQueue.findByProcessTime", query = "SELECT s FROM SmsMoQueue s WHERE s.processTime = :processTime"),
    @NamedQuery(name = "SmsMoQueue.findByStatus", query = "SELECT s FROM SmsMoQueue s WHERE s.status = :status"),
    @NamedQuery(name = "SmsMoQueue.findByMod100", query = "SELECT s FROM SmsMoQueue s WHERE s.mod100 = :mod100"),
    @NamedQuery(name = "SmsMoQueue.findByErrorDesc", query = "SELECT s FROM SmsMoQueue s WHERE s.errorDesc = :errorDesc"),
    @NamedQuery(name = "SmsMoQueue.findByExpireTime", query = "SELECT s FROM SmsMoQueue s WHERE s.expireTime = :expireTime"),
    @NamedQuery(name = "SmsMoQueue.findBySysDate", query = "SELECT s FROM SmsMoQueue s WHERE s.sysDate = :sysDate"),
    @NamedQuery(name = "SmsMoQueue.findByMtId", query = "SELECT s FROM SmsMoQueue s WHERE s.mtId = :mtId"),
    @NamedQuery(name = "SmsMoQueue.findByRetryCharge", query = "SELECT s FROM SmsMoQueue s WHERE s.retryCharge = :retryCharge"),
    @NamedQuery(name = "SmsMoQueue.findByEncodeMsg", query = "SELECT s FROM SmsMoQueue s WHERE s.encodeMsg = :encodeMsg")})
public class SmsMoQueue implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MO_ID")
    private BigDecimal moId;
    @Size(max = 25)
    @Column(name = "MSISDN")
    private String msisdn;
    @Size(max = 255)
    @Column(name = "MESSAGE")
    private String message;
    @Size(max = 255)
    @Column(name = "SHORTCODE")
    private String shortcode;
    @Column(name = "RECEIVE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveTime;
    @Column(name = "PROCESS_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date processTime;
    @Column(name = "STATUS")
    private Short status;
    @Column(name = "MOD100")
    private BigInteger mod100;
    @Size(max = 500)
    @Column(name = "ERROR_DESC")
    private String errorDesc;
    @Column(name = "EXPIRE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireTime;
    @Column(name = "SYS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sysDate;
    @Column(name = "MT_ID")
    private BigInteger mtId;
    @Column(name = "RETRY_CHARGE")
    private BigInteger retryCharge;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENCODE_MSG")
    private BigInteger encodeMsg;

    public SmsMoQueue() {
    }

    public SmsMoQueue(BigDecimal moId) {
        this.moId = moId;
    }

    public SmsMoQueue(BigDecimal moId, BigInteger encodeMsg) {
        this.moId = moId;
        this.encodeMsg = encodeMsg;
    }

    public BigDecimal getMoId() {
        return moId;
    }

    public void setMoId(BigDecimal moId) {
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

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public BigInteger getMod100() {
        return mod100;
    }

    public void setMod100(BigInteger mod100) {
        this.mod100 = mod100;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getSysDate() {
        return sysDate;
    }

    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }

    public BigInteger getMtId() {
        return mtId;
    }

    public void setMtId(BigInteger mtId) {
        this.mtId = mtId;
    }

    public BigInteger getRetryCharge() {
        return retryCharge;
    }

    public void setRetryCharge(BigInteger retryCharge) {
        this.retryCharge = retryCharge;
    }

    public BigInteger getEncodeMsg() {
        return encodeMsg;
    }

    public void setEncodeMsg(BigInteger encodeMsg) {
        this.encodeMsg = encodeMsg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moId != null ? moId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SmsMoQueue)) {
            return false;
        }
        SmsMoQueue other = (SmsMoQueue) object;
        if ((this.moId == null && other.moId != null) || (this.moId != null && !this.moId.equals(other.moId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.SmsMoQueue[ moId=" + moId + " ]";
    }
    
}
