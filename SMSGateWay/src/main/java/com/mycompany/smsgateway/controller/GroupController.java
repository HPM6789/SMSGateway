/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.controller;

import com.mycompany.smsgateway.dao.AuthUserDAO;
import com.mycompany.smsgateway.dao.GroupDAO;
import com.mycompany.smsgateway.dao.RolesDAO;
import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.model.GroupsModel;
import com.mycompany.smsgateway.model.RolesModel;
import com.mycompany.smsgateway.services.ActionLogServices;
import com.mycompany.smsgateway.services.Paging;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
public class GroupController {

    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    private AuthUserDAO authUserDAO;

    @Autowired
    private RolesDAO rolesDAO;

    @Autowired
    private Paging paging;

    @Autowired
    private ActionLogServices actionLogServices;

    @RequestMapping(value = "groupList", method = RequestMethod.GET)
    public String groupList(Model model, HttpSession session, @RequestParam(required = false) String page,
            @RequestParam String action, @RequestParam(required = false) String notice) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("GROUP_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        int numPerPage = 5;
        List<GroupsModel> groups = groupDAO.getAllGroups();
        int totalItem = groups.size();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        int end = Math.min(pageInt * numPerPage, totalItem);
        List<GroupsModel> groupPage = paging.groupPaging(start, end, groups);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("groups", groupPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("action", action);
        model.addAttribute("notice", notice);
        return "groupPages_groupList";
    }

    @RequestMapping(value = "searchGroup", method = RequestMethod.GET)
    public String groupList(Model model, String groupName, HttpSession session,
            @RequestParam(required = false) String page, @RequestParam String action) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("GROUP_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pageInt = Integer.parseInt(page);
        int numPerPage = 10;
        List<GroupsModel> groups = groupDAO.getGroupsByName(groupName);
        int totalItem = groups.size();
        int endPage = totalItem / numPerPage;
        if (totalItem % numPerPage != 0) {
            endPage++;
        }
        int start = (pageInt - 1) * numPerPage;
        int end = Math.min(pageInt * numPerPage, totalItem);
        List<GroupsModel> groupPage = paging.groupPaging(start, end, groups);
        int[] startEnd = paging.pageRange(pageInt, endPage);
        model.addAttribute("groups", groupPage);
        model.addAttribute("groupName", groupName);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("startDisplayPage", startEnd[0]);
        model.addAttribute("endDisplayPage", startEnd[1]);
        model.addAttribute("action", action);
        return "groupPages_groupList";
    }

    @RequestMapping(value = "addGroup", method = RequestMethod.GET)
    public String addGroup(Model model, @RequestParam String action, HttpSession session) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("GROUP_LIST")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        model.addAttribute("action", action);
        return "groupPages_groupDetail";
    }

    @RequestMapping(value = "addGroup", method = RequestMethod.POST)
    public String addGroup(Model model, @RequestParam String action, @RequestParam String name,
            @RequestParam String description, HttpSession session, HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("GROUP_INSERT")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        String notice = "";
        GroupsModel group = groupDAO.getGroupByName(name);
        if (group == null) {
            int result = groupDAO.addNewGroup(name, description);
            String actionResult;
            if (result == 0) {
                actionResult = "Thất bại";
            } else {
                actionResult = "Thành Công";
            }
            notice = "Add Group Successfully!";
            BigInteger ObjectId = BigInteger.valueOf(groupDAO.getNewestGrId());
            actionLogServices.logAction(userSession.getUserId(), "INSERT_GROUP",
                    "GROUPS", ObjectId, actionResult, "Thêm Nhóm", null, null, request);
            return "redirect:groupList?action=list&notice=" + notice;
        } else {
            actionLogServices.logAction(userSession.getUserId(), "INSERT_GROUP",
                    "GROUPS", BigInteger.ZERO, "Thất bại", "Thêm Nhóm", null, null, request);
            model.addAttribute("notice", "Add Group Failed!");
        }
        model.addAttribute("action", action);
        model.addAttribute("name", name);
        model.addAttribute("description", description);
        return "groupPages_groupDetail";
    }

