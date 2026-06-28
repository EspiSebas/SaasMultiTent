package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Represents a product included in a sale")
public class SaleDetailRequest {

    @Schema(
            description = "Unique identifier of the product",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Product ID is required")
    private Long productId;

    @Schema(
            description = "Quantity of the product sold",
            example = "2",
            minimum = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @Schema(
            description = "Unit price of the product",
            example = "49.99",
            minimum = "0.01",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Unit price is required")
    @DecimalMin(value = "0.01", message = "Unit price must be greater than 0")
    private BigDecimal unitPrice;

    @Schema(
            description = "Total amount for this sale item (quantity × unit price)",
            example = "99.98",
            minimum = "0.01",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Total is required")
    @DecimalMin(value = "0.01", message = "Total must be greater than 0")
    private BigDecimal total;

    public SaleDetailRequest(Long productId, Integer quantity,
                             BigDecimal unitPrice, BigDecimal total) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
    }
}
