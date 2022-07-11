/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.smsgateway.dao;

import com.mycompany.smsgateway.model.ClientTpsModel;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public interface ClientTpsDAO {

    public List<ClientTpsModel> getAllClientTps(int start, int next);
    
    public BigDecimal getNewestId();
    
    public ClientTpsModel findClientTpsById(BigDecimal clientId);

    public Long getTotalClientTps();

    public int addClientTps(BigInteger tps, String note, BigInteger cpId,
            BigInteger shcodeId);

    public int updateClientTps(BigDecimal clientId, BigInteger tps,
            String note, BigInteger cpId, BigInteger shcodeId);

    public int deleteClientTps(BigDecimal clientId);
}
