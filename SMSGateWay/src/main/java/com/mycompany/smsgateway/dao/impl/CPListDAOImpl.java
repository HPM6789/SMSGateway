/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.CPListDAO;
import com.mycompany.smsgateway.entities.AuthUser;
import com.mycompany.smsgateway.entities.CpList;
import com.mycompany.smsgateway.model.CpListModel;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
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
public class CPListDAOImpl implements CPListDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private BigDecimal getNextvalForSeqShcodeCp() {
        String sql = "select SEQ_SHORTCODE_CP.nextval from dual";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);

            BigDecimal value = (BigDecimal) query.uniqueResult();
            System.out.println("nextval: " + value.toString());
            return value;
        } catch (Exception ex) {
            Logger.getLogger(CPListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new BigDecimal("0");
        }
    }

    @Override
    public CpListModel getCPFromUsername(String username) {
        String sql = "select new " + CpListModel.class.getName()
                + "(a.cp.cpId, a.cp.cpName, a.cp.cpCode, a.cp.contact, a.cp.createdTime,"
                + " a.cp.updatedTime, a.cp.usernameMt, a.cp.passwordMt, a.cp.listipMt, a.cp.usernameCharge,"
                + " a.cp.passwordCharge, a.cp.taxCode, a.cp.representer, a.cp.address, a.cp.emailCp) "
                + " from " + AuthUser.class.getName() + " a inner join a.cp"
                + " where a.userName like ?";
        System.out.println("getCPFromUsername");
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);

            query.setParameter(0, username);
            CpListModel cp = (CpListModel) query.uniqueResult();
            return cp;
        } catch (Exception ex) {
            Logger.getLogger(CPListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<CpListModel> getAllCpList() {
        String sql = "select new " + CpListModel.class.getName()
                + "(c.cpId, c.cpName, c.cpCode, c.contact, c.createdTime,"
                + " c.updatedTime, c.usernameMt, c.passwordMt, c.listipMt, c.usernameCharge,"
                + " c.passwordCharge, c.taxCode, c.representer, c.address, c.emailCp) "
                + " from " + CpList.class.getName() + " c"
                + " order by c.createdTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);

            List<CpListModel> cp = query.list();
            return cp;
        } catch (Exception ex) {
            Logger.getLogger(CPListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<CpListModel> getCpListsByName(String name) {
        String sql = "select new " + CpListModel.class.getName()
                + "(c.cpId, c.cpName, c.cpCode, c.contact, c.createdTime,"
                + " c.updatedTime, c.usernameMt, c.passwordMt, c.listipMt, c.usernameCharge,"
                + " c.passwordCharge, c.taxCode, c.representer, c.address, c.emailCp) "
                + " from " + CpList.class.getName() + " c";
        if (name != null && !name.equals("")) {
            sql += " where upper(c.cpName) like upper('%" + name + "%')";
        }
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            List<CpListModel> cp = query.list();
            return cp;
        } catch (Exception ex) {
            Logger.getLogger(CPListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<CpListModel> getCpListsByOption(String inputSearch,
            String fromCreateDate, String toCreateDate, String fromUpdateDate, 
            String toUpdateDate) {
        String sql = "select new " + CpListModel.class.getName()
                + "(c.cpId, c.cpName, c.cpCode, c.contact, c.createdTime,"
                + " c.updatedTime, c.usernameMt, c.passwordMt, c.listipMt, c.usernameCharge,"
                + " c.passwordCharge, c.taxCode, c.representer, c.address, c.emailCp) "
                + " from " + CpList.class.getName() + " c where 1=1";
        if (inputSearch != null && !inputSearch.equals("")) {
            sql += " and (upper(c.cpName) like upper('%" + inputSearch + "%')"
                    + " or upper(c.cpCode) like upper('%" + inputSearch + "%'))";
        }
        if (fromCreateDate != null && !fromCreateDate.equals("")) {
            sql += " and c.createdTime >= to_date('" + fromCreateDate + "','yyyy/MM/dd')";
        }
        if (toCreateDate != null && !toCreateDate.equals("")) {
            sql += " and c.createdTime <= to_date('" + toCreateDate + "','yyyy/MM/dd')";
        }
        if (fromUpdateDate != null && !fromUpdateDate.equals("")) {
            sql += " and c.updatedTime >= to_date('" + fromUpdateDate + "','yyyy/MM/dd')";
        }
        if (toUpdateDate != null && !toUpdateDate.equals("")) {
            sql += " and c.updatedTime <= to_date('" + toUpdateDate + "','yyyy/MM/dd')";
        }
        sql += " order by c.createdTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            List<CpListModel> cp = query.list();
            return cp;
        } catch (Exception ex) {
            Logger.getLogger(CPListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public BigDecimal getNewestCpId() {
        String sql = "select c.cpId"
                + " from " + CpList.class.getName() + " c"
                + " order by c.createdTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setMaxResults(1);
            BigDecimal cp = (BigDecimal) query.uniqueResult();
            return cp;
        } catch (Exception ex) {
            Logger.getLogger(CPListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new BigDecimal("0");
        }
    }

    @Override
    public int addNewCpList(String cpName, String cpCode, String contact,
            String usernameMt, String passwordMt, String listipMt,
            String usernameCharge, String passwordCharge, String taxCode, String representer,
            String address, String emailCp) {
        String sql = "insert into cp_list (cp_id, cp_name, cp_code, contact, created_time, username_mt, "
                + "password_mt, listip_mt, username_charge, password_charge, tax_code, representer, address, "
                + "email_cp) values(seq_cp.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, cpName);
            query.setParameter(1, cpCode);
            query.setParameter(2, contact);
            query.setParameter(3, new Timestamp(System.currentTimeMillis()));
            query.setParameter(4, usernameMt);
            query.setParameter(5, passwordMt);
            query.setParameter(6, listipMt);
            query.setParameter(7, usernameCharge);
            query.setParameter(8, passwordCharge);
            query.setParameter(9, taxCode);
            query.setParameter(10, representer);
            query.setParameter(11, address);
            query.setParameter(12, emailCp);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(CPListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateCpList(BigDecimal cpId, String cpName, String cpCode, String contact,
            String usernameMt, String passwordMt, String listipMt, String usernameCharge,
            String passwordCharge, String taxCode, String representer, String address, String emailCp) {
        String sql = "update cp_list set cp_name = ?, cp_code = ?, contact = ?, updated_time = ?, username_mt = ?, "
                + "password_mt = ?, listip_mt = ?, username_charge = ?, password_charge = ?,"
                + " tax_code = ?, representer = ?, address = ?, "
                + "email_cp = ? where cp_id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, cpName);
            query.setParameter(1, cpCode);
            query.setParameter(2, contact);
            query.setParameter(3, new Timestamp(System.currentTimeMillis()));
            query.setParameter(4, usernameMt);
            query.setParameter(5, passwordMt);
            query.setParameter(6, listipMt);
            query.setParameter(7, usernameCharge);
            query.setParameter(8, passwordCharge);
            query.setParameter(9, taxCode);
            query.setParameter(10, representer);
            query.setParameter(11, address);
            query.setParameter(12, emailCp);
            query.setParameter(13, cpId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(CPListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int deleteCpList(BigDecimal cpId) {
        String sql = "delete from cp_list where cp_id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, cpId);

            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(CPListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public CpListModel getCpByCode(String cpCode) {
        String sql = "select new " + CpListModel.class.getName()
                + "(c.cpId, c.cpName, c.cpCode, c.contact, c.createdTime,"
                + " c.updatedTime, c.usernameMt, c.passwordMt, c.listipMt, c.usernameCharge,"
                + " c.passwordCharge, c.taxCode, c.representer, c.address, c.emailCp) "
                + " from " + CpList.class.getName() + " c"
                + " where c.cpCode like ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, cpCode);
            CpListModel cp = (CpListModel) query.uniqueResult();
            return cp;
        } catch (Exception ex) {
            Logger.getLogger(CPListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public CpListModel getCpByCpId(BigDecimal cpId) {
        String sql = "select new " + CpListModel.class.getName()
                + "(c.cpId, c.cpName, c.cpCode, c.contact, c.createdTime,"
                + " c.updatedTime, c.usernameMt, c.passwordMt, c.listipMt, c.usernameCharge,"
                + " c.passwordCharge, c.taxCode, c.representer, c.address, c.emailCp) "
                + " from " + CpList.class.getName() + " c"
                + " where c.cpId like ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, cpId);
            CpListModel cp = (CpListModel) query.uniqueResult();
            return cp;
        } catch (Exception ex) {
            Logger.getLogger(CPListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int addShortcodeForCp(BigDecimal cpId, List<BigDecimal> shcodeId) {
        String sql = "insert all ";

        for (int i = 0; i < shcodeId.size(); i++) {
            sql += " into shortcode_cp (shortcode_cp_id, shcode_id, cp_id) values ("
                    + this.getNextvalForSeqShcodeCp() + "," + shcodeId.get(i) + "," + cpId + ")";
        }
        sql += " select 1 from dual";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(CPListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int removeShortcodeForCp(BigDecimal cpId, List<BigDecimal> shcodeId) {
        String sql = "delete from shortcode_cp where (shcode_id, cp_id) in (";

        for (int i = 0; i < shcodeId.size(); i++) {
            sql += " (" + shcodeId.get(i) + "," + cpId + ")";
            if (i < shcodeId.size() - 1) {
                sql += ",";
            }
        }
        sql += " )";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(CPListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

}
