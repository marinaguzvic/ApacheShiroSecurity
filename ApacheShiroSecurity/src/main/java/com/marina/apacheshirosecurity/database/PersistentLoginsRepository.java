/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.apacheshirosecurity.databas;

import com.marina.apacheshirosecurity.database.model.PersistentLogins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MARINA
 */
@Repository
public interface PersistentLoginsRepository extends JpaRepository<PersistentLogins, String>{
    public void deleteByUsername(String username);
}
