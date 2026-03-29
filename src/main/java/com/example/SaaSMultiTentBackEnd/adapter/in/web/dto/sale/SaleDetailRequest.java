package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleDetailRequest {
    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal total;

    public SaleDetailRequest(Long productId, Integer quantity, BigDecimal unitPrice, BigDecimal total) {
    this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
    }
}
