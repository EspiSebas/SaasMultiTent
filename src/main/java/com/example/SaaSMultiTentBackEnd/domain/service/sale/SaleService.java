package com.example.SaaSMultiTentBackEnd.domain.service.sale;

import com.example.SaaSMultiTentBackEnd.domain.model.sale.DetailSale;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.PaymentMethod;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.Sale;
import com.example.SaaSMultiTentBackEnd.domain.model.stock.Product;
import com.example.SaaSMultiTentBackEnd.domain.model.stock.StatusStock;
import com.example.SaaSMultiTentBackEnd.domain.port.in.sale.SaleUseCase;
import com.example.SaaSMultiTentBackEnd.domain.port.out.sale.SaleRepository;
import com.example.SaaSMultiTentBackEnd.domain.port.out.stock.ProductRepository;
import com.example.SaaSMultiTentBackEnd.exception.BadRequestException;
import com.example.SaaSMultiTentBackEnd.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SaleService implements SaleUseCase {
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public Sale createSale(Long id,Long companyId, BigDecimal discount, PaymentMethod paymentMethod, List<DetailSale> details) {
        for(DetailSale detailSale : details ){

            Product product = productRepository.findByIdAndCompanyId(detailSale.getProductId(),companyId)
                    .orElseThrow(()-> new ResourceNotFoundException("Product not found"));

            if(product.getQuantity() < detailSale.getQuantity()){
                throw new BadRequestException("Not enough stock");
            }

            product.setQuantity(product.getQuantity() - detailSale.getQuantity());
            if(product.getQuantity() - detailSale.getQuantity()>0 && product.getQuantity() - detailSale.getQuantity()<10){
                product.setStatus(StatusStock.BAJO_MINIMO);
            } else if (product.getQuantity() - detailSale.getQuantity()==0) {
                product.setStatus(StatusStock.AGOTADO);
            }else{
                product.setStatus(StatusStock.EN_STOCK);
            }
            productRepository.save(product);

            detailSale.setUnitPrice(product.getPrice());
            detailSale.calculateTotal();

        }
        Sale sale = new Sale(
                null,
                companyId,
                LocalDate.now(),
                discount,
                paymentMethod,
                details
        );

        sale.calculateTotals();

        return saleRepository.save(sale);

    }

    @Override
    public List<Sale> getAllSales(Long companyId) {

        if (companyId == null) {
            throw new IllegalArgumentException("CompanyId is required");
        }

        return saleRepository.getAllSales(companyId);
    }
}
