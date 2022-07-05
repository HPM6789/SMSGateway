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
@Table(name = "NOTIFY_QUEUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotifyQueue.findAll", query = "SELECT n FROM NotifyQueue n"),
    @NamedQuery(name = "NotifyQueue.findByQueueId", query = "SELECT n FROM NotifyQueue n WHERE n.queueId = :queueId"),
    @NamedQuery(name = "NotifyQueue.findByCpId", query = "SELECT n FROM NotifyQueue n WHERE n.cpId = :cpId"),
    @NamedQuery(name = "NotifyQueue.findByMsisdn", query = "SELECT n FROM NotifyQueue n WHERE n.msisdn = :msisdn"),
    @NamedQuery(name = "NotifyQueue.findByShortCode", query = "SELECT n FROM NotifyQueue n WHERE n.shortCode = :shortCode"),
    @NamedQuery(name = "NotifyQueue.findByExpireTime", query = "SELECT n FROM NotifyQueue n WHERE n.expireTime = :expireTime"),
    @NamedQuery(name = "NotifyQueue.findByLastNotifyTime", query = "SELECT n FROM NotifyQueue n WHERE n.lastNotifyTime = :lastNotifyTime"),
    @NamedQuery(name = "NotifyQueue.findByRetryCount", query = "SELECT n FROM NotifyQueue n WHERE n.retryCount = :retryCount"),
    @NamedQuery(name = "NotifyQueue.findByCreateTime", query = "SELECT n FROM NotifyQueue n WHERE n.createTime = :createTime"),
    @NamedQuery(name = "NotifyQueue.findByChannel", query = "SELECT n FROM NotifyQueue n WHERE n.channel = :channel"),
    @NamedQuery(name = "NotifyQueue.findByOriginalprice", query = "SELECT n FROM NotifyQueue n WHERE n.originalprice = :originalprice"),
    @NamedQuery(name = "NotifyQueue.findByPrice", query = "SELECT n FROM NotifyQueue n WHERE n.price = :price"),
    @NamedQuery(name = "NotifyQueue.findByCmdCode", query = "SELECT n FROM NotifyQueue n WHERE n.cmdCode = :cmdCode"),
    @NamedQuery(name = "NotifyQueue.findByMod100", query = "SELECT n FROM NotifyQueue n WHERE n.mod100 = :mod100"),
    @NamedQuery(name = "NotifyQueue.findByShcodeId", query = "SELECT n FROM NotifyQueue n WHERE n.shcodeId = :shcodeId"),
    @NamedQuery(name = "NotifyQueue.findByMoId", query = "SELECT n FROM NotifyQueue n WHERE n.moId = :moId"),
    @NamedQuery(name = "NotifyQueue.findByCpCode", query = "SELECT n FROM NotifyQueue n WHERE n.cpCode = :cpCode"),
    @NamedQuery(name = "NotifyQueue.findByReceiveMoTime", query = "SELECT n FROM NotifyQueue n WHERE n.receiveMoTime = :receiveMoTime"),
    @NamedQuery(name = "NotifyQueue.findByEncodeCmd", query = "SELECT n FROM NotifyQueue n WHERE n.encodeCmd = :encodeCmd")})
public class NotifyQueue implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUEUE_ID")
    private BigDecimal queueId;
    @Column(name = "CP_ID")
    private BigInteger cpId;
    @Size(max = 20)
    @Column(name = "MSISDN")
    private String msisdn;
    @Size(max = 100)
    @Column(name = "SHORT_CODE")
    private String shortCode;
    @Column(name = "EXPIRE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireTime;
    @Column(name = "LAST_NOTIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastNotifyTime;
    @Column(name = "RETRY_COUNT")
    private BigInteger retryCount;
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Size(max = 20)
    @Column(name = "CHANNEL")
    private String channel;
    @Column(name = "ORIGINALPRICE")
    private BigInteger originalprice;
    @Column(name = "PRICE")
    private BigInteger price;
    @Size(max = 100)
    @Column(name = "CMD_CODE")
    private String cmdCode;
    @Column(name = "MOD100")
    private BigInteger mod100;
    @Column(name = "SHCODE_ID")
    private BigInteger shcodeId;
    @Column(name = "MO_ID")
    private BigInteger moId;
    @Size(max = 50)
    @Column(name = "CP_CODE")
    private String cpCode;
    @Column(name = "RECEIVE_MO_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveMoTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENCODE_CMD")
    private BigInteger encodeCmd;

    public NotifyQueue() {
    }

    public NotifyQueue(BigDecimal queueId) {
        this.queueId = queueId;
    }

    public NotifyQueue(BigDecimal queueId, BigInteger encodeCmd) {
        this.queueId = queueId;
        this.encodeCmd = encodeCmd;
    }

    public BigDecimal getQueueId() {
        return queueId;
    }

    public void setQueueId(BigDecimal queueId) {
        this.queueId = queueId;
    }

    public BigInteger getCpId() {
        return cpId;
    }

    public void setCpId(BigInteger cpId) {
        this.cpId = cpId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getLastNotifyTime() {
        return lastNotifyTime;
    }

    public void setLastNotifyTime(Date lastNotifyTime) {
        this.lastNotifyTime = lastNotifyTime;
    }

    public BigInteger getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(BigInteger retryCount) {
        this.retryCount = retryCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public BigInteger getOriginalprice() {
        return originalprice;
    }

    public void setOriginalprice(BigInteger originalprice) {
        this.originalprice = originalprice;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public String getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode;
    }

    public BigInteger getMod100() {
        return mod100;
    }

    public void setMod100(BigInteger mod100) {
        this.mod100 = mod100;
    }

    public BigInteger getShcodeId() {
        return shcodeId;
    }

    public void setShcodeId(BigInteger shcodeId) {
        this.shcodeId = shcodeId;
    }

    public BigInteger getMoId() {
        return moId;
    }

    public void setMoId(BigInteger moId) {
        this.moId = moId;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public Date getReceiveMoTime() {
        return receiveMoTime;
    }

    public void setReceiveMoTime(Date receiveMoTime) {
        this.receiveMoTime = receiveMoTime;
    }

    public BigInteger getEncodeCmd() {
        return encodeCmd;
    }

    public void setEncodeCmd(BigInteger encodeCmd) {
        this.encodeCmd = encodeCmd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (queueId != null ? queueId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotifyQueue)) {
            return false;
        }
        NotifyQueue other = (NotifyQueue) object;
        if ((this.queueId == null && other.queueId != null) || (this.queueId != null && !this.queueId.equals(other.queueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.NotifyQueue[ queueId=" + queueId + " ]";
    }
    
}
