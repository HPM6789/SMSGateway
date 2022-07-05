/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Minh Hieu Pham
 */
@Entity
@Table(name = "KPI_MO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KpiMo.findAll", query = "SELECT k FROM KpiMo k"),
    @NamedQuery(name = "KpiMo.findByDatetime", query = "SELECT k FROM KpiMo k WHERE k.kpiMoPK.datetime = :datetime"),
    @NamedQuery(name = "KpiMo.findByMoReceive", query = "SELECT k FROM KpiMo k WHERE k.moReceive = :moReceive"),
    @NamedQuery(name = "KpiMo.findByMoError", query = "SELECT k FROM KpiMo k WHERE k.moError = :moError"),
    @NamedQuery(name = "KpiMo.findByMoPending", query = "SELECT k FROM KpiMo k WHERE k.moPending = :moPending"),
    @NamedQuery(name = "KpiMo.findByMoFinished", query = "SELECT k FROM KpiMo k WHERE k.moFinished = :moFinished"),
    @NamedQuery(name = "KpiMo.findByMoShortcode", query = "SELECT k FROM KpiMo k WHERE k.kpiMoPK.moShortcode = :moShortcode"),
    @NamedQuery(name = "KpiMo.findByMoNotifyPending", query = "SELECT k FROM KpiMo k WHERE k.moNotifyPending = :moNotifyPending"),
    @NamedQuery(name = "KpiMo.findByMoNotifyFinished", query = "SELECT k FROM KpiMo k WHERE k.moNotifyFinished = :moNotifyFinished"),
    @NamedQuery(name = "KpiMo.findByMoRefund", query = "SELECT k FROM KpiMo k WHERE k.moRefund = :moRefund"),
    @NamedQuery(name = "KpiMo.findByMoCharge", query = "SELECT k FROM KpiMo k WHERE k.moCharge = :moCharge")})
public class KpiMo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KpiMoPK kpiMoPK;
    @Column(name = "MO_RECEIVE")
    private BigInteger moReceive;
    @Column(name = "MO_ERROR")
    private BigInteger moError;
    @Column(name = "MO_PENDING")
    private BigInteger moPending;
    @Column(name = "MO_FINISHED")
    private BigInteger moFinished;
    @Column(name = "MO_NOTIFY_PENDING")
    private BigInteger moNotifyPending;
    @Column(name = "MO_NOTIFY_FINISHED")
    private BigInteger moNotifyFinished;
    @Column(name = "MO_REFUND")
    private BigInteger moRefund;
    @Column(name = "MO_CHARGE")
    private BigInteger moCharge;

    public KpiMo() {
    }

    public KpiMo(KpiMoPK kpiMoPK) {
        this.kpiMoPK = kpiMoPK;
    }

    public KpiMo(String datetime, BigInteger moShortcode) {
        this.kpiMoPK = new KpiMoPK(datetime, moShortcode);
    }

    public KpiMoPK getKpiMoPK() {
        return kpiMoPK;
    }

    public void setKpiMoPK(KpiMoPK kpiMoPK) {
        this.kpiMoPK = kpiMoPK;
    }

    public BigInteger getMoReceive() {
        return moReceive;
    }

    public void setMoReceive(BigInteger moReceive) {
        this.moReceive = moReceive;
    }

    public BigInteger getMoError() {
        return moError;
    }

    public void setMoError(BigInteger moError) {
        this.moError = moError;
    }

    public BigInteger getMoPending() {
        return moPending;
    }

    public void setMoPending(BigInteger moPending) {
        this.moPending = moPending;
    }

    public BigInteger getMoFinished() {
        return moFinished;
    }

    public void setMoFinished(BigInteger moFinished) {
        this.moFinished = moFinished;
    }

    public BigInteger getMoNotifyPending() {
        return moNotifyPending;
    }

    public void setMoNotifyPending(BigInteger moNotifyPending) {
        this.moNotifyPending = moNotifyPending;
    }

    public BigInteger getMoNotifyFinished() {
        return moNotifyFinished;
    }

    public void setMoNotifyFinished(BigInteger moNotifyFinished) {
        this.moNotifyFinished = moNotifyFinished;
    }

    public BigInteger getMoRefund() {
        return moRefund;
    }

    public void setMoRefund(BigInteger moRefund) {
        this.moRefund = moRefund;
    }

    public BigInteger getMoCharge() {
        return moCharge;
    }

    public void setMoCharge(BigInteger moCharge) {
        this.moCharge = moCharge;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kpiMoPK != null ? kpiMoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KpiMo)) {
            return false;
        }
        KpiMo other = (KpiMo) object;
        if ((this.kpiMoPK == null && other.kpiMoPK != null) || (this.kpiMoPK != null && !this.kpiMoPK.equals(other.kpiMoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.KpiMo[ kpiMoPK=" + kpiMoPK + " ]";
    }
    
}
