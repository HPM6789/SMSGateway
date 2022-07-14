/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.smsgateway.dao;

import com.mycompany.smsgateway.model.SmsMtLogModel;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public interface SmsMtLogDAO {
    
    public List<SmsMtLogModel> getSmsMtLogForList(int start, int next);
    
    public List<SmsMtLogModel> getSmsMtLogByOption(String inputSearch, String fromCreateDate,
            String toCreateDate, int start, int next);
    
    public Long getTotalSmsMtLog();
    
    public Long getTotalSmsMtLogByOption(String inputSearch, String fromCreateDate,
            String toCreateDate);
    
}
