package com.evozon.service;

import com.evozon.dao.UserDAO;
import com.evozon.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dianamohanu on 19/07/2016.
 */
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public void addUser(User user) {
        this.userDAO.addUser(user);
    }

    public boolean checkIfEmailExists(String email) {
        return this.userDAO.checkIfEmailExists(email);
    }

    public boolean checkIfUsernameExists(String username) {
        return this.userDAO.checkIfUsernameExists(username);
    }

    public boolean checkKey(String keyUrl) {
        return this.userDAO.checkKey(keyUrl);
    }

    public void activateAccount(String keyUrl) {
        this.userDAO.activateAccount(keyUrl);
    }
}
