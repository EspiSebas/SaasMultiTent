package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale;

import com.example.SaaSMultiTentBackEnd.domain.model.sale.PaymentMethod;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.Sale;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class SaleDtoResponse {

    private Long id;
    private PaymentMethod paymentMethod;
    private List<SaleDetailRequest> details;
    private BigDecimal discount;
    private BigDecimal total;
    private LocalDate date;

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

        this.total = sale.getDetails()
                .stream()
                .map(detail -> detail.getTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.total = this.total.add(
                this.total.multiply(new BigDecimal("0.19"))
        );
        this.total = this.total.stripTrailingZeros();
        if (this.discount != null) {
            this.total = this.total.subtract(this.discount);
        }

        this.date = sale.getDate();
    }
}