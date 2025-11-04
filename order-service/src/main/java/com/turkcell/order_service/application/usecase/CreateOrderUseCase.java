package com.turkcell.order_service.application.usecase;

import com.turkcell.order_service.application.dto.OrderItemDto;
import com.turkcell.order_service.application.dto.OrderRequestDto;
import com.turkcell.order_service.application.dto.OrderResponseDto;
import com.turkcell.order_service.application.mapper.OrderMapper;
import com.turkcell.order_service.domain.model.*;
import com.turkcell.order_service.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public CreateOrderUseCase(OrderRepository orderRepository,
                              OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderResponseDto create(OrderRequestDto request) {

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDto dto : request.items()) {
            ProductId productId = new ProductId(dto.productId());
            Quantity quantity = new Quantity(dto.quantity());
            Money unitPrice = new Money(dto.price(), dto.currency());

            OrderItem item = OrderItem.create(productId, quantity, unitPrice);
            orderItems.add(item);
        }

        CustomerId customerId = new CustomerId(request.customerId());

        Order order = Order.create(customerId, orderItems, null);

        orderRepository.save(order);

        return orderMapper.toResponse(order);
    }
}
