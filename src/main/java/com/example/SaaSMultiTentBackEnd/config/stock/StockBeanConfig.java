package com.example.SaaSMultiTentBackEnd.config.stock;

import com.example.SaaSMultiTentBackEnd.domain.model.stock.Product;
import com.example.SaaSMultiTentBackEnd.domain.port.in.stock.CategoryUseCase;
import com.example.SaaSMultiTentBackEnd.domain.port.in.stock.ProductUseCase;
import com.example.SaaSMultiTentBackEnd.domain.port.out.stock.CategoryRepository;
import com.example.SaaSMultiTentBackEnd.domain.port.out.stock.ProductRepository;
import com.example.SaaSMultiTentBackEnd.domain.service.stock.CategoryService;
import com.example.SaaSMultiTentBackEnd.domain.service.stock.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockBeanConfig {
    @Bean
    public CategoryUseCase categoryUseCase(CategoryRepository categoryRepository){
        return new CategoryService(categoryRepository);
    }

    @Bean
    public ProductUseCase productUseCase(ProductRepository productRepository,CategoryRepository categoryRepository){
        return new ProductService(productRepository,categoryRepository);
    }
}
