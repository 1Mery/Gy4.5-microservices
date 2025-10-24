package com.turkcell.product_service.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class JpaProductEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name="name", nullable = false, length = 255)
    private String productName;

    @Column(name="description", nullable = false, length = 255)
    private String description;

    @Column(name="amount",nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Column(name="currency", nullable=false)
    private String currency;

    @Column(name="stock",nullable = false)
    private Integer stock;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
