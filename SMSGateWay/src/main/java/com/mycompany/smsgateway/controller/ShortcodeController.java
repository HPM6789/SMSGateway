/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.controller;

import com.mycompany.smsgateway.dao.ShortcodeListDAO;
import com.mycompany.smsgateway.model.AuthUserModel;
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
public class ShortcodeController {

    @Autowired
    private ShortcodeListDAO shortcodeListDAO;

    @Autowired
    private Paging paging;

    @Autowired
    private ActionLogServices actionLogServices;

    @RequestMapping(value = "shortcodeList", method = RequestMethod.GET)
    public String shortcodeList(Model model, HttpSession session,
            @RequestParam(required = false) String page, @RequestParam String action,
            @RequestParam(required = false) String notice) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("SHCODE_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        int numPerPage = 15;
        List<ShortcodeListModel> shortcodes = shortcodeListDAO.getAllShortcode();
        int totalItem = shortcodes.size();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        int end = Math.min(pageInt * numPerPage, totalItem);
        List<ShortcodeListModel> shortcodePage = paging.shortcodeListPaging(start, end, shortcodes);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("shortcodes", shortcodePage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("action", action);
        model.addAttribute("notice", notice);
        return "shortcodePages_shortcodeList";
    }

    @RequestMapping(value = "searchShortcode", method = RequestMethod.GET)
    public String shortcodeList(Model model, @RequestParam String inputSearch, HttpSession session,
            @RequestParam(required = false) String page, @RequestParam String action,
            @RequestParam(required = false) String fromCreateDate,
            @RequestParam(required = false) String toCreateDate,
            @RequestParam(required = false) String fromUpdateDate,
            @RequestParam(required = false) String toUpdateDate,
            @RequestParam(required = false) String status) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("SHCODE_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        int numPerPage = 15;
        BigInteger statusInt = null;
        if (status != null){
            statusInt = new BigInteger(status);
        }
        List<ShortcodeListModel> shortcodes = shortcodeListDAO.getAllShortcodeByOption(inputSearch,
                fromCreateDate, toCreateDate, fromUpdateDate, toUpdateDate, statusInt);
        int totalItem = shortcodes.size();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        int end = Math.min(pageInt * numPerPage, totalItem);
        List<ShortcodeListModel> shortcodePage = paging.shortcodeListPaging(start, end, shortcodes);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("shortcodes", shortcodePage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("action", action);
        model.addAttribute("inputSearch", inputSearch);
        model.addAttribute("fromCreateDate", fromCreateDate);
        model.addAttribute("toCreateDate", toCreateDate);
        model.addAttribute("fromUpdateDate", fromUpdateDate);
        model.addAttribute("toUpdateDate", toUpdateDate);
        model.addAttribute("status", status);
        return "shortcodePages_shortcodeList";
    }

    @RequestMapping(value = "addShortcode", method = RequestMethod.GET)
    public String addShortcode(Model model, HttpSession session, @RequestParam String action) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("SHCODE_INSERT")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        model.addAttribute("action", action);
        return "shortcodePages_shortcodeDetail";
    }

    @RequestMapping(value = "addShortcode", method = RequestMethod.POST)
    public String addShortcode(Model model, HttpSession session, @RequestParam String action,
            @RequestParam String shortcode, @RequestParam String price,
            @RequestParam String limitedMtNo, @RequestParam String creatorId,
            HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("SHCODE_INSERT")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigInteger priceInt = new BigInteger(price);
        BigInteger limitedMtNoInt = new BigInteger(limitedMtNo);
        Long creatorIdLong = new Long(creatorId);
        ShortcodeListModel shortcodeInDB = shortcodeListDAO.getShortcodeByCode(shortcode);
        if (shortcodeInDB != null) {
            actionLogServices.logAction(userSession.getUserId(), "INSERT_SHORTCODE",
                    "SHORTCODE_LIST", BigInteger.ZERO, "Thất bại", "Thêm đầu số",
                    null, null, request);
            model.addAttribute("notice", "This shortcode is already existed!");
        } else {
            int result = shortcodeListDAO.addNewShortcode(shortcode, priceInt, limitedMtNoInt, creatorIdLong);
            BigDecimal shcodeId = shortcodeListDAO.getNewestShcodeId();
            String actionResult = "";
            if (result == 0) {
                actionResult = "Thất bại";
            } else {
                actionResult = "Thành Công";
            }
            actionLogServices.logAction(userSession.getUserId(), "INSERT_SHORTCODE",
                    "SHORTCODE_LIST", shcodeId.toBigInteger(), actionResult, "Thêm đầu số",
                    null, null, request);
            String notice = "Add Successfully!";
            return "redirect:shortcodeList?action=list&notice=" + notice;
        }
        model.addAttribute("shortcode", shortcode);
        model.addAttribute("price", price);
        model.addAttribute("limitedMtNo", limitedMtNo);
        model.addAttribute("creatorId", creatorId);
        model.addAttribute("action", action);
        return "shortcodePages_shortcodeDetail";
    }

    @RequestMapping(value = "updateShortcode", method = RequestMethod.GET)
    public String updateShortcode(Model model, HttpSession session, @RequestParam String action,
            @RequestParam String shcodeId) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("SHCODE_UPDATE") || !roles.contains("SHCODE_DETAIL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal shcodeIdBigDecimal = new BigDecimal(shcodeId);
        ShortcodeListModel shortcodeModel = shortcodeListDAO.getShortcodeById(shcodeIdBigDecimal);
        model.addAttribute("action", action);
        model.addAttribute("shcodeId", shortcodeModel.getShcodeId());
        model.addAttribute("shortcode", shortcodeModel.getShortcode());
        model.addAttribute("price", shortcodeModel.getPrice());
        model.addAttribute("limitedMtNo", shortcodeModel.getLimitedMtNo());
        model.addAttribute("createTime", shortcodeModel.getCreateTime());
        model.addAttribute("updateTime", shortcodeModel.getUpdateTime());
        model.addAttribute("approveTime", shortcodeModel.getApproveTime());
        model.addAttribute("status", shortcodeModel.getStatus());
        model.addAttribute("creatorId", shortcodeModel.getCreatorId());
        model.addAttribute("oldshortcode", shortcodeModel.getShortcode());
        return "shortcodePages_shortcodeDetail";
    }

    @RequestMapping(value = "updateShortcode", method = RequestMethod.POST)
    public String updateShortcode(Model model, HttpSession session, @RequestParam String action,
            @RequestParam String shcodeId, @RequestParam String shortcode,
            @RequestParam String price, @RequestParam String limitedMtNo,
            @RequestParam String oldshortcode, HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("SHCODE_UPDATE") || !roles.contains("SHCODE_DETAIL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal shcodeIdBigDecimal = new BigDecimal(shcodeId);
        ShortcodeListModel shortcodeModel = shortcodeListDAO.getShortcodeById(shcodeIdBigDecimal);
        if (shortcodeModel != null && !shortcodeModel.getShortcode().equals(oldshortcode)) {
            actionLogServices.logAction(userSession.getUserId(), "UPDATE_SHORTCODE",
                    "SHORTCODE_LIST", shcodeIdBigDecimal.toBigInteger(), "Thất bại", "Cập nhật thông tin đầu số",
                    null, null, request);
            model.addAttribute("notice", "This shortcode is already existed!");
            model.addAttribute("oldshortcode", oldshortcode);
        } else {
            BigInteger priceInt = new BigInteger(price);
            BigInteger limitedMtNoInt = new BigInteger(limitedMtNo);
            int result = shortcodeListDAO.updateShortcode(shcodeIdBigDecimal, shortcode, priceInt, limitedMtNoInt);
            String actionResult = "";
            if (result == 0) {
                actionResult = "Thất bại";
                model.addAttribute("notice", "Update Failed!");
            } else {
                actionResult = "Thành Công";
                model.addAttribute("notice", "Update Successfully!");
            }
            actionLogServices.logAction(userSession.getUserId(), "UPDATE_SHORTCODE",
                    "SHORTCODE_LIST", shcodeIdBigDecimal.toBigInteger(), actionResult, "Cập nhật thông tin đầu số",
                    null, null, request);
            model.addAttribute("oldshortcode", shortcode);
        }
        shortcodeModel = shortcodeListDAO.getShortcodeById(shcodeIdBigDecimal);
        model.addAttribute("action", action);
        model.addAttribute("shcodeId", shcodeId);
        model.addAttribute("shortcode", shortcode);
        model.addAttribute("price", price);
        model.addAttribute("limitedMtNo", limitedMtNo);
        model.addAttribute("createTime", shortcodeModel.getCreateTime());
        model.addAttribute("updateTime", shortcodeModel.getUpdateTime());
        model.addAttribute("approveTime", shortcodeModel.getApproveTime());
        model.addAttribute("status", shortcodeModel.getStatus());
        return "shortcodePages_shortcodeDetail";
    }

    @RequestMapping(value = "approveShortcode", method = RequestMethod.GET)
    public String approveShortcode(Model model, HttpSession session, @RequestParam String shcodeId,
            HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("SHCODE_UPDATE")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal shcodeIdBigDecimal = new BigDecimal(shcodeId);
        int result = shortcodeListDAO.approveShortcode(shcodeIdBigDecimal);
        String actionResult = "";
        if (result == 0) {
            actionResult = "Thất bại";
        } else {
            actionResult = "Thành Công";
        }
        actionLogServices.logAction(userSession.getUserId(), "APPROVE_SHORTCODE",
                "SHORTCODE_LIST", shcodeIdBigDecimal.toBigInteger(), actionResult, "Duyệt đầu số",
                null, null, request);
        return "redirect:shortcodeList?action=list";
    }

    @RequestMapping(value = "disapproveShortcode", method = RequestMethod.GET)
    public String disapproveShortcode(Model model, HttpSession session, @RequestParam String shcodeId,
            HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("SHCODE_UPDATE")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal shcodeIdBigDecimal = new BigDecimal(shcodeId);
        int result = shortcodeListDAO.disapproveShortcode(shcodeIdBigDecimal);
        String actionResult = "";
        if (result == 0) {
            actionResult = "Thất bại";
        } else {
            actionResult = "Thành Công";
        }
        actionLogServices.logAction(userSession.getUserId(), "DISAPPROVE_SHORTCODE",
                "SHORTCODE_LIST", shcodeIdBigDecimal.toBigInteger(), actionResult, "Gỡ duyệt đầu số",
                null, null, request);
        return "redirect:shortcodeList?action=list";
    }
}