    @RequestMapping(value = "updateGroup", method = RequestMethod.GET)
    public String updateGroup(Model model, @RequestParam String action, @RequestParam String id, HttpSession session) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> rolesSession = (List<String>) session.getAttribute("roleUser");
        if (!rolesSession.contains("GROUP_UPDATE") || !rolesSession.contains("GROUP_DETAIL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        Long idLong = Long.parseLong(id);
        GroupsModel group = groupDAO.getGroupById(idLong);
        List<AuthUserModel> users = authUserDAO.getAllUser();
        List<AuthUserModel> usersInGr = authUserDAO.getUsersByGroup(idLong);
        List<String> usernames = authUserDAO.getUserNamesByGroup(idLong);
        List<RolesModel> roles = rolesDAO.getAllRoles();
        List<String> rolenames = rolesDAO.getRoleNamesByGroupId(idLong);
        model.addAttribute("roles", roles);
        model.addAttribute("rolenames", rolenames);
        model.addAttribute("action", action);
        model.addAttribute("id", group.getId());
        model.addAttribute("name", group.getName());
        model.addAttribute("oldname", group.getName());
        model.addAttribute("description", group.getDescription());
        model.addAttribute("users", users);
        model.addAttribute("usersInGr", usersInGr);
        model.addAttribute("usernames", usernames);
//        model.addAttribute("usersNotInGr", usersNotInGr);
        return "groupPages_groupDetail";
    }

    @RequestMapping(value = "updateGroup", method = RequestMethod.POST)
    public String updateGroup(Model model, @RequestParam String action, @RequestParam String name,
            @RequestParam String description, @RequestParam String id, @RequestParam String oldname,
            HttpSession session, HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("GROUP_UPDATE") || !roles.contains("GROUP_DETAIL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        Long idLong = Long.parseLong(id);
        GroupsModel group = groupDAO.getGroupById(idLong);
        List<AuthUserModel> users = authUserDAO.getUsersByGroup(idLong);
        BigInteger ObjectId = BigInteger.valueOf(idLong);
        if (group != null && !group.getName().equals(oldname)) {
            actionLogServices.logAction(userSession.getUserId(), "UPDATE_GROUP",
                    "GROUPS", ObjectId, "Thất bại", "Thêm Nhóm", null, null, request);
            model.addAttribute("notice", "Update Failed!");
            model.addAttribute("oldname", oldname);
        } else {
            int result = groupDAO.updateGroup(idLong, name, description);
            String actionResult = "";
            if (result == 0) {
                actionResult = "Thất bại";
            } else {
                actionResult = "Thành Công";
            }
            actionLogServices.logAction(userSession.getUserId(), "UPDATE_GROUP",
                    "GROUPS", ObjectId, actionResult, "Cập nhật Nhóm", null, null, request);
            model.addAttribute("notice", "Update Successfully!");
            model.addAttribute("oldname", name);
        }
        model.addAttribute("action", action);
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("description", description);
        model.addAttribute("users", users);
        return "groupPages_groupDetail";
    }

    @RequestMapping(value = "deleteGroup", method = RequestMethod.GET)
    public String deleteGroup(Model model, @RequestParam String groupId, HttpSession session,
            HttpServletRequest request) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("GROUP_UPDATE") || !roles.contains("GROUP_DETAIL")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        Long idLong = Long.parseLong(groupId);
        groupDAO.deleteGroupRoleByGroupId(idLong);
        groupDAO.deleteGroupUserByGroupId(idLong);
        int result = groupDAO.deleteGroup(idLong);
        String actionResult = "";
        if (result == 0) {
            actionResult = "Thất bại";
        } else {
            actionResult = "Thành Công";
        }
        actionLogServices.logAction(userSession.getUserId(), "DELETE_GROUP",
                    "GROUPS", BigInteger.valueOf(idLong), actionResult, "Xóa Nhóm", null, null, request);
        return "redirect:groupList?action=list";
    }
    
    @RequestMapping(value = "updateMemberForGroup", method = RequestMethod.GET)
    public String updateMemberForGroup(Model model, HttpSession session, @RequestParam String groupId,
            @RequestParam String page) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("GROUP_UPDATE")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        Long groupIdLong = Long.parseLong(groupId);
        List<AuthUserModel> users = authUserDAO.getAllUser();
        List<String> usernamesInGr = authUserDAO.getUserNamesByGroup(groupIdLong);
        model.addAttribute("users", users);
        model.addAttribute("usernamesInGr", usernamesInGr);
        model.addAttribute("groupId", groupId);
        model.addAttribute("page", page);
        return "groupPages_updateMemberForGroup";
    }

