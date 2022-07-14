/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.SmsMtLogDAO;
import com.mycompany.smsgateway.entities.SmsMtLog;
import com.mycompany.smsgateway.model.SmsMtLogModel;
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
public class SmsMtLogDAOImpl implements SmsMtLogDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<SmsMtLogModel> getSmsMtLogForList(int start, int next) {
        String sql = "select new " + SmsMtLogModel.class.getName()
                + "(s.seqMtId, s.moId, s.msisdn, s.message, " 
                + "s.createDate, s.shortCode, s.price, s.errorDesc, s.errorCode)"
                + " from " + SmsMtLog.class.getName() + " s "
                + " order by s.createDate desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<SmsMtLogModel> sms = query.list();
            return sms;
        } catch (Exception ex) {
            Logger.getLogger(SmsMtLogDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<SmsMtLogModel> getSmsMtLogByOption(String inputSearch, String fromCreateDate,
            String toCreateDate, int start, int next) {
        String sql = "select new " + SmsMtLogModel.class.getName()
                + "(s.seqMtId, s.moId, s.msisdn, s.message, " 
                + "s.createDate, s.shortCode, s.price, s.errorDesc, s.errorCode)"
                + " from " + SmsMtLog.class.getName() + " s where 1=1";
                
        if (inputSearch != null && !inputSearch.equals("")) {
            sql += " and s.shortCode like '%" + inputSearch + "%'"
                    + " or s.msisdn like '%" + inputSearch + "%')";
        }
        if (fromCreateDate != null && !fromCreateDate.equals("")) {
            sql += " and s.createDate >= to_date('" + fromCreateDate + "','yyyy/MM/dd')";
        }
        if (toCreateDate != null && !toCreateDate.equals("")) {
            sql += " and s.createDate <= to_date('" + toCreateDate + "','yyyy/MM/dd')";
        }
        sql += " order by s.createDate desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<SmsMtLogModel> sms = query.list();
            return sms;
        } catch (Exception ex) {
            Logger.getLogger(SmsMtLogDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Long getTotalSmsMtLog() {
        String sql = "select count(s)"
                + " from " + SmsMtLog.class.getName() + " s ";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            Long total = (Long) query.uniqueResult();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(SmsMtLogDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new Long("-1");
        }
    }

    @Override
    public Long getTotalSmsMtLogByOption(String inputSearch, String fromCreateDate,
            String toCreateDate) {
        String sql = "select count(s)"
                + " from " + SmsMtLog.class.getName() + " s where 1=1";
        if (inputSearch != null && !inputSearch.equals("")) {
            sql += " and s.shortCode like '%" + inputSearch + "%'"
                    + " or s.msisdn like '%" + inputSearch + "%')";
        }
        if (fromCreateDate != null && !fromCreateDate.equals("")) {
            sql += " and s.createDate >= to_date('" + fromCreateDate + "','yyyy/MM/dd')";
        }
        if (toCreateDate != null && !toCreateDate.equals("")) {
            sql += " and s.createDate <= to_date('" + toCreateDate + "','yyyy/MM/dd')";
        }
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            Long total = (Long) query.uniqueResult();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(SmsMtLogDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new Long("-1");
        }
    }
    
}
