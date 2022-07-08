/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.model;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Minh Hieu Pham
 */
public class ClientTpsModel {
    private BigDecimal clientId;
    
    private BigInteger tps;
    
    private String updateFlg;
    
    private String note;
    
    private BigInteger cpId;
    private BigDecimal cpIdDec;
    private String cpName;
    private String cpCode;
    
    private BigInteger shcodeId;
    private BigDecimal shortcodeCpIdDec;
    private BigDecimal shcodeIdDec;
    private String shortcode;

    public ClientTpsModel() {
    }

    public ClientTpsModel(BigDecimal clientId) {
        this.clientId = clientId;
    }

    public ClientTpsModel(BigDecimal clientId, BigInteger tps, String updateFlg, 
            String note, BigDecimal cpIdDec, String cpName, 
            String cpCode, BigDecimal shortcodeCpIdDec, 
            BigDecimal shcodeIdDec, String shortcode) {
        this.clientId = clientId;
        this.tps = tps;
        this.updateFlg = updateFlg;
        this.note = note;
        this.cpIdDec = cpIdDec;
        this.cpName = cpName;
        this.cpCode = cpCode;
        this.shortcodeCpIdDec = shortcodeCpIdDec;
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

    public BigDecimal getShortcodeCpIdDec() {
        return shortcodeCpIdDec;
    }

    public void setShortcodeCpIdDec(BigDecimal shortcodeCpIdDec) {
        this.shortcodeCpIdDec = shortcodeCpIdDec;
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

    public BigDecimal getClientId() {
        return clientId;
    }

    public void setClientId(BigDecimal clientId) {
        this.clientId = clientId;
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

    public BigInteger getCpId() {
        return cpId;
    }

    public void setCpId(BigInteger cpId) {
        this.cpId = cpId;
    }

    public BigInteger getShcodeId() {
        return shcodeId;
    }

    public void setShcodeId(BigInteger shcodeId) {
        this.shcodeId = shcodeId;
    }
}