    @RequestMapping(value = "updateMemberForGroup", method = RequestMethod.POST)
    public String updateMemberForGroup(Model model, @RequestParam(required = false) List<String> membersId,
            @RequestParam String groupId, HttpSession session, HttpServletRequest request,
            @RequestParam String page) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> roles = (List<String>) session.getAttribute("roleUser");
        if (!roles.contains("GROUP_UPDATE")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        Long groupIdLong = Long.parseLong(groupId);
        List<BigDecimal> userIdInGr = authUserDAO.getUserIdsByGroup(groupIdLong);
        if (userIdInGr != null && !userIdInGr.isEmpty()) {
            groupDAO.removeMemberForGroup(groupIdLong, userIdInGr);
        }
        int result = -1;
        List<BigDecimal> membersIdList = new ArrayList<>();
        if (membersId != null && !membersId.isEmpty()) {
            for (int i = 0; i < membersId.size(); i++) {
                membersIdList.add(new BigDecimal(membersId.get(i)));
                System.out.println(membersIdList.get(i));
            }
            result = groupDAO.addMemberForGroup(groupIdLong, membersIdList);
        }
        String actionResult = "";
        if (result == -1) {
            actionResult = "Thất bại";
        } else {
            actionResult = "Thành Công";
        }
        actionLogServices.logAction(userSession.getUserId(), "UPDATE_MEMBERS_FOR_GROUP",
                    "GROUPS", BigInteger.valueOf(groupIdLong), actionResult, "Cập nhật thành viên chon nhóm",
                    null, null, request);
        List<AuthUserModel> users = authUserDAO.getAllUser();
        List<String> usernamesInGr = authUserDAO.getUserNamesByGroup(groupIdLong);
        model.addAttribute("users", users);
        model.addAttribute("usernamesInGr", usernamesInGr);
        model.addAttribute("groupId", groupId);
        model.addAttribute("page", page);
        return "groupPages_updateMemberForGroup";
    }

    @RequestMapping(value = "updateRoleForGroup", method = RequestMethod.GET)
    public String updateRoleForGroup(Model model, HttpSession session, @RequestParam String groupId,
            @RequestParam String page) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> rolesSession = (List<String>) session.getAttribute("roleUser");
        if (!rolesSession.contains("GROUP_UPDATE")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        Long groupIdLong = Long.parseLong(groupId);
        List<RolesModel> roles = rolesDAO.getAllRoles();
        List<String> rolenames = rolesDAO.getRoleNamesByGroupId(groupIdLong);
        model.addAttribute("roles", roles);
        model.addAttribute("rolenames", rolenames);
        model.addAttribute("groupId", groupId);
        model.addAttribute("page", page);
        return "groupPages_updateRoleForGroup";
    }
    
    @RequestMapping(value = "updateRoleForGroup", method = RequestMethod.POST)
    public String updateRoleForGroup(Model model, HttpSession session, @RequestParam String groupId,
            @RequestParam(required = false) List<String> rolesId, HttpServletRequest request,
            @RequestParam String page) {
        AuthUserModel userSession = (AuthUserModel) session.getAttribute("user");
        if (userSession == null) {
            return "login";
        }
        List<String> rolesSession = (List<String>) session.getAttribute("roleUser");
        if (!rolesSession.contains("GROUP_UPDATE")) {
            model.addAttribute("message", "This page is protected!");
            return "accessDeniedPage";
        }
        Long groupIdLong = Long.parseLong(groupId);
        List<Long> rolesIdInGr = rolesDAO.getRoleIdsByGroupId(groupIdLong);
        if (rolesIdInGr != null && !rolesIdInGr.isEmpty()) {
            groupDAO.removeRoleForGroup(groupIdLong, rolesIdInGr);
        }
        int result = -1;
        if (rolesId != null && !rolesId.isEmpty()) {
            List<Long> rolesIdLong = new ArrayList<>();
            for (String roleId : rolesId) {
                rolesIdLong.add(new Long(roleId));
            }
            result = groupDAO.addRoleForGroup(groupIdLong, rolesIdLong);
        }
        String actionResult = "";
        if (result == -1) {
            actionResult = "Thất bại";
        } else {
            actionResult = "Thành Công";
        }
        actionLogServices.logAction(userSession.getUserId(), "UPDATE_ROLES_FOR_GROUP",
                    "GROUPS", BigInteger.valueOf(groupIdLong), actionResult, "Cập nhật quyền chon nhóm",
                    null, null, request);
        List<RolesModel> roles = rolesDAO.getAllRoles();
        List<String> rolenames = rolesDAO.getRoleNamesByGroupId(groupIdLong);
        model.addAttribute("roles", roles);
        model.addAttribute("rolenames", rolenames);
        model.addAttribute("groupId", groupId);
        model.addAttribute("page", page);
        return "groupPages_updateRoleForGroup";
    }
}
