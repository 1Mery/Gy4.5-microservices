package com.turkcell.order_service.application.dto;

import java.util.List;
import java.util.UUID;

public record OrderRequestDto(
        UUID customerId,
        List<OrderItemDto> items
) {}