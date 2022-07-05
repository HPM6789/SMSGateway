/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Minh Hieu Pham
 */
@Entity
@Table(name = "DREPORT_REVENUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DreportRevenue.findAll", query = "SELECT d FROM DreportRevenue d"),
    @NamedQuery(name = "DreportRevenue.findByDatetime", query = "SELECT d FROM DreportRevenue d WHERE d.dreportRevenuePK.datetime = :datetime"),
    @NamedQuery(name = "DreportRevenue.findByShortCode", query = "SELECT d FROM DreportRevenue d WHERE d.dreportRevenuePK.shortCode = :shortCode"),
    @NamedQuery(name = "DreportRevenue.findByRevenueTotal", query = "SELECT d FROM DreportRevenue d WHERE d.revenueTotal = :revenueTotal"),
    @NamedQuery(name = "DreportRevenue.findByRevenueMo", query = "SELECT d FROM DreportRevenue d WHERE d.revenueMo = :revenueMo"),
    @NamedQuery(name = "DreportRevenue.findByRevenueMtOver", query = "SELECT d FROM DreportRevenue d WHERE d.revenueMtOver = :revenueMtOver"),
    @NamedQuery(name = "DreportRevenue.findByRevenueRefund", query = "SELECT d FROM DreportRevenue d WHERE d.revenueRefund = :revenueRefund"),
    @NamedQuery(name = "DreportRevenue.findByCntMoTotal", query = "SELECT d FROM DreportRevenue d WHERE d.cntMoTotal = :cntMoTotal"),
    @NamedQuery(name = "DreportRevenue.findByCntMoCharge", query = "SELECT d FROM DreportRevenue d WHERE d.cntMoCharge = :cntMoCharge"),
    @NamedQuery(name = "DreportRevenue.findByCntMoNotEnoughMoney", query = "SELECT d FROM DreportRevenue d WHERE d.cntMoNotEnoughMoney = :cntMoNotEnoughMoney"),
    @NamedQuery(name = "DreportRevenue.findByCntMoInvalidCmd", query = "SELECT d FROM DreportRevenue d WHERE d.cntMoInvalidCmd = :cntMoInvalidCmd"),
    @NamedQuery(name = "DreportRevenue.findByCntMoRefund", query = "SELECT d FROM DreportRevenue d WHERE d.cntMoRefund = :cntMoRefund"),
    @NamedQuery(name = "DreportRevenue.findByCntMoOther", query = "SELECT d FROM DreportRevenue d WHERE d.cntMoOther = :cntMoOther"),
    @NamedQuery(name = "DreportRevenue.findByCntMtTotal", query = "SELECT d FROM DreportRevenue d WHERE d.cntMtTotal = :cntMtTotal"),
    @NamedQuery(name = "DreportRevenue.findByCntMtQuota", query = "SELECT d FROM DreportRevenue d WHERE d.cntMtQuota = :cntMtQuota"),
    @NamedQuery(name = "DreportRevenue.findByCntMtOver", query = "SELECT d FROM DreportRevenue d WHERE d.cntMtOver = :cntMtOver"),
    @NamedQuery(name = "DreportRevenue.findBySysDate", query = "SELECT d FROM DreportRevenue d WHERE d.sysDate = :sysDate"),
    @NamedQuery(name = "DreportRevenue.findByCntMtTotalSegment", query = "SELECT d FROM DreportRevenue d WHERE d.cntMtTotalSegment = :cntMtTotalSegment"),
    @NamedQuery(name = "DreportRevenue.findByRevenueMtQuota", query = "SELECT d FROM DreportRevenue d WHERE d.revenueMtQuota = :revenueMtQuota"),
    @NamedQuery(name = "DreportRevenue.findByCntMoRefundCp", query = "SELECT d FROM DreportRevenue d WHERE d.cntMoRefundCp = :cntMoRefundCp")})
