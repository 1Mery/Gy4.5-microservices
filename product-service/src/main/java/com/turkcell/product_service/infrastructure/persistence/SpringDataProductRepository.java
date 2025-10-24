package com.turkcell.product_service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductRepository extends JpaRepository<JpaProductEntity, String> {
    //JPA ile CRUD işlemleri yapmak için klasik Spring Data arayüzünü oluşturuyoruz.
}