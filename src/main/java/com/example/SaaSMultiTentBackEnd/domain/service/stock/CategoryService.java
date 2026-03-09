package com.example.SaaSMultiTentBackEnd.domain.service.stock;

import com.example.SaaSMultiTentBackEnd.domain.model.stock.Category;
import com.example.SaaSMultiTentBackEnd.domain.port.in.stock.CategoryUseCase;
import com.example.SaaSMultiTentBackEnd.domain.port.out.stock.CategoryRepository;

import java.util.List;

public class CategoryService implements CategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category createCategory(Long companyId, String name, String description) {
        Category category = new Category(null,name,description,companyId);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories(Long companyId) {

        if (companyId == null) {
            throw new IllegalArgumentException("CompanyId is required");
        }

        return categoryRepository.getAllCategories(companyId);
    }

    @Override
    public Category updateCategory(Long companyId, Long id, String name, String description) {
       Category category =  categoryRepository
                .findByIdAndCompanyId(id, companyId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

       category.update(name,description);
       return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long companyId, Long id) {
        Category category = categoryRepository
                .findByIdAndCompanyId(id, companyId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }



    @Override
    public Category getCategoryById(Long companyId, Long id) {
        if (companyId == null) {
            throw new IllegalArgumentException("CompanyId is required");
        }

        return categoryRepository
                .findByIdAndCompanyId(id, companyId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
