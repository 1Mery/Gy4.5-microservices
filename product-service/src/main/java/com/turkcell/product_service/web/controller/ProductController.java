package com.turkcell.product_service.web.controller;

import com.turkcell.product_service.application.dto.request.ProductRequest;
import com.turkcell.product_service.application.dto.request.UpdateProductRequest;
import com.turkcell.product_service.application.dto.response.ProductResponse;
import com.turkcell.product_service.application.service.*;
import com.turkcell.product_service.domain.model.ProductId;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;

    public ProductController(CreateProductUseCase createProductUseCase,
                             UpdateProductUseCase updateProductUseCase,
                             DeleteProductUseCase deleteProductUseCase,
                             GetProductByIdUseCase getProductByIdUseCase,
                             GetAllProductsUseCase getAllProductsUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
    }

    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest request) {
        return createProductUseCase.createProduct(request);
    }

    @PutMapping("/{productId}")
    public ProductResponse update(@PathVariable UUID productId, @RequestBody UpdateProductRequest request) {
        return updateProductUseCase.updateProduct(new ProductId(productId), request);
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable ProductId productId) {
        deleteProductUseCase.deleteProduct(productId);
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable UUID id) {
        return getProductByIdUseCase.productListById(id);
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return getAllProductsUseCase.listProduct();
    }
}
