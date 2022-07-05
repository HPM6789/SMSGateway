/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.controller;

import com.mycompany.smsgateway.dao.RolesDAO;
import com.mycompany.smsgateway.model.RolesModel;
import java.util.List;
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
public class RoleController {

    @Autowired
    private RolesDAO rolesDAO;

    @RequestMapping(value = "roleList", method = RequestMethod.GET)
    public String roleList(Model model) {

        System.out.println("roleList");
        List<RolesModel> roles = rolesDAO.getAllRoles();
        model.addAttribute("roles", roles);
        return "rolePages_roleList";
    }

    @RequestMapping(value = "roleList", method = RequestMethod.POST)
    public String roleList(Model model, @RequestParam String roleName) {

        System.out.println("roleListPost");

        List<RolesModel> roles = rolesDAO.getRolesByName(roleName);
        model.addAttribute("roles", roles);
        return "rolePages_roleList";
    }

    @RequestMapping(value = "addRole", method = RequestMethod.GET)
    public String addRole(Model model, @RequestParam String action) {
        System.out.println("addRoleController");
        model.addAttribute("action", action);
        return "rolePages_roleDetail";
    }

    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    public String addRole(Model model, @RequestParam String action, @RequestParam String name,
            @RequestParam String description, @RequestParam String url) {
        System.out.println("addRoleControllerPost");

        RolesModel role = rolesDAO.getRoleByName(name);
        if (role != null) {
            model.addAttribute("notice", "This role is already exited!");
        } else {
            rolesDAO.addNewRole(name, description, url);
            model.addAttribute("notice", "Add Successfully!");
        }
        model.addAttribute("name", name);
        model.addAttribute("description", description);
        model.addAttribute("url", url);
        model.addAttribute("action", action);
        return "rolePages_roleDetail";
    }
    
    @RequestMapping(value = "updateRole", method = RequestMethod.GET)
    public String updateRole(Model model, @RequestParam String action, @RequestParam String id) {
        System.out.println("updateRoleController");
        Long idLong = Long.parseLong(id);
        RolesModel role = rolesDAO.getRolesById(idLong);
        model.addAttribute("action", action);
        model.addAttribute("id", role.getId());
        model.addAttribute("name", role.getName());
        model.addAttribute("oldname", role.getName());
        model.addAttribute("description", role.getDescription());
        model.addAttribute("url", role.getUrl());
        return "rolePages_roleDetail";
    }

    @RequestMapping(value = "updateRole", method = RequestMethod.POST)
    public String updateRole(Model model, @RequestParam String action, @RequestParam String name,
            @RequestParam String description, @RequestParam String url, @RequestParam String id,
            @RequestParam String oldname) {
        System.out.println("updateRoleControllerPost");
        Long idLong = Long.parseLong(id);
        RolesModel role = rolesDAO.getRolesById(idLong);
        if (role != null && !role.getName().equals(oldname)) {
            model.addAttribute("notice", "This role is already exited. Update Failed!");
            model.addAttribute("oldname", oldname);
        } else {
            rolesDAO.updateRole(idLong, name, description, url);
            model.addAttribute("notice", "Update Successfully!");
            model.addAttribute("oldname", name);
        }
        model.addAttribute("name", name);
        model.addAttribute("description", description);
        model.addAttribute("url", url);
        model.addAttribute("action", action);
        return "rolePages_roleDetail";
    }
    
    @RequestMapping(value = "deleteRole", method = RequestMethod.GET)
    public String deleteRole(Model model, @RequestParam String id) {
        
        return "rolePages_roleList";
    }
}
