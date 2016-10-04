package com.evozon.dao;

import com.evozon.domain.User;

/**
 * Created by dianamohanu on 19/07/2016.
 */
public interface UserDAO {
    void addUser(User user);

    boolean checkIfEmailExists(String email);

    boolean checkIfUsernameExists(String username);

    boolean checkKey(String keyUrl);

    User getUserByKey(String keyUrl);

    void activateAccount(String keyUrl);
}
