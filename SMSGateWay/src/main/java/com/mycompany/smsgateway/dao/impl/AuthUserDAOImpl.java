/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.AuthUserDAO;
import com.mycompany.smsgateway.entities.AuthUser;
import com.mycompany.smsgateway.entities.CpList;
import com.mycompany.smsgateway.entities.Groups;
import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.model.CpListModel;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Minh Hieu Pham
 */
@Repository
@Transactional
public class AuthUserDAOImpl implements AuthUserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public AuthUserDAOImpl() {
    }

    @Override
    public int addNewAuthUser(String username, String password, String fullname, String description,
            String address, String phone, String email) {
        String sql = "insert into  auth_user  (user_id , user_name , user_pass"
                + ",user_fullname , user_desc,  user_status , user_is_super ,"
                + "user_type , user_addr ,user_phone, user_email ,"
                + "user_createdtime ,  user_otp_flg"
                + ") values (seq_auth_id.nextval,?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println("addNewAuthUser");
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, username);
            query.setParameter(1, password);
            query.setParameter(2, fullname);
            query.setParameter(3, description);
            query.setParameter(4, 0);
            query.setParameter(5, 0);
            query.setParameter(6, 0);
            query.setParameter(7, address);
            query.setParameter(8, phone);
            query.setParameter(9, email);
            query.setParameter(10, new Timestamp(System.currentTimeMillis()));
            query.setParameter(11, 0);

            int rows = query.executeUpdate();
            return rows;
        } catch (EmptyResultDataAccessException ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateAuthUser(String username, String fullname, String description, BigInteger status, BigInteger isSuper,
            BigInteger userType, String address, String phone, String email,
            String optFlag) {
        String sql = "update auth_user set user_fullname = ?, user_desc = ?, user_status = ?, user_is_super = ?,"
                + "user_type = ?, user_addr = ?, user_phone = ?, user_email = ?, user_updatedTime = ?, USER_OTP_FLG = ?"
                + " where user_name like ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, fullname);
            query.setParameter(1, description);
            query.setParameter(2, status);
            query.setParameter(3, isSuper);
            query.setParameter(4, userType);
            query.setParameter(5, address);
            query.setParameter(6, phone);
            query.setParameter(7, email);
            query.setParameter(8, new Timestamp(System.currentTimeMillis()));
            query.setParameter(9, optFlag);
            query.setParameter(10, username);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateProfile(String username, String fullname, String description, String address, String phone,
            String email) {
        String sql = "update auth_user set user_fullname = ?, user_desc = ?,"
                + " user_addr = ?, user_phone = ?, user_email = ?, user_updatedTime = ?"
                + " where user_name like ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, fullname);
            query.setParameter(1, description);
            query.setParameter(2, address);
            query.setParameter(3, phone);
            query.setParameter(4, email);
            query.setParameter(5, new Timestamp(System.currentTimeMillis()));
            query.setParameter(6, username);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public AuthUserModel findAuthUserByUsername(String username) {
        String sql = "select new " + AuthUserModel.class.getName()
                + "(a.userId, a.userName, a.userPass, a.userFullname, a.userDesc, a.userStatus, "
                + "a.userIsSuper, a.userType, a.userAddr, a.userPhone, a.userEmail, "
                + "a.userCreatedtime, a.userUpdatedtime, a.userLastTimeLogin,"
                + " a.userOtpFlg"
                + " ) from " + AuthUser.class.getName() + " a where a.userName like ?";
        System.out.println("findAuthUserByUsername");
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
//            Query query = session.createQuery(sql);
            System.out.println(username);
            query.setParameter(0, username);
            AuthUserModel user = (AuthUserModel) query.uniqueResult();
            return user;
        } catch (EmptyResultDataAccessException ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public AuthUserModel checkLoginUser(String username, String password) {
        String sql = "select new " + AuthUserModel.class.getName()
                + "(a.userId, a.userName, a.userPass, a.userFullname, a.userDesc, a.userStatus, "
                + "a.userIsSuper, a.userType, a.userAddr, a.userPhone, a.userEmail, "
                + "a.userCreatedtime, a.userUpdatedtime, a.userLastTimeLogin,"
                + " a.userOtpFlg"
                + " ) from " + AuthUser.class.getName() + " a "
                //                + " left join " + CpList.class.getName() + " c on c.cpId = a.cpId"
                + " where a.userName like ? and a.userPass like ?";
        System.out.println("checkLoginUser");
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);

            query.setParameter(0, username);
            query.setParameter(1, password);
            AuthUserModel user = (AuthUserModel) query.uniqueResult();
            return user;
        } catch (EmptyResultDataAccessException ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<AuthUserModel> getAllUser() {
        String sql = "select new " + AuthUserModel.class.getName()
                + "(a.userId, a.userName, a.userPass, a.userFullname, a.userDesc, a.userStatus, "
                + "a.userIsSuper, a.userType, a.userAddr, a.userPhone, a.userEmail, "
                + "a.userCreatedtime, a.userUpdatedtime, a.userLastTimeLogin,"
                + " a.userOtpFlg"
                + " ) from " + AuthUser.class.getName() + " a"
                + " order by a.userCreatedtime desc";
        System.out.println("getAllUser");
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);

            List<AuthUserModel> users = query.list();
            return users;
        } catch (Exception ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<AuthUserModel> getUserByUsernameOrEmail(String usernameOrEmail) {
        String sql = "select new " + AuthUserModel.class.getName()
                + "(a.userId, a.userName, a.userPass, a.userFullname, a.userDesc, a.userStatus, "
                + "a.userIsSuper, a.userType, a.userAddr, a.userPhone, a.userEmail, "
                + "a.userCreatedtime, a.userUpdatedtime, a.userLastTimeLogin,"
                + " a.userOtpFlg"
                + " ) from " + AuthUser.class.getName() + " a where 1=1";
        System.out.println("getAllUser");
        if (usernameOrEmail != null && !usernameOrEmail.equals("")) {
            sql += " and (upper(a.userName) like upper('%" + usernameOrEmail
                    + "%') or upper(a.userEmail) like upper('%" + usernameOrEmail + "%'))";
        }
        sql += " order by a.userCreatedtime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);

            List<AuthUserModel> users = query.list();
            return users;
        } catch (Exception ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<AuthUserModel> getUserByOption(String inputSearch, BigInteger status) {
        String sql = "select new " + AuthUserModel.class.getName()
                + "(a.userId, a.userName, a.userPass, a.userFullname, a.userDesc, a.userStatus, "
                + "a.userIsSuper, a.userType, a.userAddr, a.userPhone, a.userEmail, "
                + "a.userCreatedtime, a.userUpdatedtime, a.userLastTimeLogin,"
                + " a.userOtpFlg"
                + " ) from " + AuthUser.class.getName() + " a where 1=1";
        if (inputSearch != null && !inputSearch.equals("")) {
            sql += " and (upper(a.userName) like upper('%" + inputSearch
                    + "%') or upper(a.userEmail) like upper('%" + inputSearch + "%')"
                    + " or upper(a.userFullname) like upper('%" + inputSearch + "%')"
                    + " or a.userPhone like '%" + inputSearch + "%')";
        }
        if (status != null) {
            sql += " and a.userStatus = " + status;
        }
        sql += " order by a.userCreatedtime desc";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);

            List<AuthUserModel> users = query.list();
            return users;
        } catch (Exception ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public AuthUserModel getUserWithCP(String username) {
        System.out.println("username: " + username);
        String sql = "select new " + AuthUserModel.class.getName()
                + "(a.userId, a.userName, a.userPass, a.userFullname, a.userDesc, a.userStatus, "
                + "a.userIsSuper, a.userType, a.userAddr, a.userPhone, a.userEmail, "
                + "a.userCreatedtime, a.userUpdatedtime, a.userLastTimeLogin,"
                + " a.userOtpFlg, a.cp.cpId, a.cp.cpName"
                + " ) from " + AuthUser.class.getName() + " a "
                + " left join  a.cp c "
                + "  where a.userName like ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);

            query.setParameter(0, username);
            AuthUserModel user = (AuthUserModel) query.uniqueResult();
            System.out.println(query.getQueryString());
            return user;
        } catch (Exception ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public BigDecimal getUserIdByUsername(String username) {
        String sql = "select a.userId from " + AuthUser.class.getName() + " a "
                + " left join  a.cp c "
                + "  where a.userName like ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, username);
            BigDecimal user = (BigDecimal) query.uniqueResult();
            return user;
        } catch (Exception ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new BigDecimal("-1");
        }
    }

    @Override
    public int changePassword(String username, String newPassword) {
        String sql = "update auth_user set user_pass = ? where user_name like ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, newPassword);
            query.setParameter(1, username);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public List<AuthUserModel> getUsersByGroup(Long id) {
        String sql = "select new " + AuthUserModel.class.getName()
                + "(a.userId, a.userName, a.userPass, a.userFullname, a.userDesc, a.userStatus, "
                + "a.userIsSuper, a.userType, a.userAddr, a.userPhone, a.userEmail, "
                + "a.userCreatedtime, a.userUpdatedtime, a.userLastTimeLogin,"
                + " a.userOtpFlg"
                + " ) from " + AuthUser.class.getName() + " a "
                + " inner join a.groups gu "
                + " where gu.id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, id);
            List<AuthUserModel> users = query.list();
            return users;
        } catch (Exception ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<String> getUserNamesByGroup(Long id) {
        String sql = "select a.userName from " + AuthUser.class.getName() + " a "
                + " inner join a.groups gu "
                + " where gu.id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, id);
            List<String> names = query.list();
            return names;
        } catch (Exception ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<BigDecimal> getUserIdsByGroup(Long id) {
        String sql = "select a.userId from " + AuthUser.class.getName() + " a "
                + " inner join a.groups gu "
                + " where gu.id = ?";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, id);
            List<BigDecimal> ids = query.list();
            return ids;
        } catch (Exception ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<AuthUserModel> getUsersNotInGroup(Long id) {
        System.out.println("getUsersNotInGroup: groupId = " + id);
        String sql = "select new " + AuthUserModel.class.getName()
                + "(a.userId, a.userName, a.userPass, a.userFullname, a.userDesc, a.userStatus, "
                + "a.userIsSuper, a.userType, a.userAddr, a.userPhone, a.userEmail, "
                + "a.userCreatedtime, a.userUpdatedtime, a.userLastTimeLogin,"
                + " a.userOtpFlg"
                + " ) from " + AuthUser.class.getName() + " a "
                + " where a.userId not in (select "
                + "a1.userId"
                + "  from " + AuthUser.class.getName() + " a1 "
                + " inner join a1.groups gu "
                + " where gu.id = ?)";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, id);
            List<AuthUserModel> users = query.list();
            System.out.println("user1: " + users.get(0).getUserFullname());
            return users;
        } catch (Exception ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int addCpListForAuthUser(BigDecimal cpId, String username) {
        String sql = "update auth_user set cp_id = :cp_id where user_name like :user_name";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("cp_id", cpId);
            query.setParameter("user_name", username);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateLastTimeLogin(String username) {
        String sql = "update auth_user set user_last_time_login = :user_last_time_login"
                + " where user_name like :user_name";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("user_last_time_login", new Timestamp(System.currentTimeMillis()));
            query.setParameter("user_name", username);
            int rows = query.executeUpdate();
            return rows;
        } catch (Exception ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int getTotalAuthUser() {
        String sql = "select COUNT(*) from auth_user";
        try {
            Session session = sessionFactory.getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql);

            BigDecimal total = (BigDecimal) query.list().get(0);
            return total.intValue();
        } catch (Exception ex) {
            Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public BigInteger checkActiveUser(String username) {
        String sql = "select a.userStatus from " + AuthUser.class.getName() + " a"
                + " where a.userName like :username";
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter("username", username);
            BigInteger status = (BigInteger) query.uniqueResult();
            return status;
        }catch(Exception ex){
        Logger.getLogger(AuthUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new BigInteger("-1");
        }
    }

    

}
