/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.ShortcodeListDAO;
import com.mycompany.smsgateway.entities.ShortcodeList;
import com.mycompany.smsgateway.model.ShortcodeListModel;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class ShortcodeListDAOImpl implements ShortcodeListDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ShortcodeListModel> getAllShortcode() {
        String sql = "select new " + ShortcodeListModel.class.getName()
                + "(s.shcodeId, s.shortcode, s.price, s.limitedMtNo, s.createTime, s.updateTime, "
                + "s.approveTime, s.status, s.user.userName, s.user.userId)"
                + " from " + ShortcodeList.class.getName() + " s "
                + " inner join s.user u"
                + " order by s.createTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            List<ShortcodeListModel> shortcodes = query.list();
            return shortcodes;
        } catch (Exception ex) {
            Logger.getLogger(ShortcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<ShortcodeListModel> getAllShortcodeByCode(String shortcode) {
        String sql = "select new " + ShortcodeListModel.class.getName()
                + "(s.shcodeId, s.shortcode, s.price, s.limitedMtNo, s.createTime, s.updateTime, "
                + "s.approveTime, s.status, s.user.userName, s.user.userId)"
                + " from " + ShortcodeList.class.getName() + " s"
                + " inner join s.user where 1=1";
        if (shortcode != null && !shortcode.equals("")) {
            sql += " and s.shortcode like '%" + shortcode + "%'";
        }
        sql += " order by s.createTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            List<ShortcodeListModel> shortcodes = query.list();
            return shortcodes;
        } catch (Exception ex) {
            Logger.getLogger(ShortcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public BigDecimal getNewestShcodeId() {
        String sql = "select s.shcodeId"
                + " from " + ShortcodeList.class.getName() + " s"
                + " order by s.createTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setMaxResults(1);
            BigDecimal shcodeId = (BigDecimal) query.uniqueResult();
            return shcodeId;
        } catch (Exception ex) {
            Logger.getLogger(ShortcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new BigDecimal("0");
        }
    }

    @Override
    public ShortcodeListModel getShortcodeByCode(String shortcode) {
        String sql = "select new " + ShortcodeListModel.class.getName()
                + "(s.shcodeId, s.shortcode, s.price, s.limitedMtNo, s.createTime, s.updateTime, "
                + "s.approveTime, s.status, s.user.userName, s.user.userId)"
                + " from " + ShortcodeList.class.getName() + " s"
                + " inner join s.user"
                + " where s.shortcode like ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, shortcode);
            ShortcodeListModel sc = (ShortcodeListModel) query.uniqueResult();
            return sc;
        } catch (Exception ex) {
            Logger.getLogger(ShortcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ShortcodeListModel getShortcodeById(BigDecimal shcodeId) {
        String sql = "select new " + ShortcodeListModel.class.getName()
                + "(s.shcodeId, s.shortcode, s.price, s.limitedMtNo, s.createTime, s.updateTime, "
                + "s.approveTime, s.status, s.user.userName, s.user.userId)"
                + " from " + ShortcodeList.class.getName() + " s"
                + " inner join s.user"
                + " where s.shcodeId = :shcodeId";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter("shcodeId", shcodeId);
            ShortcodeListModel sc = (ShortcodeListModel) query.uniqueResult();
            return sc;
        } catch (Exception ex) {
            Logger.getLogger(ShortcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int addNewShortcode(String shortcode, BigInteger price, BigInteger limitedMtNo,
            Long creatorId) {
        String sql = "insert into shortcode_list (shcode_id, shortcode, price, limited_mt_no, create_time,"
                + "status, creator_id) values (SEQ_SHORTCODE.nextval,?,?,?,?,?,?)";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, shortcode);
            query.setParameter(1, price);
            query.setParameter(2, limitedMtNo);
            query.setParameter(3, new Timestamp(System.currentTimeMillis()));
            query.setParameter(4, 0);
            query.setParameter(5, creatorId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(ShortcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateShortcode(BigDecimal shcodeId, String shortcode, BigInteger price,
            BigInteger limitedMtNo) {

        String sql = "update shortcode_list set shortcode = :shortcode, price = :price, "
                + "limited_mt_no = :limited_mt_no, update_time = :update_time"
                + " where shcode_id = :shcode_id";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("shortcode", shortcode);
            query.setParameter("price", price);
            query.setParameter("limited_mt_no", limitedMtNo);
            query.setParameter("update_time", new Timestamp(System.currentTimeMillis()));
            query.setParameter("shcode_id", shcodeId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(ShortcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int deleteShortcodeById(BigDecimal shcodeId) {
        String sql = "delete from shortcode_list where shcode_id = :shcode_id";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("shcode_id", shcodeId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(ShortcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int approveShortcode(BigDecimal shcodeId) {
        String sql = "update shortcode_list set status = :status, approve_time = :approve_time"
                + " where shcode_id = :shcode_id";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("shcode_id", shcodeId);
            query.setParameter("approve_time", new Timestamp(System.currentTimeMillis()));
            query.setParameter("status", new BigInteger("1"));
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(ShortcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int disapproveShortcode(BigDecimal shcodeId) {
        String sql = "update shortcode_list set status = :status"
                + " where shcode_id = :shcode_id";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("shcode_id", shcodeId);
            query.setParameter("status", new BigInteger("0"));
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(ShortcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
