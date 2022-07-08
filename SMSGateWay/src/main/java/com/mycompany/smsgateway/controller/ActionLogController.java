/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.controller;

import com.mycompany.smsgateway.dao.ActionLogDAO;
import com.mycompany.smsgateway.model.ActionLogModel;
import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.services.Paging;
import java.math.BigDecimal;
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
public class ActionLogController {
    
    private int numPerPage = 10;
    
    @Autowired
    private ActionLogDAO actionLogDAO;
    
    @Autowired
    private Paging paging;
    
    @RequestMapping(value = "actionLogList", method = RequestMethod.GET)
    public String actionLogList(Model model, HttpSession session, @RequestParam String action,
            @RequestParam(required = false) String page) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        Long total = actionLogDAO.getTotalActionLogs();
        int totalItem = total.intValue();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        List<ActionLogModel> logs = actionLogDAO.getAllActions(start, numPerPage);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("logs", logs);
        model.addAttribute("action", action);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        return "actionLogPages_actionLogList";
    }
    
    
    @RequestMapping(value = "searchAction", method = RequestMethod.GET)
    public String searchAction(Model model, HttpSession session, @RequestParam String action,
            @RequestParam(required = false) String page, @RequestParam String inputSearch,
            @RequestParam(required = false) String fromCreateDate,
            @RequestParam(required = false) String toCreateDate,
            @RequestParam(required = false) String actionResult) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        Long total = actionLogDAO.getTotalActionLogsByOption(inputSearch, 
                fromCreateDate, toCreateDate, actionResult);
        int totalItem = total.intValue();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        List<ActionLogModel> logs = actionLogDAO.getAllActionsByOption(inputSearch, 
                fromCreateDate, toCreateDate, actionResult, start, numPerPage);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("logs", logs);
        model.addAttribute("action", action);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("inputSearch", inputSearch);
        model.addAttribute("fromCreateDate", fromCreateDate);
        model.addAttribute("toCreateDate", toCreateDate);
        model.addAttribute("actionResult", actionResult);
        return "actionLogPages_actionLogList";
    }
    
    @RequestMapping(value = "actionLogDetail", method = RequestMethod.GET)
    public String actionLogDetail(Model model, HttpSession session, @RequestParam String actionlogId,
            @RequestParam(required = false) String page) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        BigDecimal actionlogIdDec = new BigDecimal(actionlogId);
        ActionLogModel log = actionLogDAO.getActionLogById(actionlogIdDec);
        model.addAttribute("log", log);
        model.addAttribute("page", page);
        return "actionLogPages_actionLogDetail";
    }
}
