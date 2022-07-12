/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.ConfigMtDAO;
import com.mycompany.smsgateway.entities.ConfigMt;
import com.mycompany.smsgateway.model.ConfigMtModel;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
            sql += " and c.shortcodeInConfigMt.shortcode like '%" + inputSearch + "%'";
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
    public ConfigMtModel getMtByShortcode(String shortcode) {
        String sql = "select new " + ConfigMtModel.class.getName()
                + "(c.mtId, sc.shcodeId, sc.shortcode,"
                + " c.mtContent, c.mtCode, c.createTime, c.updateTime)"
                + " from " + ConfigMt.class.getName() + " c "
                + " left join c.shortcodeInConfigMt sc"
                + " where sc.shortcode like :shortcode";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter("shortcode", shortcode);
            ConfigMtModel config = (ConfigMtModel) query.uniqueResult();
            return config;
        } catch (Exception ex) {
            Logger.getLogger(ConfigMtDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ConfigMtModel getMtById(BigDecimal mtId) {
        String sql = "select new " + ConfigMtModel.class.getName()
                + "(c.mtId, sc.shcodeId, sc.shortcode,"
                + " c.mtContent, c.mtCode, c.createTime, c.updateTime)"
                + " from " + ConfigMt.class.getName() + " c "
                + " left join c.shortcodeInConfigMt sc"
                + " where c.mtId like :mtId";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter("mtId", mtId);
            ConfigMtModel config = (ConfigMtModel) query.uniqueResult();
            return config;
        } catch (Exception ex) {
            Logger.getLogger(ConfigMtDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public BigDecimal getNewestMtId() {
        String sql = "select c.mtId"
                + " from " + ConfigMt.class.getName() + " c "
                + " order by c.createTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setMaxResults(1);
            BigDecimal mtId = (BigDecimal) query.uniqueResult();
            return mtId;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new BigDecimal("0");
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
            sql += " and c.shortcodeInConfigMt.shortcode like '%" + inputSearch + "%'";
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
    public int addConfigMt(String shortcode, String mtContent) {
        String sql = "insert into config_mt (mt_id, mt_code, mt_content, shortcode, create_time)"
                + " values (SEQ_CONFIG_MT.nextval, :mtCode, :mtContent, :shortcode, :createTime)";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("mtCode", "INVALID");
            query.setParameter("mtContent", mtContent);
            query.setParameter("shortcode", shortcode);
            query.setParameter("createTime", new Timestamp(System.currentTimeMillis()));
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(ConfigMtDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateConfigMt(BigDecimal mtId, String shortcode, String mtContent) {
        String sql = "update config_mt set mt_content = :mtContent, shortcode = :shortcode,"
                + " update_time = :updateTime"
                + " where mt_id = :mtId";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("mtContent", mtContent);
            query.setParameter("shortcode", shortcode);
            query.setParameter("updateTime", new Timestamp(System.currentTimeMillis()));
            query.setParameter("mtId", mtId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(ConfigMtDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int deleteConfigMt(BigDecimal mtId) {
        String sql = "delete from config_mt "
                + " where mt_id = :mtId";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("mtId", mtId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(ConfigMtDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
