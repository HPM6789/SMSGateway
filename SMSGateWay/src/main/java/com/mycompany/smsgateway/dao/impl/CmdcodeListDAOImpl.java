/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.CmdcodeListDAO;
import com.mycompany.smsgateway.entities.CmdcodeList;
import com.mycompany.smsgateway.model.CmdcodeListModel;
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
public class CmdcodeListDAOImpl implements CmdcodeListDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CmdcodeListModel> getAllCmdcode() {
        System.out.println("getAllCmdcode: start - " + new Timestamp(System.currentTimeMillis()));
        String sql = "select new " + CmdcodeListModel.class.getName()
                + "(c.cmdId, c.cmdName, c.cmdCode, c.shortcodeCp.shortcodeCpId, c.updateTime, c.createTime, "
                + "c.status, c.type.typeCode, c.approveTime, c.price,c.description,"
                + "c.creator.userId, c.creator.userName, c.approver.userId, c.approver.userName"
                + ")"
                + " from " + CmdcodeList.class.getName() + " c "
                + " order by c.createTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            List<CmdcodeListModel> cmds = query.list();
            return cmds;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            System.out.println("getAllCmdcode: end - " + new Timestamp(System.currentTimeMillis()));
        }

    }

    @Override
    public List<CmdcodeListModel> getAllCmdcode2(int start, int next) {
        System.out.println("getAllCmdcode2: start - " + start + ", next: " + next);
        String sql = "select new " + CmdcodeListModel.class.getName()
                + "(c.cmdId, c.cmdName, c.cmdCode, c.shortcodeCp.shortcodeCpId, c.updateTime, c.createTime, "
                + "c.status, c.type.typeCode, c.approveTime, c.price,c.description,"
                + "c.creator.userId, c.creator.userName, c.approver.userId, c.approver.userName"
                + ")"
                + " from "
                + CmdcodeList.class.getName() + " c "
                + " left join c.shortcodeCp"
                + " left join c.creator"
                + " left join c.approver"
                + " left join c.type"
                + " order by c.createTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);

            List<CmdcodeListModel> cmds = query.list();
            System.out.println("cmd size: " + cmds.size());
            return cmds;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<CmdcodeListModel> getCmdcodeByName(String cmdName, int start, int next) {
        String sql = "select new " + CmdcodeListModel.class.getName()
                + "(c.cmdId, c.cmdName, c.cmdCode, c.shortcodeCp.shortcodeCpId, c.updateTime, c.createTime, "
                + "c.status, c.type.typeCode, c.approveTime, c.price,c.description,"
                + "c.creator.userId, c.creator.userName, c.approver.userId, c.approver.userName"
                + ")"
                + " from " + CmdcodeList.class.getName() + " c "
                + " left join c.shortcodeCp"
                + " left join c.creator"
                + " left join c.approver"
                + " left join c.type"
                + " where 1=1";
        if (cmdName != null && !cmdName.equals("")) {
            sql += " and upper(c.cmdName) like upper('%" + cmdName + "%')";
        }
        sql += " order by c.createTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<CmdcodeListModel> cmds = query.list();
            return cmds;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<CmdcodeListModel> getCmdcodeByOption(String inputSearch, String fromCreateDate,
            String toCreateDate, String fromUpdateDate, String toUpdateDate, BigInteger status,
            int start, int next) {
        String sql = "select new " + CmdcodeListModel.class.getName()
                + "(c.cmdId, c.cmdName, c.cmdCode, c.shortcodeCp.shortcodeCpId, c.updateTime, c.createTime, "
                + "c.status, c.type.typeCode, c.approveTime, c.price,c.description,"
                + "c.creator.userId, c.creator.userName, c.approver.userId, c.approver.userName"
                + ")"
                + " from " + CmdcodeList.class.getName() + " c "
                + " left join c.shortcodeCp"
                + " left join c.creator"
                + " left join c.approver"
                + " left join c.type"
                + " where 1=1";
        if (inputSearch != null && !inputSearch.equals("")) {
            sql += " and (upper(c.cmdName) like upper('%" + inputSearch + "%')"
                    + " or upper(c.cmdCode) like upper('%" + inputSearch + "%'))";
        }
        if (fromCreateDate != null && !fromCreateDate.equals("")) {
            sql += " and c.createTime >= to_date('" + fromCreateDate + "','yyyy/MM/dd')";
        }
        if (toCreateDate != null && !toCreateDate.equals("")) {
            sql += " and c.createTime <= to_date('" + toCreateDate + "','yyyy/MM/dd')";
        }
        if (fromUpdateDate != null && !fromUpdateDate.equals("")) {
            sql += " and c.updateTime >= to_date('" + fromUpdateDate + "','yyyy/MM/dd')";
        }
        if (toUpdateDate != null && !toUpdateDate.equals("")) {
            sql += " and c.updateTime <= to_date('" + toUpdateDate + "','yyyy/MM/dd')";
        }
        if (status != null) {
            sql += " and c.status = " + status;
        }
        sql += " order by c.createTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<CmdcodeListModel> cmds = query.list();
            return cmds;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public BigDecimal getNewestCmdId() {
        String sql = "select c.cmdId"
                + " from " + CmdcodeList.class.getName() + " c "
                + " order by c.createTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setMaxResults(1);
            BigDecimal cmdId = (BigDecimal) query.uniqueResult();
            return cmdId;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new BigDecimal("0");
        }
    }

    @Override
    public CmdcodeListModel getCmdcodeById(BigDecimal cmdId) {
        String sql = "select new " + CmdcodeListModel.class.getName()
                + "(c.cmdId, c.cmdName, c.cmdCode, c.shortcodeCp.shortcodeCpId, c.updateTime, c.createTime, "
                + "c.status, c.type.typeCode, c.approveTime, c.price,c.description,"
                + "c.creator.userId, c.creator.userName, c.approver.userId, c.approver.userName"
                + ")"
                + " from "
                + CmdcodeList.class.getName() + " c "
                + " left join c.shortcodeCp"
                + " left join c.creator"
                + " left join c.approver"
                + " left join c.type"
                + " where c.cmdId = :cmdId";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter("cmdId", cmdId);

            CmdcodeListModel cmd = (CmdcodeListModel) query.uniqueResult();
            return cmd;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Long getTotalCmdcode() {
        String sql = "select count(c)"
                + " from " + CmdcodeList.class.getName() + " c "
                + " order by c.createTime desc";
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
    public Long getTotalCmdcodeByName(String cmdName) {
        String sql = "select count(c)"
                + " from " + CmdcodeList.class.getName() + " c ";
        if (cmdName != null && !cmdName.equals("")) {
            sql += " where upper(c.cmdName) like upper('%" + cmdName + "%')";
        }
        sql += " order by c.createTime desc";
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
    public Long getTotalCmdcodeByOption(String inputSearch, String fromCreateDate,
            String toCreateDate, String fromUpdateDate, String toUpdateDate, BigInteger status) {
        String sql = "select count(c)"
                + " from " + CmdcodeList.class.getName() + " c where 1=1";
        if (inputSearch != null && !inputSearch.equals("")) {
            sql += " and (upper(c.cmdName) like upper('%" + inputSearch + "%')"
                    + " or upper(c.cmdCode) like upper('%" + inputSearch + "%'))";
        }
        if (fromCreateDate != null && !fromCreateDate.equals("")) {
            sql += " and c.createTime >= to_date('" + fromCreateDate + "','yyyy/MM/dd')";
        }
        if (toCreateDate != null && !toCreateDate.equals("")) {
            sql += " and c.createTime <= to_date('" + toCreateDate + "','yyyy/MM/dd')";
        }
        if (fromUpdateDate != null && !fromUpdateDate.equals("")) {
            sql += " and c.updateTime >= to_date('" + fromUpdateDate + "','yyyy/MM/dd')";
        }
        if (toUpdateDate != null && !toUpdateDate.equals("")) {
            sql += " and c.updateTime <= to_date('" + toUpdateDate + "','yyyy/MM/dd')";
        }
        if (status != null) {
            sql += " and c.status = " + status;
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
    public int approveAllCmdcode() {
        String sql = "update cmdcode_list set status = :status where status = 0";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("status", 1);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int approveCmdcode(BigDecimal cmdId) {
        String sql = "update cmdcode_list set status = :status where cmd_id = :cmdId";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("cmdId", cmdId);
            query.setParameter("status", 1);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int disapproveCmdcode(BigDecimal cmdId) {
        String sql = "update cmdcode_list set status = :status where cmd_id = :cmdId";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("cmdId", cmdId);
            query.setParameter("status", 0);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int deleteCmdcode(BigDecimal cmdId) {
        String sql = "update cmdcode_list set status = :status where cmd_id = :cmdId";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("cmdId", cmdId);
            query.setParameter("status", 2);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int restoreCmdcode(BigDecimal cmdId) {
        String sql = "update cmdcode_list set status = :status where cmd_id = :cmdId";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("cmdId", cmdId);
            query.setParameter("status", 0);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int addNewCmdcode(String cmdName, String cmdCode, BigInteger shortcodeCpId,
            String typeCode, Long price, String description, Long creatorId) {
        String sql = "insert into cmdcode_list (cmd_id, cmd_name, cmd_code, shortcode_cp_id,"
                + "create_time, status, type_code, price, creator_id, description)"
                + " values (SEQ_CMDCODE.nextval, :cmdName, :cmdCode, :shortcodeCpId,"
                + ":createTime, :status, :typeCode, :price, :creatorId, :description)";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("cmdName", cmdName);
            query.setParameter("cmdCode", cmdCode);
            query.setParameter("shortcodeCpId", shortcodeCpId);
            query.setParameter("createTime", new Timestamp(System.currentTimeMillis()));
            query.setParameter("status", 0);
            query.setParameter("typeCode", typeCode);
            query.setParameter("price", price);
            query.setParameter("creatorId", creatorId);
            query.setParameter("description", description);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateCmdcode(BigDecimal cmdId, String cmdName, String cmdCode, BigInteger shortcodeCpId,
            String typeCode, Long price, String description, Long creatorId) {
        String sql = "update cmdcode_list set cmd_name = :cmdName, cmd_code = :cmdCode,"
                + " shortcode_cp_id = :shortcodeCpId, type_code = :typeCode, update_time = :updateTime,"
                + " price = :price, description = :description, creator_id = :creatorId"
                + " where cmd_id = :cmdId";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("cmdName", cmdName);
            query.setParameter("cmdCode", cmdCode);
            query.setParameter("shortcodeCpId", shortcodeCpId);
            query.setParameter("updateTime", new Timestamp(System.currentTimeMillis()));
            query.setParameter("typeCode", typeCode);
            query.setParameter("price", price);
            query.setParameter("creatorId", creatorId);
            query.setParameter("description", description);
            query.setParameter("cmdId", cmdId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(CmdcodeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
