/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.apacheshirosecurity.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 *
 * @author MARINA
 */
@Service
public class LoginService {

    public void login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(
                    username, password, false);
            try {
                subject.login(token);
            } catch (AuthenticationException ae) {
                throw ae;
            }
        }
    }

    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

}
