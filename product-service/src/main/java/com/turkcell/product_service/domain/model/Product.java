package com.turkcell.product_service.domain.model;

import com.turkcell.product_service.domain.event.DomainEvents;
import com.turkcell.product_service.domain.event.ProductDispatchedEvent;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Product
{
    private final ProductId id;
    private String productName;
    private String description;
    private Money money;
    private Integer stock;

    private Product(ProductId id, String productName, String description, Money money, Integer stock) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.money = money;
        this.stock = stock;
    }

    // Static Factory Method
    public static Product create(String productName, String description, Money money, Integer stock) {
        validateName(productName);
        validateDescription(description);
        Objects.requireNonNull(money, "Money cannot be null");
        validateStock(stock);

        return new Product(ProductId.generate(), productName, description, money, stock);
    }

    // Rehydrate (Persistence)
    public static Product rehydrate(ProductId id, String productName, String description, Money money, Integer stock) {
        return new Product(id, productName, description, money, stock);
    }

    // Domain Behavior - Rename
    public void rename(String productName) {
        validateName(productName);
        this.productName = productName;
    }

    // Domain Behavior - Dispatch (stok düşürme)
    public void dispatch(Integer quantityToDispatch, UUID orderId) {
        if (quantityToDispatch == null || quantityToDispatch <= 0)
            throw new IllegalArgumentException("Quantity to dispatch must be positive.");
        if (this.stock < quantityToDispatch)
            throw new IllegalArgumentException("Insufficient stock.");

        this.stock -= quantityToDispatch;

        ProductDispatchedEvent event = new ProductDispatchedEvent(
                this.id.value(),
                quantityToDispatch,
                orderId,
                Instant.now()
        );

        DomainEvents.raise(event);
    }



    // Domain Behavior - Restock
    public void restock(Integer quantityToRestock) {
        if (quantityToRestock == null || quantityToRestock <= 0)
            throw new IllegalArgumentException("Quantity to restock must be positive.");
        this.stock += quantityToRestock;
    }

    // Domain Behavior - Change Price
    public void changePrice(Money newPrice) {
        Objects.requireNonNull(newPrice, "Price cannot be null");
        this.money = newPrice;
    }

    // Validation Methods
    private static void validateStock(Integer stock) {
        if (stock == null || stock <= 0)
            throw new IllegalArgumentException("Stock cannot be null or negative value");
    }

    private static void validateDescription(String description) {
        if (description == null || description.isEmpty())
            throw new IllegalArgumentException("Description cannot be null or empty");
        if (description.length() >= 255)
            throw new IllegalArgumentException("Description length must be less than 255 characters");
    }

    private static void validateName(String productName) {
        if (productName == null || productName.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (productName.length() >= 255)
            throw new IllegalArgumentException("Name length must be less than 255 characters");
    }

    // Getters
    public ProductId id() {
        return id;
    }

    public String productName() {
        return productName;
    }

    public String description() {
        return description;
    }

    public Money money() {
        return money;
    }

    public Integer stock() {
        return stock;
    }
}
