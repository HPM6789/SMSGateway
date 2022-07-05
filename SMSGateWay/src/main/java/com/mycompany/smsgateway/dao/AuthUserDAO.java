/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.smsgateway.dao;

import com.mycompany.smsgateway.model.AuthUserModel;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public interface AuthUserDAO {

    //Insert new user into database.
    public int addNewAuthUser(String username, String password, String fullname, String description,
            String address, String phone, String email);
    
    public int updateAuthUser(String username, String fullname,
             String description,  BigInteger status, BigInteger isSuper,
             BigInteger userType,  String address,  String phone,
             String email,
             String optFlag);
    
    public int updateProfile(String username, String fullname, String description,String address, String phone,
             String email);

    //Find MAuthUser by parameter username
    public AuthUserModel findAuthUserByUsername(String username);

    //Check duplicate user in database
    public AuthUserModel checkLoginUser(String username, String password);

    //Return all users in database
    public List<AuthUserModel> getAllUser();
    
    //Search user by username, email
    public List<AuthUserModel> getUserByUsernameOrEmail(String usernameOrEmail);
    
    public AuthUserModel getUserWithCP(String username);
    
    public BigDecimal getUserIdByUsername(String username);
    
    public int changePassword(String username, String newPassword);
    
    public List<AuthUserModel> getUsersByGroup(Long id);
    
    public List<String> getUserNamesByGroup(Long id);
    
    public List<BigDecimal> getUserIdsByGroup(Long id);
    
    public List<AuthUserModel> getUsersNotInGroup(Long id);
    
    public int addCpListForAuthUser(BigDecimal cpId, String username);
    
    public int updateLastTimeLogin(String username);
    
    public int getTotalAuthUser();
    
    public BigInteger checkActiveUser(String username);
    
}
