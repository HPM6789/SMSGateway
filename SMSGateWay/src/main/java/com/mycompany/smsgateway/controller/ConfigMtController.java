/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.controller;

import com.mycompany.smsgateway.dao.ConfigMtDAO;
import com.mycompany.smsgateway.dao.ShortcodeListDAO;
import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.model.ConfigMtModel;
import com.mycompany.smsgateway.model.ShortcodeListModel;
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
public class ConfigMtController {

    private final int numPerPage = 10;

    @Autowired
    private ConfigMtDAO configMtDAO;

    @Autowired
    private Paging paging;

    @Autowired
    private ActionLogServices actionLogServices;

    @Autowired
    private ShortcodeListDAO shortcodeListDAO;

    @RequestMapping(value = "configMtList", method = RequestMethod.GET)
    public String configMtList(Model model, HttpSession session, @RequestParam String action,
            @RequestParam(required = false) String page, @RequestParam(required = false) String notice) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CONFIG_MT_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        Long total = configMtDAO.getTotalConfigMt();
        int totalItem = total.intValue();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        List<ConfigMtModel> configs = configMtDAO.getAllConfigMt(start, numPerPage);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("configs", configs);
        model.addAttribute("action", action);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("notice", notice);
        return "configMtPages_configMtList";
    }

    @RequestMapping(value = "searchConfigMt", method = RequestMethod.GET)
    public String searchConfigMt(Model model, HttpSession session, @RequestParam String action,
            @RequestParam(required = false) String page, @RequestParam(required = false) String inputSearch,
            @RequestParam(required = false) String fromCreateDate,
            @RequestParam(required = false) String toCreateDate) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CONFIG_MT_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        Long total = configMtDAO.getTotalConfigMtByOption(inputSearch, fromCreateDate, toCreateDate);
        int totalItem = total.intValue();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        List<ConfigMtModel> configs = configMtDAO.getConfigMtByOption(inputSearch,
                fromCreateDate, toCreateDate, start, numPerPage);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("configs", configs);
        model.addAttribute("action", action);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("inputSearch", inputSearch);
        model.addAttribute("fromCreateDate", fromCreateDate);
        model.addAttribute("toCreateDate", toCreateDate);
        return "configMtPages_configMtList";
    }

    @RequestMapping(value = "addConfigMt", method = RequestMethod.GET)
    public String addConfigMt(Model model, HttpSession session, @RequestParam String action) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CONFIG_MT_INSERT")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        List<ShortcodeListModel> shcodes = shortcodeListDAO.getAllShortcode();
        model.addAttribute("shcodes", shcodes);
        model.addAttribute("action", action);
        return "configMtPages_configMtDetail";
    }

    @RequestMapping(value = "addConfigMt", method = RequestMethod.POST)
    public String addConfigMt(Model model, HttpSession session, @RequestParam String action,
            @RequestParam String mtContent, @RequestParam String shortcode,
            HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CONFIG_MT_INSERT")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        ConfigMtModel config = configMtDAO.getMtByShortcode(shortcode);
        if (config != null) {
            List<ShortcodeListModel> shcodes = shortcodeListDAO.getAllShortcode();
            model.addAttribute("shcodes", shcodes);
            model.addAttribute("action", action);
            model.addAttribute("notice", "Th??m th???t b???i");
            model.addAttribute("shortcode", shortcode);
            model.addAttribute("mtContent", mtContent);
            actionLogServices.logAction(userSession.getUserId(), "INSERT_CONFIG_MT",
                    "CONFIG_MT", BigInteger.ZERO, "Th???t b???i", "Th??m Config_MT",
                    null, null, request);
            return "configMtPages_configMtDetail";
        } else {
            int result = configMtDAO.addConfigMt(shortcode, mtContent);
            String actionResult = "";
            if (result == 1) {
                actionResult = "Th??nh C??ng";
            } else {
                actionResult = "Th???t b???i";
            }
            BigDecimal mtIdDec = configMtDAO.getNewestMtId();
            actionLogServices.logAction(userSession.getUserId(), "INSERT_CONFIG_MT",
                    "CONFIG_MT", mtIdDec.toBigInteger(), actionResult, "Th??m Config_MT",
                    null, null, request);
        }
        return "redirect:configMtList?action=list&notice=success";
    }
    
    @RequestMapping(value = "updateConfigMt", method = RequestMethod.GET)
    public String updateConfigMt(Model model, HttpSession session, @RequestParam String action,
            @RequestParam String mtId, @RequestParam String page) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CONFIG_MT_UPDATE") && !roles.contains("CONFIG_MT_DETAIL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal mtIdDec = new BigDecimal(mtId);
        ConfigMtModel config = configMtDAO.getMtById(mtIdDec);
        List<ShortcodeListModel> shcodes = shortcodeListDAO.getAllShortcode();
        model.addAttribute("shcodes", shcodes);
        model.addAttribute("mtId", mtId);
        model.addAttribute("shortcode", config.getShortcode());
        model.addAttribute("oldShortcode", config.getShortcode());
        model.addAttribute("mtContent", config.getMtContent());
        model.addAttribute("mtCode", config.getMtCode());
        model.addAttribute("createTime", config.getCreateTime());
        model.addAttribute("updateTime", config.getUpdateTime());
        model.addAttribute("page", page);
        model.addAttribute("action", action);
        return "configMtPages_configMtDetail";
    }
    
    @RequestMapping(value = "updateConfigMt", method = RequestMethod.POST)
    public String updateConfigMt(Model model, HttpSession session, @RequestParam String action,
            @RequestParam String mtId, @RequestParam String mtContent,
            @RequestParam String oldShortcode, @RequestParam String shortcode,
            HttpServletRequest request, @RequestParam String page) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CONFIG_MT_UPDATE") && !roles.contains("CONFIG_MT_DETAIL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal mtIdDec = new BigDecimal(mtId);
        ConfigMtModel config = configMtDAO.getMtByShortcode(shortcode);
        if(config!=null && !config.getShortcode().equals(oldShortcode)) {
            actionLogServices.logAction(userSession.getUserId(), "UPDATE_CONFIG_MT",
                    "CONFIG_MT", mtIdDec.toBigInteger(), "Th???t b???i", "Th??m Config_MT",
                    null, null, request);
            model.addAttribute("oldShortcode", oldShortcode);
            model.addAttribute("notice", "?????u S??? ???? ???????c s??? d???ng. C???p nh???t th???t b???i!");
        } else {
            model.addAttribute("oldShortcode", shortcode);
            int result = configMtDAO.updateConfigMt(mtIdDec, shortcode, mtContent);
            String actionResult = "";
            if (result == 1) {
                actionResult = "Th??nh C??ng";
            } else {
                actionResult = "Th???t b???i";
            }
            model.addAttribute("notice", "C???p nh???t th??nh c??ng!");
            actionLogServices.logAction(userSession.getUserId(), "UPDATE_CONFIG_MT",
                    "CONFIG_MT", mtIdDec.toBigInteger(), actionResult, "C???p nh???t Config_MT",
                    null, null, request);
        }
        ConfigMtModel configById = configMtDAO.getMtById(mtIdDec);
        List<ShortcodeListModel> shcodes = shortcodeListDAO.getAllShortcode();
        model.addAttribute("shcodes", shcodes);
        model.addAttribute("mtId", mtId);
        model.addAttribute("shortcode", shortcode);
        model.addAttribute("mtContent", mtContent);
        model.addAttribute("mtCode", configById.getMtCode());
        model.addAttribute("createTime", configById.getCreateTime());
        model.addAttribute("updateTime", configById.getUpdateTime());
        model.addAttribute("page", page);
        model.addAttribute("action", action);
        return "configMtPages_configMtDetail";
    }
    
    @RequestMapping(value = "deleteConfigMt", method = RequestMethod.GET)
    public String deleteConfigMt(Model model, HttpSession session, HttpServletRequest request,
            @RequestParam String mtId, @RequestParam String page) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CONFIG_MT_DELETE")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal mtIdDec = new BigDecimal(mtId);
        int result = configMtDAO.deleteConfigMt(mtIdDec);
        String redirect = "redirect:configMtList?action=list&page=" + page;
        String actionResult = "";
            if (result == 1) {
                actionResult = "Th??nh C??ng";
                redirect += "&notice=successDel";
            } else {
                actionResult = "Th???t b???i";
            }
            actionLogServices.logAction(userSession.getUserId(), "DELETE_CONFIG_MT",
                    "CONFIG_MT", mtIdDec.toBigInteger(), actionResult, "X??a Config_MT",
                    null, null, request);
        return redirect;
    }
}
