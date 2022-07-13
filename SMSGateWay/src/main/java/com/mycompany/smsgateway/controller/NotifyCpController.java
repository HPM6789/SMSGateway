/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.controller;

import com.mycompany.smsgateway.dao.CPListDAO;
import com.mycompany.smsgateway.dao.NotifyCpDAO;
import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.model.CpListModel;
import com.mycompany.smsgateway.model.NotifyCpModel;
import com.mycompany.smsgateway.services.ActionLogServices;
import com.mycompany.smsgateway.services.Paging;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
    private CPListDAO cpListDAO;

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

    @RequestMapping(value = "addNotifyCp", method = RequestMethod.GET)
    public String addNotifyCp(Model model, HttpSession session, @RequestParam String action) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("NOTIFYCP_INSERT")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        List<CpListModel> cps = cpListDAO.getAllCpList();
        model.addAttribute("cps", cps);
        model.addAttribute("action", action);
        return "notifyCpPages_notifyCpDetail";
    }

    @RequestMapping(value = "addNotifyCp", method = RequestMethod.POST)
    public String addNotifyCp(Model model, HttpSession session, @RequestParam String action,
            @RequestParam String cpId, @RequestParam String moReceiveUrl, 
            @RequestParam(required = false) String note,
            @RequestParam(required = false) String moReceiveUrlBkp, HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("NOTIFYCP_INSERT")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal cpIdDec = new BigDecimal(cpId);
        BigInteger cpIdInt = new BigInteger(cpId);
        NotifyCpModel notify = notifyCpDAO.getNotifyCpByCpId(cpIdDec);
        if (notify == null) {
            int result = notifyCpDAO.addNotifyCp(moReceiveUrl, cpIdInt, note, moReceiveUrlBkp);
            String actionResult = "";
            if (result == 1) {
                actionResult = "Thành Công";
            } else {
                actionResult = "Thất bại";
            }
            BigDecimal notifyIdDec = notifyCpDAO.getNewestNotifyId();
            actionLogServices.logAction(userSession.getUserId(), "INSERT_NOTIFY_CP",
                    "NOTIFY_CP", notifyIdDec.toBigInteger(), actionResult, "Thêm NOTIFY_CP",
                    null, null, request);
            return "redirect:notifyCpList?action=list&notice=success";
        }
        actionLogServices.logAction(userSession.getUserId(), "INSERT_NOTIFY_CP",
                    "NOTIFY_CP", BigInteger.ZERO, "Thất bại", "Thêm NOTIFY_CP",
                    null, null, request);
        List<CpListModel> cps = cpListDAO.getAllCpList();
        model.addAttribute("cps", cps);
        model.addAttribute("action", action);
        model.addAttribute("moReceiveUrl", moReceiveUrl);
        model.addAttribute("note", note);
        model.addAttribute("cpId", cpId);
        model.addAttribute("moReceiveUrlBkp", moReceiveUrlBkp);
        model.addAttribute("notice", "Thêm thất bại");
        return "notifyCpPages_notifyCpDetail";
    }
}
