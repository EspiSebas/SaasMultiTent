package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCompanyRepository extends JpaRepository<CompanyEntity,Long> {
    Optional<CompanyEntity> findById(Long id);
}
