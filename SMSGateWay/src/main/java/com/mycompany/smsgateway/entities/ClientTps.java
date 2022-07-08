/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Minh Hieu Pham
 */
@Entity
@Table(name = "CLIENT_TPS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientTps.findAll", query = "SELECT c FROM ClientTps c"),
    @NamedQuery(name = "ClientTps.findByClientId", query = "SELECT c FROM ClientTps c WHERE c.clientId = :clientId"),
    @NamedQuery(name = "ClientTps.findByTps", query = "SELECT c FROM ClientTps c WHERE c.tps = :tps"),
    @NamedQuery(name = "ClientTps.findByUpdateFlg", query = "SELECT c FROM ClientTps c WHERE c.updateFlg = :updateFlg"),
    @NamedQuery(name = "ClientTps.findByNote", query = "SELECT c FROM ClientTps c WHERE c.note = :note")
//    @NamedQuery(name = "ClientTps.findByCpId", query = "SELECT c FROM ClientTps c WHERE c.cpId = :cpId"),
//    @NamedQuery(name = "ClientTps.findByShcodeId", query = "SELECT c FROM ClientTps c WHERE c.shcodeId = :shcodeId")
})
public class ClientTps implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLIENT_ID")
    private BigDecimal clientId;
    @Column(name = "TPS")
    private BigInteger tps;
    @Size(max = 1)
    @Column(name = "UPDATE_FLG")
    private String updateFlg;
    @Size(max = 1000)
    @Column(name = "NOTE")
    private String note;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHCODE_ID")
    private ShortcodeCp shcodeCpInCLient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CP_ID")
    private CpList cpInClient;
    
    public ClientTps() {
    }

    public ClientTps(BigDecimal clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getClientId() {
        return clientId;
    }

    public void setClientId(BigDecimal clientId) {
        this.clientId = clientId;
    }

    public ShortcodeCp getShcodeCpInCLient() {
        return shcodeCpInCLient;
    }

    public void setShcodeCpInCLient(ShortcodeCp shcodeCpInCLient) {
        this.shcodeCpInCLient = shcodeCpInCLient;
    }

    public CpList getCpInClient() {
        return cpInClient;
    }

    public void setCpInClient(CpList cpInClient) {
        this.cpInClient = cpInClient;
    }

    
    public BigInteger getTps() {
        return tps;
    }

    public void setTps(BigInteger tps) {
        this.tps = tps;
    }

    public String getUpdateFlg() {
        return updateFlg;
    }

    public void setUpdateFlg(String updateFlg) {
        this.updateFlg = updateFlg;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

//    public BigInteger getCpId() {
//        return cpId;
//    }
//
//    public void setCpId(BigInteger cpId) {
//        this.cpId = cpId;
//    }

//    public BigInteger getShcodeId() {
//        return shcodeId;
//    }
//
//    public void setShcodeId(BigInteger shcodeId) {
//        this.shcodeId = shcodeId;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientId != null ? clientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientTps)) {
            return false;
        }
        ClientTps other = (ClientTps) object;
        if ((this.clientId == null && other.clientId != null) || (this.clientId != null && !this.clientId.equals(other.clientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.ClientTps[ clientId=" + clientId + " ]";
    }
    
}
