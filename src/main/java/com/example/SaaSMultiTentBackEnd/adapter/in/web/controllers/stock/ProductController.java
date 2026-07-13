package com.example.SaaSMultiTentBackEnd.adapter.in.web.controllers.stock;

import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.stock.ProductDto;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.stock.ProductDtoRequest;
import com.example.SaaSMultiTentBackEnd.config.security.SecurityUtils;
import com.example.SaaSMultiTentBackEnd.domain.model.stock.Product;
import com.example.SaaSMultiTentBackEnd.domain.port.in.stock.ProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
@Tag(name = "Product")
@SecurityRequirement(name = "BearerAuth")
public class ProductController {

    private final ProductUseCase productUseCase;

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all products")
    @ApiResponse(responseCode = "200", description = "Products retrieved successfully")
    public ResponseEntity<List<ProductDto>> getAllProducts() {

        Long companyId = SecurityUtils.getCompanyId();

        List<ProductDto> products = productUseCase.getAllProducts(companyId)
                .stream()
                .map(ProductDto::new)
                .toList();

        return ResponseEntity.ok(products);
    }

    @PostMapping("/create")
    @Operation(summary = "Create product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "400", description = "Invalid product data")
    })
    public ResponseEntity<Void> createProduct(
            @Valid @RequestBody ProductDtoRequest request) {

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

    @PutMapping("/update/{id}")
    @Operation(summary = "Update product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product updated"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDtoRequest request) {

        Long companyId = SecurityUtils.getCompanyId();

        Product product = productUseCase.updateProduct(
                companyId,
                id,
                request.getName(),
                request.getDescription(),
                request.getQuantity(),
                request.getPrice(),
                request.getCategoryId()
        );

        return ResponseEntity.ok(new ProductDto(product));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete product")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Product deleted"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        Long companyId = SecurityUtils.getCompanyId();

        productUseCase.deleteProduct(companyId, id);

        return ResponseEntity.noContent().build();
    }
}