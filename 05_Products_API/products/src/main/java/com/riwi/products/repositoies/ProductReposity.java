package com.riwi.products.repositoies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.products.entities.Products;

@Repository
public interface ProductReposity extends JpaRepository<Products, Long>{

    public Products findbyName(String name);


}
