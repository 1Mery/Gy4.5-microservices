package com.turkcell.order_service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataOrderRepository extends JpaRepository<JpaOrderEntity, UUID> {
    //JPA ile CRUD işlemleri yapmak için klasik Spring Data arayüzünü oluşturuyoruz.
}