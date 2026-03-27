package com.example.SaaSMultiTentBackEnd.domain.service.sale;

import com.example.SaaSMultiTentBackEnd.domain.model.sale.DetailSale;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.PaymentMethod;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.Sale;
import com.example.SaaSMultiTentBackEnd.domain.port.in.sale.SaleUseCase;
import com.example.SaaSMultiTentBackEnd.domain.port.out.sale.SaleRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SaleService implements SaleUseCase {
    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }


    @Override
    public Sale createSale(Long companyId, LocalDateTime date, BigDecimal discount, PaymentMethod paymentMethod, List<DetailSale> details) {

return null;
    }

    @Override
    public List<Sale> getAllSales(Long companyId) {

        if (companyId == null) {
            throw new IllegalArgumentException("CompanyId is required");
        }

        return saleRepository.getAllSales(companyId);
    }
}
