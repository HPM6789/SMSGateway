/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.TypeListDAO;
import com.mycompany.smsgateway.entities.TypeList;
import com.mycompany.smsgateway.model.TypeListModel;
import java.math.BigDecimal;
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
public class TypeListDAOImpl implements TypeListDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TypeListModel> getAllTypeList() {
        String sql = "select new " + TypeListModel.class.getName()
                + "(t.typeId, t.typeCode, t.typeName, t.createDate, t.lastUpdate)"
                + " from " + TypeList.class.getName() + " t "
                + " order by t.createDate desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            List<TypeListModel> types = query.list();
            return types;
        } catch (Exception ex) {
            Logger.getLogger(TypeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<TypeListModel> getAllTypeList(int start, int next) {
        String sql = "select new " + TypeListModel.class.getName()
                + "(t.typeId, t.typeCode, t.typeName, t.createDate, t.lastUpdate)"
                + " from " + TypeList.class.getName() + " t "
                + " order by t.createDate desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<TypeListModel> types = query.list();
            return types;
        } catch (Exception ex) {
            Logger.getLogger(TypeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<TypeListModel> getAllTypeListByName(String typeName, int start, int next) {
        String sql = "select new " + TypeListModel.class.getName()
                + "(t.typeId, t.typeCode, t.typeName, t.createDate, t.lastUpdate)"
                + " from " + TypeList.class.getName() + " t where 1=1";
        if (typeName != null && !typeName.equals("")) {
            sql += " and upper(t.typeName) like upper('%" + typeName + "%')";
        }
        sql += " order by t.createDate desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(next);
            List<TypeListModel> types = query.list();
            return types;
        } catch (Exception ex) {
            Logger.getLogger(TypeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Long getTotalTypeList() {
        String sql = "select count(t)"
                + " from " + TypeList.class.getName() + " t ";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            Long total = (Long) query.uniqueResult();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(TypeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new Long("0");
        }
    }

    @Override
    public Long getTotalTypeListByName(String typeName) {
        String sql = "select count(t)"
                + " from " + TypeList.class.getName() + " t ";
        if (typeName != null && !typeName.equals("")) {
            sql += " and upper(t.typeName) like upper('%" + typeName + "%')";
        }
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            Long total = (Long) query.uniqueResult();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(TypeListDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new Long("0");
        }
    }

}
