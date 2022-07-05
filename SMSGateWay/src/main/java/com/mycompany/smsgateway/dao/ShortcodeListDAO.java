/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.smsgateway.dao;

import com.mycompany.smsgateway.model.ShortcodeListModel;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public interface ShortcodeListDAO {

    public List<ShortcodeListModel> getAllShortcode();

    public List<ShortcodeListModel> getAllShortcodeByCode(String shortcode);
    
    public BigDecimal getNewestShcodeId();

    public ShortcodeListModel getShortcodeByCode(String shortcode);
    
    public ShortcodeListModel getShortcodeById(BigDecimal shcodeId);

    public int addNewShortcode(String shortcode, BigInteger price,
            BigInteger limitedMtNo, Long creatorId);

    public int updateShortcode(BigDecimal shcodeId, String shortcode, BigInteger price,
            BigInteger limitedMtNo);

    public int deleteShortcodeById(BigDecimal shcodeId);
    
    public int approveShortcode(BigDecimal shcodeId);
    
    public int disapproveShortcode(BigDecimal shcodeId);

}
