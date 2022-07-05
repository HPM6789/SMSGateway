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
@Table(name = "EXPORT_CP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExportCp.findAll", query = "SELECT e FROM ExportCp e"),
    @NamedQuery(name = "ExportCp.findByExportCpId", query = "SELECT e FROM ExportCp e WHERE e.exportCpId = :exportCpId"),
    @NamedQuery(name = "ExportCp.findByCpId", query = "SELECT e FROM ExportCp e WHERE e.cpId = :cpId"),
    @NamedQuery(name = "ExportCp.findByEnable", query = "SELECT e FROM ExportCp e WHERE e.enable = :enable"),
    @NamedQuery(name = "ExportCp.findByLastUpdate", query = "SELECT e FROM ExportCp e WHERE e.lastUpdate = :lastUpdate"),
    @NamedQuery(name = "ExportCp.findByPrefixName", query = "SELECT e FROM ExportCp e WHERE e.prefixName = :prefixName"),
    @NamedQuery(name = "ExportCp.findByRemoteDir", query = "SELECT e FROM ExportCp e WHERE e.remoteDir = :remoteDir"),
    @NamedQuery(name = "ExportCp.findByShcodeId", query = "SELECT e FROM ExportCp e WHERE e.shcodeId = :shcodeId")})
public class ExportCp implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXPORT_CP_ID")
    private BigDecimal exportCpId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CP_ID")
    private BigInteger cpId;
    @Column(name = "ENABLE")
    private Short enable;
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Size(max = 500)
    @Column(name = "PREFIX_NAME")
    private String prefixName;
    @Size(max = 500)
    @Column(name = "REMOTE_DIR")
    private String remoteDir;
    @Column(name = "SHCODE_ID")
    private BigInteger shcodeId;

    public ExportCp() {
    }

    public ExportCp(BigDecimal exportCpId) {
        this.exportCpId = exportCpId;
    }

    public ExportCp(BigDecimal exportCpId, BigInteger cpId) {
        this.exportCpId = exportCpId;
        this.cpId = cpId;
    }

    public BigDecimal getExportCpId() {
        return exportCpId;
    }

    public void setExportCpId(BigDecimal exportCpId) {
        this.exportCpId = exportCpId;
    }

    public BigInteger getCpId() {
        return cpId;
    }

    public void setCpId(BigInteger cpId) {
        this.cpId = cpId;
    }

    public Short getEnable() {
        return enable;
    }

    public void setEnable(Short enable) {
        this.enable = enable;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getRemoteDir() {
        return remoteDir;
    }

    public void setRemoteDir(String remoteDir) {
        this.remoteDir = remoteDir;
    }

    public BigInteger getShcodeId() {
        return shcodeId;
    }

    public void setShcodeId(BigInteger shcodeId) {
        this.shcodeId = shcodeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exportCpId != null ? exportCpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExportCp)) {
            return false;
        }
        ExportCp other = (ExportCp) object;
        if ((this.exportCpId == null && other.exportCpId != null) || (this.exportCpId != null && !this.exportCpId.equals(other.exportCpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.ExportCp[ exportCpId=" + exportCpId + " ]";
    }
    
}
