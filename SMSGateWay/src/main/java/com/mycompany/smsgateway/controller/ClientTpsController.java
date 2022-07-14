/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.controller;

import com.mycompany.smsgateway.dao.CPListDAO;
import com.mycompany.smsgateway.dao.ClientTpsDAO;
import com.mycompany.smsgateway.dao.ShortcodeCpDAO;
import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.model.ClientTpsModel;
import com.mycompany.smsgateway.model.CpListModel;
import com.mycompany.smsgateway.model.ShortcodeCpModel;
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
public class ClientTpsController {

    private int numPerPage = 10;

    @Autowired
    private ClientTpsDAO clientTpsDAO;

    @Autowired
    private Paging paging;

    @Autowired
    private CPListDAO cpListDAO;

    @Autowired
    private ShortcodeCpDAO shortcodeCpDAO;

    @Autowired
    private ActionLogServices actionLogServices;

    @RequestMapping(value = "clientTpsList", method = RequestMethod.GET)
    public String clientTpsList(Model model, HttpSession session, @RequestParam String action,
            @RequestParam(required = false) String page, @RequestParam(required = false) String notice) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CLIENT_TPS_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        Long total = clientTpsDAO.getTotalClientTps();
        int totalItem = total.intValue();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        List<ClientTpsModel> clients = clientTpsDAO.getAllClientTps(start, numPerPage);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("clients", clients);
        model.addAttribute("action", action);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("notice", notice);
        return "clientPages_clientTpsList";
    }

    @RequestMapping(value = "addClientTps", method = RequestMethod.GET)
    public String addClientTps(Model model, HttpSession session, @RequestParam String action) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CLIENT_TPS_INSERT")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        List<CpListModel> cps = cpListDAO.getAllCpList();
        List<ShortcodeCpModel> shcodeCps = shortcodeCpDAO.getAllShortcodeCp();

        model.addAttribute("cps", cps);
        model.addAttribute("shcodeCps", shcodeCps);
        model.addAttribute("action", action);
        return "clientPages_clientTpsDetail";
    }

    @RequestMapping(value = "addClientTps", method = RequestMethod.POST)
    public String addClientTps(Model model, HttpSession session, HttpServletRequest request,
            @RequestParam String tps, @RequestParam String note, @RequestParam String action,
            @RequestParam String cpId, @RequestParam String shcodeCpId) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CLIENT_TPS_INSERT")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigInteger tpsInt = new BigInteger(tps);
        BigInteger cpIdInt = new BigInteger(cpId);
        BigInteger shcodeCpIdInt = new BigInteger(shcodeCpId);
        int result = clientTpsDAO.addClientTps(tpsInt, note, cpIdInt, shcodeCpIdInt);
        String actionResult = "";
        if (result == 1) {
            actionResult = "Thành Công";
        } else {
            actionResult = "Thất bại";
        }
        BigDecimal newestId = clientTpsDAO.getNewestId();
        actionLogServices.logAction(userSession.getUserId(), "INSERT_CLIENT_TPS",
                "CLIENT_TPS", newestId.toBigInteger(), actionResult, "Thêm client tps",
                null, null, request);

        if (result == 1) {
            return "redirect:clientTpsList?action=list&notice=success";
        }
        model.addAttribute("action", action);
        model.addAttribute("tps", tps);
        model.addAttribute("note", note);
        model.addAttribute("cpId", cpId);
        model.addAttribute("shcodeCpId", shcodeCpId);
        model.addAttribute("notice", "Thêm thất bại");
        return "";
    }

    @RequestMapping(value = "updateClientTps", method = RequestMethod.GET)
    public String updateClientTps(Model model, HttpSession session, @RequestParam String action,
            @RequestParam String clientId) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CLIENT_TPS_UPDATE") && !roles.contains("CLIENT_TPS_DETAIL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal clientIdDec = new BigDecimal(clientId);
        ClientTpsModel client = clientTpsDAO.findClientTpsById(clientIdDec);
        List<CpListModel> cps = cpListDAO.getAllCpList();
        List<ShortcodeCpModel> shcodeCps = shortcodeCpDAO.getAllShortcodeCp();
        
        model.addAttribute("cps", cps);
        model.addAttribute("shcodeCps", shcodeCps);
        model.addAttribute("clientId", client.getClientId());
        model.addAttribute("tps", client.getTps());
        model.addAttribute("note", client.getNote());
        model.addAttribute("cpId", client.getCpIdDec());
        model.addAttribute("shcodeCpId", client.getShcodeId());
        model.addAttribute("action", action);
        return "clientPages_clientTpsDetail";
    }
    
    @RequestMapping(value = "updateClientTps", method = RequestMethod.POST)
    public String updateClientTps(Model model, HttpSession session, @RequestParam String action,
            @RequestParam String clientId, HttpServletRequest request,
            @RequestParam String tps, @RequestParam String note,
            @RequestParam String cpId, @RequestParam String shcodeCpId){
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CLIENT_TPS_UPDATE") && !roles.contains("CLIENT_TPS_DETAIL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal clientIdDec = new BigDecimal(clientId);
        BigInteger tpsInt = new BigInteger(tps);
        BigInteger cpIdInt = new BigInteger(cpId);
        BigInteger shcodeCpIdInt = new BigInteger(shcodeCpId);
        int result = clientTpsDAO.updateClientTps(clientIdDec, tpsInt, note, cpIdInt, shcodeCpIdInt);
        String actionResult = "";
        if (result == 1) {
            actionResult = "Thành Công";
        } else {
            actionResult = "Thất bại";
        }
        actionLogServices.logAction(userSession.getUserId(), "UPDATE_CLIENT_TPS",
                "CLIENT_TPS", clientIdDec.toBigInteger(), actionResult, "Cập nhật client tps",
                null, null, request);
        if(result == 1) {
            model.addAttribute("notice", "Cập nhật thành công");
        } else {
            model.addAttribute("notice", "Cập nhật thất bại");
        }
        List<CpListModel> cps = cpListDAO.getAllCpList();
        List<ShortcodeCpModel> shcodeCps = shortcodeCpDAO.getAllShortcodeCp();
        
        model.addAttribute("cps", cps);
        model.addAttribute("shcodeCps", shcodeCps);
        model.addAttribute("clientId", clientId);
        model.addAttribute("tps", tps);
        model.addAttribute("note", note);
        model.addAttribute("cpId", cpId);
        model.addAttribute("shcodeCpId", shcodeCpId);
        model.addAttribute("action", action);
        return "clientPages_clientTpsDetail";
    }
    
    @RequestMapping(value = "deleteClientTps", method = RequestMethod.GET)
    public String deleteClientTps(Model model, HttpSession session, HttpServletRequest request,
            @RequestParam String clientId){
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("CLIENT_TPS_DELETE")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        BigDecimal clientIdDec = new BigDecimal(clientId);
        int result = clientTpsDAO.deleteClientTps(clientIdDec);
        String actionResult = "";
        if (result == 1) {
            actionResult = "Thành Công";
        } else {
            actionResult = "Thất bại";
        }
        actionLogServices.logAction(userSession.getUserId(), "DELETE_CLIENT_TPS",
                "CLIENT_TPS", clientIdDec.toBigInteger(), actionResult, "Xóa client tps",
                null, null, request);
        if(result == 1) {
            model.addAttribute("notice", "Cập nhật thành công");
        } else {
            model.addAttribute("notice", "Cập nhật thất bại");
        }
        return"redirect:clientTpsList?action=list";
    }
}
