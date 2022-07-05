/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.GroupDAO;
import com.mycompany.smsgateway.entities.Groups;
import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.model.GroupsModel;
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
public class GroupDAOImpl implements GroupDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<GroupsModel> getGroupByUserId(BigDecimal userId) {
        System.out.println("getGroupByUserId");
        String sql = "select new " + GroupsModel.class.getName()
                + "(g.id, g.name, g.description, g.createTime, g.updateTime)"
                + " from " + Groups.class.getName()
                + " g inner join g.users gu where gu.userId = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, userId);
            List<GroupsModel> groups = query.list();
            return groups;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<GroupsModel> getAllGroups() {
        System.out.println("getAllGroups");
        String sql = "select new " + GroupsModel.class.getName()
                + "(g.id, g.name, g.description, g.createTime, g.updateTime)"
                + " from " + Groups.class.getName() + " g";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            List<GroupsModel> groups = query.list();
            return groups;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<GroupsModel> getGroupsByName(String groupName) {
        System.out.println("getGroupsByName");
        String sql = "select new " + GroupsModel.class.getName()
                + "(g.id, g.name, g.description, g.createTime, g.updateTime)"
                + " from " + Groups.class.getName() + " g where 1=1";
        if (groupName != null && !groupName.equals("")) {
            sql += " and upper(g.name) like upper('%" + groupName + "%')";
        }
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            List<GroupsModel> groups = query.list();
            return groups;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Long getNewestGrId() {
        String sql = "select g.id"
                + " from " + Groups.class.getName() + " g "
                + " order by g.createTime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setMaxResults(1);
            Long groupId = (Long) query.uniqueResult();
            return groupId;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new Long("0");
        }
    }

    @Override
    public int addNewGroup(String name, String description) {
        System.out.println("addNewGroup");
        String sql = "insert into groups (id, name, description, create_time)"
                + " values (seq_groups.nextval,?,?,?)";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, name);
            query.setParameter(1, description);
            query.setParameter(2, new Timestamp(System.currentTimeMillis()));
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public GroupsModel getGroupByName(String groupName) {
        System.out.println("getGroupByName");
        String sql = "select new " + GroupsModel.class.getName()
                + "(g.id, g.name, g.description, g.createTime, g.updateTime)"
                + " from " + Groups.class.getName() + " g where g.name like ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, groupName);
            GroupsModel groups = (GroupsModel) query.uniqueResult();
            return groups;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int updateGroup(Long id, String name, String description) {
        System.out.println("updateGroup");
        String sql = "update groups set name = ?, description = ?, update_time = ?"
                + " where id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, name);
            query.setParameter(1, description);
            query.setParameter(2, new Timestamp(System.currentTimeMillis()));
            query.setParameter(3, id);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public GroupsModel getGroupById(Long id) {
        System.out.println("getGroupById");
        String sql = "select new " + GroupsModel.class.getName()
                + "(g.id, g.name, g.description, g.createTime, g.updateTime)"
                + " from " + Groups.class.getName() + " g where g.id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, id);
            GroupsModel groups = (GroupsModel) query.uniqueResult();
            return groups;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int addMemberForGroup(Long id, List<BigDecimal> membersId) {
        System.out.println("addMemberForGroup");
        String sql = "insert all ";

        for (int i = 0; i < membersId.size(); i++) {
            sql += " into group_user (group_id, user_id) values (" + id + "," + membersId.get(i) + ")";
        }
        sql += " select 1 from dual";
        System.out.println("sql: " + sql);
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int removeMemberForGroup(Long id, List<BigDecimal> membersId) {
        System.out.println("removeMemberForGroup");
        String sql = "delete from group_user where (group_id, user_id) in (";

        for (int i = 0; i < membersId.size(); i++) {
            sql += " (" + id + "," + membersId.get(i) + ")";
            if (i < membersId.size() - 1) {
                sql += ",";
            }
        }
        sql += " )";
        System.out.println("sql: " + sql);
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int addRoleForGroup(Long groupId, List<Long> roleId) {
        System.out.println("addRoleForGroup");
        String sql = "insert all ";

        for (int i = 0; i < roleId.size(); i++) {
            sql += " into group_role (group_id, role_id) values (" + groupId + "," + roleId.get(i) + ")";
        }
        sql += " select 1 from dual";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int removeRoleForGroup(Long groupId, List<Long> roleId) {
        System.out.println("removeRoleForGroup");
        String sql = "delete from group_role where (group_id, role_id) in (";

        for (int i = 0; i < roleId.size(); i++) {
            sql += " (" + groupId + "," + roleId.get(i) + ")";
            if (i < roleId.size() - 1) {
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
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int deleteGroupRoleByRoleId(Long roleId) {
        System.out.println("deleteGroupRoleByRoleId");
        String sql = "delete from group_role where role_id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, roleId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int deleteGroupRoleByGroupId(Long groupId) {
        System.out.println("deleteGroupRoleByGroupId");
        String sql = "delete from group_role where group_id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, groupId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int deleteGroupUserByUserId(Long groupId) {
        System.out.println("deleteGroupUserByUserId");
        String sql = "delete from group_user where group_id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, groupId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int deleteGroupUserByGroupId(Long userId) {
        System.out.println("deleteGroupUserByUserId");
        String sql = "delete from group_user where user_id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, userId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int deleteGroup(Long groupId) {
        System.out.println("deleteGroup");
        String sql = "delete from groups where id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, groupId);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
