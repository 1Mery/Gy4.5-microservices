package com.turkcell.order_service.domain.model;

import java.time.Instant;
import java.util.List;

public class Order {

    private final OrderId id;

    private final CustomerId customerId;

    private List<OrderItem> items;

    private Money totalAmount;

    private Adress address;

    private OrderStatus status;

    private Instant createdAt;

    public Order(OrderId id,CustomerId customerId, List<OrderItem> items, Money totalAmount, Adress address, OrderStatus status, Instant createdAt) {
        this.id = id;
        this.customerId=customerId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.address = address;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Order create(CustomerId customerId,
                               List<OrderItem> items,
                               Adress address) {

        validateCustomer(customerId);
        validateItems(items);
        validateAddress(address);
        Money totalAmount = calculateTotal(items);

        return new Order(
                OrderId.generate(),
                customerId,
                items,
                totalAmount,
                address,
                OrderStatus.CREATED,
                Instant.now()
        );
    }

    public static Order rehydrate(OrderId id,
                                  CustomerId customerId,
                                  List<OrderItem> items,
                                  Money totalAmount,
                                  Adress address,
                                  OrderStatus status,
                                  Instant createdAt) {
        return new Order(id, customerId, items, totalAmount, address, status, createdAt);
    }


    // Validation
    private static void validateCustomer(CustomerId customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException("CustomerId cannot be null");
        }
    }

    private static void validateItems(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
    }

    private static void validateAddress(Adress address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
    }

    private static Money calculateTotal(List<OrderItem> items) {
        return items.stream()
                .map(OrderItem::lineTotal)
                .reduce(Money::add)
                .orElseThrow(() -> new IllegalArgumentException("Cannot calculate total for empty items"));
    }

    public OrderId id() {
        return id;
    }

    public CustomerId customerId() {
        return customerId;
    }

    public List<OrderItem> items() {
        return items;
    }

    public Money totalAmount() {
        return totalAmount;
    }

    public Adress address() {
        return address;
    }

    public OrderStatus status() {
        return status;
    }

    public Instant createdAt() {
        return createdAt;
    }


}

