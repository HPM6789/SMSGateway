/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.controller;

import com.mycompany.smsgateway.dao.KpiMoDAO;
import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.model.KpiMoModel;
import com.mycompany.smsgateway.services.ActionLogServices;
import com.mycompany.smsgateway.services.Paging;
import java.math.BigInteger;
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
public class KpiMoController {

    private final int numPerPage = 10;

    @Autowired
    private KpiMoDAO kpiMoDAO;

    @Autowired
    private Paging paging;

    @RequestMapping(value = "kpiMoList", method = RequestMethod.GET)
    public String kpiMoList(Model model, HttpSession session, @RequestParam(required = false) String page,
            @RequestParam String action) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("KPIMO")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        Long total = kpiMoDAO.getTotalKpiMo();
        int totalItem = total.intValue();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        List<KpiMoModel> kpis = kpiMoDAO.getKpiMoList(start, numPerPage);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("kpis", kpis);
        model.addAttribute("action", action);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        return "kpiPages_kpiMoList";
    }

    @RequestMapping(value = "searchKpiMo", method = RequestMethod.GET)
    public String searchKpiMo(Model model, HttpSession session,
            @RequestParam(required = false) String page, @RequestParam String action,
            @RequestParam(required = false) String inputSearch,
            @RequestParam(required = false) String fromCreateDate,
            @RequestParam(required = false) String toCreateDate) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("KPIMO")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        String strFrom = fromCreateDate.trim().replaceAll("-", "");
        String strTo = toCreateDate.trim().replaceAll("-", "");
        System.out.println(fromCreateDate + " from " + strFrom + " " + toCreateDate + " to " +strTo);
        Long total = kpiMoDAO.getTotalKpiMoByOption(inputSearch, strFrom, strTo);
        int totalItem = total.intValue();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        List<KpiMoModel> kpis = kpiMoDAO.getKpiMoListByOption(inputSearch, strFrom, strTo,
                start, numPerPage);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("kpis", kpis);
        model.addAttribute("action", action);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("inputSearch", inputSearch);
        model.addAttribute("fromCreateDate", fromCreateDate);
        model.addAttribute("toCreateDate", toCreateDate);
        return "kpiPages_kpiMoList";
    }
    
}
