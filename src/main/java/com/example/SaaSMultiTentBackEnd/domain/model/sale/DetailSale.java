package com.example.SaaSMultiTentBackEnd.domain.model.sale;

import java.math.BigDecimal;

public class DetailSale {
    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal total;

    public void calculateTotal() {
        this.total = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public DetailSale(Long productId, Integer quantity, BigDecimal unitPrice, BigDecimal total) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
