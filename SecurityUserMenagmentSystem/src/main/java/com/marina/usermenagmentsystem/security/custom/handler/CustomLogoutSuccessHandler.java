/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.usermenagmentsystem.security.custom.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author MARINA
 */
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    public CustomLogoutSuccessHandler() {
        super();
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final String refererUrl = request.getHeader("Referer");
        setDefaultTargetUrl("/login");
        super.onLogoutSuccess(request, response, authentication);
    }
    
    
}
