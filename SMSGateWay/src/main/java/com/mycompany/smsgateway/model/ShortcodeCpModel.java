/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.model;

import com.mycompany.smsgateway.entities.CmdcodeList;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public class ShortcodeCpModel {
    private BigDecimal shortcodeCpId;
    
    private BigInteger shcodeId;
    
    private BigInteger cpId;

    private BigDecimal cpIdDec;
    private String cpName;
    private String cpCode;
    
    private BigDecimal shcodeIdDec;
    private String shortcode;
    
   
    
    public ShortcodeCpModel() {
    }

    public ShortcodeCpModel(BigDecimal shortcodeCpId) {
        this.shortcodeCpId = shortcodeCpId;
    }

    public ShortcodeCpModel(BigDecimal shortcodeCpId, BigDecimal cpIdDec, String cpName, String cpCode, BigDecimal shcodeIdDec, String shortcode) {
        this.shortcodeCpId = shortcodeCpId;
        
        this.cpIdDec = cpIdDec;
        this.cpName = cpName;
        this.cpCode = cpCode;
        this.shcodeIdDec = shcodeIdDec;
        this.shortcode = shortcode;
    }

    public BigDecimal getCpIdDec() {
        return cpIdDec;
    }

    public void setCpIdDec(BigDecimal cpIdDec) {
        this.cpIdDec = cpIdDec;
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

    public BigDecimal getShcodeIdDec() {
        return shcodeIdDec;
    }

    public void setShcodeIdDec(BigDecimal shcodeIdDec) {
        this.shcodeIdDec = shcodeIdDec;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    

    public BigDecimal getShortcodeCpId() {
        return shortcodeCpId;
    }

    public void setShortcodeCpId(BigDecimal shortcodeCpId) {
        this.shortcodeCpId = shortcodeCpId;
    }

    public BigInteger getShcodeId() {
        return shcodeId;
    }

    public void setShcodeId(BigInteger shcodeId) {
        this.shcodeId = shcodeId;
    }

    public BigInteger getCpId() {
        return cpId;
    }

    public void setCpId(BigInteger cpId) {
        this.cpId = cpId;
    }
}
