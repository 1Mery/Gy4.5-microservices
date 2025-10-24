package com.turkcell.product_service.application.service;

import com.turkcell.product_service.application.dto.response.ProductResponse;
import com.turkcell.product_service.application.exception.ProductNotFoundException;
import com.turkcell.product_service.application.mapper.ProductMapper;
import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.model.ProductId;
import com.turkcell.product_service.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetProductByIdUseCase {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public GetProductByIdUseCase(ProductRepository productRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper=mapper;
    }

    public ProductResponse productListById(UUID id) {
        Product product = productRepository.findById(new ProductId(id))
                .orElseThrow(() -> new ProductNotFoundException("Product not found"+id));
        return mapper.toResponse(product);
    }
}
