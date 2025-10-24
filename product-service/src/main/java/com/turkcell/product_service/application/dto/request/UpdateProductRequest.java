package com.turkcell.product_service.application.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateProductRequest(
        @NotBlank String productName,
        @NotBlank String description,
        @NotNull @DecimalMin("0.0") BigDecimal amount,
        @NotBlank String currencyName,
        @Min(0) int stock
) {}
