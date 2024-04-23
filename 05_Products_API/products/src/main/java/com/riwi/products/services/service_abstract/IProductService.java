package com.riwi.products.services.service_abstract;

import java.util.List;

import com.riwi.products.entities.Products;

/**
 * IProductService
 */
public interface IProductService {

    public Products save(Products product);

    public List<Products> getAll();

    public Products findById(Long id);

    public void delete(Long id);

    public Products update(Long id, Products products);

    public List<Products> search(String name);

}