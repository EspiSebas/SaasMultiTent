package com.example.SaaSMultiTentBackEnd.adapter.in.web.sale;

import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale.SaleDtoRequest;
import com.example.SaaSMultiTentBackEnd.config.security.SecurityUtils;
import com.example.SaaSMultiTentBackEnd.domain.port.in.sale.SaleUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        Long companyId = SecurityUtils.getCompanyId();

        saleUseCase.createSale(
                reques
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
