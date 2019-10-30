/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import models.User;

/**
 *
 * @author awarsyle
 */
public class AccountService {
    
    public User login(String email, String password) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        
        if (user == null) {
            return null;
        }
        
        if (!user.getPassword().equals(password)) {
            return null;
        }
        
        return user;
    }
    
}
