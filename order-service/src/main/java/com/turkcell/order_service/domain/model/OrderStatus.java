package com.turkcell.order_service.domain.model;

public enum OrderStatus {
    CREATED,
    CONFIRMED, //Ürün stoğu onaylandı
    COMPLETED,
    CANCELLED
}