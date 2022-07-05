/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.dao.impl;

import com.mycompany.smsgateway.dao.ShortcodeCpDAO;
import com.mycompany.smsgateway.entities.ShortcodeCp;
import com.mycompany.smsgateway.model.ShortcodeCpModel;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
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
public class ShortcodeCpDAOImpl implements ShortcodeCpDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ShortcodeCpModel> getAllShortcodeCp() {
        String sql = "select new " + ShortcodeCpModel.class.getName()
                + "(sc.shortcodeCpId, sc.cp.cpId, sc.cp.cpName, sc.cp.cpCode, "
                + "sc.shortcode.shcodeId, sc.shortcode.shortcode)"
                + " from " + ShortcodeCp.class.getName() + " sc "
                + " left join sc.cp"
                + " left join sc.shortcode";
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            List<ShortcodeCpModel> shortcodeCps = query.list();
            return shortcodeCps;
        }catch(Exception ex){
            Logger.getLogger(ShortcodeCpDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<String> getAllShortcodeByCpId(BigDecimal cpId) {
        String sql = "select sc.shortcode.shortcode"
                + " from " + ShortcodeCp.class.getName() + " sc "
                + " left join sc.cp"
                + " left join sc.shortcode"
                + " where sc.cp.cpId = :cpId";
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter("cpId", cpId);
            List<String> shortcodes = query.list();
            return shortcodes;
        }catch(Exception ex){
            Logger.getLogger(ShortcodeCpDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<BigDecimal> getAllShortcodeIdByCpId(BigDecimal cpId) {
        String sql = "select sc.shortcode.shcodeId"
                + " from " + ShortcodeCp.class.getName() + " sc "
                + " left join sc.cp"
                + " left join sc.shortcode"
                + " where sc.cp.cpId = :cpId";
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(sql);
            query.setParameter("cpId", cpId);
            List<BigDecimal> shcodeIds = query.list();
            return shcodeIds;
        }catch(Exception ex){
            Logger.getLogger(ShortcodeCpDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
