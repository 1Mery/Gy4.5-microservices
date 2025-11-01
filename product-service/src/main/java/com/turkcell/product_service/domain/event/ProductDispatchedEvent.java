package com.turkcell.product_service.domain.event;

import java.time.Instant;
import java.util.UUID;

public record ProductDispatchedEvent(
        UUID productId,
        Integer quantity,
        UUID orderId,
        Instant occurredAt
) {}
