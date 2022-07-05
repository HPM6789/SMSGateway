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
public class DreportRevenuePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "DATETIME")
    private String datetime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SHORT_CODE")
    private String shortCode;

    public DreportRevenuePK() {
    }

    public DreportRevenuePK(String datetime, String shortCode) {
        this.datetime = datetime;
        this.shortCode = shortCode;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (datetime != null ? datetime.hashCode() : 0);
        hash += (shortCode != null ? shortCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DreportRevenuePK)) {
            return false;
        }
        DreportRevenuePK other = (DreportRevenuePK) object;
        if ((this.datetime == null && other.datetime != null) || (this.datetime != null && !this.datetime.equals(other.datetime))) {
            return false;
        }
        if ((this.shortCode == null && other.shortCode != null) || (this.shortCode != null && !this.shortCode.equals(other.shortCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.DreportRevenuePK[ datetime=" + datetime + ", shortCode=" + shortCode + " ]";
    }
    
}
