package com.turkcell.product_service.application.mapper;

import com.turkcell.product_service.application.dto.response.ProductResponse;
import com.turkcell.product_service.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.id().value(),
                product.productName(),
                product.description(),
                product.money().amount(),
                product.money().currency(),
                product.stock()
        );
    }
}
