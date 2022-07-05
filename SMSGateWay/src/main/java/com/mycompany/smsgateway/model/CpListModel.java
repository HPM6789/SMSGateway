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
public class CpListModel {

    private BigDecimal cpId;
    private String cpName;

    private String cpCode;

    private String contact;

    private Date createdTime;

    private Date updatedTime;

    private String usernameMt;

    private String passwordMt;

    private String listipMt;

    private String usernameCharge;

    private String passwordCharge;

    private String taxCode;

    private String representer;

    private String address;

    private String emailCp;

    public CpListModel() {
    }

    public CpListModel(BigDecimal cpId) {
        this.cpId = cpId;
    }

    public CpListModel(BigDecimal cpId, String cpName) {
        this.cpId = cpId;
        this.cpName = cpName;
    }

    public CpListModel(BigDecimal cpId, String cpName, String cpCode, String contact, Date createdTime, Date updatedTime, String usernameMt, String passwordMt, String listipMt, String usernameCharge, String passwordCharge, String taxCode, String representer, String address, String emailCp) {
        this.cpId = cpId;
        this.cpName = cpName;
        this.cpCode = cpCode;
        this.contact = contact;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.usernameMt = usernameMt;
        this.passwordMt = passwordMt;
        this.listipMt = listipMt;
        this.usernameCharge = usernameCharge;
        this.passwordCharge = passwordCharge;
        this.taxCode = taxCode;
        this.representer = representer;
        this.address = address;
        this.emailCp = emailCp;
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
}
