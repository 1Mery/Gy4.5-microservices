package com.turkcell.order_service.domain.model;


import java.util.Objects;

public class OrderItem {

    private final ProductId productId;
    private Quantity quantity;
    private  Money unitPrice;
    private  Money lineTotal;  //unitPrice * quantity

    public OrderItem(ProductId productId,
                      Quantity quantity,
                      Money unitPrice,
                      Money lineTotal) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
    }

    public static OrderItem create(ProductId productId,
                                   Quantity quantity,
                                   Money unitPrice) {
        validate(productId, quantity, unitPrice);

        Money lineTotal = unitPrice.multiply(quantity.value());

        return new OrderItem(productId, quantity, unitPrice, lineTotal);
    }

    private static void validate(ProductId productId, Quantity quantity, Money unitPrice) {
        Objects.requireNonNull(productId, "ProductId cannot be null");
        Objects.requireNonNull(quantity, "Quantity cannot be null");
        Objects.requireNonNull(unitPrice, "UnitPrice cannot be null");
    }


    public ProductId productId() {
        return productId;
    }

    public Quantity quantity() {
        return quantity;
    }

    public Money unitPrice() {
        return unitPrice;
    }

    public Money lineTotal() {
        return lineTotal;
    }
}
