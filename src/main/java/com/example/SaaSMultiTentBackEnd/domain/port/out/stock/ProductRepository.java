package com.example.SaaSMultiTentBackEnd.domain.port.out.stock;

import com.example.SaaSMultiTentBackEnd.domain.model.stock.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    List<Product> getALlProduct();
    Optional<Product> findById(Long id);
    void delete(Product product);
}
