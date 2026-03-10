package com.example.SaaSMultiTentBackEnd.domain.port.in.stock;

import com.example.SaaSMultiTentBackEnd.domain.model.stock.Category;

import java.util.List;

public interface CategoryUseCase {

    Category createCategory(Long companyId,String name, String description);
    List<Category> getAllCategories(Long companyId);
    Category updateCategory(Long companyId,Long id, String name, String description);
    void deleteCategory(Long companyId,Long id);
    Category getCategoryById(Long companyId,Long id);

}
