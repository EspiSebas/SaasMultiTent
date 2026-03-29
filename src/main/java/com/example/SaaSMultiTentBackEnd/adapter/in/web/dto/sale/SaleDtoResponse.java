package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale;

import com.example.SaaSMultiTentBackEnd.domain.model.sale.DetailSale;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.PaymentMethod;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.Sale;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleDtoResponse {
    private Long id;
    private PaymentMethod paymentMethod;
    private List<SaleDetailRequest> details;
    private BigDecimal discount;

    public SaleDtoResponse(Sale sale) {
        this.id = sale.getId();
       this.paymentMethod = sale.getPaymentMethod();
        this.discount = sale.getDiscount();
        this.details = sale.getDetails()
                .stream()
                .map(d -> new SaleDetailRequest(
                        d.getProductId(),
                        d.getQuantity(),
                        d.getUnitPrice(),
                        d.getTotal()
                ))
                .toList();
    }
}
