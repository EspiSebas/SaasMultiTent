package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.company;

import com.example.SaaSMultiTentBackEnd.domain.model.company.Company;
import com.example.SaaSMultiTentBackEnd.domain.port.out.company.CompanyRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyJpaRepositoryAdapter implements CompanyRepository {

    private final JpaCompanyRepository jpaCompanyRepository;

    public CompanyJpaRepositoryAdapter(JpaCompanyRepository jpaCompanyRepository) {
        this.jpaCompanyRepository = jpaCompanyRepository;
    }

    @Override
    public Company save(Company company) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(null);
        companyEntity.setName(companyEntity.getName());

        CompanyEntity saved = jpaCompanyRepository.save(companyEntity);

        return new Company(
                saved.getId(),
                saved.getName()
        );
    }
}
