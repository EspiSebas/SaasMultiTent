package com.example.SaaSMultiTentBackEnd.adapter.in.web.controllers.stock;

import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.stock.CategoryDto;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.stock.CategoryDtoRequest;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.stock.ProductDto;
import com.example.SaaSMultiTentBackEnd.config.security.SecurityUtils;
import com.example.SaaSMultiTentBackEnd.domain.model.stock.Category;
import com.example.SaaSMultiTentBackEnd.domain.port.in.stock.CategoryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/categories")
@Tag(name = "Category")
@SecurityRequirement(name = "BearerAuth")

public class CategoryController {
    private final CategoryUseCase categoryUseCase;

    public CategoryController(CategoryUseCase categoryUseCase) {
        this.categoryUseCase = categoryUseCase;
    }

    @PostMapping("/create")
    @Operation(summary = "Create category")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Category created"),
            @ApiResponse(responseCode = "400", description = "Category error")
    })
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CategoryDtoRequest categoryDtoRequest) {
        Long companyId = SecurityUtils.getCompanyId();
        categoryUseCase.createCategory(
                companyId,
                categoryDtoRequest.getName(),
                categoryDtoRequest.getDescription()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    @Operation(summary = "Get all category")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category got"),
            @ApiResponse(responseCode = "400", description = "Error")
    })
    public List<CategoryDto> getAllCategories() {
        Long companyId = SecurityUtils.getCompanyId();
        return categoryUseCase.getAllCategories(companyId)
                .stream()
                .map(CategoryDto::new)
                .toList();
    }


    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete a category",
            description = "Delete a category by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Category deleted"),
            @ApiResponse(responseCode = "404", description = "Category not deleted")
    })
    public ResponseEntity<Void> deleteCategory(
            @Parameter(description = "ID category", example = "1")
            @PathVariable Long id) {
        Long companyId = SecurityUtils.getCompanyId();
        categoryUseCase.deleteCategory(companyId,id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update a category",
            description = "Update all the information about category"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category updated correctly"),
            @ApiResponse(responseCode = "400", description = "Data invalid"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDtoRequest requestDto) {

        Long companyId = SecurityUtils.getCompanyId();
        Category updatedCategory = categoryUseCase.updateCategory(companyId,id, requestDto.getName(), requestDto.getDescription());

        return ResponseEntity.ok(new CategoryDto(updatedCategory));
    }

}
