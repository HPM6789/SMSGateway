/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.ClientTpsDAO;
import com.mycompany.smsgateway.entities.ClientTps;
import com.mycompany.smsgateway.model.ClientTpsModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Minh Hieu Pham
 */
@Repository
@Transactional
public class ClientTpsDAOImpl implements ClientTpsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ClientTpsModel> getAllClientTps(int start, int next) {
        String sql = "select new" + ClientTpsModel.class.getName()
                + "(c.clientId, c.tps, c.updateFlg,"
                + "c.note, c.cpInClient.cpId, c.cpInClient.cpName,"
                + "c.cpInClient.cpCode, c.shcodeCpInCLient.shortcodeCpId,"
                + "p.shcodeId, p.shortcode)"
                + " from " + ClientTps.class.getName() + " c "
                + " left join c.cpInClient"
                + " left join c.shcodeCpInCLient"
                + " left join c.shcodeCpInCLient.shortcode p";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<ClientTpsModel> clients = query.list();
            return clients;
        } catch (Exception ex) {
            Logger.getLogger(ClientTpsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Long getTotalClientTps() {
        String sql = "select count(c)"
                + " from " + ClientTps.class.getName() + " c ";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            Long total = (Long) query.uniqueResult();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new Long("0");
        }
    }

}
