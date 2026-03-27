package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleDtoRequest {
    private String paymentMethod;
    private List<SaleDetailRequest> details;
    private BigDecimal discount;
}
