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
    public Category createCategory(Long id, String name, String description) {
        Category category = new Category(id,name,description);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getALlCategories();
    }

    @Override
    public Category updateCategory(Long id, String name, String description) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found"));
        category.update(name,description);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
