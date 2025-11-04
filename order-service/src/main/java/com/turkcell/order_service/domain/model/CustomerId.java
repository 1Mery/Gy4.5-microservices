package com.turkcell.order_service.domain.model;

import java.util.UUID;

public record CustomerId(UUID value) {
    public static CustomerId from(UUID value) {
        return new CustomerId(value);
    }
}
