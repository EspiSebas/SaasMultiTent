package com.example.SaaSMultiTentBackEnd.domain.service.company;

import com.example.SaaSMultiTentBackEnd.domain.model.company.Company;
import com.example.SaaSMultiTentBackEnd.domain.port.in.company.CompanyUseCase;
import com.example.SaaSMultiTentBackEnd.domain.port.out.company.CompanyRepository;

public class CompanyService implements CompanyUseCase {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public Company createCompany(Long id, String name) {
        Company company = new Company(id,name);
        return companyRepository.save(company);
    }
}
