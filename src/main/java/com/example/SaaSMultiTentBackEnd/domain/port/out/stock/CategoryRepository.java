package com.example.SaaSMultiTentBackEnd.domain.port.out.stock;

import com.example.SaaSMultiTentBackEnd.domain.model.stock.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
   Category save(Category category);
   List<Category> getALlCategories();
   Optional<Category> findById(Long id);
   void delete(Category category);
}
