/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Minh Hieu Pham
 */
@Entity
@Table(name = "AUTH_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthUser.findAll", query = "SELECT a FROM AuthUser a"),
    @NamedQuery(name = "AuthUser.findByUserId", query = "SELECT a FROM AuthUser a WHERE a.userId = :userId"),
    @NamedQuery(name = "AuthUser.findByUserName", query = "SELECT a FROM AuthUser a WHERE a.userName = :userName"),
    @NamedQuery(name = "AuthUser.findByUserPass", query = "SELECT a FROM AuthUser a WHERE a.userPass = :userPass"),
    @NamedQuery(name = "AuthUser.findByUserFullname", query = "SELECT a FROM AuthUser a WHERE a.userFullname = :userFullname"),
    @NamedQuery(name = "AuthUser.findByUserDesc", query = "SELECT a FROM AuthUser a WHERE a.userDesc = :userDesc"),
    @NamedQuery(name = "AuthUser.findByUserStatus", query = "SELECT a FROM AuthUser a WHERE a.userStatus = :userStatus"),
    @NamedQuery(name = "AuthUser.findByUserIsSuper", query = "SELECT a FROM AuthUser a WHERE a.userIsSuper = :userIsSuper"),
    @NamedQuery(name = "AuthUser.findByUserType", query = "SELECT a FROM AuthUser a WHERE a.userType = :userType"),
    @NamedQuery(name = "AuthUser.findByUserAddr", query = "SELECT a FROM AuthUser a WHERE a.userAddr = :userAddr"),
    @NamedQuery(name = "AuthUser.findByUserPhone", query = "SELECT a FROM AuthUser a WHERE a.userPhone = :userPhone"),
    @NamedQuery(name = "AuthUser.findByUserEmail", query = "SELECT a FROM AuthUser a WHERE a.userEmail = :userEmail"),
    @NamedQuery(name = "AuthUser.findByUserCreatedtime", query = "SELECT a FROM AuthUser a WHERE a.userCreatedtime = :userCreatedtime"),
    @NamedQuery(name = "AuthUser.findByUserUpdatedtime", query = "SELECT a FROM AuthUser a WHERE a.userUpdatedtime = :userUpdatedtime"),
    @NamedQuery(name = "AuthUser.findByUserLastTimeLogin", query = "SELECT a FROM AuthUser a WHERE a.userLastTimeLogin = :userLastTimeLogin"),
    @NamedQuery(name = "AuthUser.findByUserOtpFlg", query = "SELECT a FROM AuthUser a WHERE a.userOtpFlg = :userOtpFlg"),
//    @NamedQuery(name = "AuthUser.findByCpId", query = "SELECT a FROM AuthUser a WHERE a.cpId = :cpId")
})
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private BigDecimal userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USER_NAME")
    private String userName;
    @Size(max = 100)
    @Column(name = "USER_PASS")
    private String userPass;
    @Size(max = 100)
    @Column(name = "USER_FULLNAME")
    private String userFullname;
    @Size(max = 200)
    @Column(name = "USER_DESC")
    private String userDesc;
    @Column(name = "USER_STATUS")
    private BigInteger userStatus;
    @Column(name = "USER_IS_SUPER")
    private BigInteger userIsSuper;
    @Column(name = "USER_TYPE")
    private BigInteger userType;
    @Size(max = 500)
    @Column(name = "USER_ADDR")
    private String userAddr;
    @Size(max = 100)
    @Column(name = "USER_PHONE")
    private String userPhone;
    @Size(max = 100)
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Column(name = "USER_CREATEDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCreatedtime;
    @Column(name = "USER_UPDATEDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userUpdatedtime;
    @Column(name = "USER_LAST_TIME_LOGIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userLastTimeLogin;
    @Size(max = 1)
    @Column(name = "USER_OTP_FLG")
    private String userOtpFlg;
//    @Column(name = "CP_ID")
//    private Long cpId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CP_ID")
    private CpList cp;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Groups> groups;
    
    @OneToMany(mappedBy = "creator")
    private List<CmdcodeList> cmdCreator;
    
    @OneToMany(mappedBy = "approver")
    private List<CmdcodeList> cmdApprover;
    
    @OneToMany(mappedBy = "user")
    private List<ActionLog> actions; 
    public AuthUser() {
    }

    public AuthUser(BigDecimal userId) {
        this.userId = userId;
    }

    public AuthUser(BigDecimal userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public List<ActionLog> getActions() {
        return actions;
    }

    public void setActions(List<ActionLog> actions) {
        this.actions = actions;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    public List<CmdcodeList> getCmdCreator() {
        return cmdCreator;
    }

    public void setCmdCreator(List<CmdcodeList> cmdCreator) {
        this.cmdCreator = cmdCreator;
    }

    public List<CmdcodeList> getCmdApprover() {
        return cmdApprover;
    }

    public void setCmdApprover(List<CmdcodeList> cmdApprover) {
        this.cmdApprover = cmdApprover;
    }


    
    
    public CpList getCp() {
        return cp;
    }

    public void setCp(CpList cp) {
        this.cp = cp;
    }

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

//    public Long getCpId() {
//        return cpId;
//    }
//
//    public void setCpId(Long cpId) {
//        this.cpId = cpId;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthUser)) {
            return false;
        }
        AuthUser other = (AuthUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.smsgateway.entities.AuthUser[ userId=" + userId + " ]";
    }

}
