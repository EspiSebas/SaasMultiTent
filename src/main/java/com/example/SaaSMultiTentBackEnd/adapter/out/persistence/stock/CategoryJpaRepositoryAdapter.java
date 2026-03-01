package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.stock;

import com.example.SaaSMultiTentBackEnd.domain.model.stock.Category;
import com.example.SaaSMultiTentBackEnd.domain.port.out.stock.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryJpaRepositoryAdapter implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;

    public CategoryJpaRepositoryAdapter(JpaCategoryRepository jpaCategoryRepository) {
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    public Category save(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(null);
        categoryEntity.setName(category.getName());
        categoryEntity.setDescription(category.getDescription());

        CategoryEntity saved = jpaCategoryRepository.save(categoryEntity);

        return new Category(
                saved.getId(),
                saved.getName(),
                saved.getDescription()
        );

    }

    @Override
    public List<Category> getALlCategories() {
        return jpaCategoryRepository.findAll()
                .stream()
                .map(entity -> new Category(
                        entity.getId(),
                        entity.getName(),
                        entity.getDescription()
                ))
                .toList();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return jpaCategoryRepository.findById(id)
                .map(entity -> new Category(
                        entity.getId(),
                        entity.getName(),
                        entity.getDescription()
                ));
    }

    @Override
    public void delete(Category category) {
        jpaCategoryRepository.deleteById(category.getId());

    }
}
