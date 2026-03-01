package com.example.SaaSMultiTentBackEnd.domain.service.stock;

import com.example.SaaSMultiTentBackEnd.domain.model.stock.Category;
import com.example.SaaSMultiTentBackEnd.domain.model.stock.Product;
import com.example.SaaSMultiTentBackEnd.domain.port.in.stock.ProductUseCase;
import com.example.SaaSMultiTentBackEnd.domain.port.out.stock.CategoryRepository;
import com.example.SaaSMultiTentBackEnd.domain.port.out.stock.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

public class ProductService implements ProductUseCase {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(String name, String description, int quantity, BigDecimal price, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product(null,name, description,quantity,price,category);

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getALlProduct();
    }

    @Override
    public Product updateProduct(Long id, String name, String description, int quantity, BigDecimal price, Long categoryId) {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.update(name, description,quantity,price,category);

        return productRepository.save(product);

    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found !!"));
        productRepository.delete(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found !!"));
    }
}
