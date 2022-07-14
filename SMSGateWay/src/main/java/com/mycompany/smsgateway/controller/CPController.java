/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.controller;

import com.mycompany.smsgateway.dao.CPListDAO;
import com.mycompany.smsgateway.dao.ShortcodeCpDAO;
import com.mycompany.smsgateway.dao.ShortcodeListDAO;
import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.model.CpListModel;
import com.mycompany.smsgateway.model.ShortcodeListModel;
import com.mycompany.smsgateway.services.ActionLogServices;
import com.mycompany.smsgateway.services.Paging;
import com.mycompany.smsgateway.services.ShortcodeServices;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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
public class CPController {

    @Autowired
    private CPListDAO cpListDAO;

    @Autowired
    private Paging paging;

    @Autowired
    private ShortcodeListDAO shortcodeListDAO;

    @Autowired
    private ShortcodeCpDAO shortcodeCpDAO;

    @Autowired
    private ShortcodeServices shortcodeServices;

    @Autowired
    private ActionLogServices actionLogServices;

    @RequestMapping(value = "cpList", method = RequestMethod.GET)
    public String cpList(Model model, HttpSession session,
            @RequestParam(required = false) String page, @RequestParam String action,
            @RequestParam(required = false) String notice) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CP_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        int numPerPage = 10;
        List<CpListModel> cpList = cpListDAO.getAllCpList();
        int totalItem = cpList.size();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        int end = Math.min(pageInt * numPerPage, totalItem);
        List<CpListModel> cpListPage = paging.cpListPaging(start, end, cpList);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        List<List<String>> listOfListShcode = new ArrayList<>();
        for (int i = 0; i < cpListPage.size(); i++) {
            listOfListShcode.add(shortcodeCpDAO.getAllShortcodeByCpId(cpListPage
            .get(i).getCpId()));
        }
        List<ShortcodeListModel> shortcodes = shortcodeListDAO.getAllShortcode();
        model.addAttribute("shortcodes", shortcodes);
        model.addAttribute("listOfListShcode", listOfListShcode);
        model.addAttribute("cpLists", cpListPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("action", action);
        model.addAttribute("start", start);
        model.addAttribute("notice", notice);
        return "cpPages_cpList";
    }

