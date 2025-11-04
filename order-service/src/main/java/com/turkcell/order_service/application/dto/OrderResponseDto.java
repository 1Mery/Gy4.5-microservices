package com.turkcell.order_service.application.dto;

import com.turkcell.order_service.domain.model.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderResponseDto(
        UUID orderId,
        UUID customerId,
        List<OrderItemDto> items,
        BigDecimal totalAmount,
        String currency,
        OrderStatus status,
        Instant createdAt
) {}
