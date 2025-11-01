package com.turkcell.product_service.domain.event;

import java.time.Instant;

public record ProductRenamedEvent(
        String productId,
        String oldProductName,
        String newProductName,
        Instant occurredAt
) {}