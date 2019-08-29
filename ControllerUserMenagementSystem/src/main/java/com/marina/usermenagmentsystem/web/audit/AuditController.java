/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.usermenagmentsystem.web.audit;

import com.marina.apacheshirosecurity.audit.ReadAuditInfo;
import com.marina.usermenagmentsystem.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author MARINA
 */
@Component
public class AuditController implements ReadAuditInfo{
    
    @Autowired
    public AuditService auditService;

    public boolean hasCreatedEntity(String url, String username, String entityType) {
        return auditService.checkIfUserHasCreatedEntity(url, username, entityType);
    }
    
}
