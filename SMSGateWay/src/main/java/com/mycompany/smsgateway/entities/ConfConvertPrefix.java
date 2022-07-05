/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.entities;

import java.io.Serializable;
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
@Table(name = "CONF_CONVERT_PREFIX")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfConvertPrefix.findAll", query = "SELECT c FROM ConfConvertPrefix c"),
    @NamedQuery(name = "ConfConvertPrefix.findByOldPrefix", query = "SELECT c FROM ConfConvertPrefix c WHERE c.confConvertPrefixPK.oldPrefix = :oldPrefix"),
    @NamedQuery(name = "ConfConvertPrefix.findByNewPrefix", query = "SELECT c FROM ConfConvertPrefix c WHERE c.confConvertPrefixPK.newPrefix = :newPrefix")})
public class ConfConvertPrefix implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConfConvertPrefixPK confConvertPrefixPK;

    public ConfConvertPrefix() {
    }

    public ConfConvertPrefix(ConfConvertPrefixPK confConvertPrefixPK) {
        this.confConvertPrefixPK = confConvertPrefixPK;
    }

    public ConfConvertPrefix(String oldPrefix, String newPrefix) {
        this.confConvertPrefixPK = new ConfConvertPrefixPK(oldPrefix, newPrefix);
    }

    public ConfConvertPrefixPK getConfConvertPrefixPK() {
        return confConvertPrefixPK;
    }

    public void setConfConvertPrefixPK(ConfConvertPrefixPK confConvertPrefixPK) {
        this.confConvertPrefixPK = confConvertPrefixPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (confConvertPrefixPK != null ? confConvertPrefixPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfConvertPrefix)) {
            return false;
        }
        ConfConvertPrefix other = (ConfConvertPrefix) object;
        if ((this.confConvertPrefixPK == null && other.confConvertPrefixPK != null) || (this.confConvertPrefixPK != null && !this.confConvertPrefixPK.equals(other.confConvertPrefixPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.ConfConvertPrefix[ confConvertPrefixPK=" + confConvertPrefixPK + " ]";
    }
    
}
