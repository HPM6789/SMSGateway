/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.RolesDAO;
import com.mycompany.smsgateway.entities.Roles;
import com.mycompany.smsgateway.model.RolesModel;
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
public class RolesDAOImpl implements RolesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public RolesModel getRolesById(Long id) {
        String sql = "select new " + RolesModel.class.getName()
                + "(r.id, r.name, r.description, r.url) from " + Roles.class.getName() + " r"
                + " where " + id + " like ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, id);
            RolesModel role = (RolesModel) query.uniqueResult();
            return role;
        } catch (Exception ex) {
            Logger.getLogger(RolesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public RolesModel getRoleByName(String name) {
        String sql = "select new " + RolesModel.class.getName()
                + "(r.id, r.name, r.description, r.url) from " + Roles.class.getName() + " r";
        if (name != null && !name.equals("")) {
            sql += " where r.name like '%" + name + "%'";
        }
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            RolesModel role = (RolesModel) query.uniqueResult();
            return role;
        } catch (Exception ex) {
            Logger.getLogger(RolesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<RolesModel> getRolesByName(String name) {
        String sql = "select new " + RolesModel.class.getName()
                + "(r.id, r.name, r.description, r.url) from " + Roles.class.getName() + " r";
        if (name != null && !name.equals("")) {
            sql += " where r.name like '%" + name + "%'";
        }
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            List<RolesModel> roles = query.list();
            return roles;
        } catch (Exception ex) {
            Logger.getLogger(RolesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<RolesModel> getAllRoles() {
        String sql = "select new " + RolesModel.class.getName()
                + "(r.id, r.name, r.description, r.url) from " + Roles.class.getName() + " r";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            List<RolesModel> roles = query.list();
            return roles;
        } catch (Exception ex) {
            Logger.getLogger(RolesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<RolesModel> getRolesByGroupId(Long groupId) {
        String sql = "select new " + RolesModel.class.getName()
                + "(r.id, r.name, r.description, r.url) "
                + " from " + Roles.class.getName() + " r inner join r.groups gr "
                + " where gr.id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, groupId);
            List<RolesModel> roles = query.list();
            return roles;
        } catch (Exception ex) {
            Logger.getLogger(RolesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Long> getRoleIdsByGroupId(Long groupId) {
        String sql = "select r.id "
                + " from " + Roles.class.getName() + " r inner join r.groups gr "
                + " where gr.id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, groupId);
            List<Long> ids = query.list();
            return ids;
        } catch (Exception ex) {
            Logger.getLogger(RolesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<String> getRoleNamesByGroupId(Long groupId) {
        String sql = "select r.name "
                + " from " + Roles.class.getName() + " r inner join r.groups gr "
                + " where gr.id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, groupId);
            List<String> names = query.list();
            return names;
        } catch (Exception ex) {
            Logger.getLogger(RolesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<RolesModel> getRolesByUserId(BigDecimal userId) {
        String sql = "select new " + RolesModel.class.getName()
                + "(r.id, r.name, r.description, r.url) from " + Roles.class.getName() + " r"
                + " inner join r.groups gr"
                + " inner join gr.users gu"
                + " where gu.userId = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, userId);
            List<RolesModel> roles = query.list();
            return roles;
        } catch (Exception ex) {
            Logger.getLogger(RolesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<String> getRoleNamesByUserId(BigDecimal userId) {
        String sql = "select r.name from " + Roles.class.getName() + " r"
                + " inner join r.groups gr"
                + " inner join gr.users gu"
                + " where gu.userId = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, userId);
            List<String> roles = query.list();
            return roles;
        } catch (Exception ex) {
            Logger.getLogger(RolesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
    
    @Override
    public int addNewRole(String name, String description, String url) {
        System.out.println("addNewRole");
        String sql = "insert into roles (id, name, description, url) values(seq_roles.nextval,?,?,?)";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, name);
            query.setParameter(1, description);
            query.setParameter(2, url);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(RolesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateRole(Long id, String name, String description, String url) {
        System.out.println("updateRole");
        String sql = "update roles set name = ?, description = ?, url = ? where id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, name);
            query.setParameter(1, description);
            query.setParameter(2, url);
            query.setParameter(3, id);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(RolesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int deleteRole(Long roleId) {
        String sql = "delete from roles where id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, roleId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(RolesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public RolesModel getRoleByUserAndRoleName(BigDecimal userId, String roleName) {
        String sql = "select new " + RolesModel.class.getName()
                + "(r.id, r.name, r.description, r.url) from " + Roles.class.getName() + " r"
                + " inner join r.groups gr"
                + " inner join gr.users gu"
                + " where gu.userId = ? and r.name like ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, userId);
            query.setParameter(1, roleName);
            RolesModel role = (RolesModel) query.uniqueResult();
            return role;
        } catch (Exception ex) {
            Logger.getLogger(RolesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<RolesModel> getRoleNotInGroup(Long groupId) {
        String sql = "select new " + RolesModel.class.getName()
                + "(r.id, r.name, r.description, r.url) from " + Roles.class.getName() + " r"
                + " where r.id not in ("
                + "select r1.id "
                + " from " + Roles.class.getName() + " r1 inner join r1.groups gr "
                + " where gr.id = ?)";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, groupId);
            List<RolesModel> roles = query.list();
            return roles;
        } catch(Exception ex){
            Logger.getLogger(RolesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
