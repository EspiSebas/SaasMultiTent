package com.example.SaaSMultiTentBackEnd.adapter.in.web.stock;

import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.stock.CategoryDto;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.stock.CategoryDtoRequest;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.stock.ProductDto;
import com.example.SaaSMultiTentBackEnd.domain.model.stock.Category;
import com.example.SaaSMultiTentBackEnd.domain.port.in.stock.CategoryUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryUseCase categoryUseCase;

    public CategoryController(CategoryUseCase categoryUseCase) {
        this.categoryUseCase = categoryUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCategory(@RequestParam Long companyId, @RequestBody CategoryDtoRequest categoryDtoRequest) {
        categoryUseCase.createCategory(
                companyId,
                categoryDtoRequest.getName(),
                categoryDtoRequest.getDescription()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public List<CategoryDto> getAllCategories(@RequestParam Long companyId) {
        return categoryUseCase.getAllCategories(companyId)
                .stream()
                .map(CategoryDto::new)
                .toList();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@RequestParam Long companyId,@PathVariable Long id) {
        categoryUseCase.deleteCategory(companyId,id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @RequestParam Long companyId,
            @PathVariable Long id,
            @RequestBody CategoryDtoRequest requestDto) {

        Category updatedCategory = categoryUseCase.updateCategory(companyId,id, requestDto.getName(), requestDto.getDescription());

        return ResponseEntity.ok(new CategoryDto(updatedCategory));
    }

}
