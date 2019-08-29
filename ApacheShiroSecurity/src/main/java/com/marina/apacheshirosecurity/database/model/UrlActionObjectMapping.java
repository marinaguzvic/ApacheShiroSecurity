/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.apacheshirosecurity.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author MARINA
 */
@Entity
@Table(name = "url_action_object_mapping")
public class UrlActionObjectMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "action_id", referencedColumnName = "id")
    @ManyToOne
    private ActionPrivilege actionPrivilege;
    @JoinColumn(name = "object_id", referencedColumnName = "id")
    @ManyToOne
    private ObjectPrivilege objectPrivilege;
    @Column(name = "url")
    private String url;
    @Column(name = "method")
    private String method;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActionPrivilege getActionPrivilege() {
        return actionPrivilege;
    }

    public void setActionPrivilege(ActionPrivilege actionPrivilege) {
        this.actionPrivilege = actionPrivilege;
    }

    public ObjectPrivilege getObjectPrivilege() {
        return objectPrivilege;
    }

    public void setObjectPrivilege(ObjectPrivilege objectPrivilege) {
        this.objectPrivilege = objectPrivilege;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    

}
