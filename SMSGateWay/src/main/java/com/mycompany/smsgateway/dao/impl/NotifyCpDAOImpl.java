/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.NotifyCpDAO;
import com.mycompany.smsgateway.entities.NotifyCp;
import com.mycompany.smsgateway.model.NotifyCpModel;
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
public class NotifyCpDAOImpl implements NotifyCpDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<NotifyCpModel> getAllNotifyCps() {
        String sql = "select new " + NotifyCpModel.class.getName()
                + "(n.notifyId, n.moReceiveUrl, "
                + "cp.cpId, cp.cpName, cp.cpCode, n.note, n.createDate, "
                + "n.lastUpdate, n.status, n.shcodeId, n.moReceiveUrlBkp)"
                + " from " + NotifyCp.class.getName() + " n "
                + " inner join n.cpInNotifyCp cp"
                + " order by n.createDate desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            List<NotifyCpModel> notifies = query.list();
            return notifies;
        } catch (Exception ex) {
            Logger.getLogger(NotifyCpDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<NotifyCpModel> getNotifyCps(int start, int next) {
        String sql = "select new " + NotifyCpModel.class.getName()
                + "(n.notifyId, n.moReceiveUrl, "
                + "cp.cpId, cp.cpName, cp.cpCode, n.note, n.createDate, "
                + "n.lastUpdate, n.status, n.shcodeId, n.moReceiveUrlBkp)"
                + " from " + NotifyCp.class.getName() + " n "
                + " inner join n.cpInNotifyCp cp"
                + " order by n.createDate desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<NotifyCpModel> notifies = query.list();
            return notifies;
        } catch (Exception ex) {
            Logger.getLogger(NotifyCpDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<NotifyCpModel> getNotifyCpsByOption(String inputSearch, String fromCreateDate,
            String toCreateDate, int start, int next) {
        String sql = "select new " + NotifyCpModel.class.getName()
                + "(n.notifyId, n.moReceiveUrl, "
                + "cp.cpId, cp.cpName, cp.cpCode, n.note, n.createDate, "
                + "n.lastUpdate, n.status, n.shcodeId, n.moReceiveUrlBkp)"
                + " from " + NotifyCp.class.getName() + " n "
                + " inner join n.cpInNotifyCp cp where 1=1";
        if (inputSearch != null && !inputSearch.equals("")) {
            sql += " and upper(cp.cpName) like upper('%" + inputSearch + "%')";
        }
        if (fromCreateDate != null && !fromCreateDate.equals("")) {
            sql += " and n.createDate >= to_date('" + fromCreateDate + "','yyyy/MM/dd')";
        }
        if (toCreateDate != null && !toCreateDate.equals("")) {
            sql += " and n.createDate <= to_date('" + toCreateDate + "','yyyy/MM/dd')";
        }
        sql += " order by n.createDate desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<NotifyCpModel> notifies = query.list();
            return notifies;
        } catch (Exception ex) {
            Logger.getLogger(NotifyCpDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Long getTotalNotifyCp() {
        String sql = "select count(n)"
                + " from " + NotifyCp.class.getName() + " n "
                + " inner join n.cpInNotifyCp cp where 1=1";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            Long total = (Long) query.uniqueResult();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(NotifyCpDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new Long("0");
        }
    }

    @Override
    public Long getTotalNotifyCpByOption(String inputSearch, String fromCreateDate,
            String toCreateDate) {
        String sql = "select count(n)"
                + " from " + NotifyCp.class.getName() + " n "
                + " inner join n.cpInNotifyCp cp where 1=1";
        if (inputSearch != null && !inputSearch.equals("")) {
            sql += " and upper(cp.cpName) like upper('%" + inputSearch + "%')";
        }
        if (fromCreateDate != null && !fromCreateDate.equals("")) {
            sql += " and n.createDate >= to_date('" + fromCreateDate + "','yyyy/MM/dd')";
        }
        if (toCreateDate != null && !toCreateDate.equals("")) {
            sql += " and n.createDate <= to_date('" + toCreateDate + "','yyyy/MM/dd')";
        }
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            Long total = (Long) query.uniqueResult();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(NotifyCpDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new Long("0");
        }
    }

}
