/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.apacheshirosecurity.database.model;

import com.marina.apacheshirosecurity.database.model.ActionPrivilege;
import com.marina.apacheshirosecurity.database.model.ObjectPrivilege;
import com.marina.apacheshirosecurity.database.model.Role;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author MARINA
 */
@Entity
@Table(name = "privilege")
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "action_id", referencedColumnName = "id")
    @ManyToOne
    private ActionPrivilege actionPrivilege;
    @JoinColumn(name = "object_id", referencedColumnName = "id")
    @ManyToOne
    private ObjectPrivilege objectPrivilege;
    @Column(name = "if_created")
    private boolean ifCreated;

    public boolean isIfCreated() {
        return ifCreated;
    }

    public void setIfCreated(boolean ifCreated) {
        this.ifCreated = ifCreated;
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
    
    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    
}
