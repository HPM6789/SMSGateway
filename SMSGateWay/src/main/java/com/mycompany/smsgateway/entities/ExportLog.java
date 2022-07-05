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
@Table(name = "EXPORT_LOG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExportLog.findAll", query = "SELECT e FROM ExportLog e"),
    @NamedQuery(name = "ExportLog.findByExportLogId", query = "SELECT e FROM ExportLog e WHERE e.exportLogId = :exportLogId"),
    @NamedQuery(name = "ExportLog.findByTaskName", query = "SELECT e FROM ExportLog e WHERE e.taskName = :taskName"),
    @NamedQuery(name = "ExportLog.findByCpCode", query = "SELECT e FROM ExportLog e WHERE e.cpCode = :cpCode"),
    @NamedQuery(name = "ExportLog.findByCreateDate", query = "SELECT e FROM ExportLog e WHERE e.createDate = :createDate"),
    @NamedQuery(name = "ExportLog.findByInsId", query = "SELECT e FROM ExportLog e WHERE e.insId = :insId"),
    @NamedQuery(name = "ExportLog.findByRowCount", query = "SELECT e FROM ExportLog e WHERE e.rowCount = :rowCount"),
    @NamedQuery(name = "ExportLog.findByRevenue", query = "SELECT e FROM ExportLog e WHERE e.revenue = :revenue"),
    @NamedQuery(name = "ExportLog.findByResultCode", query = "SELECT e FROM ExportLog e WHERE e.resultCode = :resultCode"),
    @NamedQuery(name = "ExportLog.findByDescription", query = "SELECT e FROM ExportLog e WHERE e.description = :description"),
    @NamedQuery(name = "ExportLog.findByShortcode", query = "SELECT e FROM ExportLog e WHERE e.shortcode = :shortcode")})
public class ExportLog implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXPORT_LOG_ID")
    private BigDecimal exportLogId;
    @Size(max = 255)
    @Column(name = "TASK_NAME")
    private String taskName;
    @Size(max = 20)
    @Column(name = "CP_CODE")
    private String cpCode;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "INS_ID")
    private BigInteger insId;
    @Column(name = "ROW_COUNT")
    private BigInteger rowCount;
    @Column(name = "REVENUE")
    private BigInteger revenue;
    @Column(name = "RESULT_CODE")
    private BigInteger resultCode;
    @Size(max = 500)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 20)
    @Column(name = "SHORTCODE")
    private String shortcode;

    public ExportLog() {
    }

    public ExportLog(BigDecimal exportLogId) {
        this.exportLogId = exportLogId;
    }

    public BigDecimal getExportLogId() {
        return exportLogId;
    }

    public void setExportLogId(BigDecimal exportLogId) {
        this.exportLogId = exportLogId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigInteger getInsId() {
        return insId;
    }

    public void setInsId(BigInteger insId) {
        this.insId = insId;
    }

    public BigInteger getRowCount() {
        return rowCount;
    }

    public void setRowCount(BigInteger rowCount) {
        this.rowCount = rowCount;
    }

    public BigInteger getRevenue() {
        return revenue;
    }

    public void setRevenue(BigInteger revenue) {
        this.revenue = revenue;
    }

    public BigInteger getResultCode() {
        return resultCode;
    }

    public void setResultCode(BigInteger resultCode) {
        this.resultCode = resultCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exportLogId != null ? exportLogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExportLog)) {
            return false;
        }
        ExportLog other = (ExportLog) object;
        if ((this.exportLogId == null && other.exportLogId != null) || (this.exportLogId != null && !this.exportLogId.equals(other.exportLogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.ExportLog[ exportLogId=" + exportLogId + " ]";
    }
    
}
