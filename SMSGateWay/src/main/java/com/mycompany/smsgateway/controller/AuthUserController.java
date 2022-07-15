/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.controller;

import com.mycompany.smsgateway.dao.ActionLogDAO;
import com.mycompany.smsgateway.dao.AuthUserDAO;
import com.mycompany.smsgateway.dao.GroupDAO;
import com.mycompany.smsgateway.dao.RolesDAO;
import com.mycompany.smsgateway.entities.AuthUser;
import com.mycompany.smsgateway.entities.Groups;
import com.mycompany.smsgateway.entities.Roles;
import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.model.GroupsModel;
import com.mycompany.smsgateway.model.RolesModel;
import com.mycompany.smsgateway.services.ActionLogServices;
import com.mycompany.smsgateway.services.AuthUserServices;
import com.mycompany.smsgateway.services.Paging;
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
public class AuthUserController {

    private final int numPerPage = 10;
    
    @Autowired
    private AuthUserDAO authUserDAO;

    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    private AuthUserServices authUserServices;

    @Autowired
    private Paging paging;

    @Autowired
    private ActionLogServices actionLogServices;

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public String userList(Model model, HttpSession session, @RequestParam(required = false) String page,
            @RequestParam String action, HttpServletRequest request) {
        System.out.println("userListController");
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("AUTH_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }

        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        List<AuthUserModel> users = authUserDAO.getAllUser();
        int totalItem = users.size();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        int end = Math.min(pageInt * numPerPage, totalItem);

        List<AuthUserModel> userPage = paging.userPaging(start, end, users);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("users", userPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("action", action);

        return "authUserPages_userList";
    }

    @RequestMapping(value = "/searchUser", method = RequestMethod.GET)
    public String userList(Model model, @RequestParam(required = false) String inputSearch, HttpSession session,
            @RequestParam(required = false) String page, @RequestParam String action, 
            @RequestParam(required = false) String status, @RequestParam(required = false) String userType) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("AUTH_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        BigInteger statusInt = null;
        BigInteger userTypeInt = null;
        if (status != null && !status.equals("")) {
            statusInt = new BigInteger(status);
        }
        if( userType !=null && !userType.equals("")) {
            userTypeInt = new BigInteger(userType);
        }
        List<AuthUserModel> users = authUserDAO.getUserByOption(inputSearch, statusInt, userTypeInt);
        int totalItem = users.size();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        System.out.println("endpage: " + endPage);
        int start = (pageInt - 1) * numPerPage;
        int end = Math.min(pageInt * numPerPage, totalItem);

        List<AuthUserModel> userPage = paging.userPaging(start, end, users);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("users", userPage);
        model.addAttribute("inputSearch", inputSearch);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("action", "search");
        model.addAttribute("status", status);
        model.addAttribute("userType", userType);
        return "authUserPages_userList";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model, @RequestParam String username, HttpSession session,
            @RequestParam(required = false) String page) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }

        System.out.println("profile/" + username);
        System.out.println(username);
        AuthUserModel user = authUserDAO.getUserWithCP(username);
        List<GroupsModel> groups = groupDAO.getGroupByUserId(user.getUserId());

        System.out.println("cp name: " + user.getCpName());

        model.addAttribute("username", user.getUserName());
        model.addAttribute("fullname", user.getUserFullname());
        model.addAttribute("description", user.getUserDesc());
        model.addAttribute("status", user.getUserStatus());
        model.addAttribute("isSuper", user.getUserIsSuper());
        model.addAttribute("userType", user.getUserType());
        model.addAttribute("address", user.getUserAddr());
        model.addAttribute("phone", user.getUserPhone());
        model.addAttribute("email", user.getUserEmail());
        model.addAttribute("createdTime", user.getUserCreatedtime());
        model.addAttribute("updatedTime", user.getUserUpdatedtime());
        model.addAttribute("lastTimeLogin", user.getUserLastTimeLogin());
        model.addAttribute("optFlag", user.getUserOtpFlg());
        model.addAttribute("cpId", user.getCpId());
        model.addAttribute("cpName", user.getCpName());
        model.addAttribute("groups", groups);
        model.addAttribute("page", page);
        return "authUserPages_profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String profile(Model model, @RequestParam String username, @RequestParam String fullname,
            @RequestParam String description, @RequestParam String address, @RequestParam String phone,
            @RequestParam String email, HttpSession session, @RequestParam(required = false) String page,
            HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }

