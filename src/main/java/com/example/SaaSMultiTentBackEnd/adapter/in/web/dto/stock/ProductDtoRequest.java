package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.stock;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtoRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Description is required")
    private String description;
    @NotBlank(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
    @NotBlank(message = "Price is required")
    @Min(value = 1, message = "Price must be at least 1")
    private BigDecimal price;
    @NotBlank(message = "Category is required")
    private  Long categoryId;

}
