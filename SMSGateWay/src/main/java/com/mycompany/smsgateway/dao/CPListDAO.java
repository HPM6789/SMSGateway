/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.smsgateway.dao;

import com.mycompany.smsgateway.entities.CpList;
import com.mycompany.smsgateway.model.CpListModel;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public interface CPListDAO {
    
    public CpListModel getCPFromUsername(String username);
    
    public List<CpListModel> getAllCpList();
    
    public List<CpListModel> getCpListsByName(String name);
    
    public BigDecimal getNewestCpId();
    
    public int addNewCpList(String cpName, String cpCode, String contact, 
            String usernameMt, String passwordMt, 
            String listipMt, String usernameCharge, String passwordCharge, String taxCode, 
            String representer, String address, String emailCp);
    
    public int updateCpList(BigDecimal cpId, String cpName, String cpCode, String contact, 
            String usernameMt, String passwordMt, 
            String listipMt, String usernameCharge, String passwordCharge, String taxCode, 
            String representer, String address, String emailCp);
    
    public int deleteCpList(BigDecimal cpId);
    
    public CpListModel getCpByCode(String cpCode);
    
    public CpListModel getCpByCpId(BigDecimal cpId);
    
    public int addShortcodeForCp(BigDecimal cpId, List<BigDecimal> shcodeId);
    
    public int removeShortcodeForCp(BigDecimal cpId, List<BigDecimal> shcodeId);
}
