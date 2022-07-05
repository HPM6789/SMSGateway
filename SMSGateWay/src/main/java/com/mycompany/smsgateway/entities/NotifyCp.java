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
@Table(name = "NOTIFY_CP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotifyCp.findAll", query = "SELECT n FROM NotifyCp n"),
    @NamedQuery(name = "NotifyCp.findByNotifyId", query = "SELECT n FROM NotifyCp n WHERE n.notifyId = :notifyId"),
    @NamedQuery(name = "NotifyCp.findByMoReceiveUrl", query = "SELECT n FROM NotifyCp n WHERE n.moReceiveUrl = :moReceiveUrl"),
    @NamedQuery(name = "NotifyCp.findByCpId", query = "SELECT n FROM NotifyCp n WHERE n.cpId = :cpId"),
    @NamedQuery(name = "NotifyCp.findByNote", query = "SELECT n FROM NotifyCp n WHERE n.note = :note"),
    @NamedQuery(name = "NotifyCp.findByCreateDate", query = "SELECT n FROM NotifyCp n WHERE n.createDate = :createDate"),
    @NamedQuery(name = "NotifyCp.findByLastUpdate", query = "SELECT n FROM NotifyCp n WHERE n.lastUpdate = :lastUpdate"),
    @NamedQuery(name = "NotifyCp.findByStatus", query = "SELECT n FROM NotifyCp n WHERE n.status = :status"),
    @NamedQuery(name = "NotifyCp.findByShcodeId", query = "SELECT n FROM NotifyCp n WHERE n.shcodeId = :shcodeId"),
    @NamedQuery(name = "NotifyCp.findByMoReceiveUrlBkp", query = "SELECT n FROM NotifyCp n WHERE n.moReceiveUrlBkp = :moReceiveUrlBkp")})
public class NotifyCp implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTIFY_ID")
    private BigDecimal notifyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "MO_RECEIVE_URL")
    private String moReceiveUrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CP_ID")
    private BigInteger cpId;
    @Size(max = 500)
    @Column(name = "NOTE")
    private String note;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Column(name = "STATUS")
    private BigInteger status;
    @Column(name = "SHCODE_ID")
    private BigInteger shcodeId;
    @Size(max = 500)
    @Column(name = "MO_RECEIVE_URL_BKP")
    private String moReceiveUrlBkp;

    public NotifyCp() {
    }

    public NotifyCp(BigDecimal notifyId) {
        this.notifyId = notifyId;
    }

    public NotifyCp(BigDecimal notifyId, String moReceiveUrl, BigInteger cpId) {
        this.notifyId = notifyId;
        this.moReceiveUrl = moReceiveUrl;
        this.cpId = cpId;
    }

    public BigDecimal getNotifyId() {
        return notifyId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notifyId != null ? notifyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotifyCp)) {
            return false;
        }
        NotifyCp other = (NotifyCp) object;
        if ((this.notifyId == null && other.notifyId != null) || (this.notifyId != null && !this.notifyId.equals(other.notifyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.NotifyCp[ notifyId=" + notifyId + " ]";
    }
    
}
