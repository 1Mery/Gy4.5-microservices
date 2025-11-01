package com.turkcell.product_service.domain.event;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Saf domain event (çerçeve bağımsız).
 */
public record ProductCreatedEvent(
        String productId,
        String productName,
        String description,
        BigDecimal price,
        Integer stock,
        Instant occurredAt
) {}