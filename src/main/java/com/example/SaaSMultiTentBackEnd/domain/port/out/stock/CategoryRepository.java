package com.example.SaaSMultiTentBackEnd.domain.port.out.stock;

import com.example.SaaSMultiTentBackEnd.domain.model.stock.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
   Category save(Category category);
   List<Category> getAllCategories(Long companyId);
   void delete(Category category);
   Optional<Category> findByIdAndCompanyId(Long id, Long companyId);
}
