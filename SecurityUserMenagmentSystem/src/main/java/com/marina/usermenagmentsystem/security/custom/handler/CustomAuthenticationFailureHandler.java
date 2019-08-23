/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.usermenagmentsystem.security.custom.handler;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 *
 * @author MARINA
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException ae) throws IOException, ServletException {
        setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, ae);
    }

    @Override
    public void setDefaultFailureUrl(String defaultFailureUrl) {
        super.setDefaultFailureUrl("/login?error=true"); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
