package com.turkcell.product_service.application.service;

import com.turkcell.product_service.domain.event.DomainEvents;
import com.turkcell.product_service.domain.event.ProductDispatchedEvent;
import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.model.ProductId;
import com.turkcell.product_service.domain.repository.ProductRepository;
import com.turkcell.product_service.infrastructure.messaging.ProductDispatchedEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDispatchedEventPublisher dispatchedPublisher;

    public ProductService(ProductRepository productRepository,
                          ProductDispatchedEventPublisher dispatchedPublisher) {
        this.productRepository = productRepository;
        this.dispatchedPublisher = dispatchedPublisher;
    }

    public void dispatchProduct(ProductId productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.dispatch(quantity,);

        List<Object> events = DomainEvents.pullEvents();

        for (Object event : events) {
            if (event instanceof ProductDispatchedEvent dispatchedEvent) {
                dispatchedPublisher.publish(dispatchedEvent);
            }
        }

        productRepository.save(product);
    }
}
