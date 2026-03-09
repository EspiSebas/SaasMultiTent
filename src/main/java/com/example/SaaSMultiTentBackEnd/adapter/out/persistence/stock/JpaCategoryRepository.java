package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity,Long> {
    List<CategoryEntity> findAllByCompanyId(Long companyId);

    Optional<CategoryEntity> findByIdAndCompanyId(Long id, Long companyId);
}
