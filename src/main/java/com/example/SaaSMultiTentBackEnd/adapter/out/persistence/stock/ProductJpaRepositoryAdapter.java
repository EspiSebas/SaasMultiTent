package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.stock;

import com.example.SaaSMultiTentBackEnd.adapter.out.persistence.company.CompanyEntity;
import com.example.SaaSMultiTentBackEnd.adapter.out.persistence.company.JpaCompanyRepository;
import com.example.SaaSMultiTentBackEnd.domain.model.stock.Category;
import com.example.SaaSMultiTentBackEnd.domain.model.stock.Product;
import com.example.SaaSMultiTentBackEnd.domain.port.out.stock.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductJpaRepositoryAdapter implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;
    private final JpaCategoryRepository jpaCategoryRepository;
    private final JpaCompanyRepository jpaCompanyRepository;

    public ProductJpaRepositoryAdapter(JpaProductRepository jpaProductRepository, JpaCategoryRepository jpaCategoryRepository, JpaCompanyRepository jpaCompanyRepository) {
        this.jpaProductRepository = jpaProductRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
        this.jpaCompanyRepository = jpaCompanyRepository;
    }

    @Override
    public Product save(Product product) {
        CategoryEntity categoryEntity = jpaCategoryRepository
                .findById(product.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        CompanyEntity companyEntity = jpaCompanyRepository
                .findById(product.getCompanyId())
                .orElseThrow(()-> new RuntimeException("Company not found"));


        ProductEntity entity = new ProductEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                categoryEntity,
                companyEntity
        );

        ProductEntity saved = jpaProductRepository.save(entity);

        return new Product(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getQuantity(),
                saved.getPrice(),
                new Category(
                        saved.getCategory().getId(),
                        saved.getCategory().getName(),
                        saved.getCategory().getDescription(),
                        saved.getCompany().getId()
                ),
                saved.getCompany().getId()
        );
    }


    @Override
    public List<Product> getALlProduct(Long companyId) {
        return jpaProductRepository.findAllByCompanyId(companyId)
                .stream()
                .map(entity -> new Product(
                        entity.getId(),
                        entity.getName(),
                        entity.getDescription(),
                        entity.getQuantity(),
                        entity.getPrice(),
                        new Category(
                                entity.getCategory().getId(),
                                entity.getCategory().getName(),
                                entity.getCategory().getDescription(),
                                entity.getCompany().getId()
                        ),
                        entity.getCompany().getId()

                ))
                .toList();
    }

    @Override
    public Optional<Product> findByIdAndCompanyId(Long id, Long companyId) {
        return jpaProductRepository.findByIdAndCompanyId(id,companyId)
                .map(entity -> new Product(
                        entity.getId(),
                        entity.getName(),
                        entity.getDescription(),
                        entity.getQuantity(),
                        entity.getPrice(),
                        new Category(
                                entity.getCategory().getId(),
                                entity.getCategory().getName(),
                                entity.getCategory().getDescription(),
                                entity.getCompany().getId()
                        ),
                        entity.getCompany().getId()
                ));

    }

    @Override
    public void delete(Product product) {

        ProductEntity productSelected = jpaProductRepository.findByIdAndCompanyId(product.getId(), product.getCompanyId())
                .orElseThrow(()-> new RuntimeException("Product not found"));
        jpaProductRepository.delete(productSelected);
    }

}
