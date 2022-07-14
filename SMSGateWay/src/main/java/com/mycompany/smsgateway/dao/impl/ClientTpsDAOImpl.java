/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.ClientTpsDAO;
import com.mycompany.smsgateway.entities.ClientTps;
import com.mycompany.smsgateway.model.ClientTpsModel;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
        String sql = "select new " + ClientTpsModel.class.getName()
                + "(c.clientId, c.tps, c.updateFlg,"
                + "c.note, cp.cpId, cp.cpName,"
                + "cp.cpCode, q.shortcodeCpId,"
                + "p.shcodeId, p.shortcode)"
                + " from " + ClientTps.class.getName() + " c "
                + " left join c.cpInClient cp"
                + " left join c.shcodeCpInCLient q"
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
    public BigDecimal getNewestId() {
        String sql = "select c.clientId"
                + " from " + ClientTps.class.getName() + " c ";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);

            BigDecimal id = (BigDecimal) query.uniqueResult();
            return id;
        } catch (Exception ex) {
            Logger.getLogger(ClientTpsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new BigDecimal("0");
        }
    }

    @Override
    public ClientTpsModel findClientTpsById(BigDecimal clientId) {
        String sql = "select new " + ClientTpsModel.class.getName()
                + "(c.clientId, c.tps, c.updateFlg,"
                + "c.note, cp.cpId, cp.cpName,"
                + "cp.cpCode, q.shortcodeCpId,"
                + "p.shcodeId, p.shortcode)"
                + " from " + ClientTps.class.getName() + " c "
                + " left join c.cpInClient cp"
                + " left join c.shcodeCpInCLient q"
                + " left join c.shcodeCpInCLient.shortcode p"
                + " where c.clientId = :clientId";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter("clientId", clientId);
            ClientTpsModel client = (ClientTpsModel) query.uniqueResult();
            return client;
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
            return new Long("-1");
        }
    }

    @Override
    public int addClientTps(BigInteger tps, String note, BigInteger cpId,
            BigInteger shcodeId) {
        String sql = "insert into client_tps (client_id, tps, update_flg, note, cp_id, shcode_id)"
                + " values(SEQ_CLIENT_TPS.nextval, :tps, :updateFlg, :note, :cpId, :shcodeId)";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("tps", tps);
            query.setParameter("updateFlg", "0");
            query.setParameter("note", note);
            query.setParameter("cpId", cpId);
            query.setParameter("shcodeId", shcodeId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateClientTps(BigDecimal clientId, BigInteger tps, String note,
            BigInteger cpId, BigInteger shcodeId) {
        String sql = "update client_tps set tps = :tps, update_flg = :updateFlg, "
                + " note = :note, cp_id = :cpId, shcode_id = :shcodeId"
                + " where client_id = :clientId";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("tps", tps);
            query.setParameter("updateFlg", "1");
            query.setParameter("note", note);
            query.setParameter("cpId", cpId);
            query.setParameter("shcodeId", shcodeId);
            query.setParameter("clientId", clientId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int deleteClientTps(BigDecimal clientId) {
        String sql = "delete client_tps where client_id = :clientId";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("clientId", clientId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
