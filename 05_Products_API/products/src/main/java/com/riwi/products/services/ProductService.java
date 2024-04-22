package com.riwi.products.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.riwi.products.entities.Products;

@Service
public interface ProductService {

    
    public Products save(Products products);
    public List<Products> getAll(Products products);
    public Products findByid(Long id);
    public boolean delete(Long id);
    public boolean update(Long id);
    public List<Products> search(Products products);

    
} 
