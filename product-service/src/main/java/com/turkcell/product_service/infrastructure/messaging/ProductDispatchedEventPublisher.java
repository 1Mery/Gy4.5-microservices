package com.turkcell.product_service.infrastructure.messaging;

import com.turkcell.product_service.domain.event.ProductDispatchedEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class ProductDispatchedEventPublisher {

    private final StreamBridge streamBridge;

    public ProductDispatchedEventPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void publish(ProductDispatchedEvent event) {
        streamBridge.send("productDispatched-out-0", event);
        System.out.println("ProductDispatchedEvent Kafka'ya gönderildi: " + event);
    }
}
