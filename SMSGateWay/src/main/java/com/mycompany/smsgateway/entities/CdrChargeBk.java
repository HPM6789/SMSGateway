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
@Table(name = "CDR_CHARGE_BK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CdrChargeBk.findAll", query = "SELECT c FROM CdrChargeBk c"),
    @NamedQuery(name = "CdrChargeBk.findByCdrChargeId", query = "SELECT c FROM CdrChargeBk c WHERE c.cdrChargeId = :cdrChargeId"),
    @NamedQuery(name = "CdrChargeBk.findByShortcode", query = "SELECT c FROM CdrChargeBk c WHERE c.shortcode = :shortcode"),
    @NamedQuery(name = "CdrChargeBk.findByCmdCode", query = "SELECT c FROM CdrChargeBk c WHERE c.cmdCode = :cmdCode"),
    @NamedQuery(name = "CdrChargeBk.findByPrice", query = "SELECT c FROM CdrChargeBk c WHERE c.price = :price"),
    @NamedQuery(name = "CdrChargeBk.findByCpId", query = "SELECT c FROM CdrChargeBk c WHERE c.cpId = :cpId"),
    @NamedQuery(name = "CdrChargeBk.findByCpCode", query = "SELECT c FROM CdrChargeBk c WHERE c.cpCode = :cpCode"),
    @NamedQuery(name = "CdrChargeBk.findByMtId", query = "SELECT c FROM CdrChargeBk c WHERE c.mtId = :mtId"),
    @NamedQuery(name = "CdrChargeBk.findByActionDate", query = "SELECT c FROM CdrChargeBk c WHERE c.actionDate = :actionDate"),
    @NamedQuery(name = "CdrChargeBk.findByStatus", query = "SELECT c FROM CdrChargeBk c WHERE c.status = :status"),
    @NamedQuery(name = "CdrChargeBk.findByMod100", query = "SELECT c FROM CdrChargeBk c WHERE c.mod100 = :mod100"),
    @NamedQuery(name = "CdrChargeBk.findByMsisdn", query = "SELECT c FROM CdrChargeBk c WHERE c.msisdn = :msisdn"),
    @NamedQuery(name = "CdrChargeBk.findByChannel", query = "SELECT c FROM CdrChargeBk c WHERE c.channel = :channel"),
    @NamedQuery(name = "CdrChargeBk.findByMoId", query = "SELECT c FROM CdrChargeBk c WHERE c.moId = :moId"),
    @NamedQuery(name = "CdrChargeBk.findByStatusExpCdr", query = "SELECT c FROM CdrChargeBk c WHERE c.statusExpCdr = :statusExpCdr"),
    @NamedQuery(name = "CdrChargeBk.findByReceiveMoTime", query = "SELECT c FROM CdrChargeBk c WHERE c.receiveMoTime = :receiveMoTime"),
    @NamedQuery(name = "CdrChargeBk.findByRequestId", query = "SELECT c FROM CdrChargeBk c WHERE c.requestId = :requestId"),
    @NamedQuery(name = "CdrChargeBk.findByStartTimeCharge", query = "SELECT c FROM CdrChargeBk c WHERE c.startTimeCharge = :startTimeCharge"),
    @NamedQuery(name = "CdrChargeBk.findByTimeCharge", query = "SELECT c FROM CdrChargeBk c WHERE c.timeCharge = :timeCharge"),
    @NamedQuery(name = "CdrChargeBk.findByEndTimeCharge", query = "SELECT c FROM CdrChargeBk c WHERE c.endTimeCharge = :endTimeCharge")})
public class CdrChargeBk implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CDR_CHARGE_ID")
    private BigDecimal cdrChargeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SHORTCODE")
    private String shortcode;
    @Size(max = 20)
    @Column(name = "CMD_CODE")
    private String cmdCode;
    @Column(name = "PRICE")
    private BigInteger price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CP_ID")
    private BigInteger cpId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CP_CODE")
    private String cpCode;
    @Column(name = "MT_ID")
    private BigInteger mtId;
    @Column(name = "ACTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actionDate;
    @Column(name = "STATUS")
    private BigInteger status;
    @Column(name = "MOD100")
    private BigInteger mod100;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MSISDN")
    private String msisdn;
    @Size(max = 20)
    @Column(name = "CHANNEL")
    private String channel;
    @Column(name = "MO_ID")
    private BigInteger moId;
    @Column(name = "STATUS_EXP_CDR")
    private BigInteger statusExpCdr;
    @Column(name = "RECEIVE_MO_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveMoTime;
    @Size(max = 100)
    @Column(name = "REQUEST_ID")
    private String requestId;
    @Column(name = "START_TIME_CHARGE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTimeCharge;
    @Column(name = "TIME_CHARGE")
    private BigInteger timeCharge;
    @Column(name = "END_TIME_CHARGE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTimeCharge;

    public CdrChargeBk() {
    }

    public CdrChargeBk(BigDecimal cdrChargeId) {
        this.cdrChargeId = cdrChargeId;
    }

    public CdrChargeBk(BigDecimal cdrChargeId, String shortcode, BigInteger cpId, String cpCode, String msisdn) {
        this.cdrChargeId = cdrChargeId;
        this.shortcode = shortcode;
        this.cpId = cpId;
        this.cpCode = cpCode;
        this.msisdn = msisdn;
    }

    public BigDecimal getCdrChargeId() {
        return cdrChargeId;
    }

    public void setCdrChargeId(BigDecimal cdrChargeId) {
        this.cdrChargeId = cdrChargeId;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public String getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public BigInteger getCpId() {
        return cpId;
    }

    public void setCpId(BigInteger cpId) {
        this.cpId = cpId;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public BigInteger getMtId() {
        return mtId;
    }

    public void setMtId(BigInteger mtId) {
        this.mtId = mtId;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public BigInteger getStatus() {
        return status;
    }

    public void setStatus(BigInteger status) {
        this.status = status;
    }

    public BigInteger getMod100() {
        return mod100;
    }

    public void setMod100(BigInteger mod100) {
        this.mod100 = mod100;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public BigInteger getMoId() {
        return moId;
    }

    public void setMoId(BigInteger moId) {
        this.moId = moId;
    }

    public BigInteger getStatusExpCdr() {
        return statusExpCdr;
    }

    public void setStatusExpCdr(BigInteger statusExpCdr) {
        this.statusExpCdr = statusExpCdr;
    }

    public Date getReceiveMoTime() {
        return receiveMoTime;
    }

    public void setReceiveMoTime(Date receiveMoTime) {
        this.receiveMoTime = receiveMoTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Date getStartTimeCharge() {
        return startTimeCharge;
    }

    public void setStartTimeCharge(Date startTimeCharge) {
        this.startTimeCharge = startTimeCharge;
    }

    public BigInteger getTimeCharge() {
        return timeCharge;
    }

    public void setTimeCharge(BigInteger timeCharge) {
        this.timeCharge = timeCharge;
    }

    public Date getEndTimeCharge() {
        return endTimeCharge;
    }

    public void setEndTimeCharge(Date endTimeCharge) {
        this.endTimeCharge = endTimeCharge;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdrChargeId != null ? cdrChargeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CdrChargeBk)) {
            return false;
        }
        CdrChargeBk other = (CdrChargeBk) object;
        if ((this.cdrChargeId == null && other.cdrChargeId != null) || (this.cdrChargeId != null && !this.cdrChargeId.equals(other.cdrChargeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.CdrChargeBk[ cdrChargeId=" + cdrChargeId + " ]";
    }
    
}
