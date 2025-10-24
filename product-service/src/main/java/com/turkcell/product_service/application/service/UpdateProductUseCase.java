package com.turkcell.product_service.application.service;

import com.turkcell.product_service.application.dto.request.UpdateProductRequest;
import com.turkcell.product_service.application.dto.response.ProductResponse;
import com.turkcell.product_service.application.exception.ProductNotFoundException;
import com.turkcell.product_service.application.mapper.ProductMapper;
import com.turkcell.product_service.domain.model.Money;
import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.model.ProductId;
import com.turkcell.product_service.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductUseCase {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public UpdateProductUseCase(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProductResponse updateProduct(ProductId productId, UpdateProductRequest request) {
        //controllerdan gelen id ile
        Product product = repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId.value()));

        product.rename(request.productName());
        product.changePrice(new Money(request.amount(), request.currencyName()));
        product.restock(request.stock());

        Product updatedProduct = repository.save(product);

        return mapper.toResponse(updatedProduct);
    }
}

