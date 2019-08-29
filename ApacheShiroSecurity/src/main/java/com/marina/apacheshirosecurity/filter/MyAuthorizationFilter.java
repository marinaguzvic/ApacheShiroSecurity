/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.apacheshirosecurity.filter;

import com.marina.apacheshirosecurity.audit.ReadAuditInfo;
import com.marina.apacheshirosecurity.database.UrlActionObjectMappingRepository;
import com.marina.apacheshirosecurity.database.model.Account;
import com.marina.apacheshirosecurity.database.model.Privilege;
import com.marina.apacheshirosecurity.database.model.Role;
import com.marina.apacheshirosecurity.database.model.UrlActionObjectMapping;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author MARINA
 */
@Component
public class MyAuthorizationFilter extends AuthorizationFilter {

    @Autowired
    UrlActionObjectMappingRepository urlActionObjectMappingRepository;
    
    
    @Autowired
    public ReadAuditInfo auditInfo;

    @Override
    protected boolean isAccessAllowed(javax.servlet.ServletRequest sr, javax.servlet.ServletResponse sr1, Object o) throws Exception {
        HttpServletRequest hsr = (HttpServletRequest) sr;
        String method = hsr.getMethod();
        String uri = hsr.getRequestURI();
        String contextPath = hsr.getContextPath();
        String path = uri.replaceFirst(contextPath, "");
        Subject subject = SecurityUtils.getSubject();
        Object p = subject.getPrincipal();
        String generalUrl = convertSpecificToGeneralUrl(path);
        UrlActionObjectMapping mapping = urlActionObjectMappingRepository.findByUrlAndMethod(generalUrl, method);
        if (mapping == null) {
            return false;
        }
        Account account = (Account)p;
        if (account == null) {
            return false;
        }
        for (Role role : account.getRoles()) {
            for (Privilege privilege : role.getPrivileges()) {
                if ((privilege.getActionPrivilege().equals(mapping.getActionPrivilege())
                        || privilege.getActionPrivilege().getName().equalsIgnoreCase("ANY_PRIVILEGE"))
                        && (privilege.getObjectPrivilege().equals(mapping.getObjectPrivilege())
                        || privilege.getObjectPrivilege().getName().equalsIgnoreCase("ANY"))) {
                    if (privilege.isIfCreated()) {
                        if (auditInfo.hasCreatedEntity(path, account.getUsername(), mapping.getObjectPrivilege().getName())) {
                            return true;
                        } else {
                            return false;
                        }

                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String convertSpecificToGeneralUrl(String url) {
        String newUrl = url.replaceAll("\\d+", "{id}");
        System.out.println(newUrl);
        return newUrl;
    }
}
