package com.evozon.dao.impl;

import com.evozon.dao.ProductDAO;
import com.evozon.domain.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public List<Product> getAllProducts() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Product as P").list();
    }

    public List<Product> getParticularProducts(List<Integer> productIds) {
        int i = 0;
        List<Product> products = new ArrayList<>();
        while(i != productIds.size()){
            products.add(getProductById(productIds.get(i)));
            i++;
        }
        return products;
    }

    public void addProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
    }

    public void deleteProduct(int product_id) {
        Session session=sessionFactory.getCurrentSession();
        Query query=session.createQuery("DELETE FROM Product WHERE productId=:id");
        query.setParameter("id", product_id);
        query.executeUpdate();
    }

    public void importFromFile(String filename) {
        Session session=sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("LOAD DATA LOCAL INFILE :filename INTO TABLE product FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' IGNORE 1 LINES (code, description, imageURL, name, price, stockLevel,idCategory)").setString("filename", filename);
        query.executeUpdate();
    }

    public List<Product> getProductsForPage(int startPageIndex, int recordsPerPage){
        int infRange = ((startPageIndex-1 )*recordsPerPage);
        int supRange = recordsPerPage ;

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product as P");

        query.setFirstResult(infRange);
        query.setMaxResults(supRange);
        List<Product> products = query.list();

        return products;
    }

    public Product getProductById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Product as P WHERE P.productId = :id");
        query.setParameter("id", id);
        List<Product> products = query.list();
        if(products.size() > 0){
            return products.get(0);
        }
        return null;
    }

    public Product getProductByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Product as P WHERE P.code = :code");
        query.setParameter("code", code);
        List<Product> products = query.list();
        if(products.size() > 0){
            return products.get(0);
        }
        return null;
    }

    public void updateProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);
    }

    public List<Product> getSortedProducts(String queryCommand, Integer startPageIndex, Integer recordsPerPage,List<Integer> selectedCategoriesIds) {

        Integer infRange = ((startPageIndex-1 )*recordsPerPage);
        Integer supRange = recordsPerPage ;

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(queryCommand);
        if (selectedCategoriesIds.size()!=0) {
            query.setParameterList("categoriesIds", selectedCategoriesIds);
        }
        query.setFirstResult(infRange);
        query.setMaxResults(supRange);
        List<Product> products = query.list();
        return products;
    }

    public List<Product> getProductsByCategory(int categoryId) {
        if (categoryId<=0){
            return getAllProducts();
        }
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Product as P WHERE P.category.id = :categoryId");
        query.setParameter("categoryId", categoryId);
        List<Product> products = query.list();
        return products;
    }

    @Override
    public List<Product> getProductsFilteredByCategories(Integer startPageIndex, int recordsPerPage, List<Integer> categoriesList) {

        Integer infRange = ((startPageIndex-1 )*recordsPerPage);
        Integer supRange = recordsPerPage ;

        List<Product> products=new ArrayList<Product>();
        if(categoriesList.size()!=0) {
            Session session=sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM Product as P WHERE P.category.id in (:categoriesIds)");
            query.setParameterList("categoriesIds", categoriesList);

            query.setFirstResult(infRange);
            query.setMaxResults(supRange);
            products = query.list();

        }
        return products;
    }

    @Override
    public void removeFromStock(int quantity, Product product) {
        Session session=sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Product SET stockLevel = :newStock" + " WHERE productId=:id");
        query.setParameter("newStock", product.getStockLevel()-quantity);
        query.setParameter("id", product.getProductId());
        query.executeUpdate();
    }
}
