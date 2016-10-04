package com.evozon.dao.impl;

import com.evozon.dao.OrderDAO;
import com.evozon.domain.Cart;
import com.evozon.domain.Entry;
import com.evozon.domain.Orders;
import com.evozon.service.SendMail;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("orderDAO")
@Transactional
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Cart getOrderByKey(String orderKey) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Cart as O WHERE ordersKey = :orderKey");
        query.setParameter("orderKey", orderKey);

        List<Orders> orders = query.list();

        if(orders.size() > 0) {
            return orders.get(0);
        }

        return null;
    }

    public Orders getOrderById(Integer orderId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Cart as O WHERE cartId = :orderId");
        query.setParameter("orderId", orderId);

        List<Orders> orders = query.list();

        if(orders.size() > 0) {
            return orders.get(0);
        }

        return null;
    }

    public void addOrder(Orders order) {
        Session session = sessionFactory.getCurrentSession();
        session.save(order);
    }

    public void updateOrder(Orders order){
        Session session = sessionFactory.getCurrentSession();
        session.update(order);
    }

    public List<Entry> getAllEntries(Integer cartId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Entry as E WHERE E.cart.cartId = :id");
        query.setParameter("id", cartId);

        List<Entry> entryList = query.list();
        return entryList;
    }

}
