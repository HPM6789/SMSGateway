/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public class AuthUserModel {
    private BigDecimal userId;
    
    
    private String userName;
    
    private String userPass;
    
    private String userFullname;
    
    private String userDesc;
    
    private BigInteger userStatus;
    private BigInteger userIsSuper;
    private BigInteger userType;
    
    private String userAddr;
    
    private String userPhone;
    
    private String userEmail;
    
    private Date userCreatedtime;
    
    private Date userUpdatedtime;
    
    private Date userLastTimeLogin;
    
    private String userOtpFlg;
    private BigDecimal cpId;
    private String cpName;

    private String cpCode;
    
    private List<GroupsModel> groups;
    
    public AuthUserModel() {
    }

    public AuthUserModel(List<GroupsModel> groups) {
        this.groups = groups;
    }
    
    public AuthUserModel(BigDecimal userId) {
        this.userId = userId;
    }

    public AuthUserModel(BigDecimal userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public AuthUserModel(BigDecimal userId, String userName, String userPass, String userFullname, String userDesc, BigInteger userStatus, BigInteger userIsSuper, BigInteger userType, String userAddr, String userPhone, String userEmail, Date userCreatedtime, Date userUpdatedtime, Date userLastTimeLogin, String userOtpFlg) {
        this.userId = userId;
        this.userName = userName;
        this.userPass = userPass;
        this.userFullname = userFullname;
        this.userDesc = userDesc;
        this.userStatus = userStatus;
        this.userIsSuper = userIsSuper;
        this.userType = userType;
        this.userAddr = userAddr;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userCreatedtime = userCreatedtime;
        this.userUpdatedtime = userUpdatedtime;
        this.userLastTimeLogin = userLastTimeLogin;
        this.userOtpFlg = userOtpFlg;
    }
    
    public AuthUserModel(BigDecimal userId, String userName, String userPass, String userFullname, 
            String userDesc, BigInteger userStatus, BigInteger userIsSuper, BigInteger userType, 
            String userAddr, String userPhone, String userEmail, Date userCreatedtime, 
            Date userUpdatedtime, Date userLastTimeLogin, String userOtpFlg, BigDecimal cpId, String cpName 
            ) {
        this.userId = userId;
        this.userName = userName;
        this.userPass = userPass;
        this.userFullname = userFullname;
        this.userDesc = userDesc;
        this.userStatus = userStatus;
        this.userIsSuper = userIsSuper;
        this.userType = userType;
        this.userAddr = userAddr;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userCreatedtime = userCreatedtime;
        this.userUpdatedtime = userUpdatedtime;
        this.userLastTimeLogin = userLastTimeLogin;
        this.userOtpFlg = userOtpFlg;
        this.cpId = cpId;
        this.cpName = cpName;
    }

    public List<GroupsModel> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupsModel> groups) {
        this.groups = groups;
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


    
//    public CpListModel getCp() {
//        return cp;
//    }
//
//    public void setCp(CpListModel cp) {
//        this.cp = cp;
//    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public BigInteger getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(BigInteger userStatus) {
        this.userStatus = userStatus;
    }

    public BigInteger getUserIsSuper() {
        return userIsSuper;
    }

    public void setUserIsSuper(BigInteger userIsSuper) {
        this.userIsSuper = userIsSuper;
    }

    public BigInteger getUserType() {
        return userType;
    }

    public void setUserType(BigInteger userType) {
        this.userType = userType;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getUserCreatedtime() {
        return userCreatedtime;
    }

    public void setUserCreatedtime(Date userCreatedtime) {
        this.userCreatedtime = userCreatedtime;
    }

    public Date getUserUpdatedtime() {
        return userUpdatedtime;
    }

    public void setUserUpdatedtime(Date userUpdatedtime) {
        this.userUpdatedtime = userUpdatedtime;
    }

    public Date getUserLastTimeLogin() {
        return userLastTimeLogin;
    }

    public void setUserLastTimeLogin(Date userLastTimeLogin) {
        this.userLastTimeLogin = userLastTimeLogin;
    }

    public String getUserOtpFlg() {
        return userOtpFlg;
    }

    public void setUserOtpFlg(String userOtpFlg) {
        this.userOtpFlg = userOtpFlg;
    }
}
