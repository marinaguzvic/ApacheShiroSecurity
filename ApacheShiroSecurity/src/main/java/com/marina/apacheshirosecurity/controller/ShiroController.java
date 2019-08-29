/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.apacheshirosecurity.controller;

import com.marina.apacheshirosecurity.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author MARINA
 */
@Controller
public class ShiroController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/perform_login", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes attr) {
        try {
            loginService.login(username, password);
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            attr.addFlashAttribute("error", "Invalid Credentials");
            return "redirect:/login";
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin() {
        return "login";
    }

    @RequestMapping(value = "/perform_logout", method = RequestMethod.GET)
    public String logout() {
        loginService.logout();
        return "redirect:/login";
    }
}
