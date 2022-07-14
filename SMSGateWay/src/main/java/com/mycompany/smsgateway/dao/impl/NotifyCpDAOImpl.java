/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.NotifyCpDAO;
import com.mycompany.smsgateway.entities.NotifyCp;
import com.mycompany.smsgateway.model.NotifyCpModel;
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
    public NotifyCpModel getNotifyCpByCpId(BigDecimal cpId) {
        String sql = "select new " + NotifyCpModel.class.getName()
                + "(n.notifyId, n.moReceiveUrl, "
                + "cp.cpId, cp.cpName, cp.cpCode, n.note, n.createDate, "
                + "n.lastUpdate, n.status, n.shcodeId, n.moReceiveUrlBkp)"
                + " from " + NotifyCp.class.getName() + " n "
                + " inner join n.cpInNotifyCp cp"
                + " where cp.cpId = :cpId";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter("cpId", cpId);
            NotifyCpModel notify = (NotifyCpModel) query.uniqueResult();
            return notify;
        } catch (Exception ex) {
            Logger.getLogger(NotifyCpDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public NotifyCpModel getNotifyCpById(BigDecimal notifyId) {
        String sql = "select new " + NotifyCpModel.class.getName()
                + "(n.notifyId, n.moReceiveUrl, "
                + "cp.cpId, cp.cpName, cp.cpCode, n.note, n.createDate, "
                + "n.lastUpdate, n.status, n.shcodeId, n.moReceiveUrlBkp)"
                + " from " + NotifyCp.class.getName() + " n "
                + " inner join n.cpInNotifyCp cp"
                + " where n.notifyId = :notifyId";
        try {
            System.out.println("notifyID: " + notifyId);
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter("notifyId", notifyId);
            NotifyCpModel notify = (NotifyCpModel) query.uniqueResult();
            return notify;
        } catch (Exception ex) {
            Logger.getLogger(NotifyCpDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public BigDecimal getNewestNotifyId() {
        String sql = "select n.notifyId"
                + " from " + NotifyCp.class.getName() + " n "
                + " inner join n.cpInNotifyCp cp"
                + " order by n.createDate desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setMaxResults(1);
            BigDecimal notifyId = (BigDecimal) query.uniqueResult();
            return notifyId;
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
            return new Long("-1");
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
            return new Long("-1");
        }
    }

    @Override
    public int addNotifyCp(String moReceiveUrl, BigInteger cpId, String note, String moReceiveUrlBkp) {
        String sql = "insert into notify_cp (notify_id, mo_receive_url, cp_id, note,"
                + " create_date, status, mo_receive_url_bkp)"
                + " values(SEQ_NOTIFY_QUEUE.nextval, :moReceiveUrl, :cpId, :note, :createDate,"
                + " :status, :moReceiveUrlBkp)";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("moReceiveUrl", moReceiveUrl);
            query.setParameter("cpId", cpId);
            query.setParameter("note", note);
            query.setParameter("createDate", new Timestamp(System.currentTimeMillis()));
            query.setParameter("status", new BigInteger("0"));
            query.setParameter("moReceiveUrlBkp", moReceiveUrlBkp);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(NotifyCpDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateNotifyCp(BigDecimal notifyId, String moReceiveUrl, BigInteger cpId,
            String note, BigInteger status, String moReceiveUrlBkp) {
        String sql = "update notify_cp set mo_receive_url = :moReceiveUrl, cp_id = :cpId, note = :note,"
                + " last_update = :lastUpdate, status = :status, mo_receive_url_bkp = :moReceiveUrlBkp"
                + " where notify_id = :notifyId";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("moReceiveUrl", moReceiveUrl);
            query.setParameter("cpId", cpId);
            query.setParameter("note", note);
            query.setParameter("lastUpdate", new Timestamp(System.currentTimeMillis()));
            query.setParameter("status", status);
            query.setParameter("moReceiveUrlBkp", moReceiveUrlBkp);
            query.setParameter("notifyId", notifyId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(NotifyCpDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int deleteNotifyCp(BigDecimal notifyId) {
        String sql = "delete from notify_cp where notify_id = :notifyId";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("notifyId", notifyId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(NotifyCpDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
