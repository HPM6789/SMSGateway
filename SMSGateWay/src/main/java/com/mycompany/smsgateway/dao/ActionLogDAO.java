/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.smsgateway.dao;

import com.mycompany.smsgateway.model.ActionLogModel;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public interface ActionLogDAO {
    
    public List<ActionLogModel> getAllActions();
    
    public List<ActionLogModel> getAllActions(int start, int next);
    
    public List<ActionLogModel> getAllActionsByName(String actionlogName, int start, int next);
    
    public ActionLogModel getActionLogById(BigDecimal actionlogId);
    
    public int addUserAction(BigInteger userId, String actionlogName, String actionlogObjectType, 
            BigInteger actionlogObjectId, String actionlogIp, String actionlogDevice, 
            String actionlogOs, String actionlogApp, String actionlogResult, 
            String actionlogDesc, String actionlogData, String actionlogMsisdn);
    
    public Long getTotalActionLogs();
    
    public Long getTotalActionLogsByName(String actionlogName);
    
}
