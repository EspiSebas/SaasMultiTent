package com.example.SaaSMultiTentBackEnd.domain.port.out.company;

import com.example.SaaSMultiTentBackEnd.domain.model.company.Company;

import java.util.Optional;

public interface CompanyRepository {
    Company save(Company company);
}
