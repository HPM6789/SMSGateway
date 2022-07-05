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
@Table(name = "CDR_REFUND_BK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CdrRefundBk.findAll", query = "SELECT c FROM CdrRefundBk c"),
    @NamedQuery(name = "CdrRefundBk.findByCdrRefundId", query = "SELECT c FROM CdrRefundBk c WHERE c.cdrRefundId = :cdrRefundId"),
    @NamedQuery(name = "CdrRefundBk.findByShortcode", query = "SELECT c FROM CdrRefundBk c WHERE c.shortcode = :shortcode"),
    @NamedQuery(name = "CdrRefundBk.findByCmdCode", query = "SELECT c FROM CdrRefundBk c WHERE c.cmdCode = :cmdCode"),
    @NamedQuery(name = "CdrRefundBk.findByPrice", query = "SELECT c FROM CdrRefundBk c WHERE c.price = :price"),
    @NamedQuery(name = "CdrRefundBk.findByCpId", query = "SELECT c FROM CdrRefundBk c WHERE c.cpId = :cpId"),
    @NamedQuery(name = "CdrRefundBk.findByCpCode", query = "SELECT c FROM CdrRefundBk c WHERE c.cpCode = :cpCode"),
    @NamedQuery(name = "CdrRefundBk.findByMtId", query = "SELECT c FROM CdrRefundBk c WHERE c.mtId = :mtId"),
    @NamedQuery(name = "CdrRefundBk.findByStatus", query = "SELECT c FROM CdrRefundBk c WHERE c.status = :status"),
    @NamedQuery(name = "CdrRefundBk.findByActionDate", query = "SELECT c FROM CdrRefundBk c WHERE c.actionDate = :actionDate"),
    @NamedQuery(name = "CdrRefundBk.findByMsisdn", query = "SELECT c FROM CdrRefundBk c WHERE c.msisdn = :msisdn"),
    @NamedQuery(name = "CdrRefundBk.findByChannel", query = "SELECT c FROM CdrRefundBk c WHERE c.channel = :channel"),
    @NamedQuery(name = "CdrRefundBk.findByExpireDate", query = "SELECT c FROM CdrRefundBk c WHERE c.expireDate = :expireDate"),
    @NamedQuery(name = "CdrRefundBk.findByMod100", query = "SELECT c FROM CdrRefundBk c WHERE c.mod100 = :mod100"),
    @NamedQuery(name = "CdrRefundBk.findByMoId", query = "SELECT c FROM CdrRefundBk c WHERE c.moId = :moId"),
    @NamedQuery(name = "CdrRefundBk.findByReceiveMtDate", query = "SELECT c FROM CdrRefundBk c WHERE c.receiveMtDate = :receiveMtDate"),
    @NamedQuery(name = "CdrRefundBk.findByReceiveMoTime", query = "SELECT c FROM CdrRefundBk c WHERE c.receiveMoTime = :receiveMoTime"),
    @NamedQuery(name = "CdrRefundBk.findByStatusExpCdr", query = "SELECT c FROM CdrRefundBk c WHERE c.statusExpCdr = :statusExpCdr"),
    @NamedQuery(name = "CdrRefundBk.findByRequestId", query = "SELECT c FROM CdrRefundBk c WHERE c.requestId = :requestId"),
    @NamedQuery(name = "CdrRefundBk.findByCreateTime", query = "SELECT c FROM CdrRefundBk c WHERE c.createTime = :createTime"),
    @NamedQuery(name = "CdrRefundBk.findBySource", query = "SELECT c FROM CdrRefundBk c WHERE c.source = :source")})
public class CdrRefundBk implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CDR_REFUND_ID")
    private BigDecimal cdrRefundId;
    @Size(max = 20)
    @Column(name = "SHORTCODE")
    private String shortcode;
    @Size(max = 50)
    @Column(name = "CMD_CODE")
    private String cmdCode;
    @Column(name = "PRICE")
    private BigInteger price;
    @Column(name = "CP_ID")
    private BigInteger cpId;
    @Size(max = 50)
    @Column(name = "CP_CODE")
    private String cpCode;
    @Column(name = "MT_ID")
    private BigInteger mtId;
    @Column(name = "STATUS")
    private BigInteger status;
    @Column(name = "ACTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actionDate;
    @Size(max = 20)
    @Column(name = "MSISDN")
    private String msisdn;
    @Size(max = 20)
    @Column(name = "CHANNEL")
    private String channel;
    @Column(name = "EXPIRE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;
    @Column(name = "MOD100")
    private BigInteger mod100;
    @Column(name = "MO_ID")
    private BigInteger moId;
    @Column(name = "RECEIVE_MT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveMtDate;
    @Column(name = "RECEIVE_MO_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveMoTime;
    @Column(name = "STATUS_EXP_CDR")
    private BigInteger statusExpCdr;
    @Size(max = 100)
    @Column(name = "REQUEST_ID")
    private String requestId;
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Size(max = 100)
    @Column(name = "SOURCE")
    private String source;

    public CdrRefundBk() {
    }

    public CdrRefundBk(BigDecimal cdrRefundId) {
        this.cdrRefundId = cdrRefundId;
    }

    public BigDecimal getCdrRefundId() {
        return cdrRefundId;
    }

    public void setCdrRefundId(BigDecimal cdrRefundId) {
        this.cdrRefundId = cdrRefundId;
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

    public BigInteger getStatus() {
        return status;
    }

    public void setStatus(BigInteger status) {
        this.status = status;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
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

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public BigInteger getMod100() {
        return mod100;
    }

    public void setMod100(BigInteger mod100) {
        this.mod100 = mod100;
    }

    public BigInteger getMoId() {
        return moId;
    }

    public void setMoId(BigInteger moId) {
        this.moId = moId;
    }

    public Date getReceiveMtDate() {
        return receiveMtDate;
    }

    public void setReceiveMtDate(Date receiveMtDate) {
        this.receiveMtDate = receiveMtDate;
    }

    public Date getReceiveMoTime() {
        return receiveMoTime;
    }

    public void setReceiveMoTime(Date receiveMoTime) {
        this.receiveMoTime = receiveMoTime;
    }

    public BigInteger getStatusExpCdr() {
        return statusExpCdr;
    }

    public void setStatusExpCdr(BigInteger statusExpCdr) {
        this.statusExpCdr = statusExpCdr;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdrRefundId != null ? cdrRefundId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CdrRefundBk)) {
            return false;
        }
        CdrRefundBk other = (CdrRefundBk) object;
        if ((this.cdrRefundId == null && other.cdrRefundId != null) || (this.cdrRefundId != null && !this.cdrRefundId.equals(other.cdrRefundId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.CdrRefundBk[ cdrRefundId=" + cdrRefundId + " ]";
    }
    
}
