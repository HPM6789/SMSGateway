/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.smsgateway.dao;

import com.mycompany.smsgateway.model.TypeListModel;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public interface TypeListDAO {
    
    public List<TypeListModel> getAllTypeList();
    
    public List<TypeListModel> getAllTypeList(int start, int next);
    
    public List<TypeListModel> getAllTypeListByName(String typeName, int start, int next);
    
    public Long getTotalTypeList();
    
    public Long getTotalTypeListByName(String typeName);
    
}
