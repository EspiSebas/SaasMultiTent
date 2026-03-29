package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.sale.mapper;

import com.example.SaaSMultiTentBackEnd.adapter.out.persistence.company.CompanyEntity;
import com.example.SaaSMultiTentBackEnd.adapter.out.persistence.sale.entity.DetailSaleEntity;
import com.example.SaaSMultiTentBackEnd.adapter.out.persistence.sale.entity.SaleEntity;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.DetailSale;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.PaymentMethod;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.Sale;

import java.util.List;

public class SalePersistenceMapper {

    public static SaleEntity toEntity(Sale sale) {
        SaleEntity entity = new SaleEntity();

        entity.setId(sale.getId());

        CompanyEntity company = new CompanyEntity();
        company.setId(sale.getCompanyId());
        entity.setCompany(company);

        entity.setDate(sale.getDate());
        entity.setSubtotal(sale.getSubtotal());
        entity.setDiscount(sale.getDiscount());
        entity.setTax(sale.getTax());
        entity.setTotal(sale.getTotal());
        entity.setPaymentMethod(sale.getPaymentMethod());

        List<DetailSaleEntity> details = sale.getDetails().stream()
                .map(d -> {
                    DetailSaleEntity detail = new DetailSaleEntity();
                    detail.setProductId(d.getProductId());
                    detail.setQuantity(d.getQuantity());
                    detail.setUnitPrice(d.getUnitPrice());
                    detail.setTotal(d.getTotal());
                    detail.setSale(entity); // 🔥 relación
                    return detail;
                }).toList();

        entity.setDetails(details);

        return entity;
    }

    public static Sale toDomain(SaleEntity entity) {
        List<DetailSale> details = entity.getDetails().stream()
                .map(d -> new DetailSale(
                        d.getProductId(),
                        d.getQuantity(),
                        d.getUnitPrice(),
                        d.getTotal()
                ))
                .toList();

        return new Sale(
                entity.getId(),
                entity.getCompany().getId(),
                entity.getDate(),
                entity.getDiscount(),
                PaymentMethod.valueOf(String.valueOf(entity.getPaymentMethod())),
                details
        );
    }
}