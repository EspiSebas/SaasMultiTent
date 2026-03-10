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
    public Product createProduct(Long companyId, String name, String description, int quantity, BigDecimal price, Long categoryId) {
        Category category = categoryRepository.findByIdAndCompanyId(categoryId,companyId).orElseThrow(()-> new RuntimeException("Category not found"));
        Product product = new Product(null,name,description,quantity,price,category,companyId);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts(Long companyId) {
        return productRepository.getALlProduct(companyId);
    }

    @Override
    public Product updateProduct(Long companyId, Long id, String name, String description, int quantity, BigDecimal price, Long categoryId) {
        Category category = categoryRepository.findByIdAndCompanyId(categoryId,companyId).orElseThrow(()-> new RuntimeException("Category not found"));

        Product product = productRepository.findByIdAndCompanyId(categoryId,companyId)
                .orElseThrow(()-> new RuntimeException("Product not found"));

        product.update(name,description,quantity,price,category);

        return productRepository.save(product);

    }

    @Override
    public void deleteProduct(Long companyId, Long id) {
        Product product = productRepository.findByIdAndCompanyId(id,companyId).orElseThrow(()-> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    @Override
    public Product getProductById(Long companyId, Long id) {
        return null;
    }
}
