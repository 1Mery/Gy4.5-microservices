package com.turkcell.order_service.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;


public record Money(BigDecimal amount, String currency) {

    public Money {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be non-negative");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Currency cannot be null or blank");
        }
    }
    //İki Money’yi toplar ama ancak aynı para birimindeyseler
    public Money add(Money other) {
        ensureSameCurrency(other);
        return new Money(amount.add(other.amount), currency);
    }
    //Bir fiyatı miktarla çarpmak
    public Money multiply(int multiplier) {
        return new Money(amount.multiply(BigDecimal.valueOf(multiplier)).setScale(2, RoundingMode.HALF_UP), currency);
    }
    //add() çağrısında “para birimi uyumsuzluğu” kontrol eder
    private void ensureSameCurrency(Money other) {
        if (!Objects.equals(this.currency, other.currency)) {
            throw new IllegalArgumentException("Currencies must match");
        }
    }

}
