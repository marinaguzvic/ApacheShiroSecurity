/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.usermenagmentsystem.security.config;

import com.marina.usermenagmentsystem.security.authorization.voter.ActionObjectVoter;
import com.marina.usermenagmentsystem.security.custom.handler.CustomAuthenticationFailureHandler;
import com.marina.usermenagmentsystem.security.custom.handler.CustomAuthenticationSuccessHandler;
import com.marina.usermenagmentsystem.security.custom.handler.CustomLogoutSuccessHandler;
import com.marina.usermenagmentsystem.security.service.impl.UserDetailsServiceImp;
import com.marina.usermenagmentsystem.security.token.PersistentLoginsTokenRepositoryImp;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 *
 * @author MARINA
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
//    @Autowired
//    private UserDetailsService userDetailsService;
//    
//    @Autowired
//    @Qualifier("persistentTokenRepository")
//    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    ActionObjectVoter actionObjectVoter;
            
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/login*").permitAll()
                .antMatchers("/registration*").permitAll()
                .antMatchers("/registration/confirm*").permitAll()
                .antMatchers("/registration/resendToken*").permitAll()
                .antMatchers("/resetPassword/request*").permitAll() 
                .antMatchers("/resetPassword*").permitAll() //change permit all with change password privilege
                .anyRequest().authenticated().accessDecisionManager(unanimous())
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
                .loginProcessingUrl("/perform_login")
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService())
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler());
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
    
    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImp();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        
        return new PersistentLoginsTokenRepositoryImp();
    }
    
    @Bean
    public AccessDecisionManager unanimous(){
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>();
        decisionVoters.add(new RoleVoter());
        decisionVoters.add(new AuthenticatedVoter());
        decisionVoters.add(new WebExpressionVoter());
        decisionVoters.add(actionObjectVoter);
        return new UnanimousBased(decisionVoters);
    }
}
