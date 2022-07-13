/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.controller;

import com.mycompany.smsgateway.dao.NotifyCpDAO;
import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.model.NotifyCpModel;
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
public class NotifyCpController {
    
    private int numPerPage = 10;
    
    @Autowired
    private NotifyCpDAO notifyCpDAO;
    
    @Autowired
    private Paging paging;

    @Autowired
    private ActionLogServices actionLogServices;
    
    @RequestMapping(value = "notifyCpList", method = RequestMethod.GET)
    public String notifyCpList(Model model, HttpSession session, @RequestParam String action,
            @RequestParam(required = false) String page, @RequestParam(required = false) String notice) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("NOTIFYCP_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        Long total = notifyCpDAO.getTotalNotifyCp();
        int totalItem = total.intValue();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        List<NotifyCpModel> notifies = notifyCpDAO.getNotifyCps(start, numPerPage);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("notifies", notifies);
        model.addAttribute("action", action);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("notice", notice);
        return "notifyCpPages_notifyCpList";
    }
    
    @RequestMapping(value = "searchNotifyCp", method = RequestMethod.GET)
    public String searchNotifyCp(Model model, HttpSession session, @RequestParam String action,
            @RequestParam(required = false) String page,
            @RequestParam(required = false) String inputSearch,
            @RequestParam(required = false) String fromCreateDate,
            @RequestParam(required = false) String toCreateDate) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("NOTIFYCP_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        Long total = notifyCpDAO.getTotalNotifyCpByOption(inputSearch, fromCreateDate, toCreateDate);
        int totalItem = total.intValue();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        List<NotifyCpModel> notifies = notifyCpDAO.getNotifyCpsByOption(inputSearch, fromCreateDate, 
                toCreateDate, start, numPerPage);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("notifies", notifies);
        model.addAttribute("action", action);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("inputSearch", inputSearch);
        model.addAttribute("fromCreateDate", fromCreateDate);
        model.addAttribute("toCreateDate", toCreateDate);
        return "notifyCpPages_notifyCpList";
    }
}