public class DreportRevenue implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DreportRevenuePK dreportRevenuePK;
    @Column(name = "REVENUE_TOTAL")
    private Long revenueTotal;
    @Column(name = "REVENUE_MO")
    private Long revenueMo;
    @Column(name = "REVENUE_MT_OVER")
    private Long revenueMtOver;
    @Column(name = "REVENUE_REFUND")
    private Long revenueRefund;
    @Column(name = "CNT_MO_TOTAL")
    private Long cntMoTotal;
    @Column(name = "CNT_MO_CHARGE")
    private Long cntMoCharge;
    @Column(name = "CNT_MO_NOT_ENOUGH_MONEY")
    private Long cntMoNotEnoughMoney;
    @Column(name = "CNT_MO_INVALID_CMD")
    private Long cntMoInvalidCmd;
    @Column(name = "CNT_MO_REFUND")
    private Long cntMoRefund;
    @Column(name = "CNT_MO_OTHER")
    private Long cntMoOther;
    @Column(name = "CNT_MT_TOTAL")
    private Long cntMtTotal;
    @Column(name = "CNT_MT_QUOTA")
    private Long cntMtQuota;
    @Column(name = "CNT_MT_OVER")
    private Long cntMtOver;
    @Column(name = "SYS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sysDate;
    @Column(name = "CNT_MT_TOTAL_SEGMENT")
    private Long cntMtTotalSegment;
    @Column(name = "REVENUE_MT_QUOTA")
    private Long revenueMtQuota;
    @Column(name = "CNT_MO_REFUND_CP")
    private Long cntMoRefundCp;

    public DreportRevenue() {
    }

    public DreportRevenue(DreportRevenuePK dreportRevenuePK) {
        this.dreportRevenuePK = dreportRevenuePK;
    }

    public DreportRevenue(String datetime, String shortCode) {
        this.dreportRevenuePK = new DreportRevenuePK(datetime, shortCode);
    }

    public DreportRevenuePK getDreportRevenuePK() {
        return dreportRevenuePK;
    }

    public void setDreportRevenuePK(DreportRevenuePK dreportRevenuePK) {
        this.dreportRevenuePK = dreportRevenuePK;
    }

    public Long getRevenueTotal() {
        return revenueTotal;
    }

    public void setRevenueTotal(Long revenueTotal) {
        this.revenueTotal = revenueTotal;
    }

    public Long getRevenueMo() {
        return revenueMo;
    }

    public void setRevenueMo(Long revenueMo) {
        this.revenueMo = revenueMo;
    }

    public Long getRevenueMtOver() {
        return revenueMtOver;
    }

    public void setRevenueMtOver(Long revenueMtOver) {
        this.revenueMtOver = revenueMtOver;
    }

    public Long getRevenueRefund() {
        return revenueRefund;
    }

    public void setRevenueRefund(Long revenueRefund) {
        this.revenueRefund = revenueRefund;
    }

    public Long getCntMoTotal() {
        return cntMoTotal;
    }

    public void setCntMoTotal(Long cntMoTotal) {
        this.cntMoTotal = cntMoTotal;
    }

    public Long getCntMoCharge() {
        return cntMoCharge;
    }

    public void setCntMoCharge(Long cntMoCharge) {
        this.cntMoCharge = cntMoCharge;
    }

    public Long getCntMoNotEnoughMoney() {
        return cntMoNotEnoughMoney;
    }

    public void setCntMoNotEnoughMoney(Long cntMoNotEnoughMoney) {
        this.cntMoNotEnoughMoney = cntMoNotEnoughMoney;
    }

    public Long getCntMoInvalidCmd() {
        return cntMoInvalidCmd;
    }

    public void setCntMoInvalidCmd(Long cntMoInvalidCmd) {
        this.cntMoInvalidCmd = cntMoInvalidCmd;
    }

    public Long getCntMoRefund() {
        return cntMoRefund;
    }

    public void setCntMoRefund(Long cntMoRefund) {
        this.cntMoRefund = cntMoRefund;
    }

    public Long getCntMoOther() {
        return cntMoOther;
    }

    public void setCntMoOther(Long cntMoOther) {
        this.cntMoOther = cntMoOther;
    }

    public Long getCntMtTotal() {
        return cntMtTotal;
    }

    public void setCntMtTotal(Long cntMtTotal) {
        this.cntMtTotal = cntMtTotal;
    }

    public Long getCntMtQuota() {
        return cntMtQuota;
    }

    public void setCntMtQuota(Long cntMtQuota) {
        this.cntMtQuota = cntMtQuota;
    }

    public Long getCntMtOver() {
        return cntMtOver;
    }

    public void setCntMtOver(Long cntMtOver) {
        this.cntMtOver = cntMtOver;
    }

    public Date getSysDate() {
        return sysDate;
    }

    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }

    public Long getCntMtTotalSegment() {
        return cntMtTotalSegment;
    }

    public void setCntMtTotalSegment(Long cntMtTotalSegment) {
        this.cntMtTotalSegment = cntMtTotalSegment;
    }

    public Long getRevenueMtQuota() {
        return revenueMtQuota;
    }

    public void setRevenueMtQuota(Long revenueMtQuota) {
        this.revenueMtQuota = revenueMtQuota;
    }

    public Long getCntMoRefundCp() {
        return cntMoRefundCp;
    }

    public void setCntMoRefundCp(Long cntMoRefundCp) {
        this.cntMoRefundCp = cntMoRefundCp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dreportRevenuePK != null ? dreportRevenuePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DreportRevenue)) {
            return false;
        }
        DreportRevenue other = (DreportRevenue) object;
        if ((this.dreportRevenuePK == null && other.dreportRevenuePK != null) || (this.dreportRevenuePK != null && !this.dreportRevenuePK.equals(other.dreportRevenuePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.DreportRevenue[ dreportRevenuePK=" + dreportRevenuePK + " ]";
    }
    
}
