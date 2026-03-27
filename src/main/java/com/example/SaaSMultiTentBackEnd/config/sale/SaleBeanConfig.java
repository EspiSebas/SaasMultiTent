package com.example.SaaSMultiTentBackEnd.config.sale;

import com.example.SaaSMultiTentBackEnd.domain.port.in.sale.SaleUseCase;
import com.example.SaaSMultiTentBackEnd.domain.port.out.sale.SaleRepository;
import com.example.SaaSMultiTentBackEnd.domain.service.sale.SaleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleBeanConfig {
    @Bean
    public SaleUseCase saleUseCase(SaleRepository saleRepository){
        return new SaleService(saleRepository);
    }
}