        System.out.println("profile/" + username);
        System.out.println(username);

        int result = authUserDAO.updateProfile(username, fullname, description, address, phone, email);
        String actionResult = "";
        if (result == 0) {
            actionResult = "Thất bại";
        } else {
            actionResult = "Thành công";
        }
        actionLogServices.logAction(userSession.getUserId(), "UPDATE_PROFILE", "AUTH_USER", 
                userSession.getUserId().toBigInteger(), actionResult, "Cập nhật hồ sơ", 
                null, null, request);
        AuthUserModel user = authUserDAO.getUserWithCP(username);
        session.setAttribute("user", user);
        List<GroupsModel> groups = groupDAO.getGroupByUserId(user.getUserId());

        model.addAttribute("username", user.getUserName());
        model.addAttribute("fullname", user.getUserFullname());
        model.addAttribute("description", user.getUserDesc());
        model.addAttribute("status", user.getUserStatus());
        model.addAttribute("isSuper", user.getUserIsSuper());
        model.addAttribute("userType", user.getUserType());
        model.addAttribute("address", user.getUserAddr());
        model.addAttribute("phone", user.getUserPhone());
        model.addAttribute("email", user.getUserEmail());
        model.addAttribute("createdTime", user.getUserCreatedtime());
        model.addAttribute("updatedTime", user.getUserUpdatedtime());
        model.addAttribute("lastTimeLogin", user.getUserLastTimeLogin());
        model.addAttribute("optFlag", user.getUserOtpFlg());
        model.addAttribute("cpId", user.getCpId());
        model.addAttribute("cpName", user.getCpName());
        model.addAttribute("groups", groups);
        model.addAttribute("page", page);
        return "authUserPages_profile";
    }

    @RequestMapping(value = "/userDetail", method = RequestMethod.GET)
    public String userDetail(Model model, @RequestParam String username, HttpSession session,
            @RequestParam String page) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("AUTH_UPDATE") && !roles.contains("AUTH_DETAIL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        System.out.println("userDetail/" + username);
        System.out.println(username);
        AuthUserModel user = authUserDAO.getUserWithCP(username);
        List<GroupsModel> groups = groupDAO.getGroupByUserId(user.getUserId());

        System.out.println("cp name: " + user.getCpName());

        model.addAttribute("username", user.getUserName());
        model.addAttribute("fullname", user.getUserFullname());
        model.addAttribute("description", user.getUserDesc());
        model.addAttribute("status", user.getUserStatus());
        model.addAttribute("isSuper", user.getUserIsSuper());
        model.addAttribute("userType", user.getUserType());
        model.addAttribute("address", user.getUserAddr());
        model.addAttribute("phone", user.getUserPhone());
        model.addAttribute("email", user.getUserEmail());
        model.addAttribute("createdTime", user.getUserCreatedtime());
        model.addAttribute("updatedTime", user.getUserUpdatedtime());
        model.addAttribute("lastTimeLogin", user.getUserLastTimeLogin());
        model.addAttribute("optFlag", user.getUserOtpFlg());
        model.addAttribute("cpId", user.getCpId());
        model.addAttribute("cpName", user.getCpName());
        model.addAttribute("groups", groups);
        model.addAttribute("page", page);
        return "authUserPages_userDetail";
    }

    @RequestMapping(value = "/userDetail", method = RequestMethod.POST)
    public String userDetail(Model model, @RequestParam String username, @RequestParam String fullname,
            @RequestParam String description, @RequestParam String status, @RequestParam String isSuper,
            @RequestParam(required = false) String userType, @RequestParam String address,
            @RequestParam String phone,
            @RequestParam String email, @RequestParam String optFlag, @RequestParam String cpId,
            @RequestParam String cpName, HttpSession session, @RequestParam(required = false) String page,
            HttpServletRequest request) {
        System.out.println("userDetailPost");
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("AUTH_UPDATE") && !roles.contains("AUTH_DETAIL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        System.out.println("userDetail/" + username);
        BigInteger statusInt = new BigInteger(status);
        BigInteger isSuperInt = new BigInteger(isSuper);
        BigInteger userTypeInt = new BigInteger("0");
        if (userType != null) {
            userTypeInt = new BigInteger(userType);
        }
        int result = authUserDAO.updateAuthUser(username, fullname, description, statusInt, isSuperInt, userTypeInt,
                address, phone, email, optFlag);
        String actionResult = "";
        if (result == 0) {
            actionResult = "Cập nhật thất bại";
        } else {
            actionResult = "Cập nhật thành công";
        }
        BigDecimal userIdAffected = authUserDAO.getUserIdByUsername(username);
        actionLogServices.logAction(userSession.getUserId(), "UPDATE_USER", "AUTH_USER", 
                userIdAffected.toBigInteger(), actionResult, "Cập nhật dữ liệu cho người dùng", 
                null, null, request);
        AuthUserModel user = authUserDAO.getUserWithCP(username);
        List<GroupsModel> groups = groupDAO.getGroupByUserId(user.getUserId());

        System.out.println("cp name: " + user.getCpName());

        model.addAttribute("username", user.getUserName());
        model.addAttribute("fullname", user.getUserFullname());
        model.addAttribute("description", user.getUserDesc());
        model.addAttribute("status", user.getUserStatus());
        model.addAttribute("isSuper", user.getUserIsSuper());
        model.addAttribute("userType", user.getUserType());
        model.addAttribute("address", user.getUserAddr());
        model.addAttribute("phone", user.getUserPhone());
        model.addAttribute("email", user.getUserEmail());
        model.addAttribute("createdTime", user.getUserCreatedtime());
        model.addAttribute("updatedTime", user.getUserUpdatedtime());
        model.addAttribute("lastTimeLogin", user.getUserLastTimeLogin());
        model.addAttribute("optFlag", user.getUserOtpFlg());
        model.addAttribute("cpId", user.getCpId());
        model.addAttribute("cpName", user.getCpName());
        model.addAttribute("groups", groups);
        model.addAttribute("page", page);
        return "authUserPages_userDetail";
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.GET)
    public String changePassword(Model model, @RequestParam String username, HttpSession session) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }

        System.out.println("changePassword: username = " + username);
        AuthUserModel user = authUserDAO.findAuthUserByUsername(username);
        model.addAttribute("originOldPassword", user.getUserPass());
        model.addAttribute("username", username);
        return "authUserPages_changePassword";
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public String changePassword(Model model, @RequestParam String username, @RequestParam String newPassword,
            @RequestParam String originOldPassword, @RequestParam String oldPassword, HttpSession session) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }

        System.out.println("originOldPassword: " + originOldPassword);
        String inputOldPw = authUserServices.encryptPasswordByMD5(oldPassword);
        System.out.println("inputOldPw: " + inputOldPw);
        if (inputOldPw.equals(originOldPassword)) {
            String encryptNewPw = authUserServices.encryptPasswordByMD5(newPassword);
            authUserDAO.changePassword(username, encryptNewPw);
            session.removeAttribute("user");
            model.addAttribute("notice", "Change Password Successfully!");
        } else {
            model.addAttribute("notice", "Old Password is not correct. Change Password Failed!");
        }
        return "authUserPages_changePassword";
    }
}
