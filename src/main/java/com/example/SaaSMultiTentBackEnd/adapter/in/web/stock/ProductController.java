package com.example.SaaSMultiTentBackEnd.adapter.in.web.stock;

import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.stock.ProductDto;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.stock.ProductDtoRequest;
import com.example.SaaSMultiTentBackEnd.config.security.SecurityUtils;
import com.example.SaaSMultiTentBackEnd.domain.model.stock.Product;
import com.example.SaaSMultiTentBackEnd.domain.port.in.stock.ProductUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductUseCase productUseCase;

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @GetMapping("/all")
    public List<ProductDto> getAllProducts(){
        Long companyId = SecurityUtils.getCompanyId();
        return productUseCase.getAllProducts(companyId)
                .stream()
                .map(ProductDto::new)
                .toList();
    }


    @PostMapping("/create")
    public ResponseEntity<Void> createProduct(@RequestBody ProductDtoRequest request){

        Long companyId = SecurityUtils.getCompanyId();
        productUseCase.createProduct(
                companyId,
                request.getName(),
                request.getDescription(),
                request.getQuantity(),
                request.getPrice(),
                request.getCategoryId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Long companyId = SecurityUtils.getCompanyId();
        productUseCase.deleteProduct(companyId,id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDtoRequest productDtoRequest
    ){

        Long companyId = SecurityUtils.getCompanyId();
        Product product = productUseCase.updateProduct(
                companyId,
                id,
                productDtoRequest.getName(),
                productDtoRequest.getDescription(),
                productDtoRequest.getQuantity(),
                productDtoRequest.getPrice(),
                productDtoRequest.getCategoryId()
        );

        return ResponseEntity.ok(new ProductDto(product));

    }

}
