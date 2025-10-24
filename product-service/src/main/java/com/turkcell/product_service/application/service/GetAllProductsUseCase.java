package com.turkcell.product_service.application.service;

import com.turkcell.product_service.application.dto.response.ProductResponse;
import com.turkcell.product_service.application.mapper.ProductMapper;
import com.turkcell.product_service.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsUseCase {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public GetAllProductsUseCase(ProductRepository productRepository,ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper=mapper;
    }

    public List<ProductResponse> listProduct() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
