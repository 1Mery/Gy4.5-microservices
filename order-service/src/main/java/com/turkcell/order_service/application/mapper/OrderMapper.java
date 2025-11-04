package com.turkcell.order_service.application.mapper;

import com.turkcell.order_service.application.dto.OrderItemDto;
import com.turkcell.order_service.application.dto.OrderResponseDto;
import com.turkcell.order_service.domain.model.Order;
import com.turkcell.order_service.domain.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    public OrderResponseDto toResponse(Order order) {
        List<OrderItemDto> itemDtos = new ArrayList<>();

        for (OrderItem item : order.items()) {
            OrderItemDto dto = new OrderItemDto(
                    item.productId().value(),
                    item.quantity().value()
            );
            itemDtos.add(dto);
        }

        return new OrderResponseDto(
                order.id().value(),
                order.customerId().value(),
                itemDtos,
                order.totalAmount().amount(),
                order.totalAmount().currency(),
                order.status(),
                order.createdAt()
        );
    }
}
