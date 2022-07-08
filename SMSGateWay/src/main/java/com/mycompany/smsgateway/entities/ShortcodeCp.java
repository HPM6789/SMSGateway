/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Minh Hieu Pham
 */
@Entity
@Table(name = "SHORTCODE_CP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShortcodeCp.findAll", query = "SELECT s FROM ShortcodeCp s"),
    @NamedQuery(name = "ShortcodeCp.findByShortcodeCpId", query = "SELECT s FROM ShortcodeCp s WHERE s.shortcodeCpId = :shortcodeCpId"),
//    @NamedQuery(name = "ShortcodeCp.findByShcodeId", query = "SELECT s FROM ShortcodeCp s WHERE s.shcodeId = :shcodeId"),
//    @NamedQuery(name = "ShortcodeCp.findByCpId", query = "SELECT s FROM ShortcodeCp s WHERE s.cpId = :cpId")
})
public class ShortcodeCp implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SHORTCODE_CP_ID")
    private BigDecimal shortcodeCpId;
//    @Column(name = "SHCODE_ID")
//    private BigInteger shcodeId;
//    @Column(name = "CP_ID")
//    private BigInteger cpId;

    @OneToMany(mappedBy = "shortcodeCp")
    private List<CmdcodeList> cmds;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CP_ID")
    private CpList cp;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHCODE_ID")
    private ShortcodeList shortcode;
    
    @OneToMany(mappedBy = "shcodeCpInCLient")
    private List<ClientTps> clientTps;
    
    public ShortcodeCp() {
    }

    public ShortcodeCp(BigDecimal shortcodeCpId) {
        this.shortcodeCpId = shortcodeCpId;
    }

    public List<ClientTps> getClientTps() {
        return clientTps;
    }

    public void setClientTps(List<ClientTps> clientTps) {
        this.clientTps = clientTps;
    }

    public ShortcodeList getShortcode() {
        return shortcode;
    }

    public void setShortcode(ShortcodeList shortcode) {
        this.shortcode = shortcode;
    }

    public CpList getCp() {
        return cp;
    }

    public void setCp(CpList cp) {
        this.cp = cp;
    }

    public List<CmdcodeList> getCmds() {
        return cmds;
    }

    public void setCmds(List<CmdcodeList> cmds) {
        this.cmds = cmds;
    }

    public BigDecimal getShortcodeCpId() {
        return shortcodeCpId;
    }

    public void setShortcodeCpId(BigDecimal shortcodeCpId) {
        this.shortcodeCpId = shortcodeCpId;
    }

//    public BigInteger getShcodeId() {
//        return shcodeId;
//    }
//
//    public void setShcodeId(BigInteger shcodeId) {
//        this.shcodeId = shcodeId;
//    }
//
//    public BigInteger getCpId() {
//        return cpId;
//    }
//
//    public void setCpId(BigInteger cpId) {
//        this.cpId = cpId;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shortcodeCpId != null ? shortcodeCpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShortcodeCp)) {
            return false;
        }
        ShortcodeCp other = (ShortcodeCp) object;
        if ((this.shortcodeCpId == null && other.shortcodeCpId != null) || (this.shortcodeCpId != null && !this.shortcodeCpId.equals(other.shortcodeCpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.ShortcodeCp[ shortcodeCpId=" + shortcodeCpId + " ]";
    }
    
}
