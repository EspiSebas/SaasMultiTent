package com.example.SaaSMultiTentBackEnd.domain.port.in.stock;

import com.example.SaaSMultiTentBackEnd.domain.model.stock.Category;

import java.util.List;

public interface CategoryUseCase {

    Category createCategory(Long id,String name, String description);
    List<Category> getAllCategories();
    Category updateCategory(Long id, String name, String description);
    void deleteCategory(Long id);
    Category getCategoryById(Long id);

}
