/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.controller;

import com.mycompany.smsgateway.dao.ConfigMtDAO;
import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.services.ActionLogServices;
import com.mycompany.smsgateway.services.Paging;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Minh Hieu Pham
 */
@Controller
public class ConfigMtController {
    
    private int numPerPage = 10;
    
    @Autowired
    private ConfigMtDAO configMtDAO;
    
    @Autowired
    private Paging paging;

    @Autowired
    private ActionLogServices actionLogServices;
    
    @RequestMapping(value = "configMtList", method = RequestMethod.GET)
    public String configMtList(Model model, HttpSession session, @RequestParam String action,
            @RequestParam(required = false) String page) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CONFIG_MT_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        return "configMtPages_configMtList";
    }
}
