package com.turkcell.order_service.domain.repository;


import com.turkcell.order_service.domain.model.Order;
import com.turkcell.order_service.domain.model.OrderId;

import java.util.List;
import java.util.Optional;

public interface OrderRepository
    {
        Order save(Order order);
        Optional<Order> findById(OrderId orderId);
        List<Order> findAll();
        void delete(Order order);
    }
