package com.example.SaaSMultiTentBackEnd.domain.port.in.company;

import com.example.SaaSMultiTentBackEnd.domain.model.company.Company;

public interface CompanyUseCase {
    Company createCompany(Long id,String name);


}
