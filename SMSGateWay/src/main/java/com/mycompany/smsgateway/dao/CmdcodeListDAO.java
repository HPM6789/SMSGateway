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
    
    public List<CmdcodeListModel> getCmdcodeByOption(String inputSearch, String fromCreateDate,
            String toCreateDate, BigInteger status,
            int start, int next);
    
    public BigDecimal getNewestCmdId();
    
    public CmdcodeListModel getCmdcodeById(BigDecimal cmdId);

    public Long getTotalCmdcode();

    public Long getTotalCmdcodeByName(String cmdName);
    
    public Long getTotalCmdcodeByOption(String inputSearch, String fromCreateDate,
            String toCreateDate, BigInteger status);
    
    public int approveAllCmdcode();
    
    public int approveCmdcodes(List<BigDecimal> cmdIds);
    
    public int approveCmdcode(BigDecimal cmdId);
    
    public int disapproveCmdcodes(List<BigDecimal> cmdIds);
    
    public int disapproveCmdcode(BigDecimal cmdId);
    
    public int deleteCmdcode(BigDecimal cmdId);
    
    public int deleteCmdcodes(List<BigDecimal> cmdIds);
    
    public int restoreCmdcode(BigDecimal cmdId);
    
    public int restoreCmdcodes(List<BigDecimal> cmdIds);
    
    public int addNewCmdcode(String cmdName, String cmdCode, BigInteger shortcodeCpId,
            String typeCode, Long price, String description, Long creatorId);
    
    public int updateCmdcode(BigDecimal cmdId, String cmdName, String cmdCode, BigInteger shortcodeCpId,
            String typeCode, Long price, String description, Long creatorId);
    
}
