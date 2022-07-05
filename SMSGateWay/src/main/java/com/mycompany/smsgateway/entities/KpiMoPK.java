/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
public class KpiMoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "DATETIME")
    private String datetime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MO_SHORTCODE")
    private BigInteger moShortcode;

    public KpiMoPK() {
    }

    public KpiMoPK(String datetime, BigInteger moShortcode) {
        this.datetime = datetime;
        this.moShortcode = moShortcode;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public BigInteger getMoShortcode() {
        return moShortcode;
    }

    public void setMoShortcode(BigInteger moShortcode) {
        this.moShortcode = moShortcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (datetime != null ? datetime.hashCode() : 0);
        hash += (moShortcode != null ? moShortcode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KpiMoPK)) {
            return false;
        }
        KpiMoPK other = (KpiMoPK) object;
        if ((this.datetime == null && other.datetime != null) || (this.datetime != null && !this.datetime.equals(other.datetime))) {
            return false;
        }
        if ((this.moShortcode == null && other.moShortcode != null) || (this.moShortcode != null && !this.moShortcode.equals(other.moShortcode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.KpiMoPK[ datetime=" + datetime + ", moShortcode=" + moShortcode + " ]";
    }
    
}
