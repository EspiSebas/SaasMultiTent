package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaProductRepository extends JpaRepository<ProductEntity,Long> {
    Optional<ProductEntity> findById(Long id);
}
