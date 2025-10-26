package com.turkcell.product_service.infrastructure.persistence;

import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.model.ProductId;
import com.turkcell.product_service.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryAdapter implements ProductRepository
{
    private final SpringDataProductRepository repository;
    private final JpaProductMapper mapper;

    public ProductRepositoryAdapter(SpringDataProductRepository repository, JpaProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {
        JpaProductEntity entity = mapper.toEntity(product);
        entity = repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public Optional<Product> findById(ProductId productId) {
        return repository
                .findById(productId.value())
                .map(mapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDomain) // Method Reference
                .toList();
    }

    @Override
    public void delete(Product product) {
        repository.deleteById(product.id().value());
    }
}
