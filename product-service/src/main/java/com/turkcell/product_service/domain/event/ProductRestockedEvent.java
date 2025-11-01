package com.turkcell.product_service.domain.event;

import java.time.Instant;

public record ProductRestockedEvent(
        String productId,
        Integer oldStock,
        Integer newStock,
        Instant occurredAt
) {}
