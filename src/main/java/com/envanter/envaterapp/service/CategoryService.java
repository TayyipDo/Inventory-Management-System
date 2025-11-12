package com.envanter.envaterapp.service;

import com.envanter.envaterapp.model.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);
    List<Category> getAllCategories();
    boolean categoryExists(String name);
    void deleteCategory(Long id);
    Category getCategoryById(Long id);
}
