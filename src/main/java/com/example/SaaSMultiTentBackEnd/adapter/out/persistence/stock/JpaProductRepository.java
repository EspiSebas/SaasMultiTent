package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaProductRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findAllByCompanyId(Long companyId);

    Optional<ProductEntity> findByIdAndCompanyId(Long id, Long companyId);
}
