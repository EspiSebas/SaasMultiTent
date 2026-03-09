package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.stock;

import com.example.SaaSMultiTentBackEnd.adapter.out.persistence.company.CompanyEntity;
import com.example.SaaSMultiTentBackEnd.adapter.out.persistence.company.JpaCompanyRepository;
import com.example.SaaSMultiTentBackEnd.domain.model.stock.Category;
import com.example.SaaSMultiTentBackEnd.domain.port.out.stock.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryJpaRepositoryAdapter implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;
    private final JpaCompanyRepository jpaCompanyRepository;

    public CategoryJpaRepositoryAdapter(JpaCategoryRepository jpaCategoryRepository, JpaCompanyRepository jpaCompanyRepository) {
        this.jpaCategoryRepository = jpaCategoryRepository;
        this.jpaCompanyRepository = jpaCompanyRepository;
    }

    @Override
    public Category save(Category category) {
        CompanyEntity company = jpaCompanyRepository.findById(category.getCompanyId()).orElseThrow(()-> new RuntimeException("Company not found"));
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(null);
        categoryEntity.setName(category.getName());
        categoryEntity.setDescription(category.getDescription());
        categoryEntity.setCompany(company);

        CategoryEntity saved = jpaCategoryRepository.save(categoryEntity);

        return new Category(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getCompany().getId()
        );

    }


    @Override
    public List<Category> getAllCategories(Long companyId) {
        return jpaCategoryRepository.findAllByCompanyId(companyId)
                .stream()
                .map(entity -> new Category(
                        entity.getId(),
                        entity.getName(),
                        entity.getDescription(),
                        entity.getCompany().getId()
                ))
                .toList();
    }


    @Override
    public void delete(Category category) {
        CategoryEntity categorySelected = jpaCategoryRepository.findByIdAndCompanyId(category.getId(),category.getCompanyId()).orElseThrow(()-> new RuntimeException("Category not found"));
        jpaCategoryRepository.delete(categorySelected);

    }

    @Override
    public Optional<Category> findByIdAndCompanyId(Long id, Long companyId) {
        return jpaCategoryRepository.findByIdAndCompanyId(id,companyId)
                .map(entity -> new Category(
                        entity.getId(),
                        entity.getName(),
                        entity.getDescription(),
                        entity.getCompany().getId()
                ));
    }
}
