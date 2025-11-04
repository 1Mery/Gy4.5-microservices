package com.turkcell.order_service.domain.model;

import java.util.UUID;

public record ProductId(UUID value) {
    public static ProductId from(UUID value) {
        return new ProductId(value);
    }
}
