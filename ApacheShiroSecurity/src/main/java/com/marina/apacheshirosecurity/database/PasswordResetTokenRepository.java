/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.apacheshirosecurity.database;

import com.marina.apacheshirosecurity.database.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MARINA
 */
@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>{

    public PasswordResetToken findByToken(String token);
    
}
