package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleDetailRequest {
    private Long productId;
    private Integer quantity;
}
