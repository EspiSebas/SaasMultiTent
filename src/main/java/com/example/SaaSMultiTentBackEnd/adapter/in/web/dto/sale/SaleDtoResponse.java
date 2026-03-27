package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleDtoResponse {
    private Long id;
    private String paymentMethod;
    private List<SaleDetailRequest> details;
    private BigDecimal discount;
}
