/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.controller;

import com.mycompany.smsgateway.dao.CmdcodeListDAO;
import com.mycompany.smsgateway.dao.ShortcodeCpDAO;
import com.mycompany.smsgateway.dao.TypeListDAO;
import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.model.CmdcodeListModel;
import com.mycompany.smsgateway.model.ShortcodeCpModel;
import com.mycompany.smsgateway.model.TypeListModel;
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
public class CmdcodeListController {

    @Autowired
    private CmdcodeListDAO cmdcodeListDAO;

    @Autowired
    private TypeListDAO typeListDAO;

    @Autowired
    private ShortcodeCpDAO shortcodeCpDAO;

    @Autowired
    private Paging paging;
    
    @Autowired
    private ActionLogServices actionLogServices;

    @RequestMapping(value = "cmdcodeList", method = RequestMethod.GET)
    public String cmdcodeList(Model model, HttpSession session, @RequestParam String action,
            @RequestParam(required = false) String page, @RequestParam(required = false) String notice) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CMDCODE_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        int numPerPage = 10;
//        List<CmdcodeListModel> cmds = cmdcodeListDAO.getAllCmdcode();
//        int totalItem = cmds.size();
        Long total = cmdcodeListDAO.getTotalCmdcode();
        int totalItem = total.intValue();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
//        int end = Math.min(pageInt * numPerPage, totalItem);
//        List<CmdcodeListModel> cmdPage = paging.cmdcodeListPaging(start, end, cmds);
        List<CmdcodeListModel> cmdPage = cmdcodeListDAO.getAllCmdcode2(start, numPerPage);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("cmds", cmdPage);
        model.addAttribute("action", action);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("notice", notice);
        return "cmdPages_cmdcodeList";
    }

    @RequestMapping(value = "searchCmdcode", method = RequestMethod.GET)
    public String searchCmdcode(Model model, HttpSession session, @RequestParam String action,
            @RequestParam(required = false) String page, @RequestParam String inputSearch,
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
        if (!roles.contains("CMDCODE_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        int numPerPage = 10;
        BigInteger statusInt = null;
        if (status != null && !status.equals("")){
            statusInt = new BigInteger(status);
        }
        int totalItem = cmdcodeListDAO.getTotalCmdcodeByOption(inputSearch, 
                fromCreateDate, toCreateDate, fromUpdateDate, toUpdateDate, statusInt).intValue();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
//        int end = Math.min(pageInt * numPerPage, totalItem);
        List<CmdcodeListModel> cmdPage = cmdcodeListDAO.getCmdcodeByOption(inputSearch, 
                fromCreateDate, toCreateDate, fromUpdateDate, toUpdateDate, statusInt, start, numPerPage);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("cmds", cmdPage);
        model.addAttribute("action", action);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("inputSearch", inputSearch);
        model.addAttribute("fromCreateDate", fromCreateDate);
        model.addAttribute("toCreateDate", toCreateDate);
        model.addAttribute("fromUpdateDate", fromUpdateDate);
        model.addAttribute("toUpdateDate", toUpdateDate);
        model.addAttribute("status", status);
        return "cmdPages_cmdcodeList";
    }

    @RequestMapping(value = "approveCmdcode", method = RequestMethod.GET)
    public String approveCmdcode(Model model, HttpSession session, @RequestParam String cmdId,
            @RequestParam String page, HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CMDCODE_UPDATE")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal cmdIdDecimal = new BigDecimal(cmdId);
        int result = cmdcodeListDAO.approveCmdcode(cmdIdDecimal);
        String actionResult = "";
        if (result == 0) {
            actionResult = "Thất bại";
        } else {
            actionResult = "Thành Công";
        }
        actionLogServices.logAction(userSession.getUserId(), "APPROVE_CMDCODE",
                "CMDCODE_LIST", cmdIdDecimal.toBigInteger(), actionResult, "Duyệt command code",
                null, null, request);
        return "redirect:cmdcodeList?action=list&page=" + page;
    }

    @RequestMapping(value = "disapproveCmdcode", method = RequestMethod.GET)
    public String disapproveCmdcode(Model model, HttpSession session, @RequestParam String cmdId,
            @RequestParam String page, HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CMDCODE_UPDATE")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal cmdIdDecimal = new BigDecimal(cmdId);
        int result = cmdcodeListDAO.disapproveCmdcode(cmdIdDecimal);
        String actionResult = "";
        if (result == 0) {
            actionResult = "Thất bại";
        } else {
            actionResult = "Thành Công";
        }
        actionLogServices.logAction(userSession.getUserId(), "DISAPPROVE_CMDCODE",
                "CMDCODE_LIST", cmdIdDecimal.toBigInteger(), actionResult, "Gỡ duyệt command code",
                null, null, request);
        return "redirect:cmdcodeList?action=list&page=" + page;
    }

    @RequestMapping(value = "deleteCmdcode", method = RequestMethod.GET)
    public String deleteCmdcode(Model model, HttpSession session, @RequestParam String cmdId,
            @RequestParam String page, HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CMDCODE_DELETE")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal cmdIdDecimal = new BigDecimal(cmdId);
        int result = cmdcodeListDAO.deleteCmdcode(cmdIdDecimal);
        String actionResult = "";
        if (result == 0) {
            actionResult = "Thất bại";
        } else {
            actionResult = "Thành Công";
        }
        actionLogServices.logAction(userSession.getUserId(), "DELETE_CMDCODE",
                "CMDCODE_LIST", cmdIdDecimal.toBigInteger(), actionResult, "Xóa command code",
                null, null, request);
        return "redirect:cmdcodeList?action=list&page=" + page;
    }

    @RequestMapping(value = "restoreCmdcode", method = RequestMethod.GET)
    public String restoreCmdcode(Model model, HttpSession session, @RequestParam String cmdId,
            @RequestParam String page, HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CMDCODE_UPDATE")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal cmdIdDecimal = new BigDecimal(cmdId);
        int result = cmdcodeListDAO.restoreCmdcode(cmdIdDecimal);
        String actionResult = "";
        if (result == 0) {
            actionResult = "Thất bại";
        } else {
            actionResult = "Thành Công";
        }
        actionLogServices.logAction(userSession.getUserId(), "RESTORE_CMDCODE",
                "CMDCODE_LIST", cmdIdDecimal.toBigInteger(), actionResult, "Khôi phục command code",
                null, null, request);
        return "redirect:cmdcodeList?action=list&page=" + page;
    }

    @RequestMapping(value = "approveAllCmdcode", method = RequestMethod.GET)
    public String approveAllCmdcode(Model model, HttpSession session, @RequestParam String page,
            HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CMDCODE_APPROVAL_ALL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        int result = cmdcodeListDAO.approveAllCmdcode();
        String actionResult = "";
        if (result == -1) {
            actionResult = "Thất bại";
        } else {
            actionResult = "Thành Công";
        }
        actionLogServices.logAction(userSession.getUserId(), "APPROVE_ALL_CMDCODE",
                "CMDCODE_LIST", BigInteger.ZERO, actionResult, "Duyệt tất cả command code",
                null, null, request);
        return "redirect:cmdcodeList?action=list&page=" + page;
    }

    @RequestMapping(value = "addCmdcode", method = RequestMethod.GET)
    public String addCmdcode(Model model, HttpSession session, @RequestParam String action) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CMDCODE_INSERT")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        List<TypeListModel> types = typeListDAO.getAllTypeList();
        List<ShortcodeCpModel> shortcodeCps = shortcodeCpDAO.getAllShortcodeCp();
        model.addAttribute("action", action);
        model.addAttribute("types", types);
        model.addAttribute("shortcodeCps", shortcodeCps);
        return "cmdPages_cmdcodeDetail";
    }

    @RequestMapping(value = "addCmdcode", method = RequestMethod.POST)
    public String addCmdcode(Model model, HttpSession session, @RequestParam String cmdName,
            @RequestParam String cmdCode, @RequestParam String shortcodeCpId,
            @RequestParam String price, @RequestParam String description,
            @RequestParam String action, @RequestParam String typeCode,
            HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CMDCODE_INSERT")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigInteger shortcodeCpIdInt = new BigInteger(shortcodeCpId);
        Long priceLong = new Long(price);
        Long creatorId = new Long(userSession.getUserId().toString());
        int result = cmdcodeListDAO.addNewCmdcode(cmdName, cmdCode, shortcodeCpIdInt, typeCode,
                priceLong, description, creatorId);
        String actionResult = "";
        if (result == 0) {
            actionResult = "Thất bại";
        } else {
            actionResult = "Thành Công";
        }
        BigDecimal cmdIdDec = cmdcodeListDAO.getNewestCmdId();
        actionLogServices.logAction(userSession.getUserId(), "INSERT_CMDCODE",
                "CMDCODE_LIST", cmdIdDec.toBigInteger(), actionResult, "Thêm command code",
                null, null, request);
        String notice = "success";
        return "redirect:cmdcodeList?action=list&notice=" + notice;
    }

    @RequestMapping(value = "updateCmdcode", method = RequestMethod.GET)
    public String updateCmdcode(Model model, HttpSession session, @RequestParam String cmdId,
            @RequestParam String page, @RequestParam String action) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CMDCODE_UPDATE") || !roles.contains("CMDCODE_DETAIL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal cmdIdDec = new BigDecimal(cmdId);
        CmdcodeListModel cmd = cmdcodeListDAO.getCmdcodeById(cmdIdDec);
        List<TypeListModel> types = typeListDAO.getAllTypeList();
        List<ShortcodeCpModel> shortcodeCps = shortcodeCpDAO.getAllShortcodeCp();
        System.out.println("type code: " + cmd.getTypeCode());
        model.addAttribute("types", types);
        model.addAttribute("shortcodeCps", shortcodeCps);
        model.addAttribute("cmd", cmd);
        model.addAttribute("page", page);
        model.addAttribute("action", action);
        return "cmdPages_cmdcodeDetail";
    }

    @RequestMapping(value = "updateCmdcode", method = RequestMethod.POST)
    public String updateCmdcode(Model model, HttpSession session, @RequestParam String cmdId,
            @RequestParam String page, @RequestParam String action,
            @RequestParam String cmdName, @RequestParam String cmdCode, @RequestParam String shortcodeCpId,
            @RequestParam String typeCode, @RequestParam String price,
            @RequestParam String description, HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CMDCODE_UPDATE") || !roles.contains("CMDCODE_DETAIL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal cmdIdDec = new BigDecimal(cmdId);
        BigInteger shortcodeCpIdInt = new BigInteger(shortcodeCpId);
        Long priceLong = new Long(price);
        Long creatorId = new Long(userSession.getUserId().toString());
        int result = cmdcodeListDAO.updateCmdcode(cmdIdDec, cmdName, cmdCode, shortcodeCpIdInt, 
                typeCode, priceLong, description, creatorId);
        String actionResult = "";
        if (result == 0) {
            actionResult = "Thất bại";
        } else {
            actionResult = "Thành Công";
        }
        actionLogServices.logAction(userSession.getUserId(), "UPDATE_CMDCODE",
                "CMDCODE_LIST", cmdIdDec.toBigInteger(), actionResult, "Cập nhật thông tin command code",
                null, null, request);
        CmdcodeListModel cmd = cmdcodeListDAO.getCmdcodeById(cmdIdDec);
        List<TypeListModel> types = typeListDAO.getAllTypeList();
        List<ShortcodeCpModel> shortcodeCps = shortcodeCpDAO.getAllShortcodeCp();
        model.addAttribute("types", types);
        model.addAttribute("shortcodeCps", shortcodeCps);
        model.addAttribute("cmd", cmd);
        model.addAttribute("page", page);
        model.addAttribute("action", action);
        model.addAttribute("notice", "Update Successfully!");
        return "cmdPages_cmdcodeDetail";
    }
}
