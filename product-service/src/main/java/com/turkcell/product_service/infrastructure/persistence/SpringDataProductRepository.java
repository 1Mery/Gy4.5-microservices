package com.turkcell.product_service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataProductRepository extends JpaRepository<JpaProductEntity, UUID> {
    //JPA ile CRUD işlemleri yapmak için klasik Spring Data arayüzünü oluşturuyoruz.
}