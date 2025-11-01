package com.turkcell.order_service.domain.model;

public record Adress(String country,
                     String city,
                     String district,
                     String street) {

    public Adress {
        if (isBlank(country))
            throw new IllegalArgumentException("Country cannot be blank");
        if (isBlank(city))
            throw new IllegalArgumentException("City cannot be blank");
        if (isBlank(district))
            throw new IllegalArgumentException("District cannot be blank");
        if (isBlank(street))
            throw new IllegalArgumentException("Street cannot be blank");
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
