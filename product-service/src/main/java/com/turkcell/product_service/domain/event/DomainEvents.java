package com.turkcell.product_service.domain.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Domain Event'leri geçici bellekte tutar.
 * Application katmanı bu event'leri alıp publish eder.
 */
public final class DomainEvents {

    private static final List<Object> events = new ArrayList<>();

    private DomainEvents() {
        // static class: instance oluşturulmaz
    }

    public static void raise(Object event) {
        events.add(event);
    }

    public static List<Object> pullEvents() {
        List<Object> copy = new ArrayList<>(events);
        events.clear(); // event'leri aldıktan sonra temizle
        return copy;
    }
}
