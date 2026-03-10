package com.example.SaaSMultiTentBackEnd.config.company;

import com.example.SaaSMultiTentBackEnd.domain.port.in.company.CompanyUseCase;
import com.example.SaaSMultiTentBackEnd.domain.port.out.company.CompanyRepository;
import com.example.SaaSMultiTentBackEnd.domain.service.company.CompanyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompanyBeanConfig {
    @Bean
    public CompanyUseCase companyUseCase(CompanyRepository companyRepository){
        return new CompanyService(companyRepository);
    }

}
