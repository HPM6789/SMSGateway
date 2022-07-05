/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Minh Hieu Pham
 */
public class TypeListModel {
    private BigDecimal typeId;
    
    private String typeCode;
    
    private String typeName;
    
    private Date createDate;
    
    private Date lastUpdate;

    public TypeListModel() {
    }

    public TypeListModel(BigDecimal typeId) {
        this.typeId = typeId;
    }

    public TypeListModel(BigDecimal typeId, String typeCode, String typeName, Date createDate, Date lastUpdate) {
        this.typeId = typeId;
        this.typeCode = typeCode;
        this.typeName = typeName;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
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
}
