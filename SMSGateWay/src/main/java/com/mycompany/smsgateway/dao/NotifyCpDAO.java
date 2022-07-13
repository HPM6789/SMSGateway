/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.smsgateway.dao;

import com.mycompany.smsgateway.model.NotifyCpModel;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public interface NotifyCpDAO {
    
    public List<NotifyCpModel> getAllNotifyCps();
    
    public List<NotifyCpModel> getNotifyCps(int start, int next);
    
    public List<NotifyCpModel> getNotifyCpsByOption(String inputSearch, String fromCreateDate,
            String toCreateDate, int start, int next);
    
    public NotifyCpModel getNotifyCpByCpId(BigDecimal cpId);
    
    public NotifyCpModel getNotifyCpById(BigDecimal notifyId);
    
    public BigDecimal getNewestNotifyId();
    
    public Long getTotalNotifyCp();
    
    public Long getTotalNotifyCpByOption(String inputSearch, String fromCreateDate,
            String toCreateDate);
    
    public int addNotifyCp(String moReceiveUrl, BigInteger cpId, String note, String moReceiveUrlBkp);
    
    public int updateNotifyCp(BigDecimal notifyId, String moReceiveUrl, BigInteger cpId, 
            String note, BigInteger status, String moReceiveUrlBkp);
    
    public int deleteNotifyCp(BigDecimal notifyId);
}
