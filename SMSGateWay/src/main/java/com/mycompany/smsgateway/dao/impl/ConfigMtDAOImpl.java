/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.ConfigMtDAO;
import com.mycompany.smsgateway.entities.ConfigMt;
import com.mycompany.smsgateway.model.CmdcodeListModel;
import com.mycompany.smsgateway.model.ConfigMtModel;
import java.math.BigDecimal;
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
public class ConfigMtDAOImpl implements ConfigMtDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ConfigMtModel> getAllConfigMt() {
        String sql = "select new " + ConfigMtModel.class.getName()
                + "(c.mtId, sc.shcodeId, sc.shortcode,"
                + " c.mtContent, c.mtCode, c.createTime, c.updateTime)"
                + " from " + ConfigMt.class.getName() + " c "
                + " left join c.shortcodeInConfigMt sc"
                + " order by c.createTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            List<ConfigMtModel> configs = query.list();
            return configs;
        } catch (Exception ex) {
            Logger.getLogger(ConfigMtDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<ConfigMtModel> getAllConfigMt(int start, int next) {
        String sql = "select new " + ConfigMtModel.class.getName()
                + "(c.mtId, sc.shcodeId, sc.shortcode,"
                + " c.mtContent, c.mtCode, c.createTime, c.updateTime)"
                + " from " + ConfigMt.class.getName() + " c "
                + " left join c.shortcodeInConfigMt sc"
                + " order by c.createTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<ConfigMtModel> configs = query.list();
            return configs;
        } catch (Exception ex) {
            Logger.getLogger(ConfigMtDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<ConfigMtModel> getConfigMtByOption(String inputSearch, String fromCreateDate,
            String toCreateDate, int start, int next) {
        String sql = "select new " + ConfigMtModel.class.getName()
                + "(c.mtId, sc.shcodeId, sc.shortcode,"
                + " c.mtContent, c.mtCode, c.createTime, c.updateTime)"
                + " from " + ConfigMt.class.getName() + " c "
                + " left join c.shortcodeInConfigMt sc where 1=1";
        if (inputSearch != null && !inputSearch.equals("")) {
            sql += " and sc.shortcode like '%" + inputSearch + "%'";
        }
        if (fromCreateDate != null && !fromCreateDate.equals("")) {
            sql += " and c.createTime >= to_date('" + fromCreateDate + "','yyyy/MM/dd')";
        }
        if (toCreateDate != null && !toCreateDate.equals("")) {
            sql += " and c.createTime <= to_date('" + toCreateDate + "','yyyy/MM/dd')";
        }
        sql += " order by c.createTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<ConfigMtModel> configs = query.list();
            return configs;
        } catch (Exception ex) {
            Logger.getLogger(ConfigMtDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Long getTotalConfigMt() {
        String sql = "select count(c)"
                + " from " + ConfigMt.class.getName() + " c "
                + " order by c.createTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            Long total = (Long) query.uniqueResult();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(ConfigMtDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new Long("0");
        }
    }

    @Override
    public Long getTotalConfigMtByOption(String inputSearch, String fromCreateDate,
            String toCreateDate) {
        String sql = "select count(c)"
                + " from " + ConfigMt.class.getName() + " c where 1=1";
        if (inputSearch != null && !inputSearch.equals("")) {
            sql += " and sc.shortcode like '%" + inputSearch + "%'";
        }
        if (fromCreateDate != null && !fromCreateDate.equals("")) {
            sql += " and c.createTime >= to_date('" + fromCreateDate + "','yyyy/MM/dd')";
        }
        if (toCreateDate != null && !toCreateDate.equals("")) {
            sql += " and c.createTime <= to_date('" + toCreateDate + "','yyyy/MM/dd')";
        }
        sql += " order by c.createTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            Long total = (Long) query.uniqueResult();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(ConfigMtDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new Long("0");
        }
    }

    @Override
    public int addConfigMt(String shortcode, String mtContent, String mtCode) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int updateConfigMt(BigDecimal mtId, String shortcode, String mtContent, String mtCode) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int deleteConfigMt(BigDecimal mtId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
