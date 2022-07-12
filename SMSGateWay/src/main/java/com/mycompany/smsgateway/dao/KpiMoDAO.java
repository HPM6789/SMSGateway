/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.smsgateway.dao;

import com.mycompany.smsgateway.model.KpiMoModel;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public interface KpiMoDAO {
    
    public List<KpiMoModel> getAllKpiMoList();
    
    public List<KpiMoModel> getKpiMoList(int start, int next);
    
    public List<KpiMoModel> getKpiMoListByOption(String inputSearch, String fromCreateDate,
            String toCreateDate, int start, int next);
    
    public KpiMoModel getKpiMoByPK(String datetime, BigInteger shortcode);
    
    public Long getTotalKpiMo();
    
    public Long getTotalKpiMoByOption(String inputSearch, String fromCreateDate,
            String toCreateDate);
    
}
