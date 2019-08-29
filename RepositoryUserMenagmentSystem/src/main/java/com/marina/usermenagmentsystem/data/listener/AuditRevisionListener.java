/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.usermenagmentsystem.data.listener;

import com.marina.apacheshirosecurity.database.model.Account;
import com.marina.usermenagmentsystem.data.model.revision.AuditRevisionEntity;
import org.apache.shiro.SecurityUtils;
import org.hibernate.envers.RevisionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author MARINA
 */
@Component
public class AuditRevisionListener implements RevisionListener{


    @Override
    public void newRevision(Object o) {
        AuditRevisionEntity entity = (AuditRevisionEntity)o;
        Account a = (Account)SecurityUtils.getSubject().getPrincipal();
        entity.setUsername(a.getUsername());
    }
    
}
