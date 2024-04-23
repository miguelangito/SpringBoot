package com.riwi.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.products.entities.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    public Products findByName(String name);
}