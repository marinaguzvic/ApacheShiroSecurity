/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.apacheshirosecurity.realm;

import com.marina.apacheshirosecurity.database.AccountRepository;
import com.marina.apacheshirosecurity.database.model.Account;
import com.marina.apacheshirosecurity.database.model.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author MARINA
 */
@Component
public class AccountJdbcRealm extends JdbcRealm {

    @Autowired
    AccountRepository accountRepository;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken uToken = (UsernamePasswordToken) token;
        if (uToken.getUsername() == null
                || uToken.getUsername().isEmpty()) {
            throw new UnknownAccountException("username not found!");
        }
        Account account = accountRepository.findByUsername(uToken.getUsername());
        
        if(!account.getUsername().equals(uToken.getUsername())){
            throw new UnknownAccountException("username not found!");
        }
        return new SimpleAuthenticationInfo(
                account,
                account.getPassword(),
                getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Set<String> roleNames = new HashSet<>();
        Set<String> permissionNames = new HashSet<>();

        principals.forEach(p -> {
                List<Role> roles = ((Account)p).getRoles();
                roles.forEach(r -> {
                    roleNames.add(r.getName());
                    r.getPrivileges().forEach(prv -> {
                        permissionNames.add(prv.getActionPrivilege().getName() + ":" + prv.getObjectPrivilege());
                    });
                });
        });
        
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roleNames);
        authorizationInfo.setStringPermissions(permissionNames);
        return authorizationInfo;
    }
    
    

}
