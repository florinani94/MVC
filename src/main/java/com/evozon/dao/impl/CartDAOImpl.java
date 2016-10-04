package com.evozon.dao.impl;

import com.evozon.dao.CartDAO;
import com.evozon.domain.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository("CartDAO")
@Transactional
public class CartDAOImpl implements CartDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Entry getEntryById(Integer entryId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Entry as E WHERE E.entryId = :id");
        query.setParameter("id", entryId);
        List<Entry> entries = query.list();
        if(entries.size() > 0){
            return entries.get(0);
        }
        return null;
    }
    @Override
    public Cart getCartById(Integer cartId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Cart as C WHERE C.cartId = :id");
        query.setParameter("id", cartId);
        List<Cart> carts = query.list();
        if(carts.size() > 0){
            return carts.get(0);
        }
        return null;
    }
    @Override
    public void addCart(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        session.save(cart);
    }

    @Override
    public void updateCart(Cart cart){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cart);
    }

    @Override
    public List<Cart> getAllCarts(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Cart as C").list();
    }

    @Override
    public void deleteCart(Integer cartId) {
        Session session = sessionFactory.getCurrentSession();
        Query query=session.createQuery("DELETE FROM Cart as C WHERE C.cartId=:id");
        query.setParameter("id", cartId);
        query.executeUpdate();
    }


    @Override
    public void updateEntryDetails(Entry entry){
        Session session = sessionFactory.getCurrentSession();
        Product product=entry.getProduct();
        entry.setProductCode(product.getCode());
        entry.setProductName(product.getName());
        entry.setProductPrice(product.getPrice());
        session.update(entry);
    }

    @Override
    public Entry addEntryToCart(Product product,Cart cart){
        Session session = sessionFactory.getCurrentSession();
        Entry entry=new Entry(cart,product,0,0.0);
        session.save(entry);
        return entry;
    }

    @Override
    public void deleteEntryFromCart(Integer entryId) {
        Session session = sessionFactory.getCurrentSession();
        Query query=session.createQuery("DELETE FROM Entry as E WHERE E.entryId=:id");
        query.setParameter("id", entryId);
        query.executeUpdate();
    }

    @Override
    public List<Entry> getEntryForAdding(Integer productId, Integer cartId) {
        Session session = sessionFactory.getCurrentSession();
        Query query=session.createQuery("SELECT E FROM Entry as E, Product as P, Cart as C WHERE E.cart=C.cartId AND P.productId=E.product AND C.cartId=:id AND P.productId=:prodId");
        query.setParameter("id", cartId);
        query.setParameter("prodId", productId);
        List<Entry> entryList=query.list();
        return entryList;
    }

    @Override
    public List<Entry> getAllEntriesFromCart(Integer cartId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Entry as E WHERE E.cart.id=:id");
        query.setParameter("id", cartId);
        List entryList = query.list();
        return entryList;
    }

    @Override
    public void updateSubTotalForEntry(Double value, Integer entryId,Integer cartId){
        Session session = sessionFactory.getCurrentSession();
        Query query=session.createQuery("FROM Entry as E WHERE E.entryId=:id");
        query.setParameter("id", entryId);
        List<Entry> entries=query.list();

        if(entries.size()>0){
            entries.get(0).setSubTotal(value);
        }
        session.saveOrUpdate(entries.get(0));

    }

    @Override
    public Double computeSubTotalForEntry(Integer entryId,Integer cartId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT E FROM Entry as E, Product as P,Cart as C WHERE E.entryId=:id AND C.cartId=:cId AND E.cart=C.cartId");
        query.setParameter("id", entryId);
        query.setParameter("cId", cartId);

        ArrayList<Entry> result = (ArrayList)query.list();

        return result.get(0).getQuantity()*result.get(0).getProductPrice();
    }

    @Override
    public void computeTotalForCart(Integer cartId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Cart as C WHERE C.cartId = :id");
        query.setParameter("id", cartId);
        List<Cart> carts = query.list();

        if(carts.size() > 0){
            Double total=0.0;
            for(Entry e:carts.get(0).getEntryList()){
                total+=e.getSubTotal();

            }
            carts.get(0).setTotal(total);
        }
        session.saveOrUpdate(carts.get(0));
    }

    @Override
    public void updateEntry(Entry entry) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entry);
    }

    @Override
    public void updateAddress(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        session.update(cart);
    }
}
