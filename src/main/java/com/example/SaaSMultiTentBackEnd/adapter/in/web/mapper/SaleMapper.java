package com.example.SaaSMultiTentBackEnd.adapter.in.web.mapper;

import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale.SaleDetailRequest;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale.SaleDtoRequest;
import com.example.SaaSMultiTentBackEnd.config.security.SecurityUtils;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.DetailSale;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.PaymentMethod;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.Sale;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class SaleMapper {
    public static Sale toDomain(SaleDtoRequest dto) {
        Long companyId = SecurityUtils.getCompanyId(); // 🔥 desde JWT

        return new Sale(
                null,
                companyId,
                LocalDate.now(),
                dto.getDiscount(),
                mapPaymentMethod(dto.getPaymentMethod()),
                mapDetails(dto.getDetails())
        );
    }

    private static PaymentMethod mapPaymentMethod(String method) {
        return PaymentMethod.valueOf(method.toUpperCase());
    }

    private static List<DetailSale> mapDetails(List<SaleDetailRequest> details) {
        return details.stream()
                .map(d -> new DetailSale(
                        d.getProductId(),
                        d.getQuantity(),
                        d.getUnitPrice(),
                        d.getTotal()
                ))
                .toList();
    }

}
