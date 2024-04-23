package com.riwi.products.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riwi.products.entities.Products;
import com.riwi.products.repositories.ProductRepository;
import com.riwi.products.services.service_abstract.IProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public Products save(Products product) {

        return this.productRepository.save(product);
    }

    @Override
    public List<Products> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Products findById(Long id) {
        return this.productRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        Products productfind = this.productRepository.findById(id).orElseThrow();
        this.productRepository.delete(productfind);
    }

    @Override
    public Products update(Long id, Products products) {
        this.productRepository.findById(id);

        products.setId(id);
        return this.productRepository.save(products);
    }

    @Override
    public List<Products> search(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

}
