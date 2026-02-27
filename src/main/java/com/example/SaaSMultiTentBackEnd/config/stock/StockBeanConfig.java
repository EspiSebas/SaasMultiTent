package com.example.SaaSMultiTentBackEnd.config.stock;

import com.example.SaaSMultiTentBackEnd.domain.port.in.stock.CategoryUseCase;
import com.example.SaaSMultiTentBackEnd.domain.port.out.stock.CategoryRepository;
import com.example.SaaSMultiTentBackEnd.domain.service.stock.CategoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockBeanConfig {
    @Bean
    public CategoryUseCase categoryUseCase(CategoryRepository categoryRepository){
        return new CategoryService(categoryRepository);
    }
}
