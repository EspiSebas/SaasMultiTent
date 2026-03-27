package com.example.SaaSMultiTentBackEnd.domain.port.in.sale;

import com.example.SaaSMultiTentBackEnd.domain.model.sale.DetailSale;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.PaymentMethod;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.Sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface SaleUseCase {
    Sale createSale(Long companyId, LocalDateTime date, BigDecimal subtotal, BigDecimal discount, BigDecimal total, BigDecimal tax, PaymentMethod paymentMethod, List<DetailSale> details);
    List<Sale> getAllSales(Long companyId);


}
