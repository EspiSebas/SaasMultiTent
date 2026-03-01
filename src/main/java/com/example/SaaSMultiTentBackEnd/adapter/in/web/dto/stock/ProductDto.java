package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.stock;

import com.example.SaaSMultiTentBackEnd.domain.model.stock.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private CategoryDto category;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
        this.category = new CategoryDto(product.getCategory());
    }
}
