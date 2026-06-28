package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.stock;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Request payload for creating a new product category")
public class CategoryDtoRequest {

    @Schema(
            description = "Category name",
            example = "Engine Parts",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(
            description = "Category description",
            example = "Components related to the engine, including pistons, valves, and crankshafts.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Description is required")
    private String description;

}
