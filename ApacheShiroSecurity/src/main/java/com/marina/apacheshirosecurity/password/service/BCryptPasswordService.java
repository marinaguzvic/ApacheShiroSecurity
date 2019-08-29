/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marina.apacheshirosecurity.password.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.shiro.authc.credential.PasswordService;

/**
 *
 * @author MARINA
 */
public class BCryptPasswordService implements PasswordService{

    @Override
    public String encryptPassword(Object o) throws IllegalArgumentException {
        return BCrypt.withDefaults().hashToString(12, ((String)o).toCharArray());
    }

    @Override
    public boolean passwordsMatch(Object o, String string) {
        return BCrypt.verifyer().verify((char [])o, string).verified;
    }
    
}
