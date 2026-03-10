package com.example.SaaSMultiTentBackEnd.domain.model.stock;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private Category category;
    private Long companyId;

    public Product(Long id, String name, String description, int quantity, BigDecimal price, Category category, Long companyId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public void update(String name, String description, int quantity, BigDecimal price, Category category ) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if(quantity < 0){
            throw new IllegalArgumentException("Quantity is not negative");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }

        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.category = category;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
