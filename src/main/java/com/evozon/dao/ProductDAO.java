package com.evozon.dao;

import com.evozon.domain.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> getAllProducts();

    List<Product> getParticularProducts( List<Integer> productIds);

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int productId);

    void importFromFile(String filename);

    Product getProductById(Integer id);

    Product getProductByCode(String code);

    List<Product> getProductsForPage(int startPageIndex, int recordsPerPage);

    List<Product> getSortedProducts(String queryCommand, Integer startPageIndex, Integer recordsPerPage,List<Integer> selectedCategoriesIds);

    List<Product> getProductsByCategory(int categoryId);

    List<Product> getProductsFilteredByCategories(Integer startPageIndex, int maxProductsPerPage, List<Integer> categoriesList);

    void removeFromStock(int quantity, Product product);
}
