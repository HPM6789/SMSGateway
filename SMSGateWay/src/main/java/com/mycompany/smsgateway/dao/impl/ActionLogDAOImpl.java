/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.ActionLogDAO;
import com.mycompany.smsgateway.entities.ActionLog;
import com.mycompany.smsgateway.model.ActionLogModel;
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
public class ActionLogDAOImpl implements ActionLogDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ActionLogModel> getAllActions() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ActionLogModel> getAllActions(int start, int next) {
        String sql = "select new " + ActionLogModel.class.getName()
                + "(a.actionlogId, a.user.userId, a.user.userName, a.user.userFullname, a.actionlogName, "
                + "a.actionlogObjectType, a.actionlogObjectId, a.actionlogIp, "
                + "a.actionlogDevice, a.actionlogOs, a.actionlogApp, a.actionlogTime, "
                + "a.actionlogResult, a.actionlogDesc, a.actionlogData, "
                + "a.actionlogMsisdn)"
                + " from " + ActionLog.class.getName() + " a "
                + " left join a.user"
                + " order by a.actionlogTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<ActionLogModel> logs = query.list();
            return logs;
        } catch (Exception ex) {
            Logger.getLogger(ActionLogDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<ActionLogModel> getAllActionsByName(String actionlogName, int start, int next) {
        String sql = "select new " + ActionLogModel.class.getName()
                + "(a.actionlogId, a.user.userId, a.user.userName, a.user.userFullname, a.actionlogName, "
                + "a.actionlogObjectType, a.actionlogObjectId, a.actionlogIp, "
                + "a.actionlogDevice, a.actionlogOs, a.actionlogApp, a.actionlogTime, "
                + "a.actionlogResult, a.actionlogDesc, a.actionlogData, "
                + "a.actionlogMsisdn)"
                + " from " + ActionLog.class.getName() + " a "
                + " left join a.user where 1=1";
        if (actionlogName != null && !actionlogName.equals("")) {
            sql += " and upper(a.actionlogName) like upper('%" + actionlogName + "%')";
        }
        sql += " order by a.actionlogTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<ActionLogModel> logs = query.list();
            return logs;
        } catch (Exception ex) {
            Logger.getLogger(ActionLogDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<ActionLogModel> getAllActionsByOption(String inputSearch, String fromCreateDate,
            String toCreateDate, String actionResult, int start, int next) {
        String sql = "select new " + ActionLogModel.class.getName()
                + "(a.actionlogId, a.user.userId, a.user.userName, a.user.userFullname, a.actionlogName, "
                + "a.actionlogObjectType, a.actionlogObjectId, a.actionlogIp, "
                + "a.actionlogDevice, a.actionlogOs, a.actionlogApp, a.actionlogTime, "
                + "a.actionlogResult, a.actionlogDesc, a.actionlogData, "
                + "a.actionlogMsisdn)"
                + " from " + ActionLog.class.getName() + " a "
                + " left join a.user where 1=1";
        if (inputSearch != null && !inputSearch.equals("")) {
            sql += " and (upper(a.actionlogName) like upper('%" + inputSearch + "%')"
                    + " or upper( a.user.userName) like upper('%" + inputSearch + "%'))";
        }
        if (fromCreateDate != null && !fromCreateDate.equals("")) {
            sql += " and a.actionlogTime >= to_date('" + fromCreateDate + "','yyyy/MM/dd')";
        }
        if (toCreateDate != null && !toCreateDate.equals("")) {
            sql += " and a.actionlogTime <= to_date('" + toCreateDate + "','yyyy/MM/dd')";
        }
        if (actionResult != null && !actionResult.equals("")) {
            sql += " and a.actionlogResult like '" + actionResult + "'";
        }
        sql += " order by a.actionlogTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<ActionLogModel> logs = query.list();
            return logs;
        } catch (Exception ex) {
            Logger.getLogger(ActionLogDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ActionLogModel getActionLogById(BigDecimal actionlogId) {
        String sql = "select new " + ActionLogModel.class.getName()
                + "(a.actionlogId, a.user.userId, a.user.userName, a.user.userFullname, a.actionlogName, "
                + "a.actionlogObjectType, a.actionlogObjectId, a.actionlogIp, "
                + "a.actionlogDevice, a.actionlogOs, a.actionlogApp, a.actionlogTime, "
                + "a.actionlogResult, a.actionlogDesc, a.actionlogData, "
                + "a.actionlogMsisdn)"
                + " from " + ActionLog.class.getName() + " a "
                + " left join a.user"
                + " where a.actionlogId = :actionlogId"
                + " order by a.actionlogTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter("actionlogId", actionlogId);
            ActionLogModel logs = (ActionLogModel) query.uniqueResult();
            return logs;
        } catch (Exception ex) {
            Logger.getLogger(ActionLogDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int addUserAction(BigInteger userId, String actionlogName, String actionlogObjectType,
            BigInteger actionlogObjectId, String actionlogIp, String actionlogDevice,
            String actionlogOs, String actionlogApp, String actionlogResult, String actionlogDesc,
            String actionlogData, String actionlogMsisdn) {
        String sql = "insert into action_log (ACTIONLOG_ID, USER_ID, ACTIONLOG_NAME, ACTIONLOG_OBJECT_TYPE, "
                + "ACTIONLOG_OBJECT_ID, ACTIONLOG_IP, ACTIONLOG_DEVICE, ACTIONLOG_OS, ACTIONLOG_APP, "
                + "ACTIONLOG_TIME, ACTIONLOG_RESULT, ACTIONLOG_DESC, ACTIONLOG_DATA, ACTIONLOG_MSISDN)"
                + " values (SEQ_ACTION_LOG.nextval, :userId, :actionlogName, :actionlogObjectType,"
                + " :actionlogObjectId, :actionlogIp, :actionlogDevice, :actionlogOs, :actionlogApp,"
                + " :actionlogTime, "
                + " :actionlogResult, :actionlogDesc, :actionlogData, :actionlogMsisdn)";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("userId", userId);
            query.setParameter("actionlogName", actionlogName);
            query.setParameter("actionlogObjectType", actionlogObjectType);
            query.setParameter("actionlogObjectId", actionlogObjectId);
            query.setParameter("actionlogIp", actionlogIp);
            query.setParameter("actionlogDevice", actionlogDevice);
            query.setParameter("actionlogOs", actionlogOs);
            query.setParameter("actionlogApp", actionlogApp);
            query.setParameter("actionlogTime", new Timestamp(System.currentTimeMillis()));
            query.setParameter("actionlogResult", actionlogResult);
            query.setParameter("actionlogDesc", actionlogDesc);
            query.setParameter("actionlogData", actionlogData);
            query.setParameter("actionlogMsisdn", actionlogMsisdn);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(ActionLogDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public Long getTotalActionLogs() {
        String sql = "select count(a)"
                + " from " + ActionLog.class.getName() + " a ";
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

    @Override
    public Long getTotalActionLogsByName(String actionlogName) {
        String sql = "select count(a)"
                + " from " + ActionLog.class.getName() + " a "
                + " where 1=1";
        if (actionlogName != null && !actionlogName.equals("")) {
            sql += " and upper(a.actionlogName) like upper('%" + actionlogName + "%')";
        }
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

    @Override
    public Long getTotalActionLogsByOption(String inputSearch, String fromCreateDate,
            String toCreateDate, String actionResult) {
        String sql = "select count(a)"
                + " from " + ActionLog.class.getName() + " a "
                + " left join a.user where 1=1";
        if (inputSearch != null && !inputSearch.equals("")) {
            sql += " and (upper(a.actionlogName) like upper('%" + inputSearch + "%')"
                    + "or upper( a.user.userName) like upper('%" + inputSearch + "%'))";
        }
        if (fromCreateDate != null && !fromCreateDate.equals("")) {
            sql += " and a.actionlogTime >= to_date('" + fromCreateDate + "','yyyy/MM/dd')";
        }
        if (toCreateDate != null && !toCreateDate.equals("")) {
            sql += " and a.actionlogTime <= to_date('" + toCreateDate + "','yyyy/MM/dd')";
        }
        if (actionResult != null && !actionResult.equals("")) {
            sql += " and a.actionlogResult like '" + actionResult + "'";
        }
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
