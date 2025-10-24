package com.turkcell.product_service.infrastructure.persistence;

import com.turkcell.product_service.domain.model.Money;
import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.model.ProductId;
import org.springframework.stereotype.Component;

@Component
public class JpaProductMapper {

    // --- Mapping: Domain -> Entity ---
    public JpaProductEntity toEntity(Product product){
        JpaProductEntity entity= new JpaProductEntity();
        entity.setId(product.id().value());
        entity.setProductName(product.productName());
        entity.setDescription(product.description());
        entity.setAmount(product.money().amount());
        entity.setCurrency(product.money().currency());
        entity.setStock(product.stock());
        return entity;
    }

    // --- Mapping: Entity -> Domain ---
    public Product toDomain(JpaProductEntity entity){
        return Product.rehydrate(
                new ProductId(entity.getId()),
                entity.getProductName(),
                entity.getDescription(),
                new Money(entity.getAmount(), entity.getCurrency()),
                entity.getStock()
        );
    }

}
