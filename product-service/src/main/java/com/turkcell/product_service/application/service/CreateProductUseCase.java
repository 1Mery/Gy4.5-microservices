package com.turkcell.product_service.application.service;

import com.turkcell.product_service.application.dto.request.ProductRequest;
import com.turkcell.product_service.application.dto.response.ProductResponse;
import com.turkcell.product_service.application.mapper.ProductMapper;
import com.turkcell.product_service.domain.model.Money;
import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCase {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public CreateProductUseCase(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper=mapper;
    }

    public ProductResponse createProduct(ProductRequest request){
        Money money = new Money(request.amount(), request.currencyName());
        Product product = Product.create(
                request.name(),
                request.description(),
                money,
                request.stock()
        );
        repository.save(product);
        return mapper.toResponse(product);
    }
}
