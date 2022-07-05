/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "CMDCODE_LIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmdcodeList.findAll", query = "SELECT c FROM CmdcodeList c"),
    @NamedQuery(name = "CmdcodeList.findByCmdId", query = "SELECT c FROM CmdcodeList c WHERE c.cmdId = :cmdId"),
    @NamedQuery(name = "CmdcodeList.findByCmdName", query = "SELECT c FROM CmdcodeList c WHERE c.cmdName = :cmdName"),
    @NamedQuery(name = "CmdcodeList.findByCmdCode", query = "SELECT c FROM CmdcodeList c WHERE c.cmdCode = :cmdCode"),
//    @NamedQuery(name = "CmdcodeList.findByShortcodeCpId", query = "SELECT c FROM CmdcodeList c WHERE c.shortcodeCpId = :shortcodeCpId"),
    @NamedQuery(name = "CmdcodeList.findByUpdateTime", query = "SELECT c FROM CmdcodeList c WHERE c.updateTime = :updateTime"),
    @NamedQuery(name = "CmdcodeList.findByCreateTime", query = "SELECT c FROM CmdcodeList c WHERE c.createTime = :createTime"),
    @NamedQuery(name = "CmdcodeList.findByStatus", query = "SELECT c FROM CmdcodeList c WHERE c.status = :status"),
//    @NamedQuery(name = "CmdcodeList.findByTypeCode", query = "SELECT c FROM CmdcodeList c WHERE c.typeCode = :typeCode"),
    @NamedQuery(name = "CmdcodeList.findByApproveTime", query = "SELECT c FROM CmdcodeList c WHERE c.approveTime = :approveTime"),
    @NamedQuery(name = "CmdcodeList.findByPrice", query = "SELECT c FROM CmdcodeList c WHERE c.price = :price"),
//    @NamedQuery(name = "CmdcodeList.findByCreatorId", query = "SELECT c FROM CmdcodeList c WHERE c.creatorId = :creatorId"),
//    @NamedQuery(name = "CmdcodeList.findByApproverId", query = "SELECT c FROM CmdcodeList c WHERE c.approverId = :approverId"),
    @NamedQuery(name = "CmdcodeList.findByDescription", query = "SELECT c FROM CmdcodeList c WHERE c.description = :description")})
public class CmdcodeList implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CMD_ID")
    private BigDecimal cmdId;
    @Size(max = 100)
    @Column(name = "CMD_NAME")
    private String cmdName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CMD_CODE")
    private String cmdCode;
    
    
//    @Column(name = "SHORTCODE_CP_ID")
//    private BigInteger shortcodeCpId;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHORTCODE_CP_ID")
    private ShortcodeCp shortcodeCp;
    
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Size(max = 2)
    @Column(name = "STATUS")
    private String status;
//    @Size(max = 50)
//    @Column(name = "TYPE_CODE")
//    private String typeCode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_CODE", referencedColumnName = "TYPE_CODE")
    private TypeList type;
    
    @Column(name = "APPROVE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approveTime;
    @Column(name = "PRICE")
    private Long price;
//    @Column(name = "CREATOR_ID")
//    private Long creatorId;
//    @Column(name = "APPROVER_ID")
//    private Long approverId;
    @Size(max = 2000)
    @Column(name = "DESCRIPTION")
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR_ID")
    private AuthUser creator;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPROVER_ID")
    private AuthUser approver;
    
    public CmdcodeList() {
    }

    public CmdcodeList(BigDecimal cmdId) {
        this.cmdId = cmdId;
    }

    public ShortcodeCp getShortcodeCp() {
        return shortcodeCp;
    }

//    public CmdcodeList(BigDecimal cmdId, String cmdCode, BigInteger shortcodeCpId) {
//        this.cmdId = cmdId;
//        this.cmdCode = cmdCode;
//        this.shortcodeCpId = shortcodeCpId;
//    }
    public void setShortcodeCp(ShortcodeCp shortcodeCp) {
        this.shortcodeCp = shortcodeCp;
    }

    public AuthUser getCreator() {
        return creator;
    }

    public void setCreator(AuthUser creator) {
        this.creator = creator;
    }

    public AuthUser getApprover() {
        return approver;
    }

    public void setApprover(AuthUser approver) {
        this.approver = approver;
    }

    public TypeList getType() {
        return type;
    }

    public void setType(TypeList type) {
        this.type = type;
    }

    

    
    public BigDecimal getCmdId() {
        return cmdId;
    }

    public void setCmdId(BigDecimal cmdId) {
        this.cmdId = cmdId;
    }

    public String getCmdName() {
        return cmdName;
    }

    public void setCmdName(String cmdName) {
        this.cmdName = cmdName;
    }

    public String getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode;
    }

//    public BigInteger getShortcodeCpId() {
//        return shortcodeCpId;
//    }
//
//    public void setShortcodeCpId(BigInteger shortcodeCpId) {
//        this.shortcodeCpId = shortcodeCpId;
//    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public String getTypeCode() {
//        return typeCode;
//    }
//
//    public void setTypeCode(String typeCode) {
//        this.typeCode = typeCode;
//    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

//    public Long getCreatorId() {
//        return creatorId;
//    }
//
//    public void setCreatorId(Long creatorId) {
//        this.creatorId = creatorId;
//    }

//    public Long getApproverId() {
//        return approverId;
//    }
//
//    public void setApproverId(Long approverId) {
//        this.approverId = approverId;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmdId != null ? cmdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CmdcodeList)) {
            return false;
        }
        CmdcodeList other = (CmdcodeList) object;
        if ((this.cmdId == null && other.cmdId != null) || (this.cmdId != null && !this.cmdId.equals(other.cmdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.CmdcodeList[ cmdId=" + cmdId + " ]";
    }
    
}
