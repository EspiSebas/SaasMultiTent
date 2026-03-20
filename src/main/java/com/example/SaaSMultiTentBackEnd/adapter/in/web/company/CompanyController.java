package com.example.SaaSMultiTentBackEnd.adapter.in.web.company;

import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.company.CompanyDto;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.company.CompanyDtoRequest;
import com.example.SaaSMultiTentBackEnd.domain.port.in.company.CompanyUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyUseCase companyUseCase;

    public CompanyController(CompanyUseCase companyUseCase) {
        this.companyUseCase = companyUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCompany(@RequestBody CompanyDtoRequest request){
        companyUseCase.createCompany(
                null,
                request.getName()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
