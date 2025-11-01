package com.turkcell.order_service.domain.model;

public record Quantity(int value) {
    public Quantity {
        if (value <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }
    //Aynı ürünün miktarlarını toplar
    public Quantity add(Quantity other) {
        return new Quantity(this.value + other.value);
    }
    //Stok veya sipariş miktarını azaltır
    public Quantity subtract(Quantity other) {
        if (this.value - other.value <= 0) {
            throw new IllegalArgumentException("Resulting quantity must be positive");
        }
        return new Quantity(this.value - other.value);
    }
}
