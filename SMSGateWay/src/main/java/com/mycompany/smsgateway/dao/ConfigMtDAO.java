/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.smsgateway.dao;

import com.mycompany.smsgateway.model.ConfigMtModel;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public interface ConfigMtDAO {

    public List<ConfigMtModel> getAllConfigMt();

    public List<ConfigMtModel> getAllConfigMt(int start, int next);
    
    public List<ConfigMtModel> getConfigMtByOption(String inputSearch, String fromCreateDate,
            String toCreateDate, int start, int next);
    
    public ConfigMtModel getMtByShortcode(String shortcode);
    
    public ConfigMtModel getMtById(BigDecimal mtId);
    
    public BigDecimal getNewestMtId();

    public Long getTotalConfigMt();
    
    public Long getTotalConfigMtByOption(String inputSearch, String fromCreateDate,
            String toCreateDate);

    public int addConfigMt(String shortcode, String mtContent);

    public int updateConfigMt(BigDecimal mtId, String shortcode, String mtContent);

    public int deleteConfigMt(BigDecimal mtId);
}
