package com.example.SaaSMultiTentBackEnd.adapter.in.web.stock;

import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.stock.CategoryDto;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.stock.CategoryDtoRequest;
import com.example.SaaSMultiTentBackEnd.domain.model.stock.Category;
import com.example.SaaSMultiTentBackEnd.domain.port.in.stock.CategoryUseCase;
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
    public String createCategory(CategoryDtoRequest categoryDtoRequest){
        categoryUseCase.createCategory(null,categoryDtoRequest.getName(),categoryDtoRequest.getDescription());
        return "Category created successfully";
    }

    @GetMapping("/all")
    public List<Category> getAllCategories(){
        return categoryUseCase.getAllCategories();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryUseCase.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryDtoRequest requestDto) {

        Category updatedCategory = categoryUseCase.updateCategory(id, requestDto.getName(), requestDto.getDescription());

        return ResponseEntity.ok(updatedCategory);
    }

}
