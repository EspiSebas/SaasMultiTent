package com.example.SaaSMultiTentBackEnd.adapter.in.web.controllers.sale;

import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale.SaleDtoRequest;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale.SaleDtoResponse;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.mapper.SaleMapper;
import com.example.SaaSMultiTentBackEnd.config.security.SecurityUtils;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.Sale;
import com.example.SaaSMultiTentBackEnd.domain.port.in.sale.SaleUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/sales")
@Tag(name = "Sales")
@SecurityRequirement(name = "BearerAuth")

public class SaleController {

    private final SaleUseCase saleUseCase;

    public SaleController(SaleUseCase saleUseCase) {
        this.saleUseCase = saleUseCase;
    }

    @PostMapping("/create")
    @Operation(summary = "Create sale")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sale created"),
            @ApiResponse(responseCode = "400", description = "Sale error")
    })
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
    @Operation(summary = "Get all sales")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sales got"),
            @ApiResponse(responseCode = "400", description = "Error")
    })
    public List<SaleDtoResponse> getAllSales(){
        Long companyId = SecurityUtils.getCompanyId();
        return saleUseCase.getAllSales(companyId)
                .stream()
                .map(SaleDtoResponse::new)
                .toList();
    }
}
