package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity,Long> {
    Optional<CategoryEntity> findById(Long id);
}
