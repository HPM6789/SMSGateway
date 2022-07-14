/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.KpiMoDAO;
import com.mycompany.smsgateway.entities.KpiMo;
import com.mycompany.smsgateway.model.KpiMoModel;
import java.math.BigInteger;
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
public class KpiMoDAOImpl implements KpiMoDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<KpiMoModel> getAllKpiMoList() {
        String sql = "select new " + KpiMoModel.class.getName()
                + "(k.kpiMoPK.datetime, k.kpiMoPK.moShortcode, k.moReceive, "
                + "k.moError, k.moPending, k.moFinished, "
                + "k.moNotifyPending, k.moNotifyFinished, k.moRefund, "
                + " k.moCharge)"
                + " from " + KpiMo.class.getName() + " k "
                + " order by k.kpiMoPK.datetime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            List<KpiMoModel> kpis = query.list();
            return kpis;
        } catch (Exception ex) {
            Logger.getLogger(KpiMoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<KpiMoModel> getKpiMoList(int start, int next) {
        String sql = "select new " + KpiMoModel.class.getName()
                + "(k.kpiMoPK.datetime, k.kpiMoPK.moShortcode, k.moReceive, "
                + "k.moError, k.moPending, k.moFinished, "
                + "k.moNotifyPending, k.moNotifyFinished, k.moRefund, "
                + " k.moCharge)"
                + " from " + KpiMo.class.getName() + " k "
                + " order by k.kpiMoPK.datetime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<KpiMoModel> kpis = query.list();
            return kpis;
        } catch (Exception ex) {
            Logger.getLogger(KpiMoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<KpiMoModel> getKpiMoListByOption(String inputSearch, String fromCreateDate,
            String toCreateDate, int start, int next) {
        String sql = "select new " + KpiMoModel.class.getName()
                + "(k.kpiMoPK.datetime, k.kpiMoPK.moShortcode, k.moReceive, "
                + "k.moError, k.moPending, k.moFinished, "
                + "k.moNotifyPending, k.moNotifyFinished, k.moRefund, "
                + " k.moCharge)"
                + " from " + KpiMo.class.getName() + " k where 1=1";
        if (inputSearch != null && !inputSearch.equals("")) {
            sql += " and k.kpiMoPK.moShortcode like '%" + inputSearch + "%'";
        }
        if (fromCreateDate != null && !fromCreateDate.equals("")) {
            sql += " and k.kpiMoPK.datetime >= '" + fromCreateDate + "'";
        }
        if (toCreateDate != null && !toCreateDate.equals("")) {
            sql += " and k.kpiMoPK.datetime <= '" + toCreateDate + "'";
        }
        sql += " order by k.kpiMoPK.datetime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<KpiMoModel> kpis = query.list();
            return kpis;
        } catch (Exception ex) {
            Logger.getLogger(KpiMoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public KpiMoModel getKpiMoByPK(String datetime, BigInteger shortcode) {
        String sql = "select new " + KpiMoModel.class.getName()
                + "(k.kpiMoPK.datetime, k.kpiMoPK.moShortcode, k.moReceive, "
                + "k.moError, k.moPending, k.moFinished, "
                + "k.moNotifyPending, k.moNotifyFinished, k.moRefund, "
                + " k.moCharge)"
                + " from " + KpiMo.class.getName() + " k "
                + " where k.kpiMoPK.datetime like :datetime"
                + " and k.kpiMoPK.moShortcode like :shortcode"
                + " order by k.kpiMoPK.datetime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter("datetime", datetime);
            query.setParameter("shortcode", shortcode);
            KpiMoModel kpi = (KpiMoModel) query.uniqueResult();
            return kpi;
        } catch (Exception ex) {
            Logger.getLogger(KpiMoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Long getTotalKpiMo() {
        String sql = "select count(k)"
                + " from " + KpiMo.class.getName() + " k ";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            Long total = (Long) query.uniqueResult();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(KpiMoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new Long("-1");
        }
    }

    @Override
    public Long getTotalKpiMoByOption(String inputSearch, String fromCreateDate,
            String toCreateDate) {
        String sql = "select count(k)"
                + " from " + KpiMo.class.getName() + " k where 1=1";
        if (inputSearch != null && !inputSearch.equals("")) {
            sql += " and k.kpiMoPK.moShortcode like '%" + inputSearch + "%'";
        }
        if (fromCreateDate != null && !fromCreateDate.equals("")) {
            sql += " and k.kpiMoPK.datetime >= '" + fromCreateDate + "'";
        }
        if (toCreateDate != null && !toCreateDate.equals("")) {
            sql += " and k.kpiMoPK.datetime <= '" + toCreateDate + "'";
        }
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            Long total = (Long) query.uniqueResult();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(KpiMoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new Long("-1");
        }
    }

}
