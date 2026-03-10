package com.example.SaaSMultiTentBackEnd.domain.port.in.stock;

import com.example.SaaSMultiTentBackEnd.domain.model.stock.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductUseCase {
    Product createProduct(Long companyId,String name, String description, int quantity, BigDecimal price, Long categoryId);
    List<Product> getAllProducts(Long companyId);
    Product updateProduct(Long companyId,Long id,
                          String name,
                          String description,
                          int quantity,
                          BigDecimal price,
                          Long categoryId);

    void deleteProduct(Long companyId,Long id);

    Product getProductById(Long companyId,Long id);
}
