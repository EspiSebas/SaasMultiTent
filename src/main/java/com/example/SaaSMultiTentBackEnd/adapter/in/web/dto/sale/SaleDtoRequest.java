package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.sale;
import com.example.SaaSMultiTentBackEnd.domain.model.sale.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleDtoRequest {
    @Schema(
            description = "Payment method (CASH, CREDIT_CARD, DEBIT_CARD, TRANSFER)",
            example = "CASH",
            allowableValues = {
                    "CASH",
                    "CARD",
                    "TRANSFER"
            },
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Payment method is required")
    private String paymentMethod;


    @Schema(
            description = "List of products included in the sale",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = "Sale details are required")
    @Valid
    private List<SaleDetailRequest> details;

    @Schema(
            description = "Discount applied to the sale",
            example = "10.00",
            minimum = "0.00"
    )
    @DecimalMin(value = "0.00", message = "Discount cannot be negative")
    private BigDecimal discount;

}
