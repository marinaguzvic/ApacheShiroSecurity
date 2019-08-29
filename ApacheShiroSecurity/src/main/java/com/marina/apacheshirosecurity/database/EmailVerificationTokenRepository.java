/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.apacheshirosecurity.databas;

import com.marina.apacheshirosecurity.database.model.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MARINA
 */
@Repository
public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long>{
    public EmailVerificationToken findByToken(String token);
    public void deleteByAccountId(Long id);
}
