/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "TYPE_LIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeList.findAll", query = "SELECT t FROM TypeList t"),
    @NamedQuery(name = "TypeList.findByTypeId", query = "SELECT t FROM TypeList t WHERE t.typeId = :typeId"),
    @NamedQuery(name = "TypeList.findByTypeCode", query = "SELECT t FROM TypeList t WHERE t.typeCode = :typeCode"),
    @NamedQuery(name = "TypeList.findByTypeName", query = "SELECT t FROM TypeList t WHERE t.typeName = :typeName"),
    @NamedQuery(name = "TypeList.findByCreateDate", query = "SELECT t FROM TypeList t WHERE t.createDate = :createDate"),
    @NamedQuery(name = "TypeList.findByLastUpdate", query = "SELECT t FROM TypeList t WHERE t.lastUpdate = :lastUpdate")})
public class TypeList implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TYPE_ID")
    private BigDecimal typeId;
    @Size(max = 100)
    @Column(name = "TYPE_CODE")
    private String typeCode;
    @Size(max = 100)
    @Column(name = "TYPE_NAME")
    private String typeName;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @OneToMany(mappedBy = "type")
    private List<CmdcodeList> cmds;
    
    public TypeList() {
    }

    public TypeList(BigDecimal typeId) {
        this.typeId = typeId;
    }

    public List<CmdcodeList> getCmds() {
        return cmds;
    }

    public void setCmds(List<CmdcodeList> cmds) {
        this.cmds = cmds;
    }

   

    public BigDecimal getTypeId() {
        return typeId;
    }

    public void setTypeId(BigDecimal typeId) {
        this.typeId = typeId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeId != null ? typeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeList)) {
            return false;
        }
        TypeList other = (TypeList) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.TypeList[ typeId=" + typeId + " ]";
    }
    
}
