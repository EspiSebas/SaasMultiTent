package com.example.SaaSMultiTentBackEnd.domain.port.in.stock;

import com.example.SaaSMultiTentBackEnd.domain.model.stock.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductUseCase {
    Product createProduct(String name, String description, int quantity, BigDecimal price, Long categoryId);
    List<Product> getAllProducts();
    Product updateProduct(Long id,
                          String name,
                          String description,
                          int quantity,
                          BigDecimal price,
                          Long categoryId);

    void deleteProduct(Long id);

    Product getProductById(Long id);
}
