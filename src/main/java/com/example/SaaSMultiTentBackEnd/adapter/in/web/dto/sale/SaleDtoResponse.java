package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale;

import com.example.SaaSMultiTentBackEnd.domain.model.sale.DetailSale;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.PaymentMethod;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.Sale;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleDtoResponse {
    private Long id;
    private PaymentMethod paymentMethod;
    private List<DetailSale> details;
    private BigDecimal discount;

    public SaleDtoResponse(Sale sale) {
        this.id = sale.getId();
       this.paymentMethod = sale.getPaymentMethod();
        this.discount = sale.getDiscount();
        this.details = sale.getDetails();
    }
}
