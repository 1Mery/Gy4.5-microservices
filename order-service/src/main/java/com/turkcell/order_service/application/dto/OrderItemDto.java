package com.turkcell.order_service.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

//Bir siparişteki tek bir ürünün verisini taşır.

public record OrderItemDto(
        UUID productId,
        int quantity,
        BigDecimal price,
        String currency
) {}
