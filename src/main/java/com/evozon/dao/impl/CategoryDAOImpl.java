package com.evozon.dao.impl;

import com.evozon.dao.CategoryDAO;
import com.evozon.domain.Category;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by mihai on 7/12/2016.
 */
@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public void addCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.save(category);
    }

    public void deleteCategory(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query=session.createQuery("DELETE FROM Category WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();

    }

    public List<Category> getAllCategories() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Category AS c").list();
    }

    public Category getCategoryById(Integer id){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Category as C WHERE C.id = :id");
        query.setParameter("id", id);
        List<Category> categories = query.list();
        if(categories.size() > 0){
            return categories.get(0);
        }
        return null;
    }
    public void updateCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.update(category);
    }

    public List<Category> getCategoriesWithAtLeastOneProduct(){
        Session session=sessionFactory.getCurrentSession();
        List<Category>categories= session.createQuery("select distinct categ " +
                "from Category categ, Product prod " +
                "where prod.category = categ").list();
        return  categories;
    }

}

