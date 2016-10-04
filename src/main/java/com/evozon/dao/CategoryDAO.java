package com.evozon.dao;

import com.evozon.domain.Category;

import java.util.List;

public interface CategoryDAO {

    void addCategory(Category category);

    void deleteCategory(int categoryId);

    List<Category> getAllCategories();

    List<Category> getCategoriesWithAtLeastOneProduct();

    Category getCategoryById(Integer id);

    void updateCategory(Category category);
}

