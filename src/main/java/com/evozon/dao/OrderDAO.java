package com.evozon.dao;

import com.evozon.domain.Cart;
import com.evozon.domain.Entry;
import com.evozon.domain.Orders;

import java.util.List;


public interface OrderDAO {

    Cart getOrderByKey(String orderKey);

    Orders getOrderById(Integer orderId);

    void addOrder(Orders order);

    void updateOrder(Orders order);

    List<Entry> getAllEntries(Integer orderId);

}
