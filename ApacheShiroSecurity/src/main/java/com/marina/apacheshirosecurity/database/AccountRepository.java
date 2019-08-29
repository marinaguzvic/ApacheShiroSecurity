/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.apacheshirosecurity.database;

import com.marina.apacheshirosecurity.database.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MARINA
 */
@Repository("accountRepository")
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long>{
    public Account findByUsername(String username);
}
