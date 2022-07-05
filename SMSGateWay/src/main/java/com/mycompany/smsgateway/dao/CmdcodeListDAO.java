/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.smsgateway.dao;

import com.mycompany.smsgateway.model.CmdcodeListModel;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public interface CmdcodeListDAO {

    public List<CmdcodeListModel> getAllCmdcode();

    public List<CmdcodeListModel> getAllCmdcode2(int start, int next);

    public List<CmdcodeListModel> getCmdcodeByName(String cmdName, int start, int next);
    
    public BigDecimal getNewestCmdId();
    
    public CmdcodeListModel getCmdcodeById(BigDecimal cmdId);

    public Long getTotalCmdcode();

    public Long getTotalCmdcodeByName(String cmdName);
    
    public int approveAllCmdcode();
    
    public int approveCmdcode(BigDecimal cmdId);
    
    public int disapproveCmdcode(BigDecimal cmdId);
    
    public int deleteCmdcode(BigDecimal cmdId);
    
    public int restoreCmdcode(BigDecimal cmdId);
    
    public int addNewCmdcode(String cmdName, String cmdCode, BigInteger shortcodeCpId,
            String typeCode, Long price, String description, Long creatorId);
    
    public int updateCmdcode(BigDecimal cmdId, String cmdName, String cmdCode, BigInteger shortcodeCpId,
            String typeCode, Long price, String description, Long creatorId);
    
}
