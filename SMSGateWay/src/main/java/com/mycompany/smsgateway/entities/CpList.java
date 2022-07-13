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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "CP_LIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CpList.findAll", query = "SELECT c FROM CpList c"),
    @NamedQuery(name = "CpList.findByCpId", query = "SELECT c FROM CpList c WHERE c.cpId = :cpId"),
    @NamedQuery(name = "CpList.findByCpName", query = "SELECT c FROM CpList c WHERE c.cpName = :cpName"),
    @NamedQuery(name = "CpList.findByCpCode", query = "SELECT c FROM CpList c WHERE c.cpCode = :cpCode"),
    @NamedQuery(name = "CpList.findByContact", query = "SELECT c FROM CpList c WHERE c.contact = :contact"),
    @NamedQuery(name = "CpList.findByCreatedTime", query = "SELECT c FROM CpList c WHERE c.createdTime = :createdTime"),
    @NamedQuery(name = "CpList.findByUpdatedTime", query = "SELECT c FROM CpList c WHERE c.updatedTime = :updatedTime"),
    @NamedQuery(name = "CpList.findByUsernameMt", query = "SELECT c FROM CpList c WHERE c.usernameMt = :usernameMt"),
    @NamedQuery(name = "CpList.findByPasswordMt", query = "SELECT c FROM CpList c WHERE c.passwordMt = :passwordMt"),
    @NamedQuery(name = "CpList.findByListipMt", query = "SELECT c FROM CpList c WHERE c.listipMt = :listipMt"),
    @NamedQuery(name = "CpList.findByUsernameCharge", query = "SELECT c FROM CpList c WHERE c.usernameCharge = :usernameCharge"),
    @NamedQuery(name = "CpList.findByPasswordCharge", query = "SELECT c FROM CpList c WHERE c.passwordCharge = :passwordCharge"),
    @NamedQuery(name = "CpList.findByTaxCode", query = "SELECT c FROM CpList c WHERE c.taxCode = :taxCode"),
    @NamedQuery(name = "CpList.findByRepresenter", query = "SELECT c FROM CpList c WHERE c.representer = :representer"),
    @NamedQuery(name = "CpList.findByAddress", query = "SELECT c FROM CpList c WHERE c.address = :address"),
    @NamedQuery(name = "CpList.findByEmailCp", query = "SELECT c FROM CpList c WHERE c.emailCp = :emailCp")})
public class CpList implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CP_ID")
    private BigDecimal cpId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CP_NAME")
    private String cpName;
    @Size(max = 100)
    @Column(name = "CP_CODE")
    private String cpCode;
    @Size(max = 200)
    @Column(name = "CONTACT")
    private String contact;
    @Column(name = "CREATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @Column(name = "UPDATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;
    @Size(max = 20)
    @Column(name = "USERNAME_MT")
    private String usernameMt;
    @Size(max = 20)
    @Column(name = "PASSWORD_MT")
    private String passwordMt;
    @Size(max = 500)
    @Column(name = "LISTIP_MT")
    private String listipMt;
    @Size(max = 30)
    @Column(name = "USERNAME_CHARGE")
    private String usernameCharge;
    @Size(max = 30)
    @Column(name = "PASSWORD_CHARGE")
    private String passwordCharge;
    @Size(max = 100)
    @Column(name = "TAX_CODE")
    private String taxCode;
    @Size(max = 20)
    @Column(name = "REPRESENTER")
    private String representer;
    @Size(max = 200)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 500)
    @Column(name = "EMAIL_CP")
    private String emailCp;
    
    @OneToMany(mappedBy = "cp")
    private List<ShortcodeCp> shortcodeCps;
    
    @OneToMany(mappedBy = "cpInClient")
    private List<ClientTps> clientTps;
    
    @OneToOne(mappedBy = "cpInNotifyCp")
    private NotifyCp notifyCp;
    
    public CpList() {
    }

    public CpList(BigDecimal cpId) {
        this.cpId = cpId;
    }

    public CpList(BigDecimal cpId, String cpName) {
        this.cpId = cpId;
        this.cpName = cpName;
    }

    public List<ClientTps> getClientTps() {
        return clientTps;
    }

    public void setClientTps(List<ClientTps> clientTps) {
        this.clientTps = clientTps;
    }

    public NotifyCp getNotifyCp() {
        return notifyCp;
    }

    public void setNotifyCp(NotifyCp notifyCp) {
        this.notifyCp = notifyCp;
    }

    public List<ShortcodeCp> getShortcodeCps() {
        return shortcodeCps;
    }

    public void setShortcodeCps(List<ShortcodeCp> shortcodeCps) {
        this.shortcodeCps = shortcodeCps;
    }

    public BigDecimal getCpId() {
        return cpId;
    }

    public void setCpId(BigDecimal cpId) {
        this.cpId = cpId;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUsernameMt() {
        return usernameMt;
    }

    public void setUsernameMt(String usernameMt) {
        this.usernameMt = usernameMt;
    }

    public String getPasswordMt() {
        return passwordMt;
    }

    public void setPasswordMt(String passwordMt) {
        this.passwordMt = passwordMt;
    }

    public String getListipMt() {
        return listipMt;
    }

    public void setListipMt(String listipMt) {
        this.listipMt = listipMt;
    }

    public String getUsernameCharge() {
        return usernameCharge;
    }

    public void setUsernameCharge(String usernameCharge) {
        this.usernameCharge = usernameCharge;
    }

    public String getPasswordCharge() {
        return passwordCharge;
    }

    public void setPasswordCharge(String passwordCharge) {
        this.passwordCharge = passwordCharge;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getRepresenter() {
        return representer;
    }

    public void setRepresenter(String representer) {
        this.representer = representer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailCp() {
        return emailCp;
    }

    public void setEmailCp(String emailCp) {
        this.emailCp = emailCp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpId != null ? cpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpList)) {
            return false;
        }
        CpList other = (CpList) object;
        if ((this.cpId == null && other.cpId != null) || (this.cpId != null && !this.cpId.equals(other.cpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.CpList[ cpId=" + cpId + " ]";
    }
    
}
