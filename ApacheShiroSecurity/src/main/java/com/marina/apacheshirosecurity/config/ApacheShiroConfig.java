/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.apacheshirosecurity.config;

import com.marina.apacheshirosecurity.filter.MyAuthorizationFilter;
import com.marina.apacheshirosecurity.password.service.BCryptPasswordService;
import com.marina.apacheshirosecurity.realm.AccountJdbcRealm;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.apache.shiro.spring.config.ShiroBeanConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroWebConfiguration;
import org.apache.shiro.spring.web.config.ShiroWebFilterConfiguration;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

/**
 *
 * @author MARINA
 */
@Configuration
@PropertySource("classpath:application.properties")
@Import({ShiroBeanConfiguration.class,
    ShiroAnnotationProcessorConfiguration.class,
    ShiroWebConfiguration.class,
    ShiroWebFilterConfiguration.class})
@ComponentScan(value = {"com.marina.apacheshirosecurity"})
public class ApacheShiroConfig {


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//        chainDefinition.addPathDefinition("/documents", "authc, perms[READ_PRIVILEGE:DOCUMENTS]");
        // logged in users with the 'admin' role
//        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");

        // logged in users with the 'document:read' permission
//        chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");

        // all other paths require a logged in user
        chainDefinition.addPathDefinition("/perform_login", "anon");
        chainDefinition.addPathDefinition("/login", "anon");
        chainDefinition.addPathDefinition("/accessDenied", "anon");
        chainDefinition.addPathDefinition("/**", "authc, myFilter");
        return chainDefinition;
    }

    @Bean
    public Realm realm() {
        JdbcRealm realm = new AccountJdbcRealm();
        PasswordMatcher passwordMatcher = new PasswordMatcher();
        passwordMatcher.setPasswordService(new BCryptPasswordService());
        realm.setCredentialsMatcher(passwordMatcher);
        return realm;
    }

    @Bean
    protected CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }
    
    @Bean 
    public AuthorizationFilter myFilter(){
        return new MyAuthorizationFilter();
    }
}
