package com.example.SaaSMultiTentBackEnd.adapter.in.web.controllers.sale;

import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale.SaleDtoRequest;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale.SaleDtoResponse;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.mapper.SaleMapper;
import com.example.SaaSMultiTentBackEnd.config.security.SecurityUtils;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.Sale;
import com.example.SaaSMultiTentBackEnd.domain.port.in.sale.SaleUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleUseCase saleUseCase;

    public SaleController(SaleUseCase saleUseCase) {
        this.saleUseCase = saleUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createSale(@RequestBody SaleDtoRequest request){
        Sale sale = SaleMapper.toDomain(request);
        Long companyId = SecurityUtils.getCompanyId();
        saleUseCase.createSale(
                null,
          companyId,
          sale.getDiscount(),
                sale.getPaymentMethod(),
                sale.getDetails()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


    @GetMapping("/all")
    public List<SaleDtoResponse> getAllSales(){
        Long companyId = SecurityUtils.getCompanyId();
        return saleUseCase.getAllSales(companyId)
                .stream()
                .map(SaleDtoResponse::new)
                .toList();
    }
}
