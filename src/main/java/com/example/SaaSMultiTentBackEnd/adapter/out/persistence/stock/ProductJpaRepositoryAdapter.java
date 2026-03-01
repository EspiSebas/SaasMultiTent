package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.stock;

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
    public ProductJpaRepositoryAdapter(JpaProductRepository jpaProductRepository, JpaCategoryRepository jpaCategoryRepository) {
        this.jpaProductRepository = jpaProductRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    public Product save(Product product) {
        CategoryEntity categoryEntity = jpaCategoryRepository
                .findById(product.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        ProductEntity entity = new ProductEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                categoryEntity
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
                        saved.getCategory().getDescription()
                )
        );
    }

    @Override
    public List<Product> getALlProduct() {
        return jpaProductRepository.findAll()
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
                                entity.getCategory().getDescription()
                        )

                ))
                .toList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id)
                .map(entity -> new Product(
                        entity.getId(),
                        entity.getName(),
                        entity.getDescription(),
                        entity.getQuantity(),
                        entity.getPrice(),
                        new Category(
                                entity.getCategory().getId(),
                                entity.getCategory().getName(),
                                entity.getCategory().getDescription()
                        )
                ));

    }

    @Override
    public void delete(Product product) {
        jpaProductRepository.deleteById(product.getId());
    }
}
