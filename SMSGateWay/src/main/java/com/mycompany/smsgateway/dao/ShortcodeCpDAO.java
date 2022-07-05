/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.smsgateway.dao;

import com.mycompany.smsgateway.model.ShortcodeCpModel;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public interface ShortcodeCpDAO {
    
    public List<ShortcodeCpModel> getAllShortcodeCp();
    
    public List<String> getAllShortcodeByCpId(BigDecimal cpId); 
    
    public List<BigDecimal> getAllShortcodeIdByCpId(BigDecimal cpId);
    
}
