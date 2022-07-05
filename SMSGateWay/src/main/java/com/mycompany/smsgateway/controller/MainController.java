/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.controller;

import com.mycompany.smsgateway.dao.AuthUserDAO;
import com.mycompany.smsgateway.dao.RolesDAO;
import com.mycompany.smsgateway.entities.AuthUser;
import com.mycompany.smsgateway.dao.impl.AuthUserDAOImpl;
import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.model.RolesModel;
import com.mycompany.smsgateway.services.AuthUserServices;
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
public class MainController {

    @Autowired
    private AuthUserDAO authUserDAO;

    @Autowired
    private AuthUserServices authUserServices;

    @Autowired
    private RolesDAO rolesDAO;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage(Model model) {
        return "home";
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logoutPage(Model model, HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/login";
    }

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String dashboard(Model model) {
        return "dashboard";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPage(Model model, @RequestParam String username, @RequestParam String password,
            HttpSession session) {
        String encryptedPw = authUserServices.encryptPasswordByMD5(password);
        AuthUserModel user = authUserDAO.checkLoginUser(username, encryptedPw);
        List<String> roles = rolesDAO.getRoleNamesByUserId(user.getUserId());
        authUserDAO.updateLastTimeLogin(username);
        BigInteger status = authUserDAO.checkActiveUser(username);
        if (user != null && status.equals(new BigInteger("1"))) {
            session.setAttribute("user", user);
            session.setAttribute("roleUser", roles);
            session.setMaxInactiveInterval(7200);
            return "home";
        } else if (user != null && status.equals(new BigInteger("0"))) {
            model.addAttribute("error", "This account is not active!");
        } else {
            model.addAttribute("error", "This account does not existed!");
        }
        model.addAttribute("username", username);
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model) {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPage(Model model, @RequestParam String username, @RequestParam String password,
            @RequestParam String fullname, @RequestParam String description,
            @RequestParam String confirmPassword, @RequestParam String address,
            @RequestParam String phone, @RequestParam String email) {
        String encryptedPw = authUserServices.encryptPasswordByMD5(password);
        AuthUserModel user = authUserDAO.findAuthUserByUsername(username);
        if (user != null) {
            model.addAttribute("notice", "This user_name is already existed!");
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            model.addAttribute("fullname", fullname);
            model.addAttribute("description", description);
            model.addAttribute("address", address);
            model.addAttribute("phone", phone);
            model.addAttribute("email", email);
            model.addAttribute("confirmPassword", confirmPassword);
            return "register";
        } else {
            authUserDAO.addNewAuthUser(username, encryptedPw, fullname, description, address, phone, email);
            return "login";
        }
    }
}
