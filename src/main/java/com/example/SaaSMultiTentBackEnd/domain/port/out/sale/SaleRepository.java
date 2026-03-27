package com.example.SaaSMultiTentBackEnd.domain.port.out.sale;

import com.example.SaaSMultiTentBackEnd.domain.model.sale.Sale;

import java.util.List;

public interface SaleRepository {
    Sale save(Sale sale);
    List<Sale> getAllSales(Long companyId);
    
}
