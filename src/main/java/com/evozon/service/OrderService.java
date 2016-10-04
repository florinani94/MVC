package com.evozon.service;

import com.evozon.dao.OrderDAO;
import com.evozon.domain.Cart;
import com.evozon.domain.Entry;
import com.evozon.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    public Cart getOrderByKey(String orderKey) {
        return orderDAO.getOrderByKey(orderKey);
    }

    public Orders getOrderById(Integer orderId) {
        return orderDAO.getOrderById(orderId);
    }

    public void addOrder(Orders order) {
        orderDAO.addOrder(order);
    }

    public void updateOrder(Orders order) { orderDAO.updateOrder(order); }

    public List<Entry> getAllEntries(Integer orderId) {
        return orderDAO.getAllEntries(orderId);
    }

}
