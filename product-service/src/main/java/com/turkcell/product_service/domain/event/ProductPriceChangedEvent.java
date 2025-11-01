package com.turkcell.product_service.domain.event;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductPriceChangedEvent(
        String productId,
        BigDecimal oldPrice,
        BigDecimal newPrice,
        Instant occurredAt
) {}