    @RequestMapping(value = "searchCP", method = RequestMethod.GET)
    public String cpList(Model model, @RequestParam(required = false) String inputSearch, HttpSession session,
            @RequestParam(required = false) String page, @RequestParam String action) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CP_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        int numPerPage = 10;
        List<CpListModel> cpList = cpListDAO.getCpListsByOption(inputSearch);
        int totalItem = cpList.size();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        int end = Math.min(pageInt * numPerPage, totalItem);
        List<CpListModel> cpListPage = paging.cpListPaging(start, end, cpList);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("cpLists", cpListPage);
        model.addAttribute("inputSearch", inputSearch);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("action", action);
        model.addAttribute("start", start);
        return "cpPages_cpList";
    }

    @RequestMapping(value = "addCP", method = RequestMethod.GET)
    public String addCP(Model model, HttpSession session, @RequestParam String action) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CP_INSERT")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        model.addAttribute("action", action);
        return "cpPages_cpDetail";
    }

    @RequestMapping(value = "addCP", method = RequestMethod.POST)
    public String addCP(Model model, HttpSession session, @RequestParam String cpName,
            @RequestParam String cpCode, @RequestParam String contact, @RequestParam String usernameMt,
            @RequestParam String passwordMt, @RequestParam String listipMt, @RequestParam String usernameCharge,
            @RequestParam String passwordCharge, @RequestParam String taxCode, @RequestParam String representer,
            @RequestParam String address, @RequestParam String emailCp, @RequestParam String action,
            HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CP_INSERT")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        model.addAttribute("action", action);
        model.addAttribute("cpName", cpName);
        model.addAttribute("cpCode", cpCode);
        model.addAttribute("contact", contact);
        model.addAttribute("usernameMt", usernameMt);
        model.addAttribute("passwordMt", passwordMt);
        model.addAttribute("listipMt", listipMt);
        model.addAttribute("usernameCharge", usernameCharge);
        model.addAttribute("passwordCharge", passwordCharge);
        model.addAttribute("taxCode", taxCode);
        model.addAttribute("representer", representer);
        model.addAttribute("address", address);
        model.addAttribute("emailCp", emailCp);
        CpListModel cp = cpListDAO.getCpByCode(cpCode);
        if (cp != null) {
            actionLogServices.logAction(userSession.getUserId(), "INSERT_CP",
                    "CP_LIST", BigInteger.ZERO, "Thất bại", "Thêm đối tác",
                    null, null, request);
            model.addAttribute("notice", "This CP Code is already existed!");
            return "cpPages_cpDetail";
        }
        int result = cpListDAO.addNewCpList(cpName, cpCode, contact, usernameMt, passwordMt, listipMt,
                usernameCharge, passwordCharge, taxCode, representer, address, emailCp);
        BigDecimal cpIdDec = cpListDAO.getNewestCpId();
        String actionResult = "";
        if (result == 0) {
            actionResult = "Thất bại";
        } else {
            actionResult = "Thành Công";
        }
        actionLogServices.logAction(userSession.getUserId(), "INSERT_CP",
                "CP_LIST", cpIdDec.toBigInteger(), actionResult, "Thêm đối tác",
                null, null, request);
        String notice = "Add Successfully!";
        return "redirect:cpList?action=list&notice=" + notice;
    }

    @RequestMapping(value = "updateCP", method = RequestMethod.GET)
    public String updateCP(Model model, HttpSession session, @RequestParam String action,
            @RequestParam String cpId) {
        System.out.println("updateCPController");
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CP_UPDATE") && !roles.contains("CP_DETAIL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        List<ShortcodeListModel> shortcodes = shortcodeListDAO.getAllShortcode();
        BigDecimal cpIdDecimal = new BigDecimal(cpId);
        List<String> shortcodeByCp = shortcodeCpDAO.getAllShortcodeByCpId(cpIdDecimal);
        CpListModel cp = cpListDAO.getCpByCpId(cpIdDecimal);
        model.addAttribute("shortcodeByCp", shortcodeByCp);
        model.addAttribute("shortcodes", shortcodes);
        model.addAttribute("action", action);
        model.addAttribute("cpId", cpId);
        model.addAttribute("cpName", cp.getCpName());
        model.addAttribute("cpCode", cp.getCpCode());
        model.addAttribute("oldcode", cp.getCpCode());
        model.addAttribute("contact", cp.getContact());
        model.addAttribute("createdTime", cp.getCreatedTime());
        model.addAttribute("updatedTime", cp.getUpdatedTime());
        model.addAttribute("usernameMt", cp.getUsernameMt());
        model.addAttribute("passwordMt", cp.getPasswordMt());
        model.addAttribute("listipMt", cp.getListipMt());
        model.addAttribute("usernameCharge", cp.getUsernameCharge());
        model.addAttribute("passwordCharge", cp.getPasswordCharge());
        model.addAttribute("taxCode", cp.getTaxCode());
        model.addAttribute("representer", cp.getRepresenter());
        model.addAttribute("address", cp.getAddress());
        model.addAttribute("emailCp", cp.getEmailCp());
        return "cpPages_cpDetail";
    }

    @RequestMapping(value = "updateCP", method = RequestMethod.POST)
    public String updateCP(Model model, HttpSession session, @RequestParam String cpName,
            @RequestParam String cpCode, @RequestParam String contact,
            @RequestParam String usernameMt,
            @RequestParam String passwordMt, @RequestParam String listipMt, @RequestParam String usernameCharge,
            @RequestParam String passwordCharge, @RequestParam String taxCode, @RequestParam String representer,
            @RequestParam String address, @RequestParam String emailCp, @RequestParam String action,
            @RequestParam String cpId, @RequestParam String oldcode, HttpServletRequest request) {
        System.out.println("updateCPControllerPost");
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CP_UPDATE") && !roles.contains("CP_DETAIL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal cpIdDecimal = new BigDecimal(cpId);
        CpListModel cp = cpListDAO.getCpByCode(cpCode);
        if (cp != null && !cp.getCpCode().equals(oldcode)) {
            actionLogServices.logAction(userSession.getUserId(), "UPDATE_CP",
                    "CP_LIST", cpIdDecimal.toBigInteger(), "Thất bại", "Cập nhật thông tin đối tác",
                    null, null, request);
            model.addAttribute("oldcode", oldcode);
            model.addAttribute("notice", "This CP code is already existed. Update Failed!");
        } else {
            int result = cpListDAO.updateCpList(cpIdDecimal, cpName, cpCode, contact, usernameMt, passwordMt,
                    listipMt, usernameCharge, passwordCharge, taxCode, representer, address, emailCp);
            String actionResult = "";
            if (result == 0) {
                actionResult = "Thất bại";
            } else {
                actionResult = "Thành Công";
            }
            actionLogServices.logAction(userSession.getUserId(), "UPDATE_CP",
                    "CP_LIST", cpIdDecimal.toBigInteger(), actionResult, "Cập nhật thông tin đối tác",
                    null, null, request);
            model.addAttribute("oldcode", cpCode);
            model.addAttribute("notice", "Update successfully!");
        }
        List<ShortcodeListModel> shortcodes = shortcodeListDAO.getAllShortcode();
        model.addAttribute("shortcodes", shortcodes);
        model.addAttribute("action", action);
        model.addAttribute("cpId", cpId);
        model.addAttribute("cpName", cpName);
        model.addAttribute("cpCode", cpCode);
        model.addAttribute("contact", contact);

        model.addAttribute("usernameMt", usernameMt);
        model.addAttribute("passwordMt", passwordMt);
        model.addAttribute("listipMt", listipMt);
        model.addAttribute("usernameCharge", usernameCharge);
        model.addAttribute("passwordCharge", passwordCharge);
        model.addAttribute("taxCode", taxCode);
        model.addAttribute("representer", representer);
        model.addAttribute("address", address);
        model.addAttribute("emailCp", emailCp);
        return "cpPages_cpDetail";
    }

    @RequestMapping(value = "updateShortcodeForCp", method = RequestMethod.GET)
    public String updateShortcodeForCp(Model model, HttpSession session, @RequestParam String cpId,
            @RequestParam String page) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CP_UPDATE")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        List<ShortcodeListModel> shortcodes = shortcodeListDAO.getAllShortcode();
        BigDecimal cpIdDecimal = new BigDecimal(cpId);
        List<String> shortcodeByCp = shortcodeCpDAO.getAllShortcodeByCpId(cpIdDecimal);
        model.addAttribute("cpId", cpId);
        model.addAttribute("shortcodeByCp", shortcodeByCp);
        model.addAttribute("shortcodes", shortcodes);
        model.addAttribute("page", page);
        return "cpPages_updateShortcode";
    }
    
    @RequestMapping(value = "updateShortcodeForCp", method = RequestMethod.POST)
    public String updateShortcodeForCp(Model model, HttpSession session,
            @RequestParam(required = false) List<String> shcodeId, @RequestParam String cpId,
            HttpServletRequest request, @RequestParam String page) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CP_UPDATE")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal cpIdDec = new BigDecimal(cpId);
        List<BigDecimal> shortcodesByCp = shortcodeCpDAO.getAllShortcodeIdByCpId(cpIdDec);

        List<BigDecimal> shcodeIdDecs = new ArrayList<>();
        int result = -1;
        if (shcodeId != null && !shcodeId.isEmpty()) {
            for (String shcode : shcodeId) {
                shcodeIdDecs.add(new BigDecimal(shcode));
            }
            if (!shortcodeServices.compareTwoListShcodeId(shortcodesByCp, shcodeIdDecs)) {
                if (shortcodesByCp != null && !shortcodesByCp.isEmpty()) {
                    cpListDAO.removeShortcodeForCp(cpIdDec, shortcodesByCp);
                }
                result = cpListDAO.addShortcodeForCp(cpIdDec, shcodeIdDecs);
            }
        }
        String actionResult = "";
        if (result == -1) {
            actionResult = "Thất bại";
        } else {
            actionResult = "Thành Công";
        }
        actionLogServices.logAction(userSession.getUserId(), "UPDATE_SHORTCODE_CP",
                "CP_LIST", cpIdDec.toBigInteger(), actionResult, "Cập nhật đầu số cho đối tác",
                null, null, request);
//        List<ShortcodeListModel> shortcodes = shortcodeListDAO.getAllShortcode();
//        BigDecimal cpIdDecimal = new BigDecimal(cpId);
//        List<String> shortcodeByCp = shortcodeCpDAO.getAllShortcodeByCpId(cpIdDecimal);
//        model.addAttribute("cpId", cpId);
//        model.addAttribute("shortcodeByCp", shortcodeByCp);
//        model.addAttribute("shortcodes", shortcodes);
//        model.addAttribute("page", page);
        return "redirect:cpList?action=list&page=" + page;
    }
}
