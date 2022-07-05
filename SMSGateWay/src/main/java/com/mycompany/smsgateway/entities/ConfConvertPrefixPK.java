/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Minh Hieu Pham
 */
@Embeddable
public class ConfConvertPrefixPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "OLD_PREFIX")
    private String oldPrefix;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "NEW_PREFIX")
    private String newPrefix;

    public ConfConvertPrefixPK() {
    }

    public ConfConvertPrefixPK(String oldPrefix, String newPrefix) {
        this.oldPrefix = oldPrefix;
        this.newPrefix = newPrefix;
    }

    public String getOldPrefix() {
        return oldPrefix;
    }

    public void setOldPrefix(String oldPrefix) {
        this.oldPrefix = oldPrefix;
    }

    public String getNewPrefix() {
        return newPrefix;
    }

    public void setNewPrefix(String newPrefix) {
        this.newPrefix = newPrefix;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oldPrefix != null ? oldPrefix.hashCode() : 0);
        hash += (newPrefix != null ? newPrefix.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfConvertPrefixPK)) {
            return false;
        }
        ConfConvertPrefixPK other = (ConfConvertPrefixPK) object;
        if ((this.oldPrefix == null && other.oldPrefix != null) || (this.oldPrefix != null && !this.oldPrefix.equals(other.oldPrefix))) {
            return false;
        }
        if ((this.newPrefix == null && other.newPrefix != null) || (this.newPrefix != null && !this.newPrefix.equals(other.newPrefix))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.ConfConvertPrefixPK[ oldPrefix=" + oldPrefix + ", newPrefix=" + newPrefix + " ]";
    }
    
}
