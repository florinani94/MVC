package com.evozon.dao.impl;

import com.evozon.dao.UserDAO;
import com.evozon.domain.Authority;
import com.evozon.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dianamohanu on 19/07/2016.
 */
@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    public boolean checkIfEmailExists(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User as U WHERE U.email = :email");
        query.setParameter("email", email);
        List<User> products = query.list();

        if (products.size() == 1) {
            return true;
        }

        return false;
    }

    public boolean checkIfUsernameExists(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User as U WHERE U.username = :username");
        query.setParameter("username", username);
        List<User> products = query.list();

        if (products.size() == 1) {
            return true;
        }

        return false;
    }

    public boolean checkKey(String keyUrl) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User as U WHERE U.keyUrl = :keyUrl");
        query.setParameter("keyUrl", keyUrl);
        List<User> products = query.list();

        if (products.size() == 1) {
            return true;
        }

        return false;
    }

    public User getUserByKey(String keyUrl) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User as U WHERE U.keyUrl = :keyUrl");
        query.setParameter("keyUrl", keyUrl);
        List<User> users = query.list();
        if(users.size() > 0){
            return users.get(0);
        }
        return null;
    }

    public void activateAccount(String keyUrl) {
        User user = this.getUserByKey(keyUrl);

        Session session = sessionFactory.getCurrentSession();

        Authority authority = new Authority();
        authority.setUser(user);
        authority.setAuthority("ROLE_ADMIN");
        session.save(authority);

        Query query = session.createQuery("UPDATE User set enabled = :enabled "  +
                "WHERE username = :username");

        query.setParameter("enabled", true);
        query.setParameter("username", user.getUsername());
        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);
    }
}
